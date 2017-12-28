package org.tcf.sql.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tcf.annotation.Column;
import org.tcf.annotation.Entity;
import org.tcf.annotation.Id;
import org.tcf.annotation.NoColumn;
import org.tcf.annotation.PrimaryKeyType;
import org.tcf.sql.entity.ColumnInfo;
import org.tcf.sql.entity.EntityInfo;


public class EntityUtil {
	public static EntityInfo getInfo(Object entity) throws Exception{
		EntityInfo info = new EntityInfo();
		Class clazz = entity.getClass();
		String className = clazz.getSimpleName();
		//处理Entity注解
		Annotation entityAnno = clazz.getAnnotation(Entity.class);
		if(entityAnno == null)
			throw new Exception(clazz.getName()+":实体没有Entity注解");
		String catelog = (String)getAnnotationVal(entityAnno, "catelog");
		info.setCatelog(isEmpty(catelog)?"":catelog+".");
		String table = (String)getAnnotationVal(entityAnno, "table");
		info.setTable(isEmpty(table)?className:table);
		//保存所有列名
		List<String> columns = new ArrayList<String>();
		//保存所有值
		List<Object> values = new ArrayList<Object>();
		//处理属性（@NoColumn，@Id，@Column）
		for(Field field:clazz.getDeclaredFields()){
			//@NoColumn
			Annotation nonPersistentAnno = field.getAnnotation(NoColumn.class);
			if(nonPersistentAnno == null){//如果不存在NonPersistent注解，就说明与数据库相关，需要信息收集
				//@Id，
				Annotation idAnno = field.getAnnotation(Id.class);
				//@Column
				Annotation columnAnno = field.getAnnotation(Column.class);
				if(idAnno != null && columnAnno != null){
					throw new Exception(clazz.getName()+"实体的属性"+field.getName()+"只能拥有@Id或@column，不能同时设置");
				}
				if(idAnno != null){
					String id = info.getId();
					if(id != null) 
						throw new Exception(clazz.getName()+"实体的属性"+field.getName()+"只能拥有一个@Id注解");
					String name = (String)getAnnotationVal(idAnno, "name");
					info.setId(isEmpty(name)?field.getName():name);
					info.setType((PrimaryKeyType)getAnnotationVal(idAnno, "type"));
					info.setIdVal(getFieldVal(field, entity));
				}else if(columnAnno != null){
					//先获得属性的值
					Object val = getFieldVal(field, entity);
					if(val != null){//如果属性的值不为空就收集信息
						String name = (String) getAnnotationVal(columnAnno, "name");
						columns.add(isEmpty(name)?field.getName():name);//列名
						values.add(val);//值
					}
				}
			}
		}
		info.setColumns(columns);
		info.setValues(values);
		return info;
	}
	private static boolean isEmpty(String str){
		return str.trim().length()==0 || str == null;
	}
	private static String getCatelog(String catelog){
		return catelog.trim().length()==0?"":catelog+".";
	}
	public static Object getFieldVal(Field field,Object target){
		field.setAccessible(true);
		try {
			return field.get(target);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Object getAnnotationVal(Annotation entityAnno,String methodName){
		Method method;
		try {
			method = entityAnno.getClass().getMethod(methodName);
			return method.invoke(entityAnno);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<ColumnInfo> getAllColumns(Class clazz){
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		for(Field field:clazz.getDeclaredFields()){
			//@Id，
			Annotation idAnno = field.getAnnotation(Id.class);
			//@Column
			Annotation columnAnno = field.getAnnotation(Column.class);
			//如果该列没有和数据库相关，就跳过
			if(idAnno == null && columnAnno == null) continue;
			ColumnInfo info = new ColumnInfo();
			if(idAnno != null){
				info.setPrimaryKey(true);
				info.setName(field.getName());
				info.setType(field.getType());
			}else if(columnAnno != null){
				info.setName(field.getName());
				info.setType(field.getType());
			}
			columns.add(info);
		}
		return columns;
	}
	
	private static Object getVal(ResultSet rs,String name,Class type){
		Object val = null;
		try {
			if("java.lang.Integer".equals(type.getName())){
				val = rs.getInt(name);
			}else if("java.lang.Double".equals(type.getName())){
				val = rs.getDouble(name);
			}else if("java.lang.Short".equals(type.getName())){
				val = rs.getShort(name);
			}else if("java.lang.Byte".equals(type.getName())){
				val = rs.getByte(name);
			}else if("java.lang.Long".equals(type.getName())){
				val = rs.getLong(name);
			}else if("java.lang.Character".equals(type.getName())){
				val = rs.getString(name).charAt(0);
			}else if("java.lang.Float".equals(type.getName())){
				val = rs.getFloat(name);
			}else if("java.lang.Boolean".equals(type.getName())){
				val = rs.getBoolean(name);
			}else if("java.lang.String".equals(type.getName())){
				val = rs.getString(name);
			}else if("java.sql.Date".equals(type.getName())){
				val = rs.getDate(name);
			}else if("java.util.Date".equals(type.getName())){
				java.sql.Date date = rs.getDate(name);
				val = new java.util.Date(date.getTime());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
		}
		return val;
	}
	/**
	 * 将resultset类型转换成实体的集合
	 * @param rs
	 * @param clazz
	 * @return
	 */
	public static List fill(ResultSet rs,Class clazz){
		List list = new ArrayList();
		List<ColumnInfo> columns = getAllColumns(clazz);
		try {
			while(rs.next()){
				Object obj = clazz.newInstance();//new Student();
				for(Field field:clazz.getDeclaredFields()){
					Object val = getVal(rs, field.getName(), field.getType());
					if(val != null){
						field.setAccessible(true);
						field.set(obj, val);
					}
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}

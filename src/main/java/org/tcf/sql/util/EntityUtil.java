package org.tcf.sql.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.tcf.sql.entity.exp.ExpValue;
import org.tcf.sql.entity.exp.Expression;

import com.tcf.exception.DaoException;

/**
 * 用于处理与实体相关的工具
 * @author Archer Tan
 *
 */
public class EntityUtil {
	/**
	 * 用于收集实体的注解信息(列和实体)
	 * @param entity
	 * @param containNull 是否收集值为Null的列信息  true:收集null值的列，false:不收集
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static EntityInfo getInfo(Object entity,boolean containNull){
		EntityInfo info = new EntityInfo();
		ColumnInfo pk = null;
		Class clazz = entity.getClass();
		String className = clazz.getSimpleName();
		//处理Entity注解
		Annotation entityAnno = clazz.getAnnotation(Entity.class);
		if(entityAnno == null)
			throw new DaoException(clazz.getName()+":实体没有Entity注解");
		String catelog = (String)AnnotationUtil.getAnnotationVal(entityAnno, "catelog");
		info.setCatelog(StringUtil.isEmpty(catelog)?"":catelog+".");//数据库名
		String schema = (String)AnnotationUtil.getAnnotationVal(entityAnno, "schema");
		info.setSchema(StringUtil.isEmpty(schema)?"":schema+".");//数据模式名
		String table = (String)AnnotationUtil.getAnnotationVal(entityAnno, "table");
		info.setTable(StringUtil.isEmpty(table)?className:table);//表名
		//保存所有列名
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		//保存所有值
		//处理属性（@NoColumn，@Id，@Column）
		for(Field field:clazz.getDeclaredFields()){
			//@NoColumn
			Annotation noColumn = field.getAnnotation(NoColumn.class);
			if(noColumn == null){//如果不存在NoColumn注解，就说明与数据库相关，需要信息收集
				//如果不收集null值的列，并且值为null,就跳过收集
				if(containNull == false && getFieldVal(field, entity) == null)
					continue;
				ColumnInfo column = new ColumnInfo();
				//@Id，
				Annotation idAnno = field.getAnnotation(Id.class);
				//@Column
				Annotation columnAnno = field.getAnnotation(Column.class);
				if(idAnno != null){
					if(pk != null) 
						throw new DaoException(clazz.getName()+"实体的属性"+field.getName()+"出现重复的@Id注解");
					//说明该列为主键
					column.setPrimaryKey(true);
					info.setType((PrimaryKeyType)AnnotationUtil.getAnnotationVal(idAnno, "type"));
					//指定主键对应的列，以免多个列指定@Id注解
					pk = column;
				}
				if(columnAnno != null){
					String name = (String) AnnotationUtil.getAnnotationVal(columnAnno, "name");//列名
					column.setName(StringUtil.isEmpty(name)?field.getName():name);//未指定列名时使用属性名
					column.setValue(getFieldVal(field, entity));//值
					column.setType(field.getType());
				}
				columns.add(column);
			}
		}
		info.setColumns(columns);
		info.setId(pk);//保存主键
		return info;
	}
	/**
	 * 收集全部的列，不包含值为Null的列
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public static EntityInfo getInfo(Object entity){
		return getInfo(entity, false);
	}
	/**
	 * 获得列的值
	 * @param field
	 * @param target
	 * @return
	 */
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
	/**
	 * 根据指定的类型获得相应的值
	 * @param rs 结果集
	 * @param name 指定的列名
	 * @param type 指定的类型
	 * @return
	 */
	public static Object getVal(ResultSet rs,String name,Class<?> type){
		Object val = null;
		try {
			if("java.lang.Integer".equals(type.getName()) 
					&& "int".equals(type.getName())){
				val = rs.getInt(name);
			}else if("java.lang.Double".equals(type.getName())
					&& "double".equals(type.getName())){
				val = rs.getDouble(name);
			}else if("java.lang.Short".equals(type.getName())
					&& "short".equals(type.getName())){
				val = rs.getShort(name);
			}else if("java.lang.Byte".equals(type.getName())
					&& "byte".equals(type.getName())){
				val = rs.getByte(name);
			}else if("java.lang.Long".equals(type.getName()) 
					&& "long".equals(type.getName())){
				val = rs.getLong(name);
			}else if("java.lang.Character".equals(type.getName())
					&& "char".equals(type.getName())){
				val = rs.getString(name).charAt(0);
			}else if("java.lang.Float".equals(type.getName())
					&& "float".equals(type.getName())){
				val = rs.getFloat(name);
			}else if("java.lang.Boolean".equals(type.getName())
					&& "boolean".equals(type.getName())){
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
	 * 将resultset类型转换成实体的集合(暂时不支持关系 ：多对一，一对多之类)
	 * @param rs
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List fill(ResultSet rs,Class clazz){
		List list = new ArrayList();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 根据提供的列信息生成条件
	 * @param columns
	 * @param likes 需要生成模糊查询的列名
	 * @return
	 */
	public static Expression getExpressionByAnd(List<ColumnInfo> columns,String...likes){
		List<ExpValue> exps = new ArrayList<ExpValue>();
		for(ColumnInfo column:columns){
			if(CollectionUtil.containsIgnoreCase(Arrays.asList(likes), column.getName())){
				exps.add(ExpUtil.like(column.getName(), column.getValue()));
			}else{
				exps.add(ExpUtil.eq(column.getName(), column.getValue()));
			}
		}
		return ExpUtil.and(exps);
	}
	/**
	 * 根据提供的列信息生成条件
	 * @param columns
	 * @param likes 需要生成模糊查询的列名
	 * @return
	 */
	public static Expression getExpressionByAnd(Map<String,Object> columns,String...likes){
		return getExpressionByAnd(change(columns), likes);
	}
	/**
	 * 将map转换成列对象集合
	 * @param map
	 * @return
	 */
	public static List<ColumnInfo> change(Map<String,Object> map){
		List<ColumnInfo> columns = new ArrayList<ColumnInfo>();
		for(String key:map.keySet()){
			columns.add(new ColumnInfo(key, map.get(key)));
		}
		return columns;
	}
	/**
	 * 将列信息映射成map对象，所有的key是字段名（小写）
	 * @param columns
	 * @return
	 */
	public static Map<String,Object> change(List<ColumnInfo> columns){
		Map<String,Object> map = new HashMap<String, Object>();
		for(ColumnInfo column:columns){
			map.put(column.getName().toLowerCase(), column.getValue());
		}
		return map;
	}
	/**
	 * 获得所有列中不为null的值
	 * @param columns
	 * @return
	 */
	public static Object[] getValues(EntityInfo info,List<String> columnNames){
		List list = new ArrayList();
		Map<String,Object> map = change(info.getColumns());
		for(String name:columnNames){
			Object val = map.get(name.toLowerCase());
			if(val != null)
				list.add(val);
		}
		return list.toArray();
	}
}

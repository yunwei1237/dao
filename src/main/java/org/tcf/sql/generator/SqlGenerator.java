package org.tcf.sql.generator;

import org.tcf.sql.entity.ConditionType;
import org.tcf.sql.entity.EntityInfo;
import org.tcf.sql.entity.Order;
/**
 * 
 * ��������sql���
 * @author Archer Tan
 *
 */
public interface SqlGenerator {
	/**
	 * ����insert���
	 * @param info ����sql��Ҫ���ֶ���Ϣ�����ݿ���Ϣ
	 * @return sql���
	 * @throws Exception
	 */
	String genertInsert(EntityInfo info) throws Exception;
	/**
	 * ����update���
	 * @param info ����sql��Ҫ���ֶ���Ϣ�����ݿ���Ϣ
	 * @return sql���
	 * @throws Exception
	 */
	String genertUpdate(EntityInfo info) throws Exception;
	/**
	 * ����delete���
	 * @param info ����sql��Ҫ���ֶ���Ϣ�����ݿ���Ϣ
	 * @return sql���
	 * @throws Exception
	 */
	String genertDelete(EntityInfo info) throws Exception;
	/**
	 * ����select���
	 * @param info ����sql��Ҫ���ֶ���Ϣ�����ݿ���Ϣ
	 * @param type ���ɵ�����֮����߼���ϵ
	 * @param likes ָ����һЩ�ֶ���Ҫģ����ѯ
	 * @param groups ʹ����һЩ�ֶν��з���
	 * @param orders ����
	 * @param begin ��ʼ����
	 * @param size ��������
	 * @return
	 * @throws Exception
	 */
	String genertSelect(EntityInfo info,ConditionType type,String[] likes,String[] groups,Order[] orders,Integer begin,Integer size) throws Exception;
}

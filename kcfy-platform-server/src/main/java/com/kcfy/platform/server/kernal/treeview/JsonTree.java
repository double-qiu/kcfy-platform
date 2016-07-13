package com.kcfy.platform.server.kernal.treeview;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kcfy.platform.server.kernal.model.ITree;

public class JsonTree {

	protected static final Logger LOGGER= LoggerFactory.getLogger(JsonTree.class);
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param treeKoalaFacade 指定根节点
	 * @return json树的数据
	 */
	public static List<Map<String,Object>> generateMapObj(List<? extends ITree> dataList,ITree treeKoalaFacade) {
		return generateMapObj(dataList,treeKoalaFacade.getId());
	}
	
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param rootId 指定根节点的id
	 * @return json树的数据
	 */
	public static List<Map<String,Object>> generateMapObj(List<? extends ITree> dataList,String rootId) {
		return generateMapObj(dataList,null,true,rootId);
	}
	
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @return json树的数据
	 */
	public static List<Map<String,Object>> generateMapObj(List<? extends ITree> dataList) {
		return generateMapObj(dataList,null,true,null);
	}
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key数组
	 * @return json树的数据
	 */
	public static List<Map<String,Object>> generateMapObj(List<? extends ITree> dataList, String[] otherNode) {
		return generateMapObj(dataList,otherNode,true,null);
	}
	
	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key数组,其他节点必须有get/set方法
	 * @param sort
	 *            是否进行排序(不排序是按id排序,否则按sn排序)
	 * @return json树的数据
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> generateMapObj(List<? extends ITree> dataList, String[] otherNode,
			Boolean sort,String rootId) {
		// 节点哈希表<节点id，用于临时存储节点对象>
		Map<String, Map<String, Object>> nodeList = constructionMapObj(dataList, otherNode);
		// 根节点
		List<Map<String,Object>> rootNodeList = new ArrayList<Map<String,Object>>();
		// 构造无序的内存多叉树
		Set<Entry<String, Map<String, Object>>> set = nodeList.entrySet();
		for (Iterator<Entry<String, Map<String, Object>>> it = set.iterator(); it.hasNext();) {
			Map<String, Object> node = (Map<String, Object>) it.next().getValue();
			//默认全是叶子节点
			if (node.get("leaf") == null){
				node.put("leaf",true);
			}
			if (node.get("parentId") == null || (StringUtils.isNotBlank(rootId) && StringUtils.equals((String)node.get("id"), rootId))) {
				// 处理有多个父节点节点的
				rootNodeList.add(node);
			} else {
				if (nodeList.get(node.get("parentId")) != null) {
					nodeList.get(node.get("parentId")).put("leaf",false);
					if(nodeList.get(node.get("parentId")).get("children") == null){
						nodeList.get(node.get("parentId")).put("children", new ArrayList<Map<String,Object>>());
					}
					((ArrayList<Map<String,Object>>)nodeList.get(node.get("parentId")).get("children")).add(node);
				}
			}
		}
		
		if (sort) {// 也对父类进行排序
			sort(rootNodeList);
		}
		
		return rootNodeList;

	}

	@SuppressWarnings("unchecked")
	private static void sort(List<Map<String,Object>> rootNodeList){
		Collections.sort(rootNodeList, new MapSnDescComparator());
		for (Map<String, Object> map : rootNodeList) {
			if(!(boolean)map.get("leaf")){
				sort((ArrayList<Map<String,Object>>)map.get("children"));
			}
		}
	}
	
	/**
	 * 节点哈希表<节点id，用于临时存储节点对象>
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key
	 * @return 节点哈希表
	 */
	private static Map<String, Map<String, Object>> constructionMapObj(List<? extends ITree> dataList, String[] otherNode) {
		// 节点哈希表<节点id，用于临时存储节点对象>
		Map<String, Map<String, Object>> nodeMap = new HashMap<String, Map<String, Object>>();
		// 根据结果集构造节点列表（存入哈希表）
		for (Iterator<? extends ITree> it = dataList.iterator(); it.hasNext();) {
			ITree treeKoalaFacade = it.next();
			Map<String, Object> node = new TreeMap<String, Object>();
			node.put("id", treeKoalaFacade.getId());
			node.put("name",treeKoalaFacade.getName());
			node.put("parentId",treeKoalaFacade.getParentId());
			node.put("sequence",treeKoalaFacade.getSequence());
			// 其它节点
			if (otherNode != null && otherNode.length > 0) {
				for (String key : otherNode) {
					Object value;
					try {
						value = getFieldValueByName(treeKoalaFacade, key);
						node.put(key,value);
					} catch (Exception e) {
						node.put(key,"");
						throw new IllegalArgumentException("otherNode 参数传入错误");
					}
				}
			}
			nodeMap.put(treeKoalaFacade.getId(), node);
		}
		return nodeMap;
	}
	
	 private static Object getFieldValueByName(Object o, String fieldName){
		 try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	    	   LOGGER.error(e.getMessage(),e);
	    	   e.printStackTrace();
	           return null;    
	       }    
	 }
	
}

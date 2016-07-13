package com.kcfy.platform.server.kernal.treeview;

import java.util.Comparator;
import java.util.Map;

public class MapSnDescComparator implements Comparator<Map<String,Object>> {
	/**
	 * 按照节点SN排序
	 */
	@Override
	public int compare(Map<String,Object> o1, Map<String,Object> o2) {
		int j1 = (int)o1.get("sequence");
		int j2 = (int)o2.get("sequence");
		return (j1 > j2 ? 1 : (j1 == j2 ? 0 : -1));
	}
}

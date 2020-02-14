package com.lernopus.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeStructure {
	public static void main(String args[]) {
		List<Map<String,String>> treeList = new ArrayList<>();
		Map<String,String> treeMap = new HashMap<>();
		treeMap.put("courseId", "6");
		treeMap.put("parentId", "4");
		treeMap.put("rootId", "7");
		treeMap.put("courseName", "Algorithm Impl");
		treeList.add(treeMap);
		Map<String,String> treeMap1 = new HashMap<>();
		treeMap1.put("courseId", "5");
		treeMap1.put("parentId", "2");
		treeMap1.put("rootId", "7");
		treeMap1.put("courseName", "Method Impl");
		treeList.add(treeMap1);
		Map<String,String> treeMap2 = new HashMap<>();
		treeMap2.put("courseId", "3");
		treeMap2.put("parentId", "2");
		treeMap2.put("rootId", "7");
		treeMap2.put("courseName", "Method Definition");
		treeList.add(treeMap2);
		Map<String,String> treeMap3 = new HashMap<>();
		treeMap3.put("courseId", "1");
		treeMap3.put("parentId", "7");
		treeMap3.put("rootId", "7");
		treeMap3.put("courseName", "Stream Intro");
		treeList.add(treeMap3);
		Map<String,String> treeMap4 = new HashMap<>();
		treeMap4.put("courseId", "2");
		treeMap4.put("parentId", "7");
		treeMap4.put("rootId", "7");
		treeMap4.put("courseName", "Stream Methods");
		treeList.add(treeMap4);
		Map<String,String> treeMap5 = new HashMap<>();
		treeMap5.put("courseId", "4");
		treeMap5.put("parentId", "7");
		treeMap5.put("rootId", "7");
		treeMap5.put("courseName", "Stream Logic & Algorithm");
		treeList.add(treeMap5);
		Map<String, Map<String, List<Map<String, String>>>> treeStructureMap = 
				treeList.stream().collect(Collectors.groupingBy(treeTrace -> treeTrace.get("rootId"), Collectors.groupingBy(treeTrace1 -> treeTrace1.get("parentId"))));
		System.out.println(treeStructureMap);
	}
}
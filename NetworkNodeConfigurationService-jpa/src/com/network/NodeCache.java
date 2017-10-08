package com.network;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Singleton;

@Singleton
public class NodeCache {
	
	Map<Integer,Node> nodeCache = new HashMap<>();
	
	public Node lookupCache(int id){
		return nodeCache.get(id);
	}
	
	public void addNodeToCache(Node node){
		System.out.println("Adding The Following Node To The Cache::"+node.toString());
		nodeCache.put(node.getId(), node);
	}
	
	public void removeEntryFromMap(Node node){
		nodeCache.remove(node.getId());
	}
}
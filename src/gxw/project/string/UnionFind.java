package gxw.project.string;

import java.util.HashMap;
import java.util.Iterator;

public class UnionFind {
	private int[] elems;
	private HashMap<String, Integer> elemsMap = new HashMap<String, Integer>();
	private HashMap<Integer, String> elemsIndexMap = new HashMap<Integer, String>();
	
	public UnionFind(String[] strs) {
		int index = 0;
		for(String str : strs) {
			elemsMap.put(str, index);
			elemsIndexMap.put(index, str);
			index++;
		}
		
		this.elems = new int[strs.length];
		for(int i=0;i<this.elems.length;i++) {
			elems[i] = i; 
		}
	}
	
	public int find(String elem) {
		if(elem == null)
			throw new IllegalArgumentException("The element for finding is null");
		
		Integer elemIdx = elemsMap.get(elem);
		if(elemIdx == null)
			throw new IllegalArgumentException("The element for finding doesn't exist");
		
		return elems[elemIdx];
	}
	
	public void union(String elem1, String elem2) {
		if(elem1.equals(elem2))
			throw new IllegalArgumentException("The elements for union can't be same");
		
		if(elem1 == null || elem2 == null)
			throw new IllegalArgumentException("The elements for union must not be null");
		
		Integer elemIdx1 = elemsMap.get(elem1);
		if(elemIdx1 == null)
			throw new IllegalArgumentException("The first element for union doesn't exist");
		
		Integer elemIdx2 = elemsMap.get(elem2);
		if(elemIdx2 == null)
			throw new IllegalArgumentException("The second element for union doesn't exist");
		
		
		if(elems[elemIdx1] == elemIdx1 && elems[elemIdx2] == elemIdx2) {
			elems[elemIdx2] = elems[elemIdx1];
		} else if(elems[elemIdx1] == elemIdx1) {
			elems[elemIdx1] = elems[elemIdx2];
		} else {
			elems[elemIdx2] = elems[elemIdx1];
		}
	}
	
	public void print(String elem) {
		int groupId = find(elem);
		String group = "";
		for(int i=0;i<elems.length;i++) {
			if(elems[i] == groupId)
				group += elemsIndexMap.get(i)+",";
		}
		
		StringBuffer sb = new StringBuffer(String.format("The element %s belongs to the group %s", elem, group));
		
		System.out.println(sb.toString());
	}
	
	public void printAll() {
		StringBuffer sb = new StringBuffer("UnionFind: \n");
		Iterator<String> elems = elemsMap.keySet().iterator();
		while(elems.hasNext()) {
			sb.append(elems.next());
			if(!elems.hasNext()) {
				sb.append("\n");
			} else {
				sb.append(",");
			}
		}
		
		Iterator<Integer> indexies = elemsIndexMap.keySet().iterator();
		while(indexies.hasNext()) {
			sb.append(this.elems[indexies.next()]);
			if(!indexies.hasNext()) {
				sb.append("\n");
			} else {
				sb.append(",");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	public static void main(String[] args) {
		String[] elems = {"A", "B", "C", "D"};
		UnionFind uf = new UnionFind(elems);
		
		uf.union("A", "B");
//		uf.union("B", "C");
		uf.union("B", "D");
		uf.print("B");
		
		uf.printAll();
	}
}

package gxw.project.chainhash;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import gxw.project.linkedlist.LinkedList;

/**
 * A data structure implementation of the chain hash. 
 * It is an array whose every element is a linked list.
 * @author golden
 *
 * @param <K>
 * @param <V>
 */
public class ChainHash<K, V> implements Iterable<K>{
	private int numElements, tableSize;
	private double maxLoadFactor;
	private final static int defaultTableSize = 16;
	private final static double defaultMaxLoadFactor = 0.75d;
	LinkedList<HashElement<K, V>>[] hashTable;
	
	public ChainHash() {
		this(defaultTableSize, defaultMaxLoadFactor);
	}
	
	public ChainHash(int tableSize) {
		this(tableSize, defaultMaxLoadFactor);
	}
	
	public ChainHash(double maxLoadFactor) {
		this(defaultTableSize, maxLoadFactor);
	}
	
	@SuppressWarnings("unchecked")
	public ChainHash(int tableSize, double maxLoadFactor) {
		if(tableSize <= 0)
			throw new IllegalArgumentException("The ChainHash's size should be a positive number and greater than 0");
		
		if(maxLoadFactor <= 0d)
			throw new IllegalArgumentException("The ChainHash's max load factor should be a positive number and greater than 0");
		
		this.tableSize = tableSize;
		this.maxLoadFactor = maxLoadFactor;
		
		hashTable = (LinkedList<HashElement<K, V>>[]) new LinkedList[tableSize];
		
		for(int i=0;i<hashTable.length;i++) {
			hashTable[i] = new LinkedList<HashElement<K, V>>();
		}
		
		maxLoadFactor = 0.75d;
		numElements = 0;
	}
	
	public boolean add(K key, V value) {
		if(maxLoadFactor <= getLoadFactor()) {
			resize(tableSize * 2);
		}
		
		HashElement<K, V> he = new HashElement<K, V>(key, value);
		
		int position = getArrayPosition(key);
		hashTable[position].addFirst(he);
		numElements++;
		
		return true;
	}
	
	public boolean remove(K key, V value) {
		if(numElements == 0)
			return false;
		
		HashElement<K, V> he = new HashElement<K, V>(key, value);
		int position = getArrayPosition(key);
		if(hashTable[position].remove(he) == null) {
			return false;
		} else {
			numElements--;
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public V getValue(K key) {
		if(numElements == 0) 
			return null;
		
		int position = getArrayPosition(key);
		LinkedList<HashElement<K, V>> ll = hashTable[position];
		for(HashElement<K, V> he : ll) {
			if(((Comparable<K>)he.getKey()).compareTo(key) == 0) {
				return he.getValue();
			}
		}
		return null;
	}
	
	public double getLoadFactor() {
		return numElements/tableSize;
	}
	
	@SuppressWarnings("unchecked")
	public V[] getValues(Class<V> vclass) {
		V[] values = (V[]) Array.newInstance(vclass, numElements);
		int position = 0;
		for(K key : this) {
			values[position++] = (V)this.getValue(key);
		}
		
		return values;
	}
	
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper();
	}
	
	private int getArrayPosition(K key) {
		int hash_code = key.hashCode();
		hash_code &= 0x7FFFFFFF;
		hash_code %= tableSize;
		
		return hash_code;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		LinkedList<HashElement<K, V>>[] tempArray = (LinkedList<HashElement<K, V>>[]) new LinkedList[newSize];
		for(int i=0;i<tempArray.length;i++) {
			tempArray[i] = new LinkedList<HashElement<K, V>>();
		}
		
		for(K key : this) {
			V value = this.getValue(key);
			HashElement<K, V> he = new HashElement<K, V>(key, value);
			int position = getArrayPosition(key);
			tempArray[position].addFirst(he);
		}
		
		hashTable = tempArray;
		tableSize = newSize;
	}
	
	private class IteratorHelper implements Iterator<K> {
		private K[] keys;
		private int position = 0;
		
		@SuppressWarnings("unchecked")
		public IteratorHelper() {
			keys = (K[]) new Object[numElements];
			for(int i=0;i<hashTable.length;i++) {
				LinkedList<HashElement<K, V>> ll = hashTable[i];
				for(HashElement<K, V> he : ll) {
					keys[position++] = he.getKey();
				}
			}
			position = 0;
		}

		@Override
		public boolean hasNext() {
			return position < keys.length;
		}

		@Override
		public K next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			return keys[position++];
		}
		
	}
	
	@SuppressWarnings("hiding")
	public class HashElement<K, V> implements Comparable<HashElement<K, V>> {
		private K key;
		private V value;
		
		public HashElement(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(HashElement<K, V> o) {
			return ((Comparable<K>)this.key).compareTo(o.key);
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}
	
	public final static void main(String[] args) {
		ChainHash<String, String> table = new ChainHash<String, String>();
		table.add("key1", "hello");
		table.add("key2", "hi");
		table.add("key3", "world");
		
		for(String s : table.getValues(String.class)) {
			System.out.println(s);
		}
	}
}

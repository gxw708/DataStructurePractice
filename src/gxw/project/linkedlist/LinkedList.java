package gxw.project.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E>{
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public LinkedList() {
		size = 0;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void addFirst(E obj) {
		Node<E> newNode = new Node<E>(obj);
		
		if(head == null) {
			head = tail = newNode;
			size++;
			return;
		}
		
		Node<E> temp = head;
		head = newNode;
		head.next = temp;
		temp.prev = head;
		size++;
	}
	
	public void addLast(E obj) {
		if(head == null) {
			addFirst(obj);
			return;
		}
		
		Node<E> newNode = new Node<E>(obj);
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		size++;
	}
	
	public E removeFirst() {
		if(head == null)
			return null;
		E tmp = head.data;
		head.next.prev = null;
		head = head.next;
		size--;
		return tmp;
	}
	
	public E removeLast() {
		if(head == null)
			return null;
		
		E tmp = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return tmp;
	}
	
	public E remove(E obj) {
		Node<E> tmp = get(head, obj);
		
		if(tmp == null) {
			return null;
		}
		
		tmp.prev.next = tmp.next;
		tmp.next.prev = tmp.prev;
		tmp.next = null;
		tmp.prev = null;
		
		return tmp.data;
	}
	
	public E peekFirst() {
		if(head == null)
			return null;
		
		return head.data;
	}
	
	public E peekLast() {
		if(tail == null) {
			return null;
		}
		
		return tail.data;
	}
	
	@SuppressWarnings("unchecked")
	private Node<E> get(Node<E> node, E obj) {
		if(node == null) {
			return null;
		}
		
		if(((Comparable<E>)node.data).compareTo(obj) == 0) {
			return node;
		} else {
			return get(node.next, obj);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		return print(head, sb);
//		return printForDebug(head, sb);
	}
	
	private String print(Node<E> node, StringBuffer sb) {
		if(node != null) {
			sb.append(String.valueOf(node.data));
		}
		
		if(node.next != null) {
			sb.append(",");
			return print(node.next, sb);
		} else {
			return sb.toString();
		}
	}
	
//	private String printForDebug(Node<E> node, StringBuffer sb) {
//		if(node != null) {
//			sb.append("(");
//			if(node.prev != null)
//				sb.append(" p:").append(node.prev.data).append(" ");
//			
//			sb.append(" c:").append(String.valueOf(node.data)).append(" ");
//			
//			if(node.next != null)
//				sb.append(" n:").append(node.next.data).append(" ");
//			sb.append(")");
//		}
//		
//		if(node.next != null) {
//			sb.append(",");
//			return printForDebug(node.next, sb);
//		} else {
//			return sb.toString();
//		}
//	}
	
	@SuppressWarnings("hiding")
	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		public Node(E obj) {
			this.data = obj;
		}
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<Integer>();
		ll.addFirst(1);
		ll.addFirst(2);
		ll.addLast(3);
		ll.addLast(4);
		ll.addFirst(5);
		System.out.println(String.format("LinkedList[%d]: %s", ll.size, ll));
		System.out.println(String.format("Removed first element: %s", String.valueOf(ll.removeFirst())));
		System.out.println(String.format("Removed last element: %s", String.valueOf(ll.removeLast())));
		System.out.println(String.format("LinkedList[%d]: %s", ll.size, ll));
		System.out.println(String.format("Removed special element: %s", String.valueOf(ll.remove(1))));
		System.out.println(String.format("LinkedList[%d]: %s", ll.size, ll));
		System.out.println(String.format("Peek the first element: %s", String.valueOf(ll.peekFirst())));
		System.out.println(String.format("Peek the last element: %s", String.valueOf(ll.peekLast())));
		
		for(Integer i : ll) {
			System.out.println(String.format("Iterator elements: %s", String.valueOf(i)));
		}
	}

	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	private class IteratorHelper implements Iterator<E> {
		private Node<E> index;
		
		public IteratorHelper() {
			index = head;
		}

		@Override
		public boolean hasNext() {
			return index != null;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			E tmp = index.data;
			index = index.next;
			return tmp;
		}
		
	}
}

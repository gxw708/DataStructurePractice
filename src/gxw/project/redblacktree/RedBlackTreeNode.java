package gxw.project.redblacktree;

public class RedBlackTreeNode<K, V> {
	private final K key;
	private final V value;
	private RedBlackTreeNode<K, V> parent;
	private RedBlackTreeNode<K, V> leftChild;
	private RedBlackTreeNode<K, V> rightChild;
	public boolean isBlack = false;
	public boolean isLeftChild = false;
	
	public RedBlackTreeNode(K key, V value) {
		this.key = key;
		this.value = value;
		this.parent = null;
		this.leftChild = null;
		this.rightChild = null;
		this.isBlack = false;
		this.isLeftChild = false;
	}
	
	public RedBlackTreeNode<K, V> getParent() {
		return parent;
	}

	public void setParent(RedBlackTreeNode<K, V> parent) {
		this.parent = parent;
	}

	public RedBlackTreeNode<K, V> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(RedBlackTreeNode<K, V> leftChild) {
		this.leftChild = leftChild;
	}

	public RedBlackTreeNode<K, V> getRightChild() {
		return rightChild;
	}

	public void setRightChild(RedBlackTreeNode<K, V> rightChild) {
		this.rightChild = rightChild;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

	public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    if(rightChild!=null) {
	    	rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
	    }
	    sb.append(prefix).append(isTail ? "└── " : "┌── ").append(String.valueOf(value)).append(" (").append(isBlack ? "Black" : "Red").append(")").append("\n");
	    if(leftChild!=null) {
	    	leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
	    }
	    return sb;
	}

	@Override
	public String toString() {
	    return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object other) {
		if(other == null)
			return false;
		
		if(!(other instanceof RedBlackTreeNode))
			return false;
		
		if(((Comparable<K>)key).compareTo(parent.getKey()) == 0)
			return true;
		else
			return false;
	}
}

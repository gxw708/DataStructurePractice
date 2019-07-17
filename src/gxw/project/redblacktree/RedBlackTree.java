package gxw.project.redblacktree;

public interface RedBlackTree<K, V> {
	/**
	 * insert the given number into the tree if it doesn't exist
	 * @param val
	 */
	public void add(K key, V value);
	
	/**
	 * remove the given number from the tree if it existed
	 * @param val
	 */
	public void remove(K key);
	
	
	/**
	 * get the node by the given key
	 * @param val
	 * @return
	 */
	public RedBlackTreeNode<K, V> get(K key);
	
	/**
	 * calculate the height of the tree
	 * @return
	 */
	public int height();
	
	/**
	 * calculate how many black nodes does the tree have
	 * @return
	 */
	public int blackNodes();
}

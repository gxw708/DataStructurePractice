package gxw.project.redblacktree;

/**
 * @author golden
 * An implementation of red black tree. The tree has below rules:
 * 1. Every node is either red or black
 * 2. Root is always black
 * 3. New insertions are always red
 * 4. Every path from root -> leaf has the same number of black nodes
 * 5. No path can have two consecutive red nodes
 * 6. Nulls are black
 * 
 * we check the uncle node of the added node to know the action for re-balancing
 * black uncle: rotate
 * red uncle: color flip
 * 
 * Color after re-balancing actions:
 * Color Flip				Rotate
 *     red                   black
 *    /   \                 /     \
 * black black             red    red
 * 
 */
public class RedBlackTreeImpl<K, V> implements RedBlackTree<K, V> {
	private RedBlackTreeNode<K, V> root;
	private int size = 0;
	
	@Override
	public void add(K key, V value) {
		RedBlackTreeNode<K, V> node = new RedBlackTreeNode<K, V>(key, value);
		
		if(root == null) {
			root = node;
			node.isBlack = true;
			size++;
			return;
		}
		
		add(root, node);
		size++;
	}
	
	@Override
	public void remove(K key) {
	}

	@Override
	public RedBlackTreeNode<K, V> get(K key) {
		if(root == null) 
			return null;
		
		return has(root, key);
	}

	@Override
	public int height() {
		if(root == null)
			return 0;
		
		return height(root) - 1;
	}

	@Override
	public int blackNodes() {
		if(root == null)
			return 0;
		
		return blackNodes(root);
	}

	@Override
	public String toString() {
		return root.toString();
	}

	public static void main(String[] args) {
		RedBlackTreeImpl<Integer, Integer> tree = new RedBlackTreeImpl<Integer, Integer>();
		tree.add(3, 3);
		tree.add(1, 1);
		tree.add(5, 5);
		tree.add(7, 7);
		tree.add(6, 6);
		tree.add(8, 8);
		tree.add(9, 9);
		tree.add(10, 10);
		
		System.out.println(tree);
		System.out.println(String.format("Tree's depth is %d", tree.height()));
		System.out.println(String.format("Tree has %d black nodes on each fork", tree.blackNodes()));
	}

	@SuppressWarnings("unchecked")
	private void add(RedBlackTreeNode<K, V> parent, RedBlackTreeNode<K, V> node) {
		if(((Comparable<K>)node.getKey()).compareTo(parent.getKey()) == 0)
			return;
		
		if(((Comparable<K>)node.getKey()).compareTo(parent.getKey()) > 0) {
			if(parent.getRightChild() == null) {
				node.setParent(parent);
				parent.setRightChild(node);
			} else {
				add(parent.getRightChild(), node);
			}
			
		} else {
			if(parent.getLeftChild() == null) {
				node.setParent(parent);
				node.isLeftChild = true;
				parent.setLeftChild(node);
			} else {
				add(parent.getLeftChild(), node);
			}
		}
		
		checkColor(node);
	}
	
	/**
	 * this method is responsible for checking color on the given node
	 * @param node
	 */
	private void checkColor(RedBlackTreeNode<K, V> node) {
		if(node == root){
			node.isBlack = true;
			return;
		}
		
		if(!node.isBlack && !node.getParent().isBlack) {
			correctTree(node);
		}
		
		checkColor(node.getParent());
	}
	
	private void correctTree(RedBlackTreeNode<K, V> node) {
		RedBlackTreeNode<K, V> uncle = null;
		if(node.getParent().isLeftChild) {
			uncle = node.getParent().getParent().getRightChild();
		} else {
			uncle = node.getParent().getParent().getLeftChild();
		}
		
		if(uncle == null || uncle.isBlack) {
			// do a rotation
			rotate(node);
		} else if(!uncle.isBlack){
			// Color Flip
			//     red <-- grand parent
			//    /   \   
			// black black <-- parent and uncle
			uncle.isBlack = true;
			node.getParent().isBlack = true;
			node.getParent().getParent().isBlack = false;
		}
	}
	
	/**
	 * grand parent -left child-> parent -left child-> node, do RIGHT rotate
	 * grand parent -left child-> parent -right child-> node, do LEFT_THEN_RIGHT rotate
	 * grand parent -right child-> parent -right child-> node, do LEFT rotate
	 * grand parent -right child-> parent -left child-> node, do RIGHT_THEN_LEFT rotate
	 * 
	 * The color of nodes after rotating would be
	 *    black 
	 *    /   \   
	 *  red   red 
	 *  
	 * @param node
	 */
	private void rotate(RedBlackTreeNode<K, V> node) {
		if(node.isLeftChild) {
			if(node.getParent().isLeftChild) {
				rightRotate(node.getParent().getParent());
				node.isBlack = false;
				if(node.getParent() != null) {
					node.getParent().isBlack = true;
					node.getParent().getRightChild().isBlack = false;
				}
			} else {
				rightLeftRotate(node.getParent().getParent());
				node.isBlack = true;
				node.getLeftChild().isBlack = false;
				node.getRightChild().isBlack = false;
			}
		} else {
			if(node.getParent().isLeftChild) {
				leftRightRotate(node.getParent().getParent());
				node.isBlack = true;
				node.getLeftChild().isBlack = false;
				node.getRightChild().isBlack = false;
			} else {
				leftRotate(node.getParent().getParent());
				node.isBlack = false;
				if(node.getParent() != null) {
					node.getParent().isBlack = true;
					node.getParent().getLeftChild().isBlack = false;
				}
			}
		}
	}
	
	/**
	 * It would do a left rotate for below case:
	 * 	node						right child (temp node)
	 * 		\			  left    	/		  \
	 * 	   right child	-------->  node   right child 2
	 * 			\        rotate
	 * 		 right child 2
	 * 
	 * @param node
	 */
	private void leftRotate(RedBlackTreeNode<K, V> node) {
		RedBlackTreeNode<K, V> temp = node.getRightChild();
		node.setRightChild(temp.getLeftChild());
		
		if(node.getRightChild() != null) {
			node.getRightChild().setParent(node);
			node.getRightChild().isLeftChild = false;
		}
		
		if(node.getParent() == null) {
			root = temp;
			temp.setParent(null);
		} else {
			temp.setParent(node.getParent());
			if(node.isLeftChild) {
				temp.isLeftChild = true;
				temp.getParent().setLeftChild(temp);
			} else {
				temp.isLeftChild = false;
				temp.getParent().setRightChild(temp);
			}
		}
		
		temp.setLeftChild(node);
		node.setParent(temp);
	}
	
	/**
	 * It would do a left-right rotate for below case:
	 * 		   node					   	   node					    right child 2
	 * 			/		  left				/          right		/			\
	 * 	   right child	-------->     right child 2   ------->   right child     node
	 * 		 	\		 rotate			  /            rotate
	 *  	  right child 2		   right child
	 * 
	 * @param node
	 */
	private void leftRightRotate(RedBlackTreeNode<K, V> node) {
		// first, do the right rotate
		leftRotate(node.getLeftChild());
		// second, do the left rotate
		rightRotate(node);
	}
	
	/**
	 * It would do a right rotate for below case:
	 * 			node					right child (temp node)
	 * 			/						/		  \
	 * 	   right child	-------->  right child 2  node
	 * 		/
	 * 	right child 2
	 * 
	 * @param node
	 */
	private void rightRotate(RedBlackTreeNode<K, V> node) {
		RedBlackTreeNode<K, V> temp = node.getLeftChild();
		node.setLeftChild(temp.getRightChild());
		
		if(node.getLeftChild() != null) {
			node.getLeftChild().setParent(node);
			node.getLeftChild().isLeftChild = true;
		}
		
		if(node.getParent() == null) {
			root = temp;
			temp.setParent(null);
		} else {
			temp.setParent(node.getParent());
			if(node.isLeftChild) {
				temp.isLeftChild = true;
				temp.getParent().setLeftChild(temp);
			} else {
				temp.isLeftChild = false;
				temp.getParent().setRightChild(temp);
			}
		}
		
		temp.setRightChild(node);
		node.setParent(temp);
	}
	
	/**
	 * It would do a right-left rotate for below case:
	 * 	node						node						right child 2		
	 * 		\		 	 right			\			 left		/			\	
	 * 	  right child	-------->   right child 2   ------->  right child	node
	 * 		 /			 rotate		   		\		 rotate
	 *  right child 2					right child
	 * 
	 * @param node
	 */
	private void rightLeftRotate(RedBlackTreeNode<K, V> node) {
		// first, do the right rotate
		rightRotate(node.getRightChild());
		// second, do the left rotate
		leftRotate(node);
	}

	@SuppressWarnings("unchecked")
	private RedBlackTreeNode<K, V> has(RedBlackTreeNode<K, V> node, K key) {
		if(node == null || node.getKey().equals(key))
			return node;
		
		if(((Comparable<K>)key).compareTo(node.getKey()) > 0) {
			return has(node.getRightChild(), key);
		} else {
			return has(node.getLeftChild(), key);
		}
	}

	private int height(RedBlackTreeNode<K, V> node) {
		if(node == null)
			return 0;
		
		int leftHeight = height(node.getLeftChild()) + 1;
		int rightHeight = height(node.getRightChild()) + 1;
		
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}
	
	private int blackNodes(RedBlackTreeNode<K, V> node) {
		// note the NIL is black
		if(node == null)
			return 1;
		
		int leftBlackNodes = blackNodes(node.getLeftChild());
		int rightBlackNodes = blackNodes(node.getRightChild());
		
		if(leftBlackNodes != rightBlackNodes) {
			throw new RuntimeException("Left and right children has different black nodes, the tree is not balanced!");
		}
		
		if(node.isBlack)
			leftBlackNodes ++;
		
		return leftBlackNodes;
	}
}

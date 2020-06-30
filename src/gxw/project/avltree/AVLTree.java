package gxw.project.avltree;

public class AVLTree<T>
{
    private int size;
    private Node<T> root;

    public AVLTree()
    {
        this.root = null;
        this.size = 0;
    }

    public void add(T obj)
    {
        Node<T> node = new Node<T>(obj);

        if (root == null)
        {
            this.root = node;
            computeNodeBalFactor(node);
            size++;
            return;
        }

        add(this.root, node);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void add(Node<T> parent, Node<T> node)
    {
        if (((Comparable) parent.data).compareTo(node.data) == 0)
        {
            return;
        }

        if (((Comparable) parent.data).compareTo(node.data) > 0)
        {
            if (parent.leftChild != null)
            {
                add(parent.leftChild, node);
            } else
            {
                parent.leftChild = node;
                node.parent = parent;
                size++;
            }
        } else
        {
            if (parent.rightChild != null)
            {
                add(parent.rightChild, node);
            } else
            {
                parent.rightChild = node;
                node.parent = parent;
                size++;
            }
        }

        computeNodeBalFactor(parent);
        checkBalance(parent);
    }

    public Node<T> get(T obj)
    {
        return get(root, obj);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Node<T> get(Node<T> node, T obj)
    {
        if (node == null)
        {
            return null;
        }

        if (node.data.equals(obj))
        {
            return node;
        }

        if (((Comparable) node.data).compareTo(obj) > 0)
        {
            return get(node.leftChild, obj);
        } else
        {
            return get(node.rightChild, obj);
        }
    }

    public void delete(T obj)
    {
        if (obj == null)
        {
            return;
        }

        Node<T> nodeForDeletion = get(obj);
        if (nodeForDeletion == null)
        {
            System.out.println(String.format("Value %s is not existed in the tree.", obj));
        } else
        {
            delete(nodeForDeletion);
            checkBalance(nodeForDeletion.parent);
            size--;
        }
    }
    
    private void delete(Node<T> nodeForDeletion)
    {

        if (nodeForDeletion.isLeaf())
        {
            if (nodeForDeletion.isLeftChild())
            {
                nodeForDeletion.parent.leftChild = null;
            } else
            {
                nodeForDeletion.parent.rightChild = null;
            }
            computeNodeBalFactor(nodeForDeletion.parent);
        } else if (nodeForDeletion.leftChild != null && nodeForDeletion.rightChild != null)
        {
            Node<T> minNode = getMinValueNode(nodeForDeletion.rightChild);
            nodeForDeletion.data = minNode.data;
            nodeForDeletion = minNode;
            delete(nodeForDeletion);
        } else
        {
            Node<T> singleChildOfDeletion = nodeForDeletion.leftChild == null ? nodeForDeletion.rightChild
                    : nodeForDeletion.leftChild;
            if (nodeForDeletion.isLeftChild())
            {
                nodeForDeletion.parent.leftChild = singleChildOfDeletion;
            } else
            {
                nodeForDeletion.parent.rightChild = singleChildOfDeletion;
            }
            singleChildOfDeletion.parent = nodeForDeletion.parent;
            computeNodeBalFactor(nodeForDeletion.parent);
        }
    }

    /**
     * Check whether the tree's balance was broken from the node to the root if
     * so, we need to re-balancing the tree
     * 
     * @param node
     */
    private void checkBalance(Node<T> node)
    {
        if (node == null)
            return;

        int leftChildHeight = getMaxNodeHeight(root.leftChild);
        int rightChildHeight = getMaxNodeHeight(root.rightChild);
        int balFact = leftChildHeight - rightChildHeight;
        if (Math.abs(balFact) > 1)
        {
            rebalance(node);
        }

        checkBalance(node.parent);
    }
    
    private void rebalance(Node<T> node)
    {
        if (node.isRoot())
        {
            // case for deletion, root may be unbalanced
            int leftChildHeight = getMaxNodeHeight(node.leftChild);
            int rightChildHeight = getMaxNodeHeight(node.rightChild);
            if (leftChildHeight > rightChildHeight)
            {
                if (node.leftChild.leftChild != null)
                {
                    node = rightRotate(node);
                } else
                {
                    node = leftRightRotate(node);
                }
            } else
            {
                if (node.rightChild.rightChild != null)
                {
                    node = leftRotate(node);
                } else
                {
                    node = rightLeftRotate(node);
                }
            }
        } else
        {
            // cases for insertion
            if (node.isLeftChild())
            {
                if (node.leftChild != null)
                {
                    // do right rotate if
                    // 10
                    // 8
                    // 4
                    node = rightRotate(node.parent);
                } else
                {
                    // do left right rotate if
                    // 10
                    // 8
                    // 9
                    node = leftRightRotate(node.parent);
                }
            } else
            {
                if (node.rightChild != null)
                {
                    // do left rotate if
                    // 10
                    // 14
                    // 20
                    node = leftRotate(node.parent);
                } else
                {
                    // do right left rotate if
                    // 10
                    // 14
                    // 12
                    node = rightLeftRotate(node.parent);
                }
            }
        }

        if (node.leftChild != null)
        {
            computeNodeBalFactor(node.leftChild);
        }

        if (node.rightChild != null)
        {
            computeNodeBalFactor(node.rightChild);
        }

        computeNodeBalFactor(node);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Node<T> rightRotate(Node<T> node)
    {
        Node<T> temp = node.leftChild;
        node.leftChild = temp.rightChild;
        if (node.leftChild != null)
            node.leftChild.parent = node;
        temp.parent = node.parent;
        temp.rightChild = node;
        node.parent = temp;
        if (temp.parent == null)
        {
            this.root = temp;
        } else
        {
            if (((Comparable) temp.data).compareTo(temp.parent.data) > 0)
            {
                temp.parent.rightChild = temp;
            } else
            {
                temp.parent.leftChild = temp;
            }
        }
        return temp;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Node<T> leftRotate(Node<T> node)
    {
        Node<T> temp = node.rightChild;
        node.rightChild = temp.leftChild;
        if (node.rightChild != null)
            node.rightChild.parent = node;
        temp.parent = node.parent;
        temp.leftChild = node;
        node.parent = temp;
        if (temp.parent == null)
        {
            this.root = temp;
        } else
        {
            if (((Comparable) temp.data).compareTo(temp.parent.data) > 0)
            {
                temp.parent.rightChild = temp;
            } else
            {
                temp.parent.leftChild = temp;
            }
        }
        return temp;
    }

    private Node<T> leftRightRotate(Node<T> node)
    {
        node.leftChild = leftRotate(node.leftChild);
        return rightRotate(node);
    }

    private Node<T> rightLeftRotate(Node<T> node)
    {
        node.rightChild = rightRotate(node.rightChild);
        return leftRotate(node);
    }

    private void computeNodeBalFactor(Node<T> node)
    {
        int leftHeight = 0;
        int rightHeight = 0;
        if (node.leftChild != null)
        {
            leftHeight = getMaxNodeHeight(node.leftChild);
        }

        if (node.rightChild != null)
        {
            rightHeight = getMaxNodeHeight(node.rightChild);
        }

        node.balFactor = leftHeight - rightHeight;
    }

    private Node<T> getMinValueNode(Node<T> node)
    {
        while (node.leftChild != null)
        {
            node = node.leftChild;
        }
        return node;
    }

    private int getMaxNodeHeight(Node<T> node)
    {
        if (node == null || node.isRoot())
        {
            return 0;
        }

        if (node.isLeaf())
        {
            return 1;
        }

        int leftMaxHeight = getMaxNodeHeight(node.leftChild) + 1;
        int rightMaxHeight = getMaxNodeHeight(node.rightChild) + 1;

        return Math.max(leftMaxHeight, rightMaxHeight);
    }

    @Override
    public String toString()
    {
        if (root == null)
            return "Empty tree";

        return String.format("ACLTree<%d>: \n%s", size, root.toString());
    }

    public static void main(String args[])
    {
        AVLTree<Integer> tree = new AVLTree<Integer>();
        System.out.println(tree);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(10);
        tree.add(20);
        tree.add(8);
        tree.add(9);
        System.out.println(tree);
        tree.delete(3);
        System.out.println(tree);
        tree.delete(1);
        System.out.println(tree);
        tree.delete(5);
        System.out.println(tree);
        tree.delete(8);
        System.out.println(tree);
    }

    @SuppressWarnings("hiding")
    private class Node<T>
    {
        private T data;
        private Node<T> parent;
        private Node<T> leftChild;
        private Node<T> rightChild;
        private int balFactor;

        private Node(T obj)
        {
            this.data = obj;
            this.parent = this.leftChild = this.rightChild = null;
        }

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb)
        {
            if (rightChild != null)
            {
                rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(String.valueOf(data))
                    .append("(" + balFactor + ")").append("\n");
            if (leftChild != null)
            {
                leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }

        private boolean isRoot()
        {
            return this.parent == null;
        }

        private boolean isLeaf()
        {
            return this.leftChild == null && this.rightChild == null;
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        private boolean isLeftChild()
        {
            if (this.isRoot())
                return false;

            return ((Comparable) this.data).compareTo((Comparable) this.parent.data) < 0;
        }

        @Override
        public String toString()
        {
            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
        }
    }
}

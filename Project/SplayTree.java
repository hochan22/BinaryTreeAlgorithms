package Project;

public class SplayTree
{
    private BinaryNode root;
    private BinaryNode aux;

    public SplayTree() {
        root = null;
        aux = new BinaryNode(0);
    }

    /**
     * Build an empty Splay Tree
     * @param <T>
     * @return
     */
    /*
    public void create() {
        return new SplayTree();
    }*/

    /**
     * Build a Splay Tree with the given elements
     * @param <T>
     * @param elements
     * @return
     */
    /*
    public static <T extends Comparable<T>> SplayTree<T> create(T... elements) {
        SplayTree<T> tree = new SplayTree<T>();
        for(T element: elements) {
            tree.insert(element);
        }
        return tree;
    }
*/
    /**
     * Insert the given element into the tree.
     * @param key The element to insert
     * @return False if element already present, true otherwise
     */
    public boolean insert(int key) {
        if (root == null) {
            root = new BinaryNode(key);
            return true;
        }
        splay(key);

        if (key == root.key) {
            return false;
        }

        BinaryNode n = new BinaryNode(key);
        if (key < root.key) {
            n.left = root.left;
            n.right = root;
            root.left = null;
        } else {
            n.right = root.right;
            n.left = root;
            root.right = null;
        }
        root = n;
        return true;
    }

    /**
     * Remove the given element from the tree.
     * @param key The element to remove.
     * @return False if element not present, true otherwise
     */
    public boolean delete(int key) {
        splay(key);

        if (key != root.key) {
            return false;
        }

        // Now delete the root
        if (root.left == null) {
            root = root.right;
        } else {
            BinaryNode x = root.right;
            root = root.left;
            splay(key);
            root.right = x;
        }
        return true;
    }

    /**
     * Find the smallest element in the tree.
     * @return
     */
    public int findMin() {
        BinaryNode x = root;
        if(root == null) return -1;
        while(x.left != null) x = x.left;
        splay(x.key);
        return x.key;
    }

    /**
     * Find the largest element in the tree.
     * @return
     */
    public int findMax() {
        BinaryNode x = root;
        if(root == null) return -1;
        while(x.right != null) x = x.right;
        splay(x.key);
        return x.key;
    }

    /**
     * Find an item in the tree.
     * @param element The element to find
     * @return
     */
    public int find(int key) {
        if (root == null) return -1;
        splay(key);
        if(key != root.key) return -1;
        return root.key;
    }

    /**
     * Check if the tree contains the given element.
     * @param element
     * @return True if present, false otherwise
     */
    public boolean search(int key) {
        return find(key) != -1;
    }

    /**
     * Test if the tree is logically empty.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        return root == null;
    }

    /* (non-Javadoc)
     * @see java.lang.Iterable#iterator()
     */
    /*
    public Iterator<T> iterator() {
        return new SplayTreeIterator();
    }

*/
    /**
     * Internal method to perform a top-down splay.
     * If the element is in the tree, then the {@link BinaryNode} containing that element becomes the root.
     * Otherwise, the root will be the ceiling or floor {@link BinaryNode} of the given element.
     * @param element
     */
    private void splay(int key) {
        BinaryNode l, r, t, y;
        l = r = aux;
        t = root;
        aux.left = aux.right = null;
        while(true) {

            if (key < t.key) {
                if (t.left == null) break;
                if (key < t.left.key) {
                    y = t.left;                            /* rotate right */
                    t.left = y.right;
                    y.right = t;
                    t = y;
                    if (t.left == null) break;
                }
                r.left = t;                                 /* link right */
                r = t;
                t = t.left;
            } else if (key > t.key) {
                if (t.right == null) break;
                if (key > t.right.key) {
                    y = t.right;                            /* rotate left */
                    t.right = y.left;
                    y.left = t;
                    t = y;
                    if (t.right == null) break;
                }
                l.right = t;                                /* link left */
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;                                   /* assemble */
        r.left = t.right;
        t.left = aux.right;
        t.right = aux.left;
        root = t;
    }


    /**
     * {@link SplayTree} internal node
     *
     * @author Pedro Oliveira
     *
     */
    private class BinaryNode {

        public int key;          // The data in the node
        public BinaryNode left;         // Left child
        public BinaryNode right;        // Right child

        public BinaryNode(int key) {
            this.key = key;
            left = right = null;
        }
    }

    /**
     * Stack-based {@link SplayTree} iterator
     * @author Pedro Oliveira
     *
     */
    /*
    private class SplayTreeIterator implements Iterator<T> {

        private final Stack<BinaryNode> nodes;

        public SplayTreeIterator() {
            nodes = new Stack<BinaryNode>();
            pushLeft(root);
        }

        public boolean hasNext() {
            return !nodes.isEmpty();
        }

        public T next() {
            BinaryNode node = nodes.pop();
            if(node != null) {
                pushLeft(node.right);
                return node.key;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void pushLeft(BinaryNode node) {
            while (node != null) {
                nodes.push(node);
                node = node.left;
            }
        }

    }
    */
}

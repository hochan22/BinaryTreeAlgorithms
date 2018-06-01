package Project;

public class SplayTree
{
    private BinaryNode root;
    private BinaryNode aux;

    public SplayTree() {
        root = null;
        aux = new BinaryNode(0);
    }

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

    public int findMin() {
        BinaryNode x = root;
        if(root == null) return -1;
        while(x.left != null) x = x.left;
        splay(x.key);
        return x.key;
    }


    public int findMax() {
        BinaryNode x = root;
        if(root == null) return -1;
        while(x.right != null) x = x.right;
        splay(x.key);
        return x.key;
    }


    public int find(int key) {
        if (root == null) return -1;
        splay(key);
        if(key != root.key) return -1;
        return root.key;
    }


    public boolean search(int key) {
        return find(key) != -1;
    }


    public boolean isEmpty() {
        return root == null;
    }


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



    private class BinaryNode {

        public int key;
        public BinaryNode left;         // Left child
        public BinaryNode right;        // Right child

        public BinaryNode(int key) {
            this.key = key;
            left = right = null;
        }
    }
}

package mjq.cwru;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }

    public class Key implements Comparable {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    public class Value {
    }

    //balanced search tree
    public class RedBlackBST {
        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private Node root;

        private class Node {
            Key key;
            Value val;
            Node left, right;
            boolean color;

            public Node(Key key, Value val) {
                this.key = key;
                this.val = val;
            }
        }

        private boolean isRed(Node x) {
            return x.color == RED;
        }

        public Value get(Key key) {
            Node x = root;
            int cmp = key.compareTo(x.key);
            while (x != null) {
                if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
                else return x.val;
            }
            return null;
        }

        private Node rotateLeft(Node h) {
            if (!isRed(h.right))
                return h;
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private Node rotateRight(Node h) {
            if (!isRed(h.left))
                return h;
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            return x;
        }

        private void flipColors(Node h) {
            if (isRed(h) && !isRed(h.left) && !isRed(h.right)) {
                h.color = RED;
                h.left.color = BLACK;
                h.left.color = BLACK;
            }
        }

        private void put(Key key, Value val) {
            root = put(root, key, val);
        }

        private Node put(Node h, Key key, Value val) {
            if (h == null)
                return new Node(key, val);
            int cmp = key.compareTo(h.key);
            if (cmp < 0)
                h.left = put(h.left, key, val);
            else if (cmp > 0)
                h.right = put(h.right, key, val);
            else h.val = val;

            // lean left
            if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
            //balance 4-node
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            //split 4-node
            if (isRed(h.left) && isRed(h.right)) flipColors(h);
            return h;

        }
    }


}

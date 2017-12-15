/**
 * Created by ehunwag on 12/15/2017.
 */
public class RedBlackTree<K extends Comparable<K>, V> {

    enum COLOR {NONE, RED, BLACK}
    //final String[] COLOR_STR = new String[]{"Red", "Black"};

    class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        public Node<K, V> parent;
        public COLOR color = COLOR.RED;

        public boolean isUncleRedToo() {
            if (parent != null && parent.parent != null) {
                Node<K, V> grandparent = parent.parent;
                if (grandparent.left != null && grandparent.left.color == COLOR.RED &&
                        grandparent.right != null && grandparent.right.color == COLOR.RED)
                    return true;
            }
            return false;
        }

        public boolean isParentRed() {
            if (parent != null && parent.color == COLOR.RED)
                return true;
            else
                return false;
        }


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            String res = new String();
            res += key;
            if (color == COLOR.RED) {
                res += " : RED";
            } else {
                res += " : BLACK";
            }
            return res;
        }
    }

    private Node<K, V> root;
    private int number;
    private int depth;


    public void print(Node<K, V> node, String indent, String left_right) {
        if (node == null) return;
        System.out.println(left_right + indent + node);
        print(node.left, indent + "  " , "L");
        print(node.right, indent + "  ", "R");
    }

    public void print() {
        System.out.println("Red Black Tree:");
        print(root, "", "O");
        System.out.println();
    }

    public V search(K key) {
        return search(root, key).value;
    }

    public Node<K, V> search(Node<K, V> node, K key) {
        if (node == null) return null;
        if (node.key.compareTo(key) == 0) return node;

        if (node.key.compareTo(key) > 0)
            return search(node.left, key);
        return search(node.right, key);
    }

    public Node<K, V> insert(Node<K, V> node, Node<K, V> new_node) {
        if (node == null) {
            return new_node;
        }
        if (node.key.compareTo(new_node.key) > 0) {
            node.left = insert(node.left, new_node);
            node.left.parent = node;
        } else {
            node.right = insert(node.right, new_node);
            node.right.parent = node;
        }
        return node;
    }

    public void insert(K key, V value) {
        Node<K, V> node = search(root, key);
        if (node != null) {
            node.value = value;
            return;
        }

        node = new Node<>(key, value);
        root = insert(root, node);

        //Change the color if needed
        reColor(node);

        //Change the root to Black
        root.color = COLOR.BLACK;

    }

    private void updateAncestor(Node<K, V> node, Node<K, V> ancestor) {
        if (node == null) return;
        node.parent = ancestor;

        if (ancestor == null) {
            root = node;
            return;
        }

        if (ancestor.key.compareTo(node.key) > 0)
            ancestor.left = node;
        else
            ancestor.right = node;
    }

    public void reColor(Node<K, V> node) {
        if (!node.isParentRed())
            return;

        Node<K, V> parent = node.parent;
        Node<K, V> grandparent = parent.parent;

        if (grandparent.left != null && grandparent.right != null && grandparent.left.color == grandparent.right.color) {
            grandparent.color = COLOR.RED;
            grandparent.left.color = COLOR.BLACK;
            grandparent.right.color = COLOR.BLACK;
            reColor(node.parent.parent);
        } else {
            if (grandparent.key.compareTo(parent.key) > 0) {
                //left left case
                if (parent.key.compareTo(node.key) > 0) {
                    updateAncestor(parent, grandparent.parent);

                    grandparent.left = parent.right;
                    if (parent.right != null)
                        parent.right.parent = grandparent;

                    parent.right = grandparent;
                    grandparent.parent = parent;

                    grandparent.color = COLOR.RED;
                    parent.color = COLOR.BLACK;

                }
                // left right case
                else {
                    updateAncestor(node, grandparent.parent);

                    parent.right = node.left;
                    if (node.left != null)
                        node.left.parent = parent;

                    grandparent.left = node.right;
                    if (node.right != null)
                        node.right.parent = grandparent;

                    node.left = parent;
                    node.right = grandparent;
                    parent.parent = node;
                    grandparent.parent = node;

                }
            } else {
                // right right case
                if (parent.key.compareTo(node.key) < 0) {
                    updateAncestor(parent, grandparent.parent);

                    grandparent.right = parent.left;
                    if (parent.left != null)
                        parent.left.parent = grandparent;

                    parent.left = grandparent;
                    grandparent.parent = parent;

                    grandparent.color = COLOR.RED;
                    parent.color = COLOR.BLACK;
                }
                // right left case
                else {
                    updateAncestor(node, grandparent.parent);
                    parent.left = node.right;
                    if (node.right != null)
                        node.right.parent = parent;

                    grandparent.right = node.left;
                    if (node.left != null)
                        node.left.parent = grandparent;

                    node.left = grandparent;
                    node.right = parent;
                    parent.parent = node;
                    grandparent.parent = node;

                }
            }

        }
    }


    public static void main(String[] args) {
        RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<>();
        redBlackTree.insert(10, "Node1");
        redBlackTree.print();
        redBlackTree.insert(20, "Node2");
        redBlackTree.print();
        redBlackTree.insert(30, "Node3");
        redBlackTree.print();
        redBlackTree.insert(15, "Node4");
        redBlackTree.print();
    }
}

import java.util.Random;

/**
 * Created by Echo Wang on 12/14/2017.
 */


class AVLTreeNode<K extends Comparable<K>, V> {
    public K key;
    public V value;
    public AVLTreeNode<K, V> left;
    public AVLTreeNode<K, V> right;
    public int height;

    public AVLTreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 0;
    }

    public void AdjustHeight(){
        int left_height = this.left != null? this.left.height : -1;
        int right_height = this.right != null? this.right.height: -1;
        int max_height = left_height > right_height? left_height: right_height;
        height = max_height + 1;
    }

    @Override
    public String toString() {
        return height + " [" + key + ":" + value + "]";
    }
}


public class AVLTree<K extends Comparable<K>, V> {
    private AVLTreeNode<K, V> root;

    public boolean isEmpty() {
        return root == null;
    }


    public void insert(K key, V value) {

        AVLTreeNode<K, V> node = search(key);
        if (node != null)
            node.value = value;
        else
            this.root = this.insert(this.root, key, value);
    }

    public AVLTreeNode<K, V> insert(AVLTreeNode<K, V> node, K key, V value) {

        if (node == null) {
            node = new AVLTreeNode<>(key, value);
            return node;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = this.insert(node.left, key, value);
            if (height(node.left) - height(node.right) == 2) {
                if (node.left.key.compareTo(key) > 0){
                    node = rotateRight(node);
                }
                else{
                    node = rotateLeftRight(node);
                }
            }
        } else {
            node.right = this.insert(node.right, key, value);
            if (height(node.right) - height(node.left) == 2) {
                if (node.right.key.compareTo(key) < 0){
                    node = rotateLeft(node);
                }
                else{
                    node = rotateRightLeft(node);
                }
            }
        }
        node.AdjustHeight();
        return node;
    }


    private AVLTreeNode<K, V> rotateRight(AVLTreeNode<K, V>node){
        AVLTreeNode<K, V> left_node = node.left;
        node.left = left_node.right;
        left_node.right = node;

        node.AdjustHeight();
        left_node.AdjustHeight();
        return left_node;
    }

    private AVLTreeNode<K, V> rotateLeft(AVLTreeNode<K, V> node){
        AVLTreeNode<K, V> right_node = node.right;
        node.right = right_node.left;
        right_node.left = node;
        node.AdjustHeight();
        right_node.AdjustHeight();
        return right_node;
    }

    private AVLTreeNode<K, V> rotateLeftRight(AVLTreeNode<K, V> node){
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

    private AVLTreeNode<K, V> rotateRightLeft(AVLTreeNode<K, V> node){
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }


    public AVLTreeNode<K, V> search(K key) {
        AVLTreeNode<K, V> res = this.root;
        while (res != null) {
            if (res.key == key) {
                return res;
            }
            if (res.key.compareTo(key) > 0) {
                res = res.left;
            } else {
                res = res.right;
            }
        }
        return null;
    }

    public int height(AVLTreeNode<K, V> node) {
        return node == null ? -1 : node.height;
    }

    public V get(K key) {
        AVLTreeNode<K, V> node = this.search(key);
        return node.value;
    }

    public AVLTreeNode<K, V> delete(K key) {
        //System.out.println("Delete Node:" + key );
        return this.root;
    }

    public void print() {
        String indent = new String("");
        this.print(this.root, indent);
    }

    public void print(AVLTreeNode<K, V> node, String indent) {
        if (node != null) {
            System.out.println(indent + node);

            print(node.left, indent + "    ");
            print(node.right, indent + "    ");
        }
    }

    public static void main(String[] args) {
        final int MAX = 1000;
        AVLTree<Integer, String> tree = new AVLTree<>();
        Random random = new Random();
        for (int i = 0; i < MAX; i++) {
            tree.insert(random.nextInt(100), "Node" + i);
        }
        tree.print();
    }
}

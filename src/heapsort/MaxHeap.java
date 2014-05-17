/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heapsort;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Cuper
 */
public class MaxHeap<T extends Comparable> {
    
    public static int COUNT = 0;
    
    // ArrayList to store all the data;
    private ArrayList<Comparable> input;
    private ArrayList<T> datas;
    private ArrayList<Node> nodes;
    
    // Root node
    private Node root;
    
    public MaxHeap() {
        input = new ArrayList();
        datas = new ArrayList();
    }
    
    // Return datas as ArrayList
    public ArrayList toArrayList() {
        return new ArrayList(datas);
    }
    
    // Return datas as native Array
    public Object[] toArray() {
        return datas.toArray();
    }
    
    // Add new data
    public void add(Comparable object) {
        input.add(object);
    }
    
    // For use with the input, pull an element out and remove it from ArrayList input
    private Comparable pull() {
        if (input.size() > 0) {
            return input.remove(0);
        } else {
            return null;
        }
    }
    
    // For use with the input, put an element into ArrayList input
    private void put(Comparable e) {
        input.add(e);
    }
    
    // Sort the overall 
    public void sort() {
        COUNT = 0;
        nodes = new ArrayList();
        // Convert the input into a Binary Tree
        int n = 0;
        root = new Node(pull());
        nodes.add(root);
        //N loop
        while (!input.isEmpty()) {
            for (int i = (int) Math.pow(2, n) - 1; i < (int) Math.pow(2, n + 1) - 1; i++) {
                if (input.size() > 0) {
                    Node node = nodes.get(i);
                    fillNode(node);
                } else {
                    break;
                }
            }
            n++;
        }
        
        datas.clear();
        while (nodes.size() > 0) {
            switchNode();
        }
    }
    
    //Switch the node
    private void switchNode() {
        datas.add((T)root.content);
        Node last = nodes.get(nodes.size() - 1);
        root.content = (T)last.content;
        nodes.remove(nodes.size() - 1);
        if (last.parent != null) {
            if (last == last.parent.left) {
                last.parent.left = null;
            }
            if (last == last.parent.right) {
                last.parent.right = null;
            }
            last.parent = null;
        }
        root.compareChild();
    }
    
    // Fill the node
    private void fillNode(Node parent) {
        if (parent.left == null) { // Fill left child node
            Comparable object = pull();
            Node node = new Node(object);
            node.parent = parent;
            nodes.add(node);
            parent.left = node;
            node.compareParent();
        }
        if (input.size() > 0) {
            if (parent.right == null) { // Fill right child node
                Comparable object = pull();
                Node node = new Node(object);
                node.parent = parent;
                nodes.add(node);
                parent.right = node;
                node.compareParent();
            }
        }
    }
    
    // Sort with Array Data Structure as input
    public void sort(Comparable[] input) {
        this.input.clear();
        for (int i = 0; i < input.length; i++) {
            put(input[i]);
        }
        sort();
    }
    
    // Sort with ArrayList Data Structure as input
    public void sort(ArrayList<Comparable> input) {
        this.input.clear();
        for (int i = 0; i < input.size(); i++) {
            put(input.get(i));
        }
        sort();
    }
    
    // Sort with Queue Data Structure as input
    public void sort(Queue<Comparable> input) {
        this.input.clear();
        while (!input.isEmpty()) {
            put(input.remove());
        }
        sort();
    }
    
}

class Node<T extends Comparable> {

    Node parent; // Parent of this node
    Node left; // Left Child Node
    Node right; // Right Child Node
    T content; // Content of this Node

    // Full Parameter
    public Node(T content, Node parent, Node left, Node right) {
        this.content = content;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    // Truncated Parameter
    public Node(T content) {
       this(content, null, null, null);
    }

    // Compare with Parent, switch if greater
    public void compareParent() {
        MaxHeap.COUNT++;
        if ((parent != null) && (content.compareTo(parent.content) > 0)) {
            T cntnt = content;
            content = (T)parent.content;
            parent.content = cntnt;
            parent.compareParent();
        }
    }
    
    public void compareChild() {
        MaxHeap.COUNT++;
        if ((left != null) && (right != null)) {
            if (left.content.compareTo(right.content) > 0) {
                if (left.content.compareTo(content) > 0) {
                    T cntnt = (T)left.content;
                    left.content = (T)content;
                    content = cntnt;
                    left.compareChild();
                }
            } else if (right.content.compareTo(left.content) > 0) {
                if (right.content.compareTo(content) > 0) {
                    T cntnt = (T)right.content;
                    right.content = (T)content;
                    content = cntnt;
                    right.compareChild();
                }
            }
        } else {
            if ((left != null) && (left.content.compareTo(content) > 0)) {
                T cntnt = (T)left.content;
                left.content = (T)content;
                content = cntnt;
                left.compareChild();
            } else if ((right != null) && (right.content.compareTo(content) > 0)) {
                T cntnt = (T)right.content;
                right.content = (T)content;
                content = cntnt;
                right.compareChild();
            }
        }
    }
    
    public void compareTo(Node node) {
        MaxHeap.COUNT++;
        if ((node != null) && (content.compareTo(node.content) > 0)) {
            T cntnt = content;
            content = (T)node.content;
            node.content = cntnt;
        }
    }
}
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
        
        for (int i = 0; i < nodes.size(); i++) {
            datas.add((T)nodes.get(i).content);
        }
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
        } else if (parent.right == null) { // Fill right child node
            Comparable object = pull();
            Node node = new Node(object);
            node.parent = parent;
            nodes.add(node);
            parent.right = node;
            node.compareParent();
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
        if ((parent != null) && (content.compareTo(parent.content) > 0)) {
            T cntnt = content;
            content = (T)parent.content;
            parent.content = cntnt;
            parent.compareParent();
        }
    }
}
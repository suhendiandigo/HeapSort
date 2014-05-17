/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package heapsort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Cuper
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Integer[] ints = new Integer[]{new Integer(7),new Integer(1),new Integer(3),new Integer(12),new Integer(2),new Integer(9),new Integer(3)};
//        MaxHeap<Integer> mh = new MaxHeap();
//        mh.sort(ints);
//        Object[] o = mh.toArray();
//        for (int i = 0; i < o.length; i++) {
//            System.out.println(o[i]);
//        }
//        System.out.println("Done");
        MaxHeap<Double> max=new MaxHeap();
        Random r=new Random();
        for (int i=0;i<100;i++){
            max.add(r.nextDouble());
        }
        max.sort();
        for (Object i:max.toArray()){
            System.out.println(i);
        }
    }
    
    
}

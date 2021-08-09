
package Utility;

//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class Data {
    
    public static Vector<Integer> getData (){
        
            int Fixed_cost = 350;
            int Variable_cost = 150;
            int Hiring_cost = 800;
            int dim = 52;
            Vector<Integer> data = new Vector<Integer>(dim + 3);
            data.add(Fixed_cost);
            data.add(Variable_cost);
            data.add(Hiring_cost);
          
            int[] x = { 12, 15, 16, 17, 17, 18, 20, 20, 21, 22, 24, 22, 20, 18, 17, 16, 14, 13, 13, 14, 15, 16, 17, 19, 21, 22, 23, 22, 24, 26, 27, 28, 30, 32, 32, 30, 29, 28, 26, 25, 25, 24, 22, 22, 19, 20, 18, 17, 16, 16, 14, 13,};
            for (int i = 0; i < dim; ++i) {
                data.add(x[i]);
            }
            List subvector = data.subList(3, data.size());
        int max = (int) Collections.max(subvector);
        data.add(max);
            return data;
        
    }
    
    public static void printdata (Vector<Integer> d) {
        System.out.println("Vector size = " + d.size());
        for (int i = 0; i < d.size(); ++i) {
            if (i == 0) { 
              System.out.println("Fixed Cost = "+d.get(i)); 
            } else if (i == 1) {
                 System.out.println("Variable Cost = "+d.get(i)); 
            } else if (i == 2) {
                System.out.println("Hiring Cost= "+d.get(i));
            } else{ if(i>2 & i<d.size()-1){
                System.out.println("v["+ (i - 2)+ "] = "+d.get(i));}
            else{
                System.out.println("max value = "+ d.get(i));
                    }
            }
        }
       
    }
}







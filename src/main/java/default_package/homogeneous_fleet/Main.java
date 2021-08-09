
package default_package.homogeneous_fleet;


import ilog.concert.IloException;
import java.io.File;
import Utility.Data;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Vector;


public class Main {
    
    public static void main (String[] args) throws FileNotFoundException, IloException {
      
        System.setOut(new PrintStream("Homogeneous Fleet Composition Problem.log"));                                                      
        Vector<Integer> DATA = Data.getData();
        Data.printdata(DATA);
        int n = (DATA.size()-4);// numero di periodi t
        
        CPLEX_model m = new CPLEX_model(DATA,n);
        m.solveMovel();
    }
    
}

package default_package.homogeneous_fleet;

import ilog.concert.*;
import ilog.cplex.*;
import java.util.Vector;

public class CPLEX_model {

    protected int n;
    protected IloCplex model;
    protected boolean CPLEX_status;
    protected Vector<Integer> d;
    protected IloIntVar v;
    protected IloIntVar dummy;
    protected IloIntVar[] y;
    protected IloIntVar[] x;


    public CPLEX_model(Vector<Integer> data, int n) throws IloException {
        model = new IloCplex();
        d = data;// 
        this.n = n;
        n = (d.size() - 4); 
        CPLEX_status = false;
        y = new IloIntVar[data.size() - 4]; 
        x = new IloIntVar[data.size() - 4];
        
    }

    public void addvariables() throws IloException {

        v = model.intVar(0, d.get(d.size() - 1), "v");

        dummy = model.intVar(1, 1, "dummy");

        for (int i = 0; i < (d.size() - 4); i++) {
            y[i] = model.intVar(0, d.get(d.size() - 1), "y[" + (i + 1) + "]");
            x[i] = model.intVar(0, 1, "x[" + (i + 1) + "]");
        }

    }

    protected void addconstrains() throws IloException {

        for (int i = 0; i < n; i++) { 
            IloLinearNumExpr expr = model.linearNumExpr();
            expr.addTerm(1, v);
            expr.addTerm(-d.get(d.size() - 1), x[i]);

            model.addGe(expr, d.get(i + 3) - d.get(d.size() - 1));
        }
        for (int i = 0; i < n; ++i) { 
            
            IloLinearNumExpr expr1 = model.linearNumExpr();
            expr1.addTerm(1, v);
            expr1.addTerm(-d.get(d.size() - 1), x[i]);
            model.addLe(expr1, d.get(i + 3));
        }

        for (int i = 0; i < n; ++i) { 
            IloLinearNumExpr expr3 = model.linearNumExpr();
            expr3.addTerm(1, y[i]);
            expr3.addTerm(-1, v);
            model.addLe(expr3, 0);
        }

        for (int i = 0; i < n; ++i) { 
            IloLinearNumExpr expr4 = model.linearNumExpr();
            expr4.addTerm(1, y[i]);
            expr4.addTerm(-d.get(d.size() - 1), x[i]);
            model.addLe(expr4, 0);
        }

        for (int i = 0; i < n; ++i) { 
            IloLinearNumExpr expr5 = model.linearNumExpr();
            expr5.addTerm(1, y[i]);
            expr5.addTerm(-1, v);
            expr5.addTerm(-d.get(d.size() - 1), x[i]);

            model.addGe(expr5, -d.get(d.size() - 1));
        }


    }

    protected void addObjective() throws IloException {
        
        IloLinearNumExpr Obj = model.linearNumExpr();
            Obj.addTerm(n*d.get(0), v);
            int value = 0;
            for (int i = 0; i < n; ++i) {
                Obj.addTerm(d.get(1)*d.get(i + 3), x[i]);
                Obj.addTerm(-d.get(1), y[i]);
                value += d.get(2)*d.get(i + 3);
                Obj.addTerm(-d.get(2)*d.get(i + 3), x[i]);
                Obj.addTerm(d.get(2), y[i]);
            }
            Obj.addTerm(-n*d.get(2), v);
            Obj.addTerm(value, dummy);
            Obj.addTerm(n*d.get(1), v);
            
            model.addObjective(IloObjectiveSense.Minimize, Obj); 

    }

    public void solveMovel() throws IloException {

        addvariables();
        addconstrains();
        addObjective();
        model.exportModel("Homogeneous Fleet Composition Problem.lp");
        model.solve();
        System.out.println("Objective value = " + model.getObjValue());
        System.out.println(v.getName() + " = " + model.getValue(v));

    }

}


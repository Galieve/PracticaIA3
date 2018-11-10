package practica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.uninformed.BreadthFirstSearch;
import javafx.util.Pair;

public class DemoClass {

    static EstadoCoche estadoInicial = new EstadoCoche();

    public static void main(String [] args)  {
    	initializeState();
    	demoCoche();
    }

    private static void demoCoche()    {
        breadthFirstDemo();
        //depthLimitedFirstDemo();
        //iterativeDeepeningDemo();

    }

    private static void  breadthFirstDemo()
    {
        System.out.println("\nMisionerosBFSDemo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);

        	SearchForActions<EstadoCoche, MueveCoche> search = new BreadthFirstSearch<>();
            SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeState() {
    	
    	Coche coche1 = new CocheHorizontal(new Pair<Integer,Integer>(1,1), new Pair<Integer,Integer>(1,3));
    	coche1.setObjetivo(true);
    	ArrayList<Coche> lista =new ArrayList<>();
    	lista.add(coche1);
    	HashSet<Pair<Integer,Integer>> puertas=new HashSet<>();
    	puertas.add(new Pair<Integer,Integer>(1,5));
    	estadoInicial=new EstadoCoche(5,5,puertas,lista);
    }
/*
    private static void depthLimitedFirstDemo()
    {
        System.out.println("\nMisionerosDLFSDEMO--->");
        try
        {
            Problem problem
                    = new Problem(estadoInicial,MisionerosFunctionFactory.getAtionsFunction(),
                    MisionerosFunctionFactory.getResultFunction(),new MisionerosGoalTest(),
                    new MisionerosStepCostFunction());

            Search search = new DepthLimitedSearch(10);
            SearchAgent agent = new SearchAgent(problem,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void iterativeDeepeningDemo()
    {
        System.out.println("\nMisionerosIDDemo--->");
        try
        {
            Problem problem
                    = new Problem(estadoInicial,MisionerosFunctionFactory.getAtionsFunction(),
                    MisionerosFunctionFactory.getResultFunction(),new MisionerosGoalTest(),
                    new MisionerosStepCostFunction());

            Search search = new IterativeDeepeningSearch();
            SearchAgent agent = new SearchAgent(problem,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
*/
    private static void printInstrumentation(Properties properties) {
        Iterator<Object> keys = properties.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            String property = properties.getProperty(key);
            System.out.println(key + " : " + property);
        }

    }

    private static void printActions(List<Action> actions) {
        for (int i = 0; i < actions.size(); i++) {
            String action = actions.get(i).toString();
            System.out.println(action);
        }
    }
}

package practica;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.function.ToDoubleFunction;

import aima.core.agent.Action;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.Node;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.BestFirstSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.UniformCostSearch;
import javafx.util.Pair;
import practica.coche.Coche;
import practica.coche.CocheHorizontal;
import practica.coche.CocheVertical;
import practica.coche.EstadoCoche;
import practica.coche.MueveCoche;
import practica.coche.ProblemaCoche;
import practica.heuristica.HeuristicaCochesTaponando;
import practica.heuristica.HeuristicaDistanciaUno;

public class DemoClass {

    static EstadoCoche estadoInicial = new EstadoCoche();
    static EstadoCoche EstadoJuguete = new EstadoCoche();

    
    
    public static void main(String [] args)  {
    	initializeState();
    	demoCoche();
    }

    private static void demoCoche()    {
        depthLimitedFirstDemo();
        depthFirstDemo();
        uniformCostDemo();
        bestFirstSearchDemo(new HeuristicaDistanciaUno());
        bestFirstSearchDemo(new HeuristicaCochesTaponando());
        greedySearchDemo(new HeuristicaDistanciaUno());
        greedySearchDemo(new HeuristicaCochesTaponando());
        aStarDemo(new HeuristicaDistanciaUno());
        aStarDemo(new HeuristicaCochesTaponando());

    }

    private static void  depthLimitedFirstDemo()
    {
        System.out.println("\nCocheDLFS(10)Demo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = new DepthLimitedSearch<>(10);
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void  depthFirstDemo()
    {
        System.out.println("\nCocheDFSDemo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = new DepthFirstSearch<>(new GraphSearch<>());
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
   
    
    private static void uniformCostDemo()
    {
        System.out.println("\nCocheUniformCostDemo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = new UniformCostSearch<>(
        			new GraphSearch<>());
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void bestFirstSearchDemo
    (ToDoubleFunction<Node<EstadoCoche, MueveCoche>> heuristica)
    {
        System.out.println("\nCocheBestFirstSearch "+heuristica.toString()+" Demo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = new BestFirstSearch<>(
        			new GraphSearch<>(),heuristica);
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private static void greedySearchDemo
    (ToDoubleFunction<Node<EstadoCoche, MueveCoche>> heuristica)
    {
        System.out.println("\nCoche GreedySearch "+heuristica.toString()+" Demo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = new GreedyBestFirstSearch<>(
        			new GraphSearch<>(),heuristica);
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void  aStarDemo
    (ToDoubleFunction<Node<EstadoCoche, MueveCoche>> heuristica)
    {
        System.out.println("\nCoche AStar "+heuristica.toString()+" Demo--->");
        try
        {
        	ProblemaCoche problema=new ProblemaCoche(estadoInicial);
        	SearchForActions<EstadoCoche, MueveCoche> search = 
        			new AStarSearch<>(new GraphSearch<>(),heuristica);
        	SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

            printActions(agent.getActions());
            printInstrumentation(agent.getInstrumentation());

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeState() {
    	
    	Coche coche1 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(2,2),0);
    	Coche coche2 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(1,2),1);
    	Coche coche3 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(0,4),2);
    	Coche coche4 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(2,4),3);
    	Coche coche5 = new CocheVertical(new Pair<Integer,Integer>(3,1), new Pair<Integer,Integer>(3,2),4);
    	Coche coche6 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(4,3),5);
    	Coche coche7 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(4,4),6);
    	Coche coche8 = new CocheVertical(new Pair<Integer,Integer>(3,1), new Pair<Integer,Integer>(0,5),9);
    	
    	Coche cP=new CocheHorizontal(new Pair<Integer,Integer>(1,1), new Pair<Integer,Integer>(1,1),0);
    	Coche cP2=new CocheVertical(new Pair<Integer,Integer>(1,1), new Pair<Integer,Integer>(1,2),5);
    	coche1.setObjetivo(true);
    	cP.setObjetivo(true);
    	//lista.add(cP);
    	HashSet<Pair<Integer,Integer>> puertas=new HashSet<>();
    	puertas.add(new Pair<Integer,Integer>(1,6));
    	estadoInicial=new EstadoCoche(6,6,puertas,
    			coche1,coche2,coche3,coche4,coche5,coche6,coche8,coche7);
    	EstadoJuguete=new EstadoCoche(6,6,puertas,
    			cP,cP2);
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
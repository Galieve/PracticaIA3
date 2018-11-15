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
import aima.core.search.framework.qsearch.GraphSearchBFS;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
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
import practica.heuristica.HeuristicaCochesBloqueadores;
import practica.heuristica.HeuristicaDistanciaUno;

public class DemoClass {

	static EstadoCoche estadoInicial = null;



	public static void main(String [] args)  {
		initializeState();
		demoCoche();
	}

	private static void demoCoche()    {
		depthLimitedFirstDemo(7);
		depthFirstDemo();
		bfsDemo();
		uniformCostDemo();
		greedySearchDemo(new HeuristicaDistanciaUno());
		greedySearchDemo(new HeuristicaCochesBloqueadores());
		aStarDemo(new HeuristicaDistanciaUno());
		aStarDemo(new HeuristicaCochesBloqueadores());

	}

	private static void demo(SearchForActions<EstadoCoche, MueveCoche> search, String mensaje) {
		try
		{	
			System.out.println(mensaje);
			ProblemaCoche problema = new ProblemaCoche(estadoInicial);
			SearchAgent<EstadoCoche, MueveCoche> agent = new SearchAgent<>(problema,search);

			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void  depthLimitedFirstDemo(int profundidad)
	{
		try
		{
			demo(new DepthLimitedSearch<>(profundidad),"\nCocheDLFS (" +profundidad+ ") Demo--->");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void  depthFirstDemo()
	{
		try
		{
			demo(new DepthFirstSearch<>(new GraphSearch<>()),"\nCocheDFSDemo--->");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void  bfsDemo()
	{
		
		try
		{
			demo(new BreadthFirstSearch<>(new GraphSearchBFS<>()),"\nCocheBFSDemo--->");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void uniformCostDemo()
	{
		try
		{
			demo(new UniformCostSearch<>(
					new GraphSearch<>()),"\nCocheUniformCostDemo--->");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static void greedySearchDemo
	(ToDoubleFunction<Node<EstadoCoche, MueveCoche>> heuristica)
	{
		try
		{
			demo(new GreedyBestFirstSearch<>(
					new GraphSearch<>(),heuristica),"\nCoche GreedySearch "+heuristica.toString()+" Demo--->");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void  aStarDemo
	(ToDoubleFunction<Node<EstadoCoche, MueveCoche>> heuristica)
	{
		try
		{
			demo(new AStarSearch<>(new GraphSearch<>(),heuristica),"\nCoche AStar "+heuristica.toString()+" Demo--->");

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initializeState() {

		Coche coche1 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(2,2),1);
		Coche coche2 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(1,2),2);
		Coche coche3 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(0,4),3);
		Coche coche4 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(2,4),4);
		Coche coche5 = new CocheVertical(new Pair<Integer,Integer>(3,1), new Pair<Integer,Integer>(3,2),5);
		Coche coche6 = new CocheVertical(new Pair<Integer,Integer>(2,1), new Pair<Integer,Integer>(4,3),6);
		Coche coche7 = new CocheHorizontal(new Pair<Integer,Integer>(1,2), new Pair<Integer,Integer>(4,4),7);
		Coche coche8 = new CocheVertical(new Pair<Integer,Integer>(3,1), new Pair<Integer,Integer>(0,5),8);

		coche1.setObjetivo(true);
		HashSet<Pair<Integer,Integer>> puertas=new HashSet<>();
		puertas.add(new Pair<Integer,Integer>(2,6));
		estadoInicial=new EstadoCoche(6,6,puertas,
				coche1,coche2,coche3,coche4,coche5,coche6,coche8,coche7);
	}
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

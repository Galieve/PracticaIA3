/**
 * 
 */
package practica;

import aima.core.environment.map.Map;
import aima.core.environment.map.MapFunctions;
import aima.core.environment.map.MoveToAction;
import aima.core.search.framework.problem.BidirectionalProblem;
import aima.core.search.framework.problem.GeneralProblem;
import aima.core.search.framework.problem.GoalTest;

/**
 * @author usuario_local
 *
 */
public class ProblemaCoche extends GeneralProblem<EstadoCoche, MueveCoche>{

	public ProblemaCoche(EstadoCoche initialState) {
		super(initialState, CocheFactoryFunction.getActionFunction(), 
				CocheFactoryFunction.getResultFunction(), 
				CocheFactoryFunction.getGoalTest(), CocheFactoryFunction.getStepFunction());
	}
	
	//TODO: crear un constructor con heuristicas.
}

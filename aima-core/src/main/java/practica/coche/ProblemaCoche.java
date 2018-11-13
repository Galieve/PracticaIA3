/**
 * 
 */
package practica.coche;

import aima.core.search.framework.problem.GeneralProblem;

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
	
}

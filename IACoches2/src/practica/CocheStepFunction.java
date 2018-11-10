package practica;

import aima.core.search.framework.problem.StepCostFunction;

public class CocheStepFunction implements StepCostFunction<EstadoCoche, MueveCoche> {

	@Override
	public double applyAsDouble(EstadoCoche origen, MueveCoche accion, EstadoCoche destino) {
		return 1;
	}

}

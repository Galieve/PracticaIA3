package practica.coche;

import java.util.List;

import aima.core.search.framework.problem.ActionsFunction;

public class CochesActionFunction implements ActionsFunction<EstadoCoche, MueveCoche> {


	public List<MueveCoche> apply(EstadoCoche estado) {
		return estado.generarAcciones();
	}

}

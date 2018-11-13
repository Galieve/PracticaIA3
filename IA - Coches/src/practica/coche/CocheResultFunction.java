package practica.coche;

import aima.core.search.framework.problem.ResultFunction;

public class CocheResultFunction implements ResultFunction<EstadoCoche, MueveCoche> {

	@Override
	public EstadoCoche apply(EstadoCoche e, MueveCoche m) {
		EstadoCoche estado=new EstadoCoche(e);
		estado.executeAction(m);
		return estado;
	}

}

package practica.coche;


import aima.core.search.framework.problem.GoalTest;

public class CocheGoalTest implements GoalTest<EstadoCoche> {
	
	//Nota, las puertas tienen posiciones INVÁLIDAS en el tablero, pero son adyacentes al mismo.
	
	
	public CocheGoalTest() {}
	
	

	@Override
	public boolean test(EstadoCoche estado) {
		return estado.isGoal();
	}
	

}

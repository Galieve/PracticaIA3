package practica;

import java.util.ArrayList;
import java.util.List;

import aima.core.search.framework.problem.GoalTest;
import javafx.util.Pair;

public class CocheGoalTest implements GoalTest<EstadoCoche> {
	
	//Nota, las puertas tienen posiciones INVALIDAS en el tablero, pero son adyacentes al mismo.
	
	
	public CocheGoalTest() {}
	
	

	@Override
	public boolean test(EstadoCoche estado) {
		return estado.isGoal();
	}
	

}

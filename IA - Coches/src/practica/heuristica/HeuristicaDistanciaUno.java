package practica.heuristica;

import java.util.function.ToDoubleFunction;

import javafx.util.Pair;
import practica.coche.Coche;
import practica.coche.EstadoCoche;
import practica.coche.MueveCoche;
import aima.core.search.framework.Node;

public class HeuristicaDistanciaUno implements ToDoubleFunction<Node<EstadoCoche,MueveCoche>> {

	private double getHeur(EstadoCoche estado){
		if(estado.getListaPuertas().size()>1)return 0;
		Pair<Integer,Integer> puerta=estado.getListaPuertas().iterator().next();
		Coche objetivo=estado.getObjetivo();
		if(puerta.getKey()==objetivo.getPos().getKey()){//El coche es horizontal
			
			if(objetivo.getPos().getValue()>puerta.getValue()){//La puerta está a la izquierda
				return objetivo.getPos().getValue();
			}
			else{//Está a la derecha
				return puerta.getValue()-objetivo.getPos().getValue()-objetivo.getTam().getValue();
			}			
		}
		else{//El coche es vertical
			if(objetivo.getPos().getKey()>puerta.getKey()){//La puerta está arriba
				return objetivo.getPos().getKey();
			}else{
				return puerta.getKey()-objetivo.getPos().getKey()-objetivo.getTam().getKey();
			}				
		}
	}
	
	@Override
	public double applyAsDouble(Node<EstadoCoche, MueveCoche> value) {
		return getHeur(value.getState());
	}

	@Override
	public String toString() {
		return "HeuristicaDistanciaUno";
	}
	
	

}

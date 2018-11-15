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
		if(puerta.getKey()==objetivo.getPos().getKey()){//Suponemos horizontal 
			
			if(objetivo.getPos().getValue()>puerta.getValue()){//Esta a la izquierda la puerta
				return objetivo.getPos().getValue();
			}
			else{//A la derecha
				return puerta.getValue()-objetivo.getPos().getValue()-objetivo.getTam().getValue();
			}			
		}
		else{//Suponemos vertical
			if(objetivo.getPos().getKey()>puerta.getKey()){//puerta arriba
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

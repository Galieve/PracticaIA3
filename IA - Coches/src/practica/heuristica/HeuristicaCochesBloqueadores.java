package practica.heuristica;

import java.util.function.ToDoubleFunction;

import javafx.util.Pair;
import practica.coche.Coche;
import practica.coche.EstadoCoche;
import practica.coche.MueveCoche;
import aima.core.search.framework.Node;

public class HeuristicaCochesBloqueadores implements ToDoubleFunction
<Node<EstadoCoche,MueveCoche>>{



	private double recorridoHorizontal(EstadoCoche estado, Coche objetivo, Pair<Integer,Integer> puerta, boolean izquierda){
		Integer[][] tablero=estado.getTablero();
		double valor=0;
		int i=puerta.getKey();
		if(izquierda){
			for(int j=objetivo.getPos().getValue()-1;j>=0;){//La puerta está a la izquierda
				if(tablero[i][j]==-1){
					valor++;
					j--;
				}
				else{
					Coche analizado=estado.getIdACoche().get(tablero[i][j]);
					valor+= Math.min(Math.abs(i-analizado.getPos().getKey())
							,Math.abs(analizado.getPos().getKey()+analizado.getTam().getKey()-1-i))+1;
					j-=analizado.getTam().getValue();
					valor+=analizado.getTam().getValue();
				}
			}	
		}
		else{
			for(int j=objetivo.getPos().getValue()+objetivo.getTam().getValue();j<puerta.getValue();){//La puerta está a la derecha
				if(tablero[i][j]==-1){
					valor++;
					j++;
				}
				else{
					Coche analizado=estado.getIdACoche().get(tablero[i][j]);
					valor+=Math.min(Math.abs(i-analizado.getPos().getKey())
							,Math.abs(analizado.getPos().getKey()+analizado.getTam().getKey()-1-i))+1;
					j+=analizado.getTam().getValue();
					valor+=analizado.getTam().getValue();
				}
			}	
		}
		return valor;
	}

	private double recorridoVertical(EstadoCoche estado, Coche objetivo, Pair<Integer,Integer> puerta, boolean arriba){
		Integer[][] tablero=estado.getTablero();
		double valor=0;
		int j=puerta.getValue();
		if(arriba){
			for(int i=objetivo.getPos().getKey()-1;i>=0;){//La puerta está arriba
				if(tablero[i][j]==-1){
					valor++;
					i--;
				}
				else{
					Coche analizado=estado.getIdACoche().get(tablero[i][j]);
					valor+=Math.min(Math.abs(j-analizado.getPos().getValue())
							,Math.abs(analizado.getPos().getValue()+analizado.getTam().getValue()-1-j))+1;
					j-=analizado.getTam().getKey();
					valor+=analizado.getTam().getKey();
				}
			}	
		}
		else{
			for(int i=objetivo.getPos().getKey()+objetivo.getTam().getKey();i<puerta.getKey();){//La puerta está abajo
				if(tablero[i][j]==-1){
					valor++;
					i++;
				}
				else{
					Coche analizado=estado.getIdACoche().get(tablero[i][j]);
					valor+=Math.min(Math.abs(j-analizado.getPos().getValue())
							,Math.abs(analizado.getPos().getValue()+analizado.getTam().getValue()-1-j))+1;
					i+=analizado.getTam().getKey();
					valor+=analizado.getTam().getKey();
				}
			}	
		}
		return valor;
	}


	private double getHeur(EstadoCoche estado){
		if(estado.getListaPuertas().size()>1)return 0;
		Pair<Integer,Integer> puerta=estado.getListaPuertas().iterator().next();
		Coche objetivo=estado.getObjetivo();
		if(puerta.getKey()==objetivo.getPos().getKey()){//El coche es horizontal
			
			return recorridoHorizontal(estado,objetivo,puerta,
					objetivo.getPos().getValue()>puerta.getValue());
			
		}
		else{//Es vertical
				
			return recorridoVertical(estado,objetivo,puerta,
					objetivo.getPos().getKey()>puerta.getKey());
				
		}
	}
	
	@Override
	public String toString() {
		return "HeuristicaCochesBloqueadores";
	}


	@Override
	public double applyAsDouble(Node<EstadoCoche, MueveCoche> value) {
		return getHeur(value.getState());
	}
}

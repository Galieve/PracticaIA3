package practica;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aima.core.agent.Action;
import javafx.util.Pair;

public class CocheVertical extends Coche {

	/**
	 * 
	 */
	
	public CocheVertical() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param t
	 * @param p
	 */
	public CocheVertical(Pair<Integer, Integer> t, Pair<Integer, Integer> p) {
		super(t, p);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see practica.Coche#getActions()
	 */
	@Override
	public List<MueveCoche> getActions(EstadoCoche estado) {
		List<MueveCoche> lista=new ArrayList<>();
		boolean abajo=true;
		boolean arriba=true;
		
		for (int ctrl=0;ctrl<tam.getKey() && abajo;ctrl++) {
			abajo=estado.libre(pos.getKey()+ctrl, pos.getValue()+tam.getValue());
		}
		if(abajo)lista.add(new MueveCoche("Abajo", id));
		
		
		for (int ctrl=0;ctrl<tam.getKey() && arriba;ctrl++) {
			arriba=estado.libre(pos.getKey()+ctrl, pos.getValue()-1);
		}
		if(arriba)lista.add(new MueveCoche("Arriba", id));
		return lista;
	}
	
	@Override
	protected boolean abstractPuedeSalir(Set<Pair<Integer, Integer>> lista) {
		//ARRIBA
		boolean salir = true;
		for(int j=pos.getValue(); salir && j< pos.getValue()+tam.getValue(); ++j) {
			if(!lista.contains(new Pair<>(pos.getKey()-1,j))) {
				salir = false;
			}
		}
		if(salir) return true;
		salir = true;
		for(int j=pos.getValue(); salir && j < pos.getValue()+tam.getValue(); ++j) {
			if(!lista.contains(new Pair<>(pos.getKey()+tam.getKey(),j))) {
				salir = false;
			}
		}
		return salir;
	}

}

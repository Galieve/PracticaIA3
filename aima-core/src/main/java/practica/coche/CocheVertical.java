package practica.coche;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;

public class CocheVertical extends Coche {

	/**
	 * 
	 */
	
	public CocheVertical() {}

	/**
	 * @param t
	 * @param p
	 */
	public CocheVertical(Pair<Integer, Integer> t, Pair<Integer, Integer> p, Integer i) {
		super(t, p, i);
	}

	/* (non-Javadoc)
	 * @see practica.Coche#getActions()
	 */
	@Override
	public List<MueveCoche> getActions(EstadoCoche estado) {
		List<MueveCoche> lista=new ArrayList<>();
		boolean abajo=true;
		boolean arriba=true;
		
		for (int ctrl=0;ctrl<tam.getValue() && abajo;ctrl++) {
			abajo=estado.libre(pos.getKey()+tam.getKey(), pos.getValue()+ctrl);
		}
		if(abajo)lista.add(new MueveCoche("Abajo", id));
		
		
		for (int ctrl=0;ctrl<tam.getValue() && arriba;ctrl++) {
			arriba=estado.libre(pos.getKey()-1, pos.getValue()+ctrl);
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

	@Override
	public Coche copy() {
		CocheVertical a=new CocheVertical();
		a.copiaCoche(this);
		return a;
	}
	
}

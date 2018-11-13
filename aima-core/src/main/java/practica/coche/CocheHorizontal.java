/**
 * 
 */
package practica.coche;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;

/**
 * @author usuario_local
 *
 */
public class CocheHorizontal extends Coche {

	/**
	 * 
	 */
	public CocheHorizontal() {	}

	/**
	 * @param t
	 * @param p
	 */
	public CocheHorizontal(Pair<Integer, Integer> t, Pair<Integer, Integer> p, Integer i) {
		super(t, p, i);
	}

	/* (non-Javadoc)
	 * @see practica.Coche#getActions()
	 */
	@Override
	public List<MueveCoche> getActions(EstadoCoche estado) {
		List<MueveCoche> lista=new ArrayList<MueveCoche>();
		boolean izquierda=true;
		boolean derecha=true;
		
		for (int ctrl=0;ctrl<tam.getKey() && izquierda;ctrl++) {
			izquierda=estado.libre(pos.getKey()+ctrl, pos.getValue()-1);
		}
		if(izquierda)lista.add(new MueveCoche("Izquierda", id));
		
		
		for (int ctrl=0;ctrl<tam.getKey() && derecha;ctrl++) {
			derecha=estado.libre(pos.getKey()+ctrl, pos.getValue()+tam.getValue());
		}
		if(derecha)lista.add(new MueveCoche("Derecha", id));
		return lista;
	}

	@Override
	protected boolean abstractPuedeSalir(Set<Pair<Integer, Integer>> lista) {
		//IZQUIERDA
		boolean salir = true;
		for(int i=pos.getKey(); salir && i< pos.getKey()+tam.getKey(); ++i) {
			if(!lista.contains(new Pair<>(i,pos.getValue()-1))) {
				salir = false;
			}
		}
		if(salir) return true;
		salir = true;
		for(int i=pos.getKey(); salir && i< pos.getKey()+tam.getKey(); ++i) {
			if(!lista.contains(new Pair<>(i,pos.getValue()+tam.getValue()))) {
				salir = false;
			}
		}
		return salir;
	}

	@Override
	public Coche copy() {
		CocheHorizontal a=new CocheHorizontal();
		a.copiaCoche(this);
		return a;
	}

}

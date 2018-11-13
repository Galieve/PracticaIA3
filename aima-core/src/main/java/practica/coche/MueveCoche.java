/**
 * 
 */
package practica.coche;

import javafx.util.Pair;
import aima.core.agent.impl.DynamicAction;

/**
 * @author usuario_local
 *
 */
public class MueveCoche extends DynamicAction {

	/**
	 * 
	 */
	
	private static final String accion="Acci�n";
	
	
	
	public MueveCoche(String s, Integer i) {
		super("MoveCoche");
		this.setAttribute(accion, new Pair<String, Integer>(s,i));
	}

	public String getSentido() {
		@SuppressWarnings("unchecked")
		Pair<String, Integer> p = (Pair<String, Integer>) this.getAttribute(accion);
		return p.getKey();
	}
	public Integer getMatricula() {
		@SuppressWarnings("unchecked")
		Pair<String, Integer> p = (Pair<String, Integer>) this.getAttribute(accion);
		return p.getValue();
	}
	
	public String toString() {
		return accion + " : " + this.getAttribute(accion).toString();
	}
	
	/* (non-Javadoc)
	 * @see aima.core.agent.Action#isNoOp()
	 */

}

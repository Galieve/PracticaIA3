/**
 * 
 */
package practica.coche;

import java.util.List;
import java.util.Set;

import javafx.util.Pair;

/**
 * @author usuario_local
 *
 */
public abstract class Coche {

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (objetivo ? 1231 : 1237);
		result = prime * result + ((pos == null) ? 0 : pos.hashCode());
		result = prime * result + ((tam == null) ? 0 : tam.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coche other = (Coche) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (objetivo != other.objetivo)
			return false;
		if (pos == null) {
			if (other.pos != null)
				return false;
		} else if (!pos.equals(other.pos))
			return false;
		if (tam == null) {
			if (other.tam != null)
				return false;
		} else if (!tam.equals(other.tam))
			return false;
		return true;
	}

	protected Pair<Integer,Integer> tam; //Filas, Columnas
	protected Pair<Integer,Integer> pos;
	
	protected Integer id = null;
	
	protected boolean objetivo=false;
	
	public Coche(Coche c) {
		this.id=c.id;
		this.pos=new Pair<Integer,Integer>(c.getPos().getKey(),c.getPos().getValue());
		this.tam=new Pair<Integer,Integer>(c.getTam().getKey(),c.getTam().getValue());
		objetivo=c.objetivo;
	}
	
	public abstract Coche copy();
	
	protected void copiaCoche(Coche c){
		
		this.id=c.id;
		this.tam=new Pair<Integer,Integer>(c.tam.getKey(),c.tam.getValue());
		this.pos=new Pair<Integer,Integer>(c.pos.getKey(),c.pos.getValue());
		this.objetivo=c.objetivo;
	}

	public boolean isObjetivo() {
		return objetivo;
	}
	public void setObjetivo(boolean objetivo) {
		this.objetivo = objetivo;
	}
	public Pair<Integer, Integer> getTam() {
		return tam;
	}
	public void setTam(Pair<Integer, Integer> tam) {
		this.tam = tam;
	}
	public Pair<Integer, Integer> getPos() {
		return pos;
	}
	public void setPos(Pair<Integer, Integer> pos) {
		this.pos = pos;
	}

	public boolean puedeSalir(Set<Pair<Integer, Integer>> lista) {
		if(!objetivo) return false;
		else return abstractPuedeSalir(lista);	
	}
	
	protected abstract boolean abstractPuedeSalir(Set<Pair<Integer, Integer>> lista);
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Coche() {
		id=-1;
		tam = new Pair<>(1,1);
		pos = new Pair<>(0,0);
	}
	public Coche(Pair<Integer,Integer>t, Pair<Integer,Integer> p, Integer i) {
		if(t.getKey() < 0 || t.getValue() < 0) 
			throw new IllegalArgumentException("No se puede crear un coche de tamaño negativo");
		tam = t;
		pos = p;
		id=i;
	}

	public Coche(Pair<Integer,Integer>t, Pair<Integer,Integer> p, boolean rojo) {
		if(t.getKey() < 0 || t.getValue() < 0) 
			throw new IllegalArgumentException("No se puede crear un coche de tamaño negativo");
		tam = t;
		pos = p;
		objetivo=rojo;
	}
	
	public abstract List<MueveCoche> getActions(EstadoCoche estado);
	
}

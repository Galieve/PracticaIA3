package practica.coche;

import java.util.*;

import javafx.util.Pair;


public class EstadoCoche {

	//private List<Coche> listaCoches;

	private HashMap<Integer, Coche> idACoche;
	
	private Integer cocheObjetivo;
	
	private Integer tablero[][];
	
	private Set<Pair<Integer, Integer>> listaPuertas;

	public int getFilas(){
		return tablero.length;
	}
	
	
	public int getColumnas(){
		return tablero[0].length;	
	}
	
	public Coche getObjetivo(){
		return idACoche.get(cocheObjetivo);
	}
	
	public Set<Pair<Integer,Integer>> getListaPuertas(){
		return listaPuertas;
	}
	
	private void rellenarTableroVacio() {
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero[0].length;j++) {
				tablero[i][j]=-1;
			}
		}
	}
    
    private void añadir(Coche...c)throws IllegalArgumentException{
    	boolean encontrado=false;
    	for(Coche cprima:c){
    		if(cprima.isObjetivo()&&encontrado){
    			throw new IllegalArgumentException("Se ha puesto más de un coche objetivo");
    		}
    		else {
    			encontrado=cprima.isObjetivo() || encontrado;
    			idACoche.put(cprima.getId(), cprima);
    			if(cprima.isObjetivo())cocheObjetivo=cprima.getId();
    		}
    	}
    	if(!encontrado){
			throw new IllegalArgumentException("No se ha añadido ningún coche objetivo");
    	}
    }
	
	private void rellenarTableroConCoches() {
		for(Integer i : idACoche.keySet()) {
			ponerCoche(idACoche.get(i));
		}
	}
	
	private void ponerCoche(Coche coche) {
		for(Integer i=coche.getPos().getKey();i<coche.getPos().getKey()+coche.getTam().getKey();i++) {
			for(Integer j=coche.getPos().getValue();j<coche.getPos().getValue()+coche.getTam().getValue();j++) {
				if(!libre(i,j)) throw new IllegalArgumentException("Coches" + tablero[i][j] +
						" y "+ coche.getId() + 
						" colisionando en la casilla:"
						+ "["+ i+ "]["+j+"]");			
				tablero[i][j]=coche.getId();
			}
		}
	}
	
	//Posi = posicion adyacente a la puerta
	public boolean puertaAlcanzada(Pair<Integer,Integer> posi) {
		return tablero[posi.getKey()][posi.getValue()] != -1 &&
				idACoche.get(tablero[posi.getKey()][posi.getValue()]).isObjetivo();
		}
	
	//NOTA: NO USAR PORQUE NO HAY PUERTAS 
	public EstadoCoche() {
		tablero=new Integer[10][10];
		rellenarTableroVacio();
		listaPuertas = new HashSet<>();
		idACoche = new HashMap<>();
	}
	public EstadoCoche(Integer dimx, Integer dimy, Set<Pair<Integer, Integer>> puertas, Coche...coches) {
		tablero=new Integer[dimx][dimy];
		
		rellenarTableroVacio();
		listaPuertas = new HashSet<>();
		idACoche=new HashMap<Integer,Coche>();
		this.añadir(coches);
		
		rellenarTableroConCoches();
		for(Pair<Integer, Integer> puerta: puertas) {
			addValid(puerta);
		}
		
	}

	public EstadoCoche(EstadoCoche copia) {
		idACoche=new HashMap<>();
		for(Integer i : copia.idACoche.keySet()) {
			idACoche.put(i, copia.idACoche.get(i).copy());
		}
		//listaCoches = new ArrayList<>(copia.listaCoches);
		cocheObjetivo=copia.cocheObjetivo;
		tablero = new Integer[copia.tablero.length][copia.tablero[0].length];
		listaPuertas = new HashSet<>(copia.listaPuertas);
		for(int ctrl=0;ctrl<tablero.length;ctrl++) {
			System.arraycopy(copia.tablero[ctrl], 0, tablero[ctrl], 0, tablero[0].length);
		}
	}

	public boolean libre(Integer x, Integer y) {
		return x>=0 && y>=0 && x<tablero.length && y<tablero[0].length && 
				tablero[x][y].equals(-1);
	}

	public List<MueveCoche> generarAcciones(){
		List<MueveCoche> listaAcciones=new ArrayList<>();
		for(Coche c: idACoche.values()) {
			listaAcciones.addAll(c.getActions(this));
		}
		return listaAcciones;
	}

	public void executeAction(MueveCoche accion) {
		Pair<Integer,Integer> p = idACoche.get(accion.getMatricula()).getPos();
		idACoche.get(accion.getMatricula()).setPos(mover(accion.getSentido(), p));
		actualizar(accion.getSentido(),idACoche.get(accion.getMatricula()));
		/*for(Integer[] c: tablero) {
			for(Integer d: c) {
				if(d.equals(-1))System.out.print(d);
				else System.out.print(" "+d);
			}
			System.out.print("\n");
		}
		System.out.print("\n-----\n");*/
	}

	private void addValid(Pair<Integer, Integer> puerta) {
		if(puerta.getKey() == -1 && puerta.getValue() >= 0 && puerta.getValue() < tablero[0].length) {
			listaPuertas.add(puerta);
		}
		else if(puerta.getKey() == tablero.length && puerta.getValue() >= 0 && puerta.getValue() < tablero[0].length) {
			listaPuertas.add(puerta);
		}
		else if(puerta.getValue() == -1 && puerta.getKey() >= 0 && puerta.getKey() < tablero.length) {
			listaPuertas.add(puerta);
		}
		else if(puerta.getValue() == tablero[0].length && puerta.getKey() >= 0 && puerta.getKey() < tablero.length) {
			listaPuertas.add(puerta);
		}
		else {
			throw new IllegalArgumentException("No se puede colocar una puerta en " + 
		"["+puerta.getKey() + "]["+ puerta.getValue() +"]");
		}
	}

	private Pair<Integer,Integer> mover(String mov, Pair<Integer,Integer> pos) {
		switch (mov) {
		case "Izquierda":return new Pair<Integer,Integer>(pos.getKey(),pos.getValue()-1);
		case "Derecha":return new Pair<Integer,Integer>(pos.getKey(),pos.getValue()+1);
		case "Arriba":return new Pair<Integer,Integer>(pos.getKey()-1,pos.getValue());
		case "Abajo":return new Pair<Integer,Integer>(pos.getKey()+1,pos.getValue());
		default: 
			return null;
			
		}

	}

	//Coche Actualizado
	private void actualizar(String mov, Coche c) {
		switch (mov) {
		case "Izquierda":
			for(int i=c.getPos().getKey();
					i<c.getPos().getKey()+c.getTam().getKey(); ++i) {
				tablero[i][c.getPos().getValue()]=c.getId();
				tablero[i][c.getPos().getValue()+c.getTam().getValue()]=-1;
			}
			break;
		case "Derecha":
			for(int i=c.getPos().getKey();
					i<c.getPos().getKey()+c.getTam().getKey(); ++i) {
				tablero[i][c.getPos().getValue()-1]=-1;
				tablero[i][c.getPos().getValue()+c.getTam().getValue()-1]=c.getId();
			}
			break;
		case "Arriba":
			for(int i=c.getPos().getValue();
					i<c.getPos().getValue()+c.getTam().getValue(); ++i) {
				tablero[c.getPos().getKey()][i]=c.getId();
				tablero[c.getPos().getKey()+c.getTam().getKey()][i]=-1;
			}
			break;
		case "Abajo":
			for(int i=c.getPos().getValue();
					i<c.getPos().getValue()+c.getTam().getValue(); ++i) {
				tablero[c.getPos().getKey()-1][i]=-1;
				tablero[c.getPos().getKey()+c.getTam().getKey()-1][i]=c.getId();
			}
			break;
		default: break;
		}
	}	
	
	public boolean isGoal() {
		for(Coche coche: idACoche.values()) {
			if(coche.puedeSalir(listaPuertas)) return true;
		}
		return false;
	}


	@Override
	public int hashCode() {
		return java.util.Arrays.deepHashCode(tablero);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoCoche other = (EstadoCoche) obj;
		if (idACoche == null) {
			if (other.idACoche != null)
				return false;
		} else if (!idACoche.equals(other.idACoche))
			return false;
		if (listaPuertas == null) {
			if (other.listaPuertas != null)
				return false;
		} else if (!listaPuertas.equals(other.listaPuertas))
			return false;
		if (!Arrays.deepEquals(tablero, other.tablero))
			return false;
		return true;
	}


	public Integer[][] getTablero() {
		return tablero;
	}


	public HashMap<Integer, Coche> getIdACoche() {
		return idACoche;
	}
	
}

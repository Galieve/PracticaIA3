package practica;

import java.util.*;

import javafx.util.Pair;


public class EstadoCoche {

	private List<Coche> listaCoches;

	private Integer tablero[][];

	private Set<Pair<Integer, Integer>> listaPuertas;

	
	
	private void rellenarTableroVacio() {
		for(int i=0;i<tablero.length;i++) {
			for(int j=0;j<tablero[0].length;j++) {
				tablero[i][j]=-1;
			}
		}
	}
	
	private void rellenarTableroConCoches() {
		for(Coche coche : listaCoches) {
			ponerCoche(coche);
		}
	}
	
	private void ponerCoche(Coche coche) {
		for(Integer i=coche.getPos().getKey();i<coche.getPos().getKey()+coche.getTam().getKey();i++) {
			for(Integer j=coche.getPos().getValue();j<coche.getPos().getValue()+coche.getTam().getValue();j++) {
				//TODO Excepcion
				if(!libre(i,j)) return;			
				tablero[i][j]=coche.getId();
			}
		}
	}
	
	//Posi = posicion adyacente a la puerta
	public boolean puertaAlcanzada(Pair<Integer,Integer> posi) {
		return tablero[posi.getKey()][posi.getValue()] != -1 && 
				listaCoches.get(tablero[posi.getKey()][posi.getValue()]).isObjetivo();
	}
	
	//NOTA: NO USAR PORQUE NO HAY PUERTAS 
	public EstadoCoche() {
		tablero=new Integer[10][10];
		rellenarTableroVacio();
		listaPuertas = new HashSet<>();
		listaCoches = new ArrayList<>();
	}
	public EstadoCoche(Integer dimx, Integer dimy, Set<Pair<Integer, Integer>> puertas, List<Coche> coches) {
		tablero=new Integer[dimx][dimy];
		
		rellenarTableroVacio();
		listaPuertas = new HashSet<>();
		listaCoches = coches;
		
		for(int ctrl=0;ctrl<listaCoches.size();ctrl++) {
			//TODO tudú (revisar la edición de los coches) :(
			listaCoches.get(ctrl).setId(ctrl);
		}
		
		rellenarTableroConCoches();
		for(Pair<Integer, Integer> puerta: puertas) {
			addValid(puerta);
		}
		
	}

	public EstadoCoche(EstadoCoche copia) {
		listaCoches=new ArrayList<>();
		for(Coche coche : copia.listaCoches) {
			//TODO NO SE COPIA BIEN
			listaCoches.add(new Coche(coche));
		}
		//listaCoches = new ArrayList<>(copia.listaCoches);
		tablero = new Integer[copia.tablero.length][copia.tablero[0].length];
		listaPuertas = new HashSet<>(copia.listaPuertas);
		for(int ctrl=0;ctrl<tablero.length;ctrl++) {
			System.arraycopy(copia.tablero[ctrl], 0, tablero[ctrl], 0, tablero[0].length);
		}
	}

	public boolean libre(Integer x, Integer y) {
		return x>=0 && y>=0 && x<tablero.length && y<tablero[0].length && 
				tablero[x][y]==-1;
	}

	public List<MueveCoche> generarAcciones(){
		List<MueveCoche> listaAcciones=new ArrayList<>();
		for(Coche c: listaCoches) {
			listaAcciones.addAll(c.getActions(this));
		}
		return listaAcciones;
	}

	public void executeAction(MueveCoche accion) {
		Pair<Integer,Integer> p = listaCoches.get(accion.getMatricula()).getPos();
		listaCoches.get(accion.getMatricula()).setPos(mover(accion.getSentido(), p));
		actualizar(accion.getSentido(),listaCoches.get(accion.getMatricula()));
		for(Integer[] c: tablero) {
			for(Integer d: c) {
				System.out.print(d);
			}
			System.out.print("\n");
		}
		System.out.print("\n-----\n");
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
			//TODO lanzar excepcion
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
		for(Coche coche: listaCoches) {
			if(coche.puedeSalir(listaPuertas)) return true;
		}
		return false;
	}

}

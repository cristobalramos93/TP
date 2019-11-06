//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;

import java.util.ArrayList;

import java.util.List;

public class PhysicsSimulator {

	private double tiempoReal = 0.0, tiempoRealPasos;
	GravityLaws gravitylaw;
	List<Body> bodylist = new ArrayList<>();
	List<SimulatorObserver> observadorlist = new ArrayList<>();


	public PhysicsSimulator(double tiempoRealPasos, GravityLaws gravityLaw) throws Exception { //revisar
		if(tiempoRealPasos == 0.0) throw IllegalArgumentException();
		else this.tiempoRealPasos = tiempoRealPasos;
		if(gravityLaw == null) throw IllegalArgumentException();
		else this.gravitylaw = gravityLaw;
	}

	public void advance() {
		gravitylaw.apply(bodylist);
		for (int i = 0; i < bodylist.size(); i++) {
			bodylist.get(i).move(tiempoRealPasos);
		}
		tiempoReal += tiempoRealPasos;
		
		for(int i = 0; i < observadorlist.size(); i++) {
			observadorlist.get(i).onAdvance(bodylist, tiempoReal);
		}
	}

	public void addBody(Body b) throws Exception {
		for (int i = 0; i < bodylist.size(); i++) {
			if(bodylist.get(i).getId().equals(b.getId())) {
				throw IllegalArgumentException();
			}
		}
		bodylist.add(b);
		
		for(int i = 0; i < observadorlist.size(); i++) {
			observadorlist.get(i).onBodyAdded(bodylist, b);
		}
	}

	public String toString() {
		return "{ \"time\": " + tiempoReal +", \"bodies\": " + bodylist.toString() +"}";	
	}

	private Exception IllegalArgumentException() {
		System.out.println("Illegal Argument when introducing body to list");		
		return null;
	}


	public void reset() {//comprobar
		bodylist  = new ArrayList<>();
		tiempoReal = 0.0;
		for(int i = 0; i < observadorlist.size(); i++) {
			observadorlist.get(i).onReset(bodylist, tiempoReal, tiempoRealPasos, gravitylaw.toString());
		}
	}

	public void setDeltaTime(double dt) {
		try {
			tiempoRealPasos = dt;
			for(int i = 0; i < observadorlist.size(); i++) {
				observadorlist.get(i).onDeltaTimeChanged(tiempoRealPasos);
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("dt invalido en PhysicsSimulator");
		}
	}



	public void setGravityLaws(GravityLaws gravityLaws) {
		try {
			this.gravitylaw = gravityLaws;
			for(int i = 0; i < observadorlist.size(); i++) {
				observadorlist.get(i).onGravityLawChanged(gravitylaw.toString());
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println("Valor nulo de gravityLaws en PhysicSimulator");
		}
	}


	public void addObserver(SimulatorObserver o) {
		if(!observadorlist.contains(o)) {//comprobar el contains
			observadorlist.add(o);
			o.onRegister(bodylist, tiempoReal, tiempoRealPasos, gravitylaw.toString());
		}
		else System.out.println("Este observador ya existe (PhysicSimulator)");


	}







}

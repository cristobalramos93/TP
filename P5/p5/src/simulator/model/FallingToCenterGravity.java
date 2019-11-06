//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class FallingToCenterGravity implements GravityLaws {
	
	
	public FallingToCenterGravity() {
		
	}
	
	final double g = 9.81;
	Vector aux = new Vector(20); //vector nuevo a 0s
	
	@Override
	public void apply(List<Body> bodies) {
		
		for(int i = 0; i < bodies.size(); i++) {
			aux = bodies.get(i).getPosition().direction();
			bodies.get(i).setAcceleration(aux.scale(-g));
		}
		
	}
	
	public String toString() {
		String t;
		t = "Falling To Center Gravity";
		return t;
	}
}

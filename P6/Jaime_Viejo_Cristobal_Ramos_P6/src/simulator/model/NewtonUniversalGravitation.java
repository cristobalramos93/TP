//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;

import java.util.List;

import simulator.misc.Vector;

public class NewtonUniversalGravitation implements GravityLaws {




	public NewtonUniversalGravitation() {
	}
	final double G = 6.67E-11;
	@Override
	public void apply(List<Body> bodies) {
		for(int i = 0; i < bodies.size(); i++) {
			Vector sum = new Vector(bodies.get(i).getAcceleration().dim());

			Vector aux = new Vector(20); //vector nuevo a 0s

			for(int j = 0; j < bodies.size(); j++) {
				if(bodies.get(i).masa <= 0 ) {
					Vector ma = new Vector (bodies.get(i).getAcceleration().dim());
					bodies.get(i).setAcceleration(ma);
					Vector man = new Vector (bodies.get(i).getVelocity().dim());
					bodies.get(i).setVelocity(man);
				}
				else if(bodies.get(j).getId()!=bodies.get(i).getId()) { 
						double m;
						double position;
						m = bodies.get(i).getMass() * bodies.get(j).getMass(); //Mi * Mj
						m = G * m;
						position = bodies.get(j).getPosition().distanceTo(bodies.get(i).getPosition()); //  | Pj-Pi |
						position = position * position;
						m = m/position; // f i,j
						aux = bodies.get(j).getPosition().minus(bodies.get(i).getPosition());
						aux = aux.direction();
						sum = sum.plus(aux.scale(m)); // sumatorio de d i,j * f i,j
				}
						aux = sum.scale(1/bodies.get(i).getMass()); // Fi = 1/masa i
						bodies.get(i).setAcceleration(aux);

					
				
			}
		}
	}
	
	
	public String toString() {
		String t;
		t = "Newtons Universal Gravitation Law";
		return t;
	}
	
}

//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;

import java.util.List;

public class NoGravity implements GravityLaws {

	public NoGravity() {

	}

	@Override
	public void apply(List<Body> bodies) {

		//esta vacio para que los cuerpos se muevan con una aceleracion fija

	}


	public String toString() {
		String t;
		t = "Simplemente no hace nada";
		return t;
	}
	
}

//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;
import simulator.misc.Vector;

public class MassLossingBody extends Body {

	
	
	private double lossFactor;//factor de perdida de masa
	private double lossFrecuency;//intervalo de tiempo despues del cual es objecto pierde masa
	private double c;


	
	public MassLossingBody(String id, double masa, Vector v, Vector a, Vector p, double lossFactor, double lossFrecuency, double c) {
		super(id, masa, v, a, p);
		this.lossFactor = lossFactor;
		this.lossFrecuency = lossFrecuency;
		this.c = c;
	}






	void move(double t) {
		Vector aux = new Vector(20);
		Vector aux2 = new Vector(20);
		aux = v.scale(t);
		aux = p.plus(aux);
		aux2 = a.scale(0.5);
		aux2 = aux2.scale(Math.pow(t,2));
		p = aux.plus(aux2);
		aux = a.scale(t);
		v = v.plus(aux);
		c = c + t;
		if(c >= this.lossFrecuency) { 
			this.lossFactor = this.masa * (1- this.lossFactor);
			c = 0.0;
		}
	}
	
	
	
	
	
	
}

//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.model;

import simulator.misc.Vector;


public class Body {
	
	protected String id;
	protected double masa;
	protected Vector v; 
	protected Vector a;
	protected Vector p;
	
	
	public Body(String id, double masa, Vector v, Vector a, Vector p) {
		this.id = id;
		this.masa = masa;
		this.v = v;
		this.a = a;
		this.p = p;
	}

	public String getId() {
		return this.id;
	}
	
	public Vector getVelocity() {
		return this.v;
	}
	
	public Vector getAcceleration() {
		return this.a;
	}
	
	public Vector getPosition() {
		return this.p;
	}
	
	public double getMass() {
		return this.masa;
	}
	
	void setVelocity(Vector v) {
		this.v = v;
	}
	
	void setAcceleration(Vector a) {
		this.a = a;
	}
	
	void setPosition(Vector p) {
		this.p = p;
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
	}
	
	
	public String toString() {
		return " {  \"id\": \""+ this.id + "\", \"mass\": "+ this.masa +", \"pos\": "+ this.p +", \"vel\": "+ this.v +", \"acc\": "+ this.a +" }";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

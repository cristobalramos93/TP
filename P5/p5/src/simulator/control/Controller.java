//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.control;

import java.io.InputStream;

import java.io.OutputStream;
import java.io.PrintStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {
	PhysicsSimulator simulator;
	Factory<Body> body;
	Factory<GravityLaws> gravitylaws;
	public Controller(PhysicsSimulator simulator, Factory<Body> body,Factory<GravityLaws> gravitylaws) {
		this.simulator = simulator;
		this.body = body;
		this.gravitylaws = gravitylaws;
	}
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt = new JSONObject(new JSONTokener(in));
		JSONArray bodies = jsonInupt.getJSONArray("bodies");
		for (int i = 0; i < bodies.length(); i ++) {
			
				try {
					simulator.addBody(body.createInstance(bodies.getJSONObject(i)));
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	
	public void run(int n, OutputStream out) {//pinta como en el out
		int cont = 0;
		PrintStream print = new PrintStream(out);
		print.println("{");
		print.println("\"states\": [");
		print.print(simulator.toString());
		if(n != cont)print.println(","); //esto es para que no ponga una coma al final de la ultima linea
		else print.println();
		for (int i = 0; i < n; i++) {
			cont++;
			simulator.advance();
			print.print(simulator.toString());
			if(n != cont)print.println(","); //esto es para que no ponga una coma al final de la ultima linea
			else print.println();
		}
		print.println(" ]");
		print.println(" }");

	}
	
	public void reset() {
		simulator.reset();
	}
	
	public void setDeltaTime(double dt) {
		simulator.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		simulator.addObserver(o);
	}
	
	public Factory<GravityLaws> getGravityLawsFactory(){
		return gravitylaws;
	}
	
	public void setGravityLaws(JSONObject info) {
		simulator.setGravityLaws(gravitylaws.createInstance(info));
	}
	
	public void run(int n) {
		for (int i = 0; i < n; i++) {
			simulator.advance();
		}
	}
	
	
	
}

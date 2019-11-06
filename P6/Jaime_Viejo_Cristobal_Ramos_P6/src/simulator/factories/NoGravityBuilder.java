//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;


public class NoGravityBuilder<T> extends Builder<GravityLaws> {


	
	public NoGravityBuilder(String typeTag, String desc) {
		super("ng", "No gravity");
	}
	
	public NoGravityBuilder() {
		super("ng", "No gravity");

	}

	public NoGravity createTheInstance(JSONObject info) { //exception
		if(info.getString("type") == typeTag && info.has("data")) {
			return new NoGravity();

		}
		else return null;
	}
}



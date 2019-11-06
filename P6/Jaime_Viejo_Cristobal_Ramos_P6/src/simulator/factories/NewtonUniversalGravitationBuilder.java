//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder<T> extends Builder<GravityLaws> {
	
	

	public NewtonUniversalGravitationBuilder(String typeTag, String desc) {
		super("nlug", "Newton’s law of universal gravitation");
	}
	
	public NewtonUniversalGravitationBuilder() {
		super("nlug", "Newton’s law of universal gravitation");

	}

	public NewtonUniversalGravitation createTheInstance(JSONObject info) { //exception
		if(info.getString("type") == typeTag && info.has("data")) {
			return  new NewtonUniversalGravitation();

		}
		else return null;
	}
}



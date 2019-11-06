//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;





public class FallingToCenterGravityBuilder<T> extends Builder<GravityLaws> {

	
	
	public FallingToCenterGravityBuilder(String typeTag, String desc) {
		super("ftcg", "Falling to center gravity");
	}
	
	public FallingToCenterGravityBuilder() {
		super("ftcg", "Falling to center gravity");

	}


	@Override
	protected GravityLaws createTheInstance(JSONObject info) {
		if(info.getString("type") == typeTag && info.has("data")) {
			return  new FallingToCenterGravity();

		}
		else return null;
	}


}




//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;


import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder<T> extends Builder<Body> {
	
	
	
	public BasicBodyBuilder(String typeTag, String desc) {
		super("basic", "Basic body");
	}
	
	public BasicBodyBuilder() {
		super("basic", "Basic body");

	}

	double mass;
	Vector a = null;
	Vector p = null;
	Vector v = null;
	String id;
	JSONArray jaux;


	@Override
	protected Body createTheInstance(JSONObject info) {
		if(info.getString("type").equalsIgnoreCase(typeTag) && info.has("data")) {

			if(info.getJSONObject("data").has("id")) {
				try {
					//id
					id = info.getJSONObject("data").getString("id");
					//pos
					jaux = info.getJSONObject("data").getJSONArray("pos");
					double[] posicion = new double[jaux.length()];
					posicion = jsonArrayTodoubleArray(jaux);
					p = new Vector(posicion);
					//velocidad
					jaux = info.getJSONObject("data").getJSONArray("vel");
					double[] velocidad = new double[jaux.length()];
					velocidad = jsonArrayTodoubleArray(jaux);
					v = new Vector(velocidad);
					a = new Vector(v.dim());
					//masa
					mass = info.getJSONObject("data").getDouble("mass");
					return new Body(id,mass,v,a,p);
				}
				catch(IllegalArgumentException e) {
					System.out.println("Fallo en el data");
					return null;
				}
			}

			else return null;


		}
		else return null;
	}
	
	public JSONObject createData() {
		JSONObject json = new JSONObject();
		json.put("id", "el id");
		json.put("pos", "la posicion");
		json.put("vel", "la velocidad");
		json.put("mass", "la masa");
		return json;
	}

}

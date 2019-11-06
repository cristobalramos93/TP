//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;


import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder<T> extends Builder<Body> {
	
	
	
	
	
	public MassLosingBodyBuilder(String typeTag, String desc) {
		super("mlb", "Mass losing Body");
	}
	
	public MassLosingBodyBuilder() {
		super("mlb", "Mass losing Body");
		
	}

	double masa;
	double lossfreq;
	double lossfactor;
	Vector a = null;
	Vector p = null;
	Vector v = null;
	String id;
	JSONArray jaux;


	@Override
	protected Body createTheInstance(JSONObject info) {
		if(info.getString("type").equalsIgnoreCase(typeTag)&& info.has("data")) {
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
				masa = info.getJSONObject("data").getDouble("mass");
				lossfreq = info.getJSONObject("data").getDouble("freq");
				lossfactor = info.getJSONObject("data").getDouble("factor");
				return  new MassLossingBody(id,masa,v,a,p,this.lossfactor,this.lossfreq,0.0);	
				}
				catch(IllegalArgumentException e) {
					System.out.println("Fallo en el data");
					return null;
				}
			}
			return null;
		}
		return null;
	}
	
	public JSONObject createData() {
		JSONObject json = new JSONObject();
		json.put("id", "el id");
		json.put("pos", "la posicion");
		json.put("vel", "la velocidad");
		json.put("mass", "la masa");
		json.put("freq", "la frecuencia");
		json.put("factor", "el factor");
		return json;
	}
}


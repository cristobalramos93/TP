//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

public abstract class Builder<T> {

	protected String typeTag;
	protected String desc;
	
	
	
	public Builder() {

	}
	
	public Builder(String typeTag, String desc) {
		this.typeTag = typeTag;
		this.desc = desc;
	}

	public T createInstance(JSONObject info) {
		T aux = null;
		if(info.has("type") && info.has("data")) {
			aux = createTheInstance(info);
		}
		return aux;
	}
	
	

	protected abstract T createTheInstance(JSONObject info);

	public JSONObject getBuilderInfo() {//FALTA
		JSONObject json = new JSONObject();
		json.put("type", typeTag);
		json.put("data", createData());
		json.put("desc", desc);
		return json;
	}
	
	public JSONObject createData() {
		return new JSONObject();
	}
	
	
	
	public double [] jsonArrayTodoubleArray(JSONArray a) {
		double[] v = new double[a.length()]; 
		for(int i = 0; i < a.length(); i++) {
			v[i] = a.getDouble(i);//comprobar
		}
		return v;
		
	}
	
}

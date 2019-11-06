//JAIME VIEJO MARTINEZ
//CRISTOBAL RAMOS LAINA
package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


public class BuilderBasedFactory<T> implements Factory<T> {
	static String a;
	static String b;
	private List<Builder<T>> list;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		list = new ArrayList<>(builders);
	}

	@Override
	public T createInstance(JSONObject info) {
		T t = null;

		try {
		boolean encontrado = false;
		int n = 0;
		while(n < list.size() && !encontrado){
				t = list.get(n).createInstance(info);
				n++;
				if(t != null){
					encontrado = true;
				}	
				

		}
		}
		catch(IllegalArgumentException e) {
			System.out.println("Info incorrecto");
		}
		return t;

	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> listaJson = new ArrayList<>();
		for(int i = 0; i < list.size(); i++){
			listaJson.add(list.get(i).getBuilderInfo());
		}
		return listaJson;
	}
}



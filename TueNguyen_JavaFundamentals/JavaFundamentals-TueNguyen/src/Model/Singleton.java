package Model;

import java.util.HashSet;
import java.util.Set;

public class Singleton {
	Set<Model> models = new HashSet<Model>();
	private static Singleton singleton;

	private Singleton(){
		
	}
	/*
	 * get Singleton object
	 * */
	public static Singleton getSingleton() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	/*
	 * add new model to list model
	 * @param model: model added to list
	 * @return boolean: result add successful or not
	 * */
	public boolean addModel(Model model) {
		if (models.add(model)) {
			return true;
		}
		else {
			return false;
		}
	}
}

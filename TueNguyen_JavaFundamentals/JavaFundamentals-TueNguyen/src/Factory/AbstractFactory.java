package Factory;

import Model.Model;

public abstract class AbstractFactory {
	protected Model input;

	public AbstractFactory(Model input) {
		super();
		this.input = input;
	}
	
	/*
	 * Get a formatted model corresponding Viet Nam or American
	 * */
	public abstract Model getModel();
}

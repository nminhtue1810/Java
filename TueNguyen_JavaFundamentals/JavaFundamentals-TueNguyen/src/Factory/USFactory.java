package Factory;

import Model.Model;

public class USFactory extends AbstractFactory {
	@Override
	public
	Model getModel() {
		String newID = "SPN - " + input.getID() + " - US format";
		String newAddress = "Address: " + input.getAddress() + " - US format";
		Model formattedModel = new Model(input.getName(), newID, input.getPhoneNumber(), newAddress);
		return formattedModel;
	}

	public USFactory(Model input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
}

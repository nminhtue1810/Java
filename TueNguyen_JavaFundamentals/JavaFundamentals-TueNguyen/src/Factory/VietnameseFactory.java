package Factory;

import Model.Model;

public class VietnameseFactory extends AbstractFactory {
	@Override
	public
	Model getModel() {
		String ID = "CMND - " + input.getID() + " - Vietnamese format";
		String address = "Dia chi: " + input.getAddress() + " - Vietnamese format";
		Model formattedModel = new Model(input.getName(), ID, input.getPhoneNumber(), address);
		return formattedModel;
	}

	public VietnameseFactory(Model input) {
		super(input);
		// TODO Auto-generated constructor stub
	}
	

}

package Factory;

import Model.Model;

public class InputFactory{
	public static AbstractFactory getFactoryObject(String format,Model input) throws Exception {
		if (format.equalsIgnoreCase("Viet Nam")) {
			return new VietnameseFactory(input);			
		} else if (format.equalsIgnoreCase("US")) {
			return new USFactory(input);			
		} else {
            throw new Exception("Format is not found");
        }		
	}
}

package Model;

public class Model {
	private String name;
	private String ID;
	private String phoneNumber;
	private String address;
	
	public String getName() {
		return name;
	}

	public String getID() {
		return ID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}
	
	public Model(String name, String ID, String phoneNumber, String address) {
		super();
		this.name = name;
		this.ID = ID;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}	
	
	public void setName(String name) {
		this.name = name;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public void setPhoneNumber(String phone) {
		this.phoneNumber = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}
	
	/*
	 * compare 2 model base on ID
	 * @return boolean: true if they are equal, false if not
	 * */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		return true;
	}
	
}

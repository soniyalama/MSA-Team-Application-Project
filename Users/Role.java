package users;

public enum Role {
	TEAMLEAD("team leader"),
	TECHNICALSUPPORT("technical support"),
	BIOINFORMATICIAN("bioinformatician");
	
	private String str;
	
	Role(String str) {
		this.str = str;
	}
	
	/**
	 * Returns the string representation of the enum type.
	 * 
	 * @return the string representation of the enum type.
	 */
	public String getString () {
		return this.str;
	}	
}
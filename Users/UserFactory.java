package users;

public class UserFactory {

	public User getUser(String userType) {
		if (userType == null) {
			return null;
		}
		if (userType.equalsIgnoreCase("TeamLead")) {
			return new TeamLeader();

		} else if (userType.equalsIgnoreCase("Bioinformatician")) {
			return new Bioinformatician();

		} else if (userType.equalsIgnoreCase("TechnicalSupport")) {
			return new TechnicalSupport();
		}
		
		return null;
	}

}

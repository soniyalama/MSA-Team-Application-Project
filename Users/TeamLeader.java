package users;

public class TeamLeader  extends User {
	@Override
	public Role getRole() {
		return Role.TEAMLEAD;		
	}
}

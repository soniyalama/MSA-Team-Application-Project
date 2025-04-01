package operations;

import java.util.ArrayList;

import dataio.DataReader;
import dataio.DataWriter;
import users.User;

public class UserActions {
	protected DataReader reader;
	protected DataWriter dataWriter;
	protected User currentUser;
	protected ArrayList<User> users;

	/**
	 * 
	 * 
	 * @param reader
	 * @param dataWriter
	 */
	public UserActions(DataReader reader, DataWriter dataWriter, ArrayList<User> users) {
		this.reader = reader;
		this.dataWriter = dataWriter;
		this.users = users;
}
	
	/**
	 * 
	 */
	protected String getBaseFilename(User user) {
		return user.getFirstname() + user.getLastname();
	}
	
	/**
	 * Returns a User object for the users that has a fullname similar to the one
	 * given in the parameter. Returns null if the users does not exist.
	 * 
	 * @param fullname - the firstname + " " + lastname of the users to search.
	 * @return a User object for the users with the given fullname.
	 */
	public User getUser(String fullname) {
		for (User user : users) {
			if (user.getFullname().equalsIgnoreCase(fullname)) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Returns the current user executing this action.
	 * 
	 * @return the current user performing this action.
	 */
	public User getCurrentUser() {
		return currentUser;
	}
	
	public void setCurrentUser(String fullname) {
		for (User user : users) {
			if (user.getFullname().equalsIgnoreCase(fullname)) {
				currentUser = user;
			}
		}		
	}
}

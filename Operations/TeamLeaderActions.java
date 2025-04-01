package operations;

import java.io.IOException;
import java.util.ArrayList;

import alignment.Alignment;
import alignment.IAlignment;
import alignment.SNPAdapter;
import dataio.DataReader;
import dataio.DataWriter;
import exceptions.BioinformaticsException;
import users.Role;
import users.User;

/**
 * The TeamLeaderActions class provides a menu with items that are accessible to
 * a team leader.
 */
public class TeamLeaderActions extends UserActions {

	/**
	 * 
	 * @param scanner
	 * @param reader
	 * @param writer
	 * @param users
	 * @param currentUser
	 */
	public TeamLeaderActions(DataReader dataReader, DataWriter dataWriter, ArrayList<User> users) {
		super(dataReader, dataWriter, users);
	}

	/**
	 * Writes all of the users’ alignments to one single file that is named with
	 * the team leader's first name + last name with a .alignment.txt extension.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws BioinformaticsException
	 */
	public void writeDataToFile() throws NumberFormatException, IOException, BioinformaticsException {
		System.out.println("Writing all of the users' alignments to " + currentUser.getFullname());
		
		String sourcePath;
		String content = "";
		Alignment alignment;

		// Write all of the users alignment to a single file.
		for (User user : users) {
			sourcePath = getBaseFilename(user) + ".alignment.txt";
			if (user.getRole() == Role.BIOINFORMATICIAN && reader.fileExists(sourcePath)) {
				alignment = reader.readAlignment(sourcePath);
				content += sourcePath + "\n" + alignment.toString() + "\n";
			}
		}

		// Overwrite the file with new alignment data.
		String destinationPath = getBaseFilename(currentUser) + ".alignment.txt";
		dataWriter.write(destinationPath, content);
	}


	/**
	 * Write all of the users’ alignments scores to a file that is named with the
	 * team leader's first name + last name with a .score.txt extension.
	 * 
	 * @throws BioinformaticsException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */

	public void writeReportToFile() throws NumberFormatException, IOException, BioinformaticsException  {

		System.out.println("Writing all of the users' alignments scores to "
				+ currentUser.getFullname());
		
		String scores = "";
		String filename;

		// Write all of the users alignment to a single file.
		for (User user : users) {
			filename = getBaseFilename(user) + ".score.txt";
			if (user.getRole() == Role.BIOINFORMATICIAN && reader.fileExists(filename)) {
				scores += user.getFullname() + ": " + reader.readScore(filename) + "\n";
			}
		}

		// Overwrite the file with new alignment data.
		String destinationPath = getBaseFilename(currentUser) + ".score.txt";
		dataWriter.write(destinationPath, scores);
	}

	/**
	 * Copy a users’s alignment to the optimal alignment in the repository. The action
	 * also affect the SNiP alignment. However, after this operation, changes to the 
	 * users’s alignment do not affect the optimal alignment (nor the other way around).
	 * 
	 * @throws BioinformaticsException 
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public void promoteUserAlignment(String userFullname) throws NumberFormatException, IOException, BioinformaticsException {
		
		System.out.println("Promoting alignment from " + userFullname + " to shared alignment");
		
		User user = getUser(userFullname);

		if (user == null) {
			System.out.println("Error: there is no users with the name " + userFullname);
		} else if (user.getRole() != Role.BIOINFORMATICIAN) {
			System.out.println("Error: " + userFullname + " is not a bioinformatician");
		} else {
			String sourcePath = getBaseFilename(user) + ".alignment.txt";
			String destinationPath = reader.getOptimalFilename();
			String snipPath = "snip.alignment.txt";

			Alignment userAlignment = reader.readAlignment(sourcePath);

			dataWriter.write(destinationPath, userAlignment.toString());
			
			IAlignment snpAlignment = new SNPAdapter();
			
			dataWriter.write(snipPath, snpAlignment.alignmentToString(userAlignment));
		}
	}

	/**
	 * Overwrites a users’s alignment with the optimal alignment. After this
	 * operation, changes to the users’s alignment do not affect the optimal
	 * alignment (nor the other way around).
	 * 
	 * @throws BioinformaticsException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void overwriteUserAlignment(String userFullname) throws NumberFormatException, IOException, BioinformaticsException {		
		
		System.out.println("Copying shared alignment to " + userFullname);

		User user = getUser(userFullname);

		if (user == null) {
			System.out.println("Error: there is no users with the name " + userFullname);
		} else if (user.getRole() != Role.BIOINFORMATICIAN) {
			System.out.println("Error: " + userFullname + " is not a bioinformatician");
		} else {
			String sourcePath = reader.getOptimalFilename();
			String destinationPath = getBaseFilename(user) + ".alignment.txt";

			Alignment userAlignment = reader.readAlignment(sourcePath);

			dataWriter.write(destinationPath, userAlignment.toString());
		}
	}

}

package operations;

import java.io.IOException;
import java.util.ArrayList;

import dataio.DataReader;
import dataio.DataWriter;
import users.Role;
import users.User;

/**
 * The SupportActions class provides methods that mimic the operations a
 * technical support users.
 */
public class SupportActions extends UserActions {

	/**
	 * Constructor creates an instance of this class with the dataReader and
	 * dataWriter objects, and the users ArrayList initializes with the 
	 * corresponding objects given as parameters.
	 * 
	 * @param dataReader
	 * @param dataWriter
	 */
	public SupportActions(DataReader dataReader, DataWriter dataWriter, ArrayList<User> users) {
		super(dataReader, dataWriter, users);
	}

	/**
	 * Backups up the repository data: make a hard / deep copy of the current
	 * optimal alignment, its corresponding SNiP alignment and, all users' personal
	 * alignments. Whenever such a backup is made, the date and time of the backup
	 * procedure is stored as an instance variable for the technical support member.
	 * 
	 * @throws IOException
	 */
	public void backupRepository() throws IOException {
		System.out.println("Backing up repository data");

		dataWriter.copyFile(reader.getOptimalFilename(), "optimal.alignment.bak.txt");
		dataWriter.copyFile("snip.alignment.txt", "snip.alignment.bak.txt");

		String sourcePath;
		String destinationPath;

		// Restore all users' personal alignment.
		for (User user : users) {
			sourcePath = getBaseFilename(user) + ".alignment.txt";
			if (user.getRole() != Role.TECHNICALSUPPORT && reader.fileExists(sourcePath)) {
				destinationPath = getBaseFilename(user) + ".alignment.bak.txt";
				dataWriter.copyFile(sourcePath, destinationPath);
			}
		}
	}

	/**
	 * Restores the repository data: reinstates the backup data which involves
	 * overwriting the contents of the current optimal alignment, its corresponding
	 * SNiP alignment and all users' personal alignments.
	 * 
	 * @throws IOException
	 */
	public void restoreRepository() throws IOException {
		System.out.println("Restoring repository data");

		dataWriter.copyFile("optimal.alignment.bak.txt", reader.getOptimalFilename());
		dataWriter.copyFile("snip.alignment.bak.txt", "snip.alignment.txt");

		String sourcePath;
		String destinationPath;

		// Restore all users' personal alignment.
		for (User user : users) {
			sourcePath = getBaseFilename(user) + ".alignment.bak.txt";
			if (user.getRole() != Role.TECHNICALSUPPORT && reader.fileExists(sourcePath)) {
				destinationPath = getBaseFilename(user) + ".alignment.txt";
				dataWriter.copyFile(sourcePath, destinationPath);
			}
		}
	}

	/**
	 * Clears the repository data: removes / empties the current optimal standard
	 * alignment, its corresponding SNiP alignment and, all users personal alignment
	 */
	public void clearRepository() {
		System.out.println("Clearing repository data");

		dataWriter.deleteFile(reader.getOptimalFilename());
		dataWriter.deleteFile("snip.alignment.txt");

		String targetFilePath;

		// Clear files storing the standard and SNiP alignments of all users.
		for (User user : users) {
			targetFilePath = getBaseFilename(user) + ".alignment.txt";
			dataWriter.deleteFile(targetFilePath);
		}
	}

}

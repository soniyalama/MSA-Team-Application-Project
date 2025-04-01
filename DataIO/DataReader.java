package dataio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import alignment.*;
import exceptions.BioinformaticsException;
import users.User;
import users.UserFactory;

public class DataReader {
	// Stores the name of the file with genome data.
	private String fastaFilename;
	// Stores the name of the file with users's information
	private String teamFilename;

	/**
	 * Stores an instance of this DataReader class to model a singleton.
	 */
	private static DataReader dataReader = null;

	/**
	 * 
	 */
	private DataReader() {
		readFilenames();
	}

	/**
	 * 
	 * @return
	 */
	public static DataReader getInstance() {
		if (dataReader == null) {
			dataReader = new DataReader();
		}
		return dataReader;
	}

	public String getOptimalFilename() {
		return fastaFilename;
	}
	
	/**
	 * 
	 */
	private void readFilenames() {
		Properties properties = new Properties();
		InputStream inStream = null;

		try {
			inStream = new FileInputStream("config.properties");
			properties.load(inStream);
			fastaFilename = properties.getProperty("fastafilename");
			teamFilename = properties.getProperty("teamfilename");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	public ArrayList<User> readUsers() throws BioinformaticsException, IOException {
		int id = 1;
		String line;
		User user = null;
		ArrayList<User> users = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(teamFilename));
		}catch(IOException ex) {
			closeReader(reader);
			throw new BioinformaticsException("Error. unable to open the users repository.");
		}
		
		UserFactory userFactory = new UserFactory();

		while ((line = reader.readLine()) != null) {

			String[] userInfo = line.split("\\s+");

			user = userFactory.getUser(userInfo[0]);

			user.setId(id++);
			user.setFirstname(userInfo[1]);
			user.setLastname(userInfo[2]);
			try {
				user.setYearsOfExperience(Integer.parseInt(userInfo[3]));
			}catch(NullPointerException | IllegalArgumentException ex) {
				closeReader(reader);
				throw new BioinformaticsException("Error: either the role or"
						+ " years of experience is improperly formatted.");
			}

			users.add(user);
		}

		closeReader(reader);

		return users;
	}
	
	private void closeReader(BufferedReader reader) throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	public Alignment readAlignment() throws NumberFormatException, IOException, BioinformaticsException {
		return readAlignment(fastaFilename);
	}
	
	public Alignment readAlignment(String filename) throws NumberFormatException, IOException, BioinformaticsException {
		String line;
		boolean valid;
		Genome newGenome = null;
		Alignment alignment = new StandardAlignment();
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		while ((line = reader.readLine()) != null) {
			if (line.trim().startsWith(">")) {
				newGenome = new Genome(line);

				// Read the line with genome sequence, but ignore blank lines.
				do {
					line = reader.readLine();
					valid = !line.trim().isEmpty();
				} while (line != null && !valid);

				newGenome.setNucleotides(line);

				alignment.addGenome(newGenome);
			}
		}
		if (reader != null) {
			reader.close();
		}

		return alignment;
	}
	
	public String readScore(String filename) throws IOException{
		String score = "";
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		
		score = reader.readLine();
				
		if (reader != null) {
			reader.close();
		}

		return score;
	}
	
	public boolean fileExists(String filename) {
		return (new File(filename)).exists();		
	}

}

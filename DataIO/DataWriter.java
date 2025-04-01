package dataio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class DataWriter {

	/**
	 * Stores an instance of this DataWriter class to 
	 * model a singleton.
	 */
	private static DataWriter dataWriter = null;

	/**
	 * A private constructor to prevent instantiating an
	 * object of this class. 
	 */
	private DataWriter() {}

	/**
	 * Returns an instance of this class.
	 * @return
	 */
	public static DataWriter getInstance() {
		if (dataWriter == null) {
			dataWriter = new DataWriter();
		}
		return dataWriter;
	}
	
	/**
	 * 
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public void write(String filename, String data) throws NumberFormatException, IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
		
		writer.write(data);

		if (writer != null) {
			writer.close();
		}
	}
		
	/**
	 * Copies the contents of the file at 'source' to the file at 'destination'.
	 * 
	 * @param source - the path/filename to the source file.
	 * @param destination - the path/filename to the destination file.
	 * @throws IOException 
	 */
	public void copyFile(String source, String destination) throws IOException {
		File sourceFile = new File(source);
		File destFile = new File(destination);
		
		if (destFile.exists()) {
			destFile.delete();
		}
		
		Files.copy(sourceFile.toPath(), destFile.toPath());
	}
	
	/**
	 * Deletes the file with name/path given in the parameter.
	 * 
	 * @param filename - the path/filename to the file to delete.
	 */
	public void deleteFile(String filename) {
		File targetFile = new File(filename);
		targetFile.delete();		
	}
}

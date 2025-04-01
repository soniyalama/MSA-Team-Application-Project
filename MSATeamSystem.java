
import java.io.IOException;
import java.util.ArrayList;

import dataio.DataReader;
import dataio.DataWriter;
import exceptions.BioinformaticsException;
import operations.InformaticianActions;
import operations.SupportActions;
import operations.TeamLeaderActions;
import users.User;

public class MSATeamSystem {

	public static void main(String[] args) {
		MSATeamSystem msaTeamsystem = new MSATeamSystem();
		try {
			msaTeamsystem.executor();
		} catch (BioinformaticsException | NullPointerException | IllegalArgumentException | IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void executor() throws IllegalArgumentException, IOException, BioinformaticsException {
		ArrayList<User> users = null;

		System.out.println("Welcome to Multiple Sequence Alignment Team System\n");

		// Get a DataReader object and a DataWriter object, which are singletons.
		DataReader dataReader = DataReader.getInstance();
		DataWriter dataWriter = DataWriter.getInstance();

		// Read users from the 'team.txt' file into an ArrayList.
		users = dataReader.readUsers();
		
		// A TeamLeaderActions' object for executing team leader's actions.
		TeamLeaderActions leaderAction = new TeamLeaderActions(dataReader, dataWriter, users);
		
		// A SupportActions object for executing technical support's actions.
		SupportActions supportAction = new SupportActions(dataReader, dataWriter, users);
		
		// A SupportActions object for executing bioinformatician's actions.
		InformaticianActions informaticianAction = new InformaticianActions(dataReader, dataWriter, users);

		
		// Team leader: set the active team leader as 'Jozef Groenewegen'.
		leaderAction.setCurrentUser("Jozef Groenewegen");
				
		// Team Leader: copy shared/optimal alignment to users Marc Janssens, Werner Lippens, Yves Colpaert.
		leaderAction.overwriteUserAlignment("Marc Janssens");		
		leaderAction.overwriteUserAlignment("Werner Lippens");		
		leaderAction.overwriteUserAlignment("Yves Colpaert");
		
		// Team Leader: copy a users’s alignment to the optimal alignment in the repository.
		leaderAction.promoteUserAlignment("Marc Janssens");
		
		// Team Leader: write all users' alignments to a file.
		leaderAction.writeDataToFile();

		// Technical Support: Set the technical support user as 'Jeff Stevenson'.
		supportAction.setCurrentUser("Jeff Stevenson");
		
		// Technical Support: backup up repository
		supportAction.backupRepository();
				
		// Bioinformatician: Set the bioinformatician as 'Jozef Groenewegen'.
		informaticianAction.setCurrentUser("Marc Janssens");
		
		// Bioinformatician: retrieve own alignment.
		informaticianAction.retrieveAlignment();

		// Bioinformatician: Add a genome with its corresponding name / identifier.
		informaticianAction.addGenome();

		// Bioinformatician: search through the genomes for a specific sequence of characters.
		informaticianAction.searchGenome();

		// Bioinformatician: replace a genome in the alignment with a new sequence.
		informaticianAction.replaceEntireSequence();

		// Bioinformatician: in a given genome, replace all occurrences of a given sequence
		// of characters by a new sequence of characters (without changing the total length
		// of the genome).
		informaticianAction.replaceSubsequenceInGenome();

		// Bioinformatician: in the entire alignment, replace all occurrences of a given 
		// sequence of characters by a new sequence of characters (without changing the
		// total length of the alignment).
		informaticianAction.replaceSubsequenceInAlignment();

		// Bioinformatician: remove a genome, based on its name / identifier
		informaticianAction.removeGenome();

		// Bioinformatician: write the bioinformatician's alignment to a file.
		informaticianAction.writeDataToFile();

		// Bioinformatician: write the difference score of the bioinformatician's
		// alignment to a file.
		informaticianAction.writeReportToFile();
		
		// Team Leader: write all users' alignments scores to a file.
		leaderAction.writeReportToFile();
		
		// Technical Support: clear up repository
		supportAction.clearRepository();
		// Technical Support: restore repository
		supportAction.restoreRepository();
		
	}

}

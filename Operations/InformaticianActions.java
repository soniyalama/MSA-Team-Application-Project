package operations;

import java.io.IOException;
import java.util.ArrayList;

import alignment.*;
import dataio.DataReader;
import dataio.DataWriter;
import exceptions.BioinformaticsException;
import users.User;

/**
 * The InformaticianActions provides a menu with items that are accessible to a bioinformatician.
 */
public class InformaticianActions extends UserActions {
	private Alignment currentAlignment;

	public InformaticianActions(DataReader dataReader, DataWriter dataWriter, ArrayList<User> users) {
		super(dataReader, dataWriter, users);		
		this.currentAlignment = null;
	}


	/**
	 * Retrieves the alignment owned by a specific bioinformatician.
	 * 
	 * @throws IOException             when there is an error reading the alignment
	 *                                 from the file.
	 * @throws NumberFormatException
	 * @throws BioinformaticsException
	 */
	public void retrieveAlignment() throws NumberFormatException, IOException, BioinformaticsException {
		System.out.println("Retrieving the alignment for " + currentUser.getFullname());

		String pathToFile = getBaseFilename(currentUser) + ".alignment.txt";

		if (reader.fileExists(pathToFile)) {
			currentAlignment = reader.readAlignment(pathToFile);
		} else {
			System.out.println("You have not been assigned the initial alignment");
		}
	}

	/**
	 * Adds a new genome, which consists of an identifier and a sequence of
	 * nucleotides.
	 * 
	 * @throws BioinformaticsException
	 */
	public void addGenome() throws BioinformaticsException {

		System.out.println("\nAdding the genome 2022.F1.ZZ.93.VI850");

		String sequence = "GCAGCCGACATAGGGCGATCAGTGCTGCATGGGATCGTGCCGGGCCTCTCGAGAGTCAGATT"
				+ "GAGAGCCTACTCCAGTATGTTTTTACAGGGGTCTTCATCTAGGAAAGACCTGCTGGGACGTCATTCAAAA"
				+ "CAGCATGACATCATCTTCTCTCGCCATTATCACCCGCCGCGAACATACTCGGCTCCTATAGGATTGCACC"
				+ "TAACTACAAGAACGATCCGCTAGAAAGTTTGTATGATCCAGTCATTTAACGTCCTTGGATTACGGGCCTT"
				+ "ACGATGACAGCGACATAGAACAAATACTATTCCCGGAGTCAAATAGTGTCGAAAGGCTTCTTGGTACAAA"
				+ "GATACTCCGGTTTGGCGCTTGACTGGACCTGAGCTACCCGATTCGACAGGAGATAATTAAGCACTCCTAA"
				+ "GACCGGGGAGTGTCGAAGATGGAGATCTGTGTTCATGTGAATGCACTTCTACACATTTTGCGCGAATGAC"
				+ "TGCGAGGCCCTCTAATAACTCCGGAGTCTTCCGCATTAAGGGCCGGCCGGGGGAATCTGTACTTGGGCGT"
				+ "GCCATAAACATAGGGTCCACGGCAGAAGTCGATTGGTAACTCTGCTGTTGTAGGAGAAACTTGAGTTGTC"
				+ "CTGGGCGTCGAGAACGTGACTGTAGCGCGGAGAAATAGGGCGGGTAGTTCCTGCCATTGTCCGGGCCCCT"
				+ "GTTGTTGCCCATTCATGGACGATGCGGTGCCGGCACGAATCCGATTATCAGCTGCTGGCTTGCTGCCAAT"
				+ "TAGCGGACGGGTGCATCCTGCTTCGGTCGCAAGTGGGTCCAGTGCAACCCAGCGCCTACCGCCGGCCTCT"
				+ "GATGGATCTTTCGCGCAGATCAATGAGGCGATGCTGTGCAACGGTTCATATTCCTACGGTACGGGCTATA"
				+ "TACGGGATGTTGGACAAGGTTAGGTCACCGCACCTTCATAGGCTGGAGATATACGTTACAAGGGGTGTAC"
				+ "CCTACTCGTTGCGCACTCCGAGGGTAGAATCGTGCGGCACCCTAGCACCCGTCGCTTGGTTGACGTTCTC"
				+ "CCCGATAAATGGACATTATTCCGAAATAATTGTCTGAAGTTTCGAAACCCAGCGAACGAAAGTTTCAAGT"
				+ "AAACGGTAGTCTTCTCATAGCTGTATTCAGGTACTTCGTCAATCCCCAGCACCATTTAATCACCCGCCGA"
				+ "CCCAGATGACCTGCATAGGGGTGTCACTGGCCAATATATTACGCGACTCGGACAGAGTTAATCGACACTG"
				+ "TTGGCCTATTCCCGATGGTGTGAGTTTTTTCACCACCTTCGGTGCTGAGTCCAAAGTTATAAATAAATGT"
				+ "CGGGATGATGCTAGATATAGGTCTATACTTGCACTCTCGAATTCATATTGGAGAAGCAAACGCTGAGGGC"
				+ "GCGGGGCGTAAACGAGTACCATGTCGAAGCCATAGGGCCGGATTTTGCACCTTAGTATTTAAGAACTATC"
				+ "CGTCACGCCCGTGCAGACTAACCTTGCGTTGGCCCGCGAATAGTAGAATGTGTCCCAAAATTGTCATGCG"
				+ "TCGCATCTGGGTAACATTAAGGGTCTCTGAAAGCGCATATCATCATTACTTGCCGAGTTCTTGCGATCGG"
				+ "ATCTGGAGCCAGGTAAATCTAGTCGGGGGCGCCGCTACTTCGGGTATGGCAGCGGTAAAGGTGTCCAGTG"
				+ "CAACTGAGCCACACGGGCTCGCTGCCCATTTCGCACGCGGGATGCCTGTTCTGTATAGAACGCTTTCAAT"
				+ "TCTCGCGCCATCCAGCGGTGCAATCCGGCACAAGGTTGCTCGCGGGGAAGCAGTCAGCTTCAGCGAAGGG"
				+ "CCAGACATGCTATCAAACCATCGCGTAACGAACTGGACCTTACCACCGAGTGTAATTCGTGCGACTAAGA"
				+ "GATTTGACTGCCTCTAGCAAAGTTAGGCTAGGGACCCCCGGTACAGTACCATAATGACTCAAGCAGATAC"
				+ "TGCGAGATAATAAGAACAAGGCCGAAATTGAGTAGCCTTATACGTACACCAGCGCTAAACGTGTGCATAG"
				+ "AATGACGGAAAAACAAGAACCGTCGCCACAGCCTCGCAATGGGAAGGAAAGCAGGTATCTACCGGAGCCG"
				+ "CCAACTCGGGGTTGTTCTTCATACACGTGATTCGGCTGTAAAATGTTTCAGGACAAATGTATGCAGATAT"
				+ "GGACGTCTAAAGGCGCGTGGTCCTGTATCCGACTTAGCCATCCGCGGTTCCTCCTAGCTGGTTCTTAATG"
				+ "TTGAAAAAGCAGAGACTAGTCCGTGCTAGACGTATCGCGGACCTTATTCACGGTCGCGACCATTTTTTTG"
				+ "AGAACCCTGGTCGGTGCTTAGTCACGTCGGCGCATGTCTATGCGGTACAACCAGCAAGCACCCTGTTGCA"
				+ "TAACAGCCCAGTGGGAAGACGTCCTTACTGGACCATGTAGCGCAGGTGTCAAGCTTACGAGCGCCGGCAG"
				+ "CACGTGTCCGTAGTACAATGATCGTTTCCCGATCAACGCTCAAACTTTGGTAACGGTG";

		Genome newGenome = new Genome(">2022.F1.ZZ.93.VI850");
		newGenome.setNucleotides(sequence);
		currentAlignment.addGenome(newGenome);
	}

	/**
	 * Searches through the genomes for a specific sequence of characters (e.g.
	 * AACAAATG) and displays the corresponding names / identifiers for those
	 * genomes in which the sequence can be found.
	 */
	public void searchGenome() {
		ArrayList<String> genomes;

		System.out.println("\nSearching through the genome for the sequence 'AACAAATG'");
		genomes = currentAlignment.searchSequence("AACAAATG");
		for (String genome : genomes) {
			System.out.println(genome);
		}

		System.out.println("\nSearching through the genome for the sequence 'TTTTC'");
		genomes = currentAlignment.searchSequence("TTTTC");
		for (String genome : genomes) {
			System.out.println(genome);
		}
	}

	/**
	 * Replaces a genome in the alignment with a new sequence (i.e. a genome that
	 * was previously not part of the alignment and that has a name / identifier
	 * that was not part of the initial FASTA file.
	 * 
	 * @throws BioinformaticsException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void replaceEntireSequence() throws NumberFormatException, IOException, BioinformaticsException {
		String sequence = "TGCCTGAGTCTTGTCATAATCATATGTTGGACGTCATCTTATTTACAGGATAGGAGTCC"
				+ "TTTTATAGTTACGTCATTATTTTGGACCTCCATCATAATCATTTTTGGTCGATCACCGCCAAATAGG"
				+ "GGTGGGCCAGTCGTTAGTTGTTGAATCATTATATTGTCGCCCAAGTGCTCGCTTTACACCGCAGCGT"
				+ "GGTGCAAAATAGTATGCCCTCATCTCCTATTACTGGCGGGTCGTGCGATCTCTATCTACCACAATTA"
				+ "GCCACGTACGCAATAACCTCCCAACTTGAATGGCACTACATCTAGAGGTGACTAATTATGGGGACAC"
				+ "AATGTAGGCGGATTGATAACCTGTTCGAGCCACGGAAATAGCGCCTTGGGCTGAATACAGGGAACGT"
				+ "CACAATTGCTGCAGACATTGTGTGGCCTCTCGCAAACAGGGCGTGCGACCCGACCCCGGGGGTGGAG"
				+ "CGCGAGCCCTTTCGCACTATCTTTCGCATGTGGCTACCTTACCCACCGTAGACACCCATAGGCAGAT"
				+ "TGATAATTCGTGTACATAGGCTCTGGCCTGAAGCGGACAGCACGATGAGACATCCCTCTATGGCGGC"
				+ "AGGCCTACCTGCAACTTTGCCATGTCAGACCTTACGTAAATAGTGTGTACTCGTACCTCATTTCCAG"
				+ "CCGCTTCCAAGAAGGGAGTGTAGTAGCTGGTAATTCTTCATTTATCGTAACCCAATAATCTGCTGTA"
				+ "TCTAGCAACGTGAAGAATGCGCTAGGCTGTCCCCGATCCGATCGGTGCTTATCCCGGCCCCAACTAC"
				+ "AACACGGAGACCACTGGTTACTGCGGCTTAAGAGGTTGCAATGCGACAGTGGGCACACGTTTAATAG"
				+ "GCGAAATGGCAAGTGTGCAGAAGCCCTTCCTCAACCGGTTTATGAGATCCGGAGACCGGTATGCTCT"
				+ "AGGCTAATACTCGTATACTTATCCCTACAGTCACCTGCACCGATTTATGAAGCGACACACATATTTA"
				+ "AATCCCAGCCCGTCCAGAATTTGGTTCGGCAGTACCTGGACCCAGAATTTGTTGGACGTTCACGGAG"
				+ "CTAAGGATGGTCTAGTCAGTTGCAGTTTAACTTAGGGCCTCGCATCGTAGACGAGACCCCTCATATC"
				+ "TTACCCTGTTAATTCAAGGCCAGTCTTGATGGAGCACTAAACGACAAGCACGCTAACGTTTGAACGG"
				+ "AGCTCCTCACAATAAGCAAGTTACGACACATAAGGATCTATGGCCGCACAGCGTCGCTGCAGGCAAC"
				+ "CCTGAAGGTTGACGTTAGACTCCACGTGGGTCTCTCTCAAAGAATATGAACCTACGATAAAAAAGCC"
				+ "AAGGAGTAGTGTCGTAAGATGTGGGTTGGTCTCTAATTGTGCCCTTCCCAACTATCAGTGTTACCTA"
				+ "GCTCGTAACATTGGAATGAAATACCACATCCTACATCTATAATCGGGGTCGAACGCTTCACCTACCC"
				+ "TTCTGCGCAACCCACTAGCATCCTAGGAGAGATATATGACATATAATCGTCTTAACTACACATAAAG"
				+ "AGATGCTTCGGAGTCCGTTTTTCATAAACTTTCGAGGGGCAAGGCTGACGCTCTCACAATACGCTAT"
				+ "ATGGAAGCGTCTTGCTTTCGCTGCCCTAAGTAGCACCGCATTCGCCTTGCAAGTGCCAACGATGTAC"
				+ "TAACAGCGTCCGTACCAGCCGCACTATCCCTAGCACTATGTCTACTTTTTCTCCAACCGTTACAATA"
				+ "GGACAATGGAGACGGATGTTCTATGCTCTAGGATCCGCGGCGGTTTAGTCGCAGCCGACACTGCAAG"
				+ "CGCCGATGGATAGTTCTGCCCCCTAACTGAATTTGTCCGAGAAAACGCAGGGAATCCAAGTACCTGG"
				+ "AGATGGTCACGTCTGTGACTCCCACGGCCGTGAACTCCGTTTTTTGAATCAGCCCCGAAGACAGTTC"
				+ "CATTTCGCAGGGTGCATTCCGTTCCCCAAGCCGCAACAGAAAGGTAGGCCTACACACGCTCCCATGA"
				+ "CAATCGGCCATATACAAGCAAGAGAGAAATATCATGAACCCTCATGCACCATTAGCCGGAGGGATTG"
				+ "GGCCAAGGCACTGAACACGTTGCATGCAATCAGAGGCGCGGTTGGTGTTAATGACCTGGAGTGACAA"
				+ "GGCAAAATAAAGGGTAGTAATGGGTCTCGGCGTCTTTCAATTGCCGCATATCTCTCAATTTGAGTTG"
				+ "CCGATGTCGAACGACGACGCATACGCTTTCTAATGACGGTCGCAGCTTCCAAGGTACTAAGGTATGA"
				+ "GTTATACGCGCGGAATGAAAATTTCTTGTCTGGCAGACGCTAGGAGTGAGAGGGCCGGGGACTGGGC"
				+ "CATACATGTTGGGGGGACAATGACCTGATGTTAAGATACCGCTGAAACGGCTTGCTCAGTGGGGTCG"
				+ "CTAGCCTTTTGATTGCTCCGTTAACGGAGCGCCAACCCGTGGGCTGGAGGATCACCCTGGTATGTCC"
				+ "TGCAAGAAAAGGGCAGGCGTGTTGGGTAC";

		String genomeId = ">2022.F1.ZZ.93.VI850";

		if (genomeExisted(genomeId, sequence)) {
			System.out.println("The identifier or sequence specified were part of the " + "initial FASTA file.");

		} else {
			currentAlignment.replaceGenome(genomeId, sequence);
		}
	}

	/**
	 * Replaces, in a given genome, all occurrences of a given sequence of
	 * characters by a new sequence of characters (without changing the total length
	 * of the genome).
	 */
	public void replaceSubsequenceInGenome() {
		System.out.println("Replacing TTTTC with TTTTT in genome 1998.A1.UG.98.98UG57136");
		currentAlignment.replaceSequencesById("TTTTC", "TTTTT", "1998.A1.UG.98.98UG57136");
	}

	public void replaceSubsequenceInAlignment() {
		System.out.println("Replacing TTTTC with TTTTT in all genomes in the entire alignment");
		currentAlignment.replaceAllSequences("TTTTC", "TTTTT");
	}

	/**
	 * Removes a genome based on its name / identifier.
	 */
	public void removeGenome() {
		System.out.println("Removing the genome 1998.A1.UG.98.98UG57136");
		currentAlignment.removeGenome("1998.A1.UG.98.98UG57136");
	}

	/**
	 * Writes the bioinformatician's own personal alignment to an output text file
	 * name with the bioinformatician's first name + last name with a
	 * '.alignment.txt' extension.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void writeDataToFile() throws NumberFormatException, IOException {
		// Ensure there is an active bioinformatician who is assigned an alignment.
		if (currentUser == null || currentAlignment == null) {
			return;
		}

		String fullname = currentUser.getFullname();

		System.out.println("Writing " + fullname + " alignment to a file.");

		String destinationPath = getBaseFilename(currentUser) + ".alignment.txt";

		dataWriter.write(destinationPath, currentAlignment.toString());
	}

	/**
	 * Writes the difference score for a bioinformatician's own personal alignment
	 * to an output text file name with the bioinformatician's first name + last
	 * name with a .score.txt extension.
	 */
	public void writeReportToFile() throws NumberFormatException, IOException {
		String fullname = currentUser.getFullname();

		System.out.println("Writing report for " + fullname + " alignment to a file.");

		String destinationPath = getBaseFilename(currentUser) + ".score.txt";

		dataWriter.write(destinationPath, "" + currentAlignment.calculateScore() + "\n");
	}

	/**
	 * 
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 * @throws BioinformaticsException
	 */
	private Alignment readInitialAlignment() throws NumberFormatException, IOException, BioinformaticsException {
		String pathToBackupFile = getBaseFilename(currentUser) + "_bak.alignment.txt";

		if (reader.fileExists(pathToBackupFile)) {
			return reader.readAlignment(pathToBackupFile);
		} else {
			System.out.println(currentUser.getFullname() + ", you have not been assigned the initial alignment");
			return null;
		}
	}

	/**
	 * Returns true if the given genome name/identifier and sequence were previously
	 * part of the of the initial FASTA file; otherwise returns false.
	 * 
	 * @return true if the id and sequence previously existed in the initial faste
	 *         file or false otherwise.
	 * @throws BioinformaticsException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	private boolean genomeExisted(String genomeId, String sequence)
			throws NumberFormatException, IOException, BioinformaticsException {
		Alignment initialAlignment = readInitialAlignment();
		if (initialAlignment != null) {
			AlignmentIterator iterator = initialAlignment.iterator();
			while (iterator.hasNext()) {
				Genome genome = iterator.next();
				if (genome.matchesIdOrSequence(genomeId, sequence)) {
					return true;
				}
			}
		}
		return false;
	}

}

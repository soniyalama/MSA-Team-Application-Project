package alignment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Alignment class encapsulates the attributes of a real-world sequence
 * alignment, and has methods that simulate the typical actions on a multiple
 * sequence alignment. It implements the Iterable interface to enable access to
 * genomes in the map without exposing its internal implementation.
 */
public abstract class Alignment implements Iterable<Genome> {
	// Stores the genomes that make up the alignment.
	Map<String, Genome> genomes;

	/**
	 * The default constructor that creates an empty alignment.
	 */
	public Alignment() {
		genomes = new LinkedHashMap<>();
	}

	/**
	 * A copy constructor to construct a copy of the given alignment.
	 * 
	 * @param alignment the alignment from which to create this alignment.
	 */
	public Alignment(Alignment alignment) {
		this();
		genomes = new LinkedHashMap<>();
		for (Map.Entry<String, Genome> entry : genomes.entrySet()) {
			genomes.put(entry.getKey(), entry.getValue().clone());
		}
	}

	/**
	 * Returns the first/top genome in this alignment.
	 * 
	 * @return the topmost genome in this alignment.
	 */
	public Genome getTopGenome() {
		Map.Entry<String, Genome> entry = genomes.entrySet().iterator().next();
		return entry.getValue();
	}

	/**
	 * Adds a new genome with a corresponding identifier to this alignment.
	 * 
	 * @param genome - the new genome to add.
	 */
	public void addGenome(Genome genome) {
		genomes.put(genome.getId(), genome);
	}

	/**
	 * Searches through the genomes in the entire alignment for a specific sequence
	 * of characters such as AACAAATG, and returns an ArrayList in which each
	 * element is a string that represent the name/identifier for each genome in
	 * which the sequence can be found.
	 */
	public ArrayList<String> searchSequence(String sequence) {
		ArrayList<String> matchedGenomeIds = new ArrayList<>();
		if (sequence != null && !sequence.isEmpty()) {
			String id = null;
			Genome genome;
			for (Map.Entry<String, Genome> entry : genomes.entrySet()) {
				genome = entry.getValue();
				id = genome.getIdWithSequence(sequence);
				if (id != null) {
					matchedGenomeIds.add(id);
				}
			}
		}
		return matchedGenomeIds;
	}

	/**
	 * Replaces a genome in the alignment with a new sequence (i.e. a genome that
	 * was previously not part of the alignment and that has a name / identifier
	 * that was not part of the initial FASTA file.
	 */
	public void replaceGenome(String genomeId, String newSequence) throws IllegalArgumentException {
		Genome genome = genomes.get(genomeId);
		genome.replaceEntireSequence(newSequence);
	}

	/**
	 * Replaces all occurrences of the sequence in the variable 'sequence' with the
	 * sequence 'newSequence' in the genome identified by the value in 'genomeId'.
	 * 
	 * @param sequence - the sequence to search
	 * @param newSequence - the sequence that will replace the occurrences.
	 * @param genomeId - the identifier of the genome in which to replace sequences.
	 * @throws IllegalArgumentException thrown when invalid arguments are provided.
	 */
	public void replaceSequencesById(String sequence, String newSequence, String genomeId)
			throws IllegalArgumentException {
		if (sequence == null || newSequence == null || genomeId == null || sequence.isEmpty() || newSequence.isEmpty()
				|| genomeId.isEmpty()) {
			throw new IllegalArgumentException("Invalid arguments.");
		}

		if (!genomeId.startsWith(">")) {
			genomeId = ">" + genomeId;
		}
		Genome genome = genomes.get(genomeId);
		if (genome != null) {
			genome.replaceSubSequences(sequence, newSequence);
		}
	}

	/**
	 * Replaces, in the entire alignment, all occurrences of the sequence in the variable
	 * 'sequence' with the sequence in 'newSequence'.
	 * 
	 * @param sequence - the sequence to search
	 * @param newSequence - the sequence that will replace the occurrences.
	 * @throws IllegalArgumentException thrown when invalid arguments are provided.
	 */
	public void replaceAllSequences(String sequence, String newSequence) throws IllegalArgumentException {
		if (sequence == null || newSequence == null || sequence.isEmpty() || newSequence.isEmpty()) {
			throw new IllegalArgumentException("Invalid arguments.");
		}

		Genome genome;
		for (Map.Entry<String, Genome> entry : genomes.entrySet()) {
			genome = entry.getValue();
			genome.replaceSubSequences(sequence, newSequence);
		}

	}

	/**
	 * Removes the entry identified by the given genome id from the LinkedHashMap
	 * "genome".
	 */
	public void removeGenome(String genomeId) {
		genomes.remove(genomeId);
	}

	/**
	 * Returns an integer that represent the difference score of this alignment.
	 * 
	 * @return the difference score of this alignment.
	 * @throws ArrayIndexOutOfBoundsException when there is a variance in the lengths
	 * of some the genomes in this alignment.
	 */
	public int calculateScore() throws ArrayIndexOutOfBoundsException {
		int diffScore = 0;
		Genome referenceGenome = getTopGenome();
		AlignmentIterator iterator = iterator();

		if (iterator.hasNext()) {
			// Skip the reference genome (the first/topmost genome).
			iterator.next();

			while (iterator.hasNext()) {
				Genome genome = iterator.next();
				diffScore += genome.getScore(referenceGenome);
			}
		}
		return diffScore;
	}

	/**
	 * An iterator to enable access of the Genomes in the map of this alignments
	 * without exposing the internal mechanism of the class.
	 */
	@Override
	public AlignmentIterator iterator() {
		return new AlignmentIterator(genomes);
	}

	/**
	 * Returns a string representation of this alignment in a format resembles the
	 * FASTA file format of an alignment.
	 */
	@Override
	public String toString() {
		String str = "";
		for (Map.Entry<String, Genome> entry : genomes.entrySet()) {
			str += ((Genome) entry.getValue()).toString() + "\n";
		}
		return str;
	}
	
	
	/**
	 * An abstract method that should be implemented by the concrete child classes.
	 */
	public abstract Alignment clone();

//	/**
//	 * Prints the entire alignment to the screen.
//	 * 
//	 * @throws ArrayIndexOutOfBoundsException when there is a variance in the 
//	 * lengths of some the genomes in this alignment.
//	 */
//	public void print() throws ArrayIndexOutOfBoundsException {
//		print(0);
//	}
//
//	/**
//	 * An abstract method to be implemented by the concrete child classes. The
//	 * method prints to the screen only a portion of the specific alignment. The
//	 * portion is the number of genomes given by the parameter maxOut.
//	 * 
//	 * @param maxOut - the number of genomes to display.
//	 * @throws ArrayIndexOutOfBoundsException when there is a variance in the 
//	 * lengths of some the genomes in this alignment.
//	 */
//	public abstract void print(int maxOut) throws ArrayIndexOutOfBoundsException;
//

}

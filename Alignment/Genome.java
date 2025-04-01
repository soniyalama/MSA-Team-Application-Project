package alignment;

import java.util.Arrays;

import exceptions.BioinformaticsException;

public class Genome {
	private String id;
	private char[] nucleotides;

	public Genome(String id) {
		this.id = id;
		this.nucleotides = null;
	}

	public Genome(Genome genome) {
		this.id = genome.getId();
		this.nucleotides = new char[genome.size()];
		for (int i = 0; i < genome.size(); i++) {
			this.nucleotides[i] = genome.getNucleotide(i);
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public char[] getNucleotides() {
		return nucleotides;
	}

	public char getNucleotide(int index) throws ArrayIndexOutOfBoundsException {
		return nucleotides[index];
	}

	public void setNucleotide(int index, char nucleotide) throws ArrayIndexOutOfBoundsException {
		if (index >= this.nucleotides.length)
			throw new ArrayIndexOutOfBoundsException();

		this.nucleotides[index] = nucleotide;
	}

	public void setNucleotides(char[] nucleotides) {
		this.nucleotides = nucleotides;
	}

	public void setNucleotides(String nucleotides) throws BioinformaticsException {
		int len = nucleotides.length();
		
		this.nucleotides = new char[len];
		for (int i = 0; i < len; i++) {
			this.nucleotides[i] = nucleotides.charAt(i);
		}
	}

	public void changeSequence(int start, char[] nucleotides) throws ArrayIndexOutOfBoundsException {
		int len = nucleotides.length;
		if (start + len > this.nucleotides.length) {
			throw new ArrayIndexOutOfBoundsException();
		}

		for (int i = 0; i < len; i++) {
			this.nucleotides[i + start] = nucleotides[i];
		}
	}

	public String getIdWithSequence(String sequence) {
		int i = 0, j;
		int matched;
		while (i < nucleotides.length - 1) {
			j = 0;
			matched = 0;
			while (j < sequence.length() && i < nucleotides.length) {
				if (nucleotides[i] == sequence.charAt(j)) {
					matched++;
				} else {
					i++;
					break;
				}
				j++;
				i++;
			}
			if (matched == sequence.length()) {
				return id;
			}
			i -= j;
		}
		return null;
	}

	public void replaceEntireSequence(String newSequence) throws IllegalArgumentException {
		if (nucleotides.length != newSequence.length()) {
			throw new IllegalArgumentException("The length of the new sequence is not valid.");
		}

		for (int i = 0; i < nucleotides.length; i++) {
			nucleotides[i] = newSequence.charAt(i);
		}
	}

	public void replaceSubSequences(String sequence, String newSequence) throws IllegalArgumentException {
		if (sequence.length() != newSequence.length()) {
			throw new IllegalArgumentException("The lengths of the old and new sequences do not match.");
		}

		int i = 0, j;
		int matched;
		while (i < nucleotides.length) {
			j = 0;
			matched = 0;
			while (j < sequence.length() && i < nucleotides.length) {
				if (nucleotides[i] == sequence.charAt(j)) {
					matched++;
				} else {
					i++;
					break;
				}
				j++;
				i++;
			}
			if (matched == sequence.length()) {
				for (int k = 0; k < j; k++) {
					nucleotides[i - j + k] = newSequence.charAt(k);
				}
			} else {
				if (i >= nucleotides.length)
					break;
				else
					i -= j;
			}
		}
	}

	public int size() {
		return nucleotides.length;
	}
	
	public Genome clone() {
		return new Genome(this);
	}
	
	public boolean equalsBySequence(Genome other) {
		if (this == other)
			return true;
		if (other == null) {
			return false;
		}
		
		return Arrays.equals(this.nucleotides, other.nucleotides);
	}
	
	public boolean equalsBySequence(char[] nucleotides) {
		return Arrays.equals(this.nucleotides, nucleotides);
	}
	
	/**
	 * Returns true if either the given genome identifier or sequences matches
	 * the corresponding id or sequence of this genome. Otherwise return true.
	 * 
	 * @param genomeId - the genome identifier/name.
	 * @param sequence - the entire sequence (nucleotides).
	 * @return true or false if this genome attributes matches the given id/sequence
	 * or not.
	 */
	public boolean matchesIdOrSequence(String genomeId, String sequence) {
		if (id.equals(genomeId)) {
			return true;			
		}
		
		String thisSequence = Arrays.toString(this.nucleotides);
		
		return thisSequence.equals(sequence);
	}
	
	
	public int getScore(Genome referenceGenome) {
		int diffScore = 0;
		int referenceLength = referenceGenome.size();
		for (int i = 0; i < referenceLength; i++) {
			if (this.getNucleotide(i) != referenceGenome.getNucleotide(i)) {
				diffScore++;					
			}
		}
		return diffScore;
	}

	@Override
	public String toString() {
		String str = id + "\n";
		for (int i = 0; i < nucleotides.length; i++) {
			str += nucleotides[i];
		}
		return str;
	}

}

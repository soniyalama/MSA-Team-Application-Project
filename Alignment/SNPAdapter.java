package alignment;

public class SNPAdapter implements IAlignment{

	public String alignmentToString(Alignment alignment) throws ArrayIndexOutOfBoundsException {
			
		String alignmentStr = "";
		
		Genome referenceGenome = alignment.getTopGenome();
		alignmentStr += referenceGenome.toString();
		
		AlignmentIterator iterator = alignment.iterator();
		iterator.next();

		while (iterator.hasNext()) {
			Genome genome = iterator.next();
			alignmentStr += genome.getId() + "\n";
			alignmentStr += genomeToSNPString(genome, referenceGenome)  + "\n";			
		}
		
		return alignmentStr;
	}
	
	/**
	 * 
	 * @param genome
	 * @param referenceGenome
	 */
	private String genomeToSNPString(Genome genome, Genome referenceGenome) {
		String str = "";
		int referenceLength = referenceGenome.size();
		for (int i = 0; i < referenceLength; i++) {
			if (genome.getNucleotide(i) == referenceGenome.getNucleotide(i)) {
				str += ".";
			}else {
				str += genome.getNucleotide(i);					
			}
		}
		str += "\n";
		
		return str;
	}


}

package alignment;

import java.util.Iterator;
import java.util.Map;

public class AlignmentIterator implements Iterator<Genome> {
	private Iterator<Map.Entry<String, Genome>> iterator;

	public AlignmentIterator(Map<String, Genome> genomes) {
		iterator = genomes.entrySet().iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Genome next() {
		return iterator.next().getValue();
	}
	
	public void remove() {
		//
	}

}

package alignment;

public class StandardAlignment extends Alignment {

	public StandardAlignment() {
		super();
	}
	
	public StandardAlignment(StandardAlignment standardAlignment) {
		super(standardAlignment);
	}

	@Override
	public StandardAlignment clone() {
		return new StandardAlignment(this);
	}
	
}

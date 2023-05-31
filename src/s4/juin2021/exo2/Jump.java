package s4.juin2021.exo2;

public class Jump extends Instruction {

	private int offset;

	public Jump(int offset) {
		this.offset = offset;
	}

	@Override
	public boolean execute(Computer computer) {
		computer.setPC(computer.getPC() + offset);
		return true; // ATTENTION ici on met true vu que le compteur ordinal a changé !
	}

	@Override
	public String toString() {
		return "jump␣" + offset;
	}
}

package s4.juin2021.exo2;

public class Move extends Instruction {
	private int source;
	private int destination;

	public Move(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public boolean execute(Computer computer) {
		computer.setRegister(destination, computer.getRegister(source));
		return false;
	}

	@Override
	public String toString() {
		return "move␣" + source + "␣" + destination;
	}
}

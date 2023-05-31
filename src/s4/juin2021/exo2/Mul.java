package s4.juin2021.exo2;

public class Mul extends Instruction {

	private int source1;
	private int source2;
	private int destination;

	public Mul(int source1, int source2, int destination) {
		this.source1 = source1;
		this.source2 = source2;
		this.destination = destination;
	}

	@Override
	public boolean execute(Computer computer) {
		computer.setRegister(destination, computer.getRegister(source1) * computer.getRegister(source2));
		return false;
	}

	@Override
	public String toString() {
		return "mul␣" + source1 + "␣" + source2 + "␣" + destination;
	}
}

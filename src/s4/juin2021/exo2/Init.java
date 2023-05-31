package s4.juin2021.exo2;

public class Init extends Instruction {

	private int destination;
	private int val;

	public Init(int destination, int val) {
		this.destination = destination;
		this.val = val;
	}

	@Override
	public boolean execute(Computer computer) {
		computer.setRegister(destination, val);
		return false;
	}

	@Override
	public String toString() {
		return "init␣" + destination + "␣" + val;
	}

}

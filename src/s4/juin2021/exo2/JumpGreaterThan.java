package s4.juin2021.exo2;

public class JumpGreaterThan extends Instruction {

	private int i;
	private int j;
	private int offset;

	public JumpGreaterThan(int i, int j, int offset) {
		this.i = i;
		this.j = j;
		this.offset = offset;
	}

	@Override
	public boolean execute(Computer computer) {
		if (computer.getRegister(i) > computer.getRegister(j)) {
			computer.setPC(computer.getPC() + offset);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "jgt␣" + i + "␣" + j + "␣" + offset;
	}
}

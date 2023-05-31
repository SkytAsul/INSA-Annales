package s4.juin2021.exo2;

public class Inc extends Instruction {

	private int register;

	public Inc(int register) {
		this.register = register;
	}

	@Override
	public boolean execute(Computer computer) {
		computer.setRegister(register, computer.getRegister(register) + 1);
		return false;
	}

	@Override
	public String toString() {
		return "inc␣" + register;
	}
}

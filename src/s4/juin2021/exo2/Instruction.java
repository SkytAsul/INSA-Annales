package s4.juin2021.exo2;

public abstract class Instruction {
	public abstract boolean execute(Computer computer);

	@Override
	public abstract String toString();
}

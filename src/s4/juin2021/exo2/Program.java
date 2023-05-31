package s4.juin2021.exo2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Program {

	private Computer computer;

	private List<Instruction> program;

	public Program(Computer computer) {
		this.computer = computer;
		this.program = new ArrayList<>();
	}

	public Program(Computer computer, String instructions) {
		this(computer);
		for (String instruction : instructions.split("\n")) {
			program.add(instructionFromString(instruction));
		}

		// pour flex hein
		// program = Arrays.stream(instructions.split("\n")).map(this::instructionFromString).collect(Collectors.toList());
	}

	private Instruction instructionFromString(String instruction) {
		String[] args = instruction.split("␣");
		// un switch c'est l'équivalent de plein de if-else à la suite
		switch (args[0]) {
			case "init":
				return new Init(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			case "inc":
				return new Inc(Integer.parseInt(args[1]));
			case "jump":
				return new Jump(Integer.parseInt(args[1]));
			case "jgt":
				return new JumpGreaterThan(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			case "move":
				return new Move(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			case "mul":
				return new Mul(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			default:
				throw new IllegalArgumentException("Cannot find instruction " + args[0]);
		}
	}

	public void add(Instruction i) {
		program.add(i);
	}

	public void execute() {
		while (computer.getPC() < program.size()) { // ça veut dire qu'il y a encore des instructions
			Instruction instruction = program.get(computer.getPC());
			boolean pcChanged = instruction.execute(computer);
			if (!pcChanged)
				computer.incPC();
			// on incrémente le PC uniquement si l'instruction n'a pas explicitement modifié le PC
			// (les instructions de saut)
		}
	}

	@Override
	public String toString() {
		return program.stream().map(Instruction::toString).collect(Collectors.joining("\n"));
		// ne pas se soucier de ça, c'est pas demandé pour cette annale
	}

	public static void main(String[] args) {
		Computer computer = new Computer(8);
		Program prog = new Program(computer);
		prog.add(new Init(0, 42));
		prog.add(new Init(1, 10));
		prog.add(new Mul(0, 1, 1));
		prog.add(new Move(1, 7));
		System.out.println(prog);
		prog.execute();
		System.out.println(computer);
		System.out.println();

		// factorielle :
		prog = new Program(computer);
		computer.setPC(0); // on reset le pc vu qu'il y a un autre programme qui s'est exécuté avant
		computer.setRegister(0, 6); // valeur de base
		// programme
		// liste des registres :
		// 0: paramètre de la factorielle (n)
		// 7: résultat qui va augmenter au fur et à mesure qu'on multipliera
		// 6: numéro qui augmentera de 1 à n et par quoi on multipliera 7
		prog.add(new Init(7, 1));
		prog.add(new Init(6, 1));
		prog.add(new Inc(6));
		prog.add(new JumpGreaterThan(6, 0, 100)); // signifie qu'on a dépassé n donc on peut s'arrêter là
		prog.add(new Mul(6, 7, 7));
		prog.add(new Jump(-3));
		System.out.println(prog);
		prog.execute();
		System.out.println(computer);

		computer = new Computer(8);
		String instructions =
				"init␣0␣42\n" +
						"init␣1␣10\n" +
						"mul␣0␣1␣1\n" +
						"move␣1␣7";
		prog = new Program(computer, instructions);
		System.out.println(prog);
		System.out.println(computer);
		System.out.println();
		prog.execute();
		System.out.println(computer);
	}


}

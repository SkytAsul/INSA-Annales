package s4.juin2021.exo2;

import java.util.Arrays;

public class Computer {
	private int[] registers;
	private int programCounter;

	public Computer(int nbRegisters) {
		registers = new int[nbRegisters];
	}

	public int getRegister(int reg) {
		return registers[reg];
	}

	public void setRegister(int reg, int val) {
		registers[reg] = val;
	}

	public void incPC() {
		programCounter++;
	}

	public int getPC() {
		return programCounter;
	}

	public void setPC(int n) {
		programCounter = n;
	}

	@Override
	public String toString() {
		return "Registers: " + Arrays.toString(registers) + ", PC: " + programCounter;
	}
}

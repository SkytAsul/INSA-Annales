package s4.juin2019.exo2;

import java.util.ArrayList;
import java.util.List;

public class Orientation {
	private List<Coordinate> orientation;

	public Orientation() {
		this.orientation = new ArrayList<>();
	}

	/** Ajoute une coordonnée à l’orientation. */
	public void add(Coordinate c) {
		this.orientation.add(c);
	}

	/** Donne les coordonnées décrivant l’orientation. */
	public List<Coordinate> getCoordinates() {
		return this.orientation;
	}

	@Override
	public String toString() {
		// ne pas se préoccuper de ça
		int width = orientation.stream().mapToInt(Coordinate::getI).max().getAsInt();
		int height = orientation.stream().mapToInt(Coordinate::getJ).max().getAsInt();
		StringBuilder stb = new StringBuilder();
		for (int i = 0; i <= height; i++) {
			for (int j = 0; j <= width; j++) {
				stb.append(orientation.contains(new Coordinate(i, j)) ? 'x' : 'o');
			}
			stb.append('\n');
		}
		return stb.toString();
	}

}

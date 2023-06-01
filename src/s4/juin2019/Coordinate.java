package s4.juin2019;

public class Coordinate {
	private final int i;
	private final int j;

	/** i représente la ligne et j la colonne de la coordonnée. */
	public Coordinate(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/** Donne la ligne de la coordonnée. */
	public int getI() {
		return this.i;
	}

	/** Donne la colonne de la coordonnée. */
	public int getJ() {
		return this.j;
	}

	public boolean equals(Coordinate coordinate) {
		// franchement jsp si ils attendent ça ou bien la version du dessous...
		return coordinate.i == i && coordinate.j == j;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof Coordinate) {
			// l'objet passé est de type Coordinate donc on peut comparer
			Coordinate coordinate = (Coordinate) object;
			return coordinate.i == i && coordinate.j == j;
		} else {
			// l'objet passé n'est pas de type Coordinate donc c'est sûr que c'est pas equals
			return false;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.i;
		result = prime * result + this.j;
		return result;
	}

	@Override
	public String toString() {
		return i + " " + j;
	}

}

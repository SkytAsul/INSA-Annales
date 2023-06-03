package s4.juin2019.exo2;

import java.util.HashSet;
import java.util.Set;

public class Board {
	// Le plateau est représenté par une matrice
	protected Matrix board;
	// L’ensemble des cases libres du plateau (une case libre dans le plateau a pour valeur 0)
	private Set<Coordinate> free;

	/** Crée un plateau vide de taille n x m. */
	public Board(int n, int m) {
		board = new Matrix(n, m);
		free = new HashSet<>();
		// comme toutes les cases sont vide on doit remplir l'ensemble "free" par toutes les cases
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < m; column++) {
				free.add(new Coordinate(row, column));
			}
		}
	}

	/**
	 * Répond vrai si on peut placer l’orientation orientation en position startingPoint (qui représente
	 * la position du coin supérieur gauche de l’orientation).
	 */
	public boolean possible(Coordinate startingPoint, Orientation orientation) {
		for (Coordinate c : orientation.getCoordinates()) {
			int i = startingPoint.getI() + c.getI();
			if (i < 0 || i >= board.getHeight())
				return false;
			int j = startingPoint.getJ() + c.getJ();
			if (j < 0 || j >= board.getWidth())
				return false;
			if (board.get(i, j) != 0)
				return false;
		}
		return true;
	}

	/** Place la pièce d’orientation orientation et de couleur color en position startingPosition. */
	public void put(Coordinate startingPosition, Orientation orientation, int color) {
		for (Coordinate c : orientation.getCoordinates()) {
			int i = startingPosition.getI() + c.getI();
			int j = startingPosition.getJ() + c.getJ();
			// pas besoin de vérifier si i et j sont bien dans les dimensions du plateau
			// et si il n'y a pas déjà de pièce ici car cf consigne
			// "On suppose qu’il est possible de placer la pièce dans cette orientation et dans cette case."
			board.set(i, j, color);
			free.remove(new Coordinate(i, j));
		}
	}

	/** Retire la pièce d’orientation orientation qui se trouve en position startingPosition. */
	public void remove(Coordinate startingPosition, Orientation orientation) {
		for (Coordinate c : orientation.getCoordinates()) {
			int i = startingPosition.getI() + c.getI();
			int j = startingPosition.getJ() + c.getJ();
			board.set(i, j, 0);
			free.add(new Coordinate(i, j));
		}
	}

	/** Donne l’ensemble des cases libres du plateau. */
	public Set<Coordinate> getFreeCoordinates() {
		return new HashSet(free);
	}

	/** Répond vrai si le plateau est rempli. */
	public boolean isFull() {
		return this.free.isEmpty();
	}
}

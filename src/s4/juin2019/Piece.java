package s4.juin2019;

import java.util.List;

public class Piece {
	// Les différentes orientations possibles de la pièce
	private List<Orientation> possibleOrientations;
	// La couleur de la pièce
	private int color;
	// Le coin supérieur gauche du rectangle englobant la pièce si elle est placée sur le plateau ou
	// null sinon
	private Coordinate startingPoint;
	// L’orientation de la pièce si elle est posée sur le plateau ou null sinon
	private Orientation currentOrientation;

	/** Une pièce est décrite par ses différentes orientations possibles et sa couleur. */
	public Piece(List<Orientation> orientations, int color) {
		this.possibleOrientations = orientations;
		this.color = color;
	}

	/** Permet de fixer l’orientation et la position de la pièce sur le plateau de jeu. */
	public void setPosition(Coordinate startingPoint, Orientation orientation) {
		this.startingPoint = startingPoint;
		this.currentOrientation = orientation;
		// erreur dans le sujet ; c'était "this.orientation" or il n'y a pas de champ "orientation"...
	}

	/** Permet de retirer la pièce du plateau de jeu. */
	public void unsetPosition() {
		this.startingPoint = null;
		this.currentOrientation = null;
		// même erreur que plus haut
	}

	/** Répond vrai si la pièce n’est pas encore posée sur le plateau. */
	public boolean isMovable() {
		return (this.startingPoint == null);
	}

	/** Donne la couleur de la pièce. */
	public int getColor() {
		return this.color;
	}

	/** Donne le coin supérieur gauche du rectangle englobant la pièce sur le plateau. */
	public Coordinate getStartingPosition() {
		return this.startingPoint;
		// erreur dans le sujet, c'étiat "this.startingPosition"
	}

	/** Donne l’orientation de la pièce sur le plateau. */
	public Orientation getOrientation() {
		return this.currentOrientation;
	}

	/** Donne les différentes orientations possibles de la pièce. */
	public List<Orientation> getOrientations() {
		return this.possibleOrientations;
	}

	@Override
	public String toString() {
		return "Piece " + color + "; orientations: " + possibleOrientations;
	}

}

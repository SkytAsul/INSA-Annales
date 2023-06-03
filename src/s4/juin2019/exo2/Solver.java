package s4.juin2019.exo2;

public class Solver {
	// Le plateau (5 lignes x 11 colonnes)
	private Board board;
	// Le tableau des pièces (déjà fixées ou non)
	private Piece[] pieces;

	/** Le constructeur va placer les pièces fixes sur le plateau. */
	public Solver(Board board, Piece[] pieces) {
		this.board = board;
		this.pieces = pieces;
		for (Piece p : pieces) {
			if (!p.isMovable()) {
				this.board.put(p.getStartingPosition(), p.getOrientation(), p.getColor());
			}
		}
	}

	/** Lance la recherche puis rend vrai si une solution est trouvée. */
	public boolean search() {
		return dfs(0);
	}

	/**
	 * Rend vrai si une solution est trouvée. Cette méthode va essayer de placer les pièces sur le
	 * plateau. Le paramètre indexPiece est l’index de la pièce du tableau pieces que l’on est en train
	 * de traiter actuellement.
	 */
	private boolean dfs(int indexPiece) {
		if (indexPiece >= pieces.length) {
			// signifie qu'on est passé à la pièce suivante alors qu'il n'y en a plus
			// donc l'algorithme se termine correctement
			return true;
		}

		Piece piece = pieces[indexPiece];
		if (!piece.isMovable()) {
			// la pièce est déjà posée
			return dfs(indexPiece + 1);
		}

		// ATTENTION:
		// ne pas itérer sur "board.getFreeCoordinates()" car on manquerait les cas de clipping
		// par exemple: les boules d'une pièce X sont notés par des X, idem pour les boules d'une pièce Y,
		// les espaces libres sont marqués O
		// la pièce X est déjà placée et on essaye de placer Y
		// XXXO -> XXXY
		// OXOO -> OXYY
		// la pièce Y s'emboîte bien dans l'espace libre, mais le point en haut à gauche de la pièce Y
		// n'est pas une coordonnée libre donc ne serait pas testé...
		for (int row = 0; row < board.board.getHeight(); row++) {
			for (int column = 0; column < board.board.getWidth(); column++) {
				Coordinate coordinate = new Coordinate(row, column);
				// coordinate représente absolument tous les points du tableau, de gauche à droite et de haut en bas

				for (Orientation orientation : piece.getOrientations()) {
					if (board.possible(coordinate, orientation)) {
						board.put(coordinate, orientation, piece.getColor());
						boolean resteFaisable = dfs(indexPiece + 1);
						if (resteFaisable) {
							// on s'arrête là : ça veut dire que toutes les pièces suivantes ont pu être posées
							return true;
						} else {
							// les autres pièces n'ont pas pu être toutes posées :
							// on vire celle-là et on continue la boucle avec les autres orientations et positions
							board.remove(coordinate, orientation);
						}
					}
				}
			}
		}

		// si on arrive là c'est qu'à aucun moment on a pu poser la pièce avec une orientation à une
		// position telle que l'algorithme a pu continuer, donc on renvoit false
		return false;
	}

}

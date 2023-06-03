package s4.juin2019.exo2;

public class Matrix {
	private int[][] mat;

	/**
	 * Crée une matrice initialisée à 0, de n lignes et m colonnes (dont les indices commencent à 0).
	 */
	public Matrix(int n, int m) {
		mat = new int[n][m];
	}

	/** Donne la hauteur de la matrice (son nombre de lignes). */
	public int getHeight() {
		return mat.length;
	}

	/** Donne la largeur de la matrice (son nombre de colonnes). */
	public int getWidth() {
		return mat[0].length;
	}

	/** Donne l’élément en ligne i et colonne j (dont les indices commencent à 0). */
	public int get(int i, int j) {
		return mat[i][j];
	}

	/** Met la valeur v dans l’élément en ligne i et colonne j (dont les indices commencent à 0). */
	public void set(int i, int j, int v) {
		mat[i][j] = v;
	}
}

package s4.juin2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IQPuzzlerPro {

	private List<String> pendingOrientation;
	private Piece pendingPiece;

	private List<Piece> pieces = new ArrayList<>();

	public IQPuzzlerPro() {}

	public void read() {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(IQPuzzlerPro.class.getResourceAsStream("pieces.txt"), StandardCharsets.UTF_8))) {

			while (reader.ready()) {
				String line = reader.readLine();
				if (line.startsWith("#"))
					continue;

				parse(line);
			}

			finishPiece();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parse(String line) {
		try {
			int parsedColor = Integer.parseInt(line);
			finishPiece();
			pendingPiece = new Piece(new ArrayList<>(4), parsedColor);
			pendingOrientation = new ArrayList<>();
		} catch (NumberFormatException ex) {
			// pas une ligne de couleur
			if (line.equals("-")) {
				finishOrientation();
				pendingOrientation = new ArrayList<>();
			} else if (line.startsWith("=")) {
				String[] args = line.substring(1).split(";");
				int i = Integer.parseInt(args[0]);
				int j = Integer.parseInt(args[1]);
				int orient = Integer.parseInt(args[2]);
				pendingPiece.setPosition(new Coordinate(i, j), pendingPiece.getOrientations().get(orient));
			} else if (!line.isBlank()) {
				pendingOrientation.add(line);
			}
		}
	}

	private void finishOrientation() {
		if (pendingOrientation.isEmpty())
			return;

		Orientation orientation = new Orientation();
		for (int i = 0; i < pendingOrientation.size(); i++) {
			String row = pendingOrientation.get(i);
			for (int j = 0; j < row.length(); j++) {
				char ch = row.charAt(j);
				if (ch == 'x')
					orientation.add(new Coordinate(i, j));
			}
		}
		pendingPiece.getOrientations().add(orientation);
	}

	private void finishPiece() {
		if (pendingPiece == null)
			return;
		finishOrientation();
		pieces.add(pendingPiece);
		pendingPiece = null;
	}

	public static void main(String[] args) {
		IQPuzzlerPro puzzler = new IQPuzzlerPro();
		puzzler.read();
		// System.out.println(puzzler.pieces);
		Board board = new Board(5, 11);
		Solver solver = new Solver(board, puzzler.pieces.toArray(Piece[]::new));
		System.out.println(solver.search());
	}

}

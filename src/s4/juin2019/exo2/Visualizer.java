package s4.juin2019.exo2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Visualizer extends Application {

	private static final Color[] COLORS =
			{Color.WHITE, Color.BLACK, Color.TURQUOISE, Color.LIGHTSKYBLUE, Color.ORANGE, Color.YELLOW, Color.RED};
	private static final int LINES = 5;
	private static final int COLUMNS = 11;
	private static final double SIDE_PX = 50;


	private Canvas canvas;

	private boolean paused = false;
	private Object pauseWatcher = new Object();

	@Override
	public void start(Stage primaryStage) {
		canvas = new Canvas(COLUMNS * SIDE_PX, LINES * SIDE_PX);

		Board board = new VisualizerBoard(LINES, COLUMNS);
		Piece[] pieces = IQPuzzlerPro.readPieces();
		Solver solver = new Solver(board, pieces);

		Scene scene = new Scene(new AnchorPane(canvas));
		primaryStage.setScene(scene);
		primaryStage.show();

		Thread solverThread = new Thread(() -> {
			boolean success = solver.search();
			System.out.println(success ? "SUCCES" : "ECHEC");
		});
		solverThread.start();

		primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
			if (event.getCode() == KeyCode.SPACE) {
				if (paused) {
					synchronized (pauseWatcher) {
						pauseWatcher.notifyAll();
					}
				}
				paused = !paused;
				event.consume();
			}
		});
		primaryStage.setOnCloseRequest(event -> {
			solverThread.interrupt();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}

	class VisualizerBoard extends Board {

		public VisualizerBoard(int n, int m) {
			super(n, m);
		}

		public void refresh() {
			try {
				while (paused) {
					synchronized (pauseWatcher) {
						pauseWatcher.wait();
					}
				}

				canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				for (int i = 0; i < board.getHeight(); i++) {
					for (int j = 0; j < board.getWidth(); j++) {
						Color color = COLORS[board.get(i, j)];
						canvas.getGraphicsContext2D().setFill(color);
						canvas.getGraphicsContext2D().fillRect(j * SIDE_PX, i * SIDE_PX, SIDE_PX, SIDE_PX);
					}
				}

				Thread.sleep(20);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		@Override
		public void put(Coordinate startingPosition, Orientation orientation, int color) {
			super.put(startingPosition, orientation, color);
			refresh();
		}

		@Override
		public void remove(Coordinate startingPosition, Orientation orientation) {
			super.remove(startingPosition, orientation);
			refresh();
		}

	}

}

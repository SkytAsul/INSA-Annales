package s4.juin2021;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class Visualizer extends Application {

	@Override
	public void start(Stage primaryStage) {
		ScatterChart<Number, Number> chart = new ScatterChart<>(new NumberAxis(), new NumberAxis());
		chart.setMinHeight(600);
		chart.setMinWidth(800);
		Scene scene = new Scene(chart);
		primaryStage.setScene(scene);
		primaryStage.show();

		Nuage nuage;
		// nuage = parseNuage("0;0,1;10,5;12,10;10,3;5,4;7,-4;6,-5;2,-1;10,6;5");
		nuage = new Nuage(40, 20, 20);

		System.out.println(nuage.pixels);
		Pixel lowest = nuage.plusBas();
		Pixel highest = nuage.plusHaut();
		List<Pixel> enveloppe = nuage.quickHull();
		System.out.println("Lowest: " + lowest);
		System.out.println("Highest: " + highest);
		System.out.println("Rightmost: " + Nuage.plusADroite(lowest, highest, nuage.pixels));
		System.out.println("Enveloppe: " + enveloppe);

		Series<Number, Number> series = new Series<>();
		Series<Number, Number> enveloppeSeries = new Series<>();
		nuage.pixels.forEach(pixel -> {
			Data<Number, Number> data = new Data<>(pixel.x, pixel.y);
			if (enveloppe.contains(pixel))
				enveloppeSeries.getData().add(data);
			else
				series.getData().add(data);
		});

		chart.getData().addAll(series, enveloppeSeries);
		chart.lookupAll(".series0").forEach(node -> node.setStyle("-fx-background-color: #48D1CC;"));
		chart.lookupAll(".series1").forEach(node -> node.setStyle("-fx-background-color: red;"));
	}

	private Nuage parseNuage(String value) {
		return new Nuage(Stream.of(value.split(",")).map(string -> {
			String[] pixel = string.split(";");
			return new Pixel(Integer.parseInt(pixel[0]), Integer.parseInt(pixel[1]));
		}).collect(Collectors.toSet()));
	}

	public static void main(String[] args) {
		launch(args);
	}
}

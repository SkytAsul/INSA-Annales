package s4.juin2021.exo1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Nuage implements Iterable<Pixel> {

	public Set<Pixel> pixels;

	public Nuage() {
		this(new HashSet<>());
	}

	public Nuage(Set<Pixel> pixels) {
		this.pixels = pixels;
	}

	public Nuage(int n, int maxX, int maxY) {
		// pour flex
		// this(Stream.generate(() -> new Pixel(random(0, maxX), random(0, maxY)))
		// .limit(n).collect(Collectors.toSet()));

		this(new HashSet<>(n));
		for (int i = 0; i < n; i++) {
			pixels.add(new Pixel(random(0, maxX), random(0, maxY)));
		}
	}

	public Pixel plusHaut() {
		Pixel highest = null;
		for (Pixel pixel : pixels) {
			if (highest != null) {
				if (pixel.y < highest.y)
					continue;
				if (pixel.y == highest.y && pixel.x < highest.x)
					continue;
			}
			highest = pixel;
		}
		return highest;
	}

	public Pixel plusBas() {
		Pixel lowest = null;
		for (Pixel pixel : pixels) {
			if (lowest != null) {
				if (pixel.y > lowest.y)
					continue;
				if (pixel.y == lowest.y && pixel.x > lowest.x)
					continue;
			}
			lowest = pixel;
		}
		return lowest;
	}

	@Override
	public Iterator<Pixel> iterator() {
		return pixels.iterator();
	}

	public List<Pixel> quickHull() {
		Pixel lowest = plusBas();
		Pixel highest = plusHaut();
		List<Pixel> enveloppe = new ArrayList<>();
		enveloppe.addAll(quickHullR(lowest, highest, pixels));
		enveloppe.addAll(quickHullR(highest, lowest, pixels));
		return enveloppe;
	}

	public static Collection<Pixel> aDroite(Pixel deb, Pixel fin, Collection<Pixel> pixels) {

		List<Pixel> right = new ArrayList<>();
		for (Pixel pixel : pixels) {
			if (Pixel.alpha(deb, fin, pixel) < 0) { // attention à bien mettre Pixel.alpha
				right.add(pixel);
			}
		}
		return right;

		// juste pour flex
		// return pixels.stream().filter(pixel -> Pixel.alpha(deb, fin, pixel) < 0).toList();

	}

	public static Pixel plusADroite(Pixel deb, Pixel fin, Collection<Pixel> pixels) {

		int minAlpha = 0;
		Pixel rightmost = null;

		for (Pixel pixel : pixels) {
			int alpha = Pixel.alpha(deb, fin, pixel);
			if (alpha < minAlpha) {
				// on a pas besoin de vérifier si "alpha < 0" pour être sûr que c'est à droite
				// car minAlpha est d'office initialisé à 0
				minAlpha = alpha;
				rightmost = pixel;
			}
		}

		return rightmost;

		// pour flex encore une fois
		// return pixels.stream().min(Comparator.comparingInt(pixel -> Pixel.alpha(deb, fin, pixel)))
		// .filter(pixel -> Pixel.alpha(deb, fin, pixel) < 0).orElse(null);

	}

	private static List<Pixel> quickHullR(Pixel deb, Pixel fin, Collection<Pixel> pixels) {

		Pixel rightmost = plusADroite(deb, fin, pixels);
		if (rightmost == null)
			return Arrays.asList(deb);

		List<Pixel> rights = new ArrayList<>();
		rights.addAll(quickHullR(deb, rightmost, pixels));
		rights.addAll(quickHullR(rightmost, fin, pixels));

		return rights;

	}

	public static int random(int min, int max) {
		// on peut aussi utiliser Math.random() ou bien new Random().nextInt
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}

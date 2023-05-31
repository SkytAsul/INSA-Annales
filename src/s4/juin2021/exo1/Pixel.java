package s4.juin2021.exo1;

public class Pixel {

	public int x;
	public int y;

	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Pixel[x=" + x + ", y=" + y + "]";
	}

	public static int alpha(Pixel a, Pixel b, Pixel c) {
		return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y);
	}

}

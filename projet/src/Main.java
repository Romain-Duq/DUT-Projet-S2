
public class Main {
	public static void main(String[] args) {
		String[] images = { "images/eau.png", "images/herbe.png", "images/rocher.png","images/plage.png"};
		SuperPlateau map = new SuperPlateau(images, 20, 25);
		map.setJeu();
		map.afficher();
	}
}

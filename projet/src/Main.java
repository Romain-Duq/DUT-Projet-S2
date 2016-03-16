public class Main {
	public static void main(String[] args) {
		String[] images = { "images/eau.png", "images/herbe.png", "images/rocher.png","images/bateau1.png","images/bateau2.png","images/cle.png","images/coffre.png"};
		SuperPlateau map = new SuperPlateau(images, 20, 25);
		map.setJeu();
		map.afficher();
	}
}
public class Main {
	public Main(int taille, int proportion) {
		String[] images = { "img/eau.png", "img/herbe.png", "img/rocher.png","img/bateau1.png","img/bateau2.png","img/cle.png","img/coffre.png"};

		SuperPlateau map = new SuperPlateau(images, taille, proportion);
		map.setJeu();
		map.afficher();
	}
}	

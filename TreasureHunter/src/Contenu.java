import java.awt.Image;

import javax.swing.ImageIcon;

public class Contenu {
	Image image;
	String nom;
	
	static String BATEAU1 = "ressource/OBJECTS/bateau1.png";
	static String BATEAU2 = "ressource/OBJECTS/bateau2.png";

	public Contenu() {}
	public Contenu(String url) {
		nom = url;
		image = new ImageIcon(url).getImage();
	}
}

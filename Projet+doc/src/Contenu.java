import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Contenu {
	Image image;
	String nom;
	
	static String BATEAU1 = "ressource/OBJECTS/bateau1.png";
	static String BATEAU2 = "ressource/OBJECTS/bateau2.png";

	public Contenu() {}
	/**
	 * Ajout d'un contenu
	 * @param url
	 * 		Contenu
	 */
	public Contenu(String url) {
		nom = url;
		image = new ImageIcon(url).getImage();
	}
}

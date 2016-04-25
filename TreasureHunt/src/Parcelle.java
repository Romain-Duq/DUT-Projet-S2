import java.awt.Image;

import javax.swing.ImageIcon;

public class Parcelle {
	Image imageFond;
	Contenu contenu;
	boolean traversable;
	
	static String EAU = "ressource/TERRAIN/eau.png";
	static String HERBE = "ressource/TERRAIN/herbe.png";
	
	public Parcelle(){}
	
	public Parcelle(String URLimagefond, boolean traversable) {
		this.imageFond = new ImageIcon(URLimagefond).getImage();
		this.contenu = null;
		this.traversable = traversable;
	}
	
	public void ajouterContenu(Contenu contenu, boolean traversable){
		this.contenu = contenu;
		this.traversable = traversable;
	}
	
	public void videContenu(){
		this.contenu = null;
		this.traversable = true;
	}
}

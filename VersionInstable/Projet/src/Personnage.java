
public class Personnage extends Element {
	int pos_x, pos_y, nbdeplacement;
	boolean estSelect = false;
	String color;
	String nom;
	
	static String ExplorateurBas = "ExploBas";

	public Personnage(String nom, String image, String color) {
		super(image);
		this.nom = nom;
		this.color = color;
		this.nbdeplacement = 3;
	}
	
	public void setPosition(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
}


public class Personnage extends Element {
	int pos_x, pos_y, nbdeplacement, oriantation;
	boolean estSelect = false;
	String[] tabImage;
	String color;
	String nom;
	
	static int BAS = 0;
	static int HAUT = 1;
	static int GAUCHE = 2;
	static int DROITE = 3;
	
	static String EXPLORATEUR = "Eplorateur";
	static String[] Explorateur = new String[]{"ExploBas", "ExploHaut", "ExploGauche", "ExploDroite"};

	public Personnage(String nom, int oriantation, String color) {
		super(null);
		if (nom == Personnage.EXPLORATEUR){
			this.image = Explorateur[oriantation];
		}
	
		this.nom = nom;
		this.color = color;
		this.nbdeplacement = 3;
	}
	
	public void setOrientation(int oriantation){
		if (this.nom == Personnage.EXPLORATEUR){
			this.image = Explorateur[oriantation];
		}
	}
	
	public void setPosition(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
}

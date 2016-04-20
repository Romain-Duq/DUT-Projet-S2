
public class Parcelle {
	String fond;
	Element contenu;
	boolean traversable, brouillar;
	
	int pos_x, pos_y;
	
	static String EAU = "eau";
	static String HERBE = "herbe";
	static String PLAGEBas = "plageBas";
	static String PLAGEHaut = "plageHaut";
	static String PLAGEGauche = "plageGauche";
	static String PLAGEDroite = "plageDroite";
	static String PLAGEANGLEHautGauche = "plageAngleHautGauche";
	static String PLAGEANGLEHautDroite = "plageAngleHautDroite";
	static String PLAGEANGLEBasGauche = "plageAngleBasGauche";
	static String PLAGEANGLEBasDroite = "plageAngleBasDroite";
	
	public Parcelle(Element contenu, String fond, boolean traversable, int pos_x, int pos_y) {
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.contenu = contenu;
		this.fond = fond;
		this.traversable = traversable;
		this.brouillar = true;
	}
	
	public Parcelle(String fond, boolean traversable, int pos_x, int pos_y){
		this(new Element(Element.VIDE), fond, traversable, pos_x, pos_y);
	}

	public void addContenu(Element contenu , boolean traversable){
		this.contenu = contenu;
		this.traversable = traversable;
	}
}


public class Personnage {
	private Parcelle parcelle;
	private int numPerso;
		static int CHERCHER = 1;
		static int VOLEUR = 2;
	private int orientation;
		static int NORTH = 1; // nord
		static int WEST = 2; // ouest
		static int EAST = 3; // est
		static int SOUTH = 4; // sud
	private int vie;
	
	public Personnage(int typePerso, int orientation){
		this.numPerso = typePerso;
		this.orientation = orientation;
		this.vie = 100;	
		this.parcelle = new Parcelle(orientation + 4);
	}
	
	public void changerOrientation(int orientation){
		this.orientation = orientation;
	}
	
	public int getVie(){
		return vie;
	}
}

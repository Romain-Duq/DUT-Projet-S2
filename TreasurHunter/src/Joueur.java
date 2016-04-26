
public class Joueur {
	int posX_Bateau, posY_Bateau, numEquipe;
	boolean gagne;
	Personnage[] perso;
	String nom;
	
	public Joueur(int posX_Bateau, int posY_Bateau, Personnage[] perso, String nom, int numEquipe) {
		this.posX_Bateau = posX_Bateau;
		this.posY_Bateau = posY_Bateau;
		this.perso = perso;
		this.nom = nom;
		this.numEquipe = numEquipe;
	}
}

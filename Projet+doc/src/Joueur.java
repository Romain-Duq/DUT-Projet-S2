/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Joueur {
	int posX_Bateau, posY_Bateau, numEquipe;
	boolean gagne;
	Personnage[] perso;
	String nom;
	
	/**
	 * Creation d'un joueur
	 * 
	 * @param posX_Bateau
	 * 		position x du bateau
	 * @param posY_Bateau
	 * 		position y du bateau
	 * @param perso
	 * 		type de personnage
	 * @param nom
	 * 		nom du joueur
	 * @param numEquipe
	 * 		numero de son equipe
	 */
	public Joueur(int posX_Bateau, int posY_Bateau, Personnage[] perso, String nom, int numEquipe) {
		this.posX_Bateau = posX_Bateau;
		this.posY_Bateau = posY_Bateau;
		this.perso = perso;
		this.nom = nom;
		this.numEquipe = numEquipe;
	}
}

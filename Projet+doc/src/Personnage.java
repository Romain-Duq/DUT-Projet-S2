import javax.swing.ImageIcon;
/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Personnage extends Contenu{
	
	static String EXPLORATEUR = "EXPLORATEUR";
	static String GUERRIER = "GUERRIER";
	static String PIEGEUR = "PIEGEUR";
	static String VOLEUR = "VOLEUR";
	
	static String FACE = "face";
	static String DROITE = "droite";
	static String DOS = "dos";
	static String GAUCHE = "gauche";
	
	String typePerso, oriantaion;
	
	int pos_x, pos_y, pm, pmInitiale, vie, vieMax, energie, energieMax, numEquipe;
	boolean cle, tresor, dansLeBateau, select;
/**
 * Creer personnage
 * 
 * @param typePerso
 * 		type de personnage
 * @param oriantaion
 * 		orientation du personnage
 * @param pos_x
 * 		position x du personnage
 * @param pos_y
 * 		position y du personnage
 * @param numEquipe
 * 		numero de l'equipe du personnage
 */
	public Personnage(String typePerso, String oriantaion, int pos_x, int pos_y, int numEquipe) {
		super("ressource/" + typePerso + "/" + oriantaion + ".png");
		this.typePerso = typePerso;
		this.oriantaion = oriantaion;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.numEquipe = numEquipe;
		if(typePerso == Personnage.EXPLORATEUR){
			this.pm = 3;
			this.vie = 200;
		}else if(typePerso == Personnage.GUERRIER){
			this.pm = 2;
			this.vie = 100;
		}else if(typePerso == Personnage.PIEGEUR){
			this.pm = 3;
			this.vie = 50;
		}else if(typePerso == Personnage.VOLEUR){
			this.pm = 4;
			this.vie = 50;
		}
		this.energie = 100;
		this.energieMax = this.energie;
		this.vieMax = this.vie;
		this.pmInitiale = pm;
	}
	/**
	 * Changement d'orientation
	 * @param oriantaion
	 * 		nouvelle orientation
	 */
	public void changerOriantation(String oriantaion){
		super.image = new ImageIcon("ressource/" + typePerso + "/" + oriantaion + ".png").getImage();
		this.oriantaion = oriantaion;
	}
}

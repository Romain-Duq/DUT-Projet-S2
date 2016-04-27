/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Rocher extends Contenu{
	boolean cle;
	boolean cleIci;
	boolean coffre;
	boolean coffreIci;
/**
 * Creer rocher
 * @param cle
 * 		vrai si il y a la cle
 * @param coffre
 * 		vrai si il y a le coffre
 */
	public Rocher(boolean cle, boolean coffre) {
		super("ressource/OBJECTS/rocher.png");
		this.cle = cle;
		this.cleIci = cle;
		this.coffre = coffre;
		this.coffreIci = coffre;
	}
}

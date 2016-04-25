
public class Rocher extends Contenu{
	boolean cle;
	boolean cleIci;
	boolean coffre;
	boolean coffreIci;

	public Rocher(boolean cle, boolean coffre) {
		super("ressource/OBJECTS/rocher.png");
		this.cle = cle;
		this.cleIci = cle;
		this.coffre = coffre;
		this.coffreIci = coffre;
	}
}

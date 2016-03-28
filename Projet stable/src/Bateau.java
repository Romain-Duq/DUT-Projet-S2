
public class Bateau extends Element {
	int pos_x, pos_y;
	
	static String BATEAUFace = "bateauface";
	static String BATEAUDos = "bateauDos";
	
	public Bateau(String bateau, int pos_x, int pos_y){
		super(bateau);
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
}

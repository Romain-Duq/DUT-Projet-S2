
public class Personnage extends Element {
	int pos_x, pos_y;
	boolean estSelect = false;
	String color;
	
	static String ExplorateurBas = "ExploBas";

	public Personnage(String image, String color) {
		super(image);
		this.color = color;
	}
	
	public void setPosition(int pos_x, int pos_y){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
	}
}

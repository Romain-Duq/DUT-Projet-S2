import javax.swing.*;
public class Paramètres {
	private String taille = "20";
	private String proportion = "25";
	public Paramètres(){}
	
	public int getTaille(){
		return Integer.parseInt(taille);
	}
	
	public int getProportion(){
		return Integer.parseInt(proportion);
	}
	
	public void setParametre(){
		do{
			taille = JOptionPane.showInputDialog(null, "Rentrez une taille de grille : ");
			proportion = JOptionPane.showInputDialog(null, "Rentrez un pourcentage de rochers : ");
		}while(!taille.matches("\\d+") && !proportion.matches("\\d+"));
	}
}

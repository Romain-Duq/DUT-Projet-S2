import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		
		String taille;
		String pourcentage;
		do{
			taille = JOptionPane.showInputDialog(null, "Entre la taille de la carte :");
			pourcentage = JOptionPane.showInputDialog(null, "Entre la taille de la carte :");
		}while(!taille.matches("\\d+") && !pourcentage.matches("\\d+"));
		
		SuperPlateau map = new SuperPlateau( Integer.parseInt(taille), Integer.parseInt(pourcentage));
		map.setJeu();
		map.afficher();
	}
}
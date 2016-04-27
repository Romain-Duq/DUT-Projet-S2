/**
 * 
 * @author Lucas Gilmant, Benjamin Lelong, Romain Duquesne, Alexis Massiaux
 *
 */
public class Main {
	/**
	 * Principale
	 * @param args
	 */
	public static void main(String[] args) {
		Launcher laucheur = new Launcher();
		while (!laucheur.finLauncher) {
			laucheur.repaint();
		}
		
		laucheur.setVisible(false);
		new Partie(laucheur.tailleCarte, laucheur.pourcentageRocher);
	}
}

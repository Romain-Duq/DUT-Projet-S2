
public class Main {
	public static void main(String[] args) {
		Launcher laucheur = new Launcher();
		while (!laucheur.finLauncher) {
			laucheur.repaint();
		}
		
		laucheur.setVisible(false);
		new Partie(laucheur.tailleCarte, laucheur.pourcentageRocher);
	}
}

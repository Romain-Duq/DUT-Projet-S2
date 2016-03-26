import javax.swing.JOptionPane;

public class Partie {
	
	MenuJeux menu = new MenuJeux(); // on genere le menu
	DessinCarte dessinCarte;
	Carte carte;
	Fenetre fenetre;
	
	public Partie(){
		this.carte = new Carte(20, 5); // on cree la carte
		this.dessinCarte = new DessinCarte(carte); // on genere l'afficheur
		
		this.fenetre = new Fenetre("fenetre", dessinCarte, this.menu);
		//fenetre.fullScrean();
		
		// on cree les joueur
		Joueur joueurUn = new Joueur("benjamin", 0, carte);
		Joueur joueurDeux = new Joueur("Lulu", 1, carte);
		
		while(!joueurUn.win || !joueurDeux.win){
			
			JOptionPane.showMessageDialog(fenetre, "c'est le tour de " + joueurUn.pseudo);
			for (int i = 0; i < 3; i++) {
				this.tourDejeux(joueurUn, i);
			}
			JOptionPane.showMessageDialog(fenetre, "c'est le tour de " + joueurDeux.pseudo);
			for (int i = 0; i < 3; i++) {
				this.tourDejeux(joueurDeux, i);
			}	
		}
	}
	
	private void tourDejeux(Joueur joueur, int numeroPersonnage){
		joueur.personnages.get(numeroPersonnage).estSelect = true;
		this.menu.setJoueur(joueur, 3, numeroPersonnage);
		dessinCarte.setVisibilityCleCoffre(joueur.cle, joueur.coffre);
		while (menu.continu) {
			fenetre.repaint();
		}
		joueur.personnages.get(numeroPersonnage).estSelect = false;
	}
}

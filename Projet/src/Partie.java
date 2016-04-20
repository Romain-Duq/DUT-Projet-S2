import javax.swing.JOptionPane;

public class Partie {
	
	MenuJeux menu = new MenuJeux(); // on genere le menu
	DessinCarte dessinCarte;
	Carte carte;
	Fenetre fenetre;
	
	public Partie(){
		this.carte = new Carte(20, 5); // on cree la carte
		this.dessinCarte = new DessinCarte(carte); // on genere l'afficheur
		
		this.fenetre = new Fenetre( this.dessinCarte, this.menu);
		//fenetre.fullScrean();
		
		// on cree les joueur
		Joueur joueurUn = new Joueur("benjamin", 0, carte);
		Joueur joueurDeux = new Joueur("Lulu", 1, carte);
		
		this.menu.setJoueur(joueurUn, 0);
		this.dessinCarte.setJoueur(joueurUn);
		
		while(!joueurUn.win || !joueurDeux.win){
			
			this.dessinCarte.setJoueur(joueurUn);
			JOptionPane.showMessageDialog(fenetre, "c'est le tour de " + joueurUn.pseudo);
			for (int i = 0; i < 3; i++) {
				this.tourDejeux(joueurUn, i);
			}
			for (int i = 0; i < 3; i++) {
				joueurUn.personnages.get(i).nbdeplacement = 3;
			}
			this.dessinCarte.setJoueur(joueurDeux);
			JOptionPane.showMessageDialog(fenetre, "c'est le tour de " + joueurDeux.pseudo);
			for (int i = 0; i < 3; i++) {
				this.tourDejeux(joueurDeux, i);
			}
			for (int i = 0; i < 3; i++) {
				joueurDeux.personnages.get(i).nbdeplacement = 3;
			}
		}
	}
	
	private void tourDejeux(Joueur joueur, int numeroPersonnage){
		joueur.personnages.get(numeroPersonnage).estSelect = true;
		this.menu.setJoueur(joueur, numeroPersonnage);
		dessinCarte.setVisibilityCleCoffre(joueur.cle, joueur.coffre);
		while (menu.continu) {
			fenetre.repaint();
		}
		joueur.personnages.get(numeroPersonnage).estSelect = false;
	}
}

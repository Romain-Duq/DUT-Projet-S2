import javax.swing.JOptionPane;

public class Partie {
	public Partie(int taille, int pourcentage) {
		
		MenuPersonnage menuPersonnage = new MenuPersonnage(0, taille);
		while (menuPersonnage.joueur == null) {
			try {
			    Thread.sleep(100);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		Joueur joueur1 = menuPersonnage.joueur;
		menuPersonnage = new MenuPersonnage(1, taille);
		while (menuPersonnage.joueur == null) {
			try {
			    Thread.sleep(100);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		Joueur joueur2 = menuPersonnage.joueur;
		
		Carte carte = new Carte( taille,(float) pourcentage, joueur1, joueur2);
		PanelAction panelAction1 = new PanelAction(joueur1.perso[0], carte);
		Fenetre fenetre1 = new Fenetre(new DessinCarte(carte, joueur1), panelAction1, joueur1.nom);
		PanelAction panelAction2 = new PanelAction(joueur2.perso[0], carte);
		Fenetre fenetre2 = new Fenetre(new DessinCarte(carte, joueur2), panelAction2, joueur2.nom);
		
		while(!joueur1.gagne && !joueur2.gagne && equipeMorte(joueur1,joueur2)==0){
			fenetre1.setVisible(true);
			for (int i = 0; i < joueur1.perso.length; i++) {
				panelAction1.setPersonnage(joueur1.perso[i]);
				while(!panelAction1.finTour){
					fenetre1.repaint();
				}	
				panelAction1.finTour = false;
			}
			fenetre1.setVisible(false);
			
			fenetre2.setVisible(true);
			for (int i = 0; i < joueur2.perso.length; i++) {
				panelAction2.setPersonnage(joueur2.perso[i]);
				while(!panelAction2.finTour){
					fenetre2.repaint();
				}	
				panelAction2.finTour = false;
			}
			fenetre2.setVisible(false);
		}
		JOptionPane.showMessageDialog(null, "Le joueur "+equipeMorte(joueur1,joueur2)+" a remporté la partie car l'équipe adverse est morte !");
		System.exit(0);
	}
	int equipeMorte(Joueur joueur1,Joueur joueur2){
		if(joueur1.perso[0].mort && joueur1.perso[1].mort && joueur1.perso[2].mort)return 1;
		if(joueur2.perso[0].mort && joueur2.perso[1].mort && joueur2.perso[2].mort)return 2;
		return 0;
	}
}

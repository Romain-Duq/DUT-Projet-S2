import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DessinCarte extends JPanel {
	Carte carte;
	boolean cle, coffre;
	Joueur joueur;

	static HashMap<String, Image> IMAGE = new HashMap<>();

	public DessinCarte(Carte carte) {
		this.carte = carte;
		this.setSize(carte.taille * 32 + 2, carte.taille * 32 + 28);
	}
	
	public void refreche(){
		this.repaint();
	}
	
	public void setJoueur(Joueur joueur){
		this.joueur = joueur;
	}
	
	public void setBrouillard(){
		for (int i = 0; i < this.carte.parcelles.length; i++) {
			for (int j = 0; j < this.carte.parcelles.length; j++) {
				this.carte.parcelles[i][j].brouillar = true;
			}
		}
		
		for (int i = 0; i < this.joueur.personnages.size(); i++) {
			int x = this.joueur.personnages.get(i).pos_x;
			int y = this.joueur.personnages.get(i).pos_y;
			for (int j = 0; j <= 3; j++) {
				if(x - j >= 0){
					this.carte.parcelles[x - j][y].brouillar = false;
				}
				if(x + j < this.carte.parcelles.length){
					this.carte.parcelles[x + j][y].brouillar = false;
				}
				if(y - j >= 0){
					this.carte.parcelles[x][y - j].brouillar = false;
				}
				if(y + j < this.carte.parcelles.length){
					this.carte.parcelles[x][y + j].brouillar = false;
				}
			}
			for (int j = 0; j <= 2; j++) {
				if(x - j >= 0){
					this.carte.parcelles[x - j][y + 1].brouillar = false;
				}
				if(x + j < this.carte.parcelles.length){
					this.carte.parcelles[x + j][y + 1].brouillar = false;
				}
				if(y - j >= 0){
					this.carte.parcelles[x + 1][y - j].brouillar = false;
				}
				if(y + j < this.carte.parcelles.length){
					this.carte.parcelles[x + 1][y + j].brouillar = false;
				}
			}
		}
	}

	public void paintComponent(Graphics g) {
		setBrouillard();
		for (int i = 0; i < carte.taille; i++) {
			for (int j = 0; j < carte.taille; j++) {
				g.drawImage(IMAGE.get(carte.parcelles[i][j].fond), i * 32, j * 32, null);
				if(!carte.parcelles[i][j].brouillar){
					
						if (carte.parcelles[i][j].contenu.image != null) {
							if (carte.parcelles[i][j].contenu instanceof Rocher && ((Rocher) carte.parcelles[i][j].contenu).contenu == 1 && this.cle) {
								g.drawImage(IMAGE.get(Bateau.BATEAUDos), i * 32, j * 32, null);
							} else if (carte.parcelles[i][j].contenu instanceof Rocher && ((Rocher) carte.parcelles[i][j].contenu).contenu == 2 && this.coffre) {
								g.drawImage(IMAGE.get(Bateau.BATEAUFace), i * 32, j * 32, null);
							}else if (carte.parcelles[i][j].contenu instanceof Personnage){
								if(((Personnage) carte.parcelles[i][j].contenu).estSelect){
									g.drawImage(IMAGE.get("joueurSelect" + ((Personnage) carte.parcelles[i][j].contenu).color), i * 32, j * 32, null);
								}else{
									g.drawImage(IMAGE.get("joueur" + ((Personnage) carte.parcelles[i][j].contenu).color), i * 32, j * 32, null);
								}
							}else if(carte.parcelles[i][j].contenu instanceof Personnage){
								g.drawImage(IMAGE.get("joueurSelect" + ((Personnage) carte.parcelles[i][j].contenu).color), i * 32, j * 32, null);
							}
								g.drawImage(IMAGE.get(carte.parcelles[i][j].contenu.image), i * 32, j * 32, null);
						
						}
				}else{
					g.drawImage(new ImageIcon("images/brouillar.png").getImage(), i * 32, j * 32, null);
				}
			}
		}
	}
	
	public void setVisibilityCleCoffre(boolean cle, boolean coffre){
		this.cle = cle;
		this.coffre = coffre;
	}

	static void initImage() {
		IMAGE.put( "bordure", new ImageIcon("images/bordure.png").getImage());
		IMAGE.put( "joueurSelect" + Joueur.couleur[0], new ImageIcon("images/personnageSelectRouge.png").getImage()); // rouge
		IMAGE.put( "joueurSelect" + Joueur.couleur[1], new ImageIcon("images/personnageSelectBleu.png").getImage()); // bleu
		IMAGE.put( "joueur" + Joueur.couleur[0], new ImageIcon("images/personnageRouge.png").getImage()); // rouge
		IMAGE.put( "joueur" + Joueur.couleur[1], new ImageIcon("images/personnageBleu.png").getImage()); // bleu

		IMAGE.put(Parcelle.EAU, new ImageIcon("images/eau.png").getImage());
		IMAGE.put(Parcelle.HERBE, new ImageIcon("images/herbe.png").getImage());
		IMAGE.put(Parcelle.PLAGEBas, new ImageIcon("images/plage/bas.png").getImage());
		IMAGE.put(Parcelle.PLAGEHaut, new ImageIcon("images/plage/haut.png").getImage());
		IMAGE.put(Parcelle.PLAGEGauche, new ImageIcon("images/plage/gauche.png").getImage());
		IMAGE.put(Parcelle.PLAGEDroite, new ImageIcon("images/plage/droite.png").getImage());
		IMAGE.put(Parcelle.PLAGEANGLEHautGauche, new ImageIcon("images/plage/angleHautGauche.png").getImage());
		IMAGE.put(Parcelle.PLAGEANGLEHautDroite, new ImageIcon("images/plage/angleHautDroite.png").getImage());
		IMAGE.put(Parcelle.PLAGEANGLEBasGauche, new ImageIcon("images/plage/anglebasGauche.png").getImage());
		IMAGE.put(Parcelle.PLAGEANGLEBasDroite, new ImageIcon("images/plage/anglebasDroite.png").getImage());

		IMAGE.put(Rocher.ROCHER, new ImageIcon("images/rocher.png").getImage());

		IMAGE.put(Bateau.BATEAUFace, new ImageIcon("images/bateauFace.png").getImage());
		IMAGE.put(Bateau.BATEAUDos, new ImageIcon("images/bateauDos.png").getImage());
		
		IMAGE.put(Personnage.Explorateur[0], new ImageIcon("images/Explorateur/Down.png").getImage());
		IMAGE.put(Personnage.Explorateur[1], new ImageIcon("images/Explorateur/Up.png").getImage());
		IMAGE.put(Personnage.Explorateur[2], new ImageIcon("images/Explorateur/Right.png").getImage());
		IMAGE.put(Personnage.Explorateur[3], new ImageIcon("images/Explorateur/Left.png").getImage());
	}
}

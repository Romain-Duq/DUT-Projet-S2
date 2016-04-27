import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DessinCarte extends JPanel{
	
	private Carte carte;
	private int taille;
	boolean[][] visible;
	Joueur joueur;

	public DessinCarte(Carte carte, Joueur joueur) {
		int taille = carte.parcelles.length;
		this.setSize(taille * 32, taille * 32);
		this.carte = carte;
		this.taille = taille;
		this.joueur = joueur;
	}
	
	public void paint(Graphics g) {
		setVisible();
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				g.drawImage(carte.parcelles[i][j].imageFond, i * 32, j * 32, null);
				if(carte.parcelles[i][j].contenu instanceof Piege && joueur.numEquipe != ((Piege)carte.parcelles[i][j].contenu).numEquipe && visible[i][j]){
					//Bah rien ^^"
				}else if(carte.parcelles[i][j].contenu instanceof Personnage && visible[i][j]){
					if(((Personnage)carte.parcelles[i][j].contenu).numEquipe == 0){
						if(((Personnage)carte.parcelles[i][j].contenu).select){
							g.drawImage(new ImageIcon("ressource/MASQUE/SelectBleu.png").getImage(), i * 32, j * 32, null);
						}else{
							g.drawImage(new ImageIcon("ressource/MASQUE/Bleu.png").getImage(), i * 32, j * 32, null);
						}
					}else{
						if(((Personnage)carte.parcelles[i][j].contenu).select){
							g.drawImage(new ImageIcon("ressource/MASQUE/SelectRouge.png").getImage(), i * 32, j * 32, null);
						}else{
							g.drawImage(new ImageIcon("ressource/MASQUE/Rouge.png").getImage(), i * 32, j * 32, null);
						}
					}
					g.drawImage(carte.parcelles[i][j].contenu.image, i * 32, j * 32, null);
				}else if(carte.parcelles[i][j].contenu != null && visible[i][j]){
					g.drawImage(carte.parcelles[i][j].contenu.image, i * 32, j * 32, null);
				}else if(carte.parcelles[i][j].contenu instanceof Rocher){
					g.drawImage(carte.parcelles[i][j].contenu.image, i * 32, j * 32, null);
				}
				if(!visible[i][j]){
					g.drawImage(new ImageIcon("ressource/MASQUE/brouillar.png").getImage(), i * 32, j * 32, null);
				}
			}
		}
	}
	
	public void setVisible(){
		this.visible = new boolean[taille][taille];
		for (int i = 0; i < joueur.perso.length; i++) {
			int pos_x = joueur.perso[i].pos_x;
			int pos_y = joueur.perso[i].pos_y;
			
			for (int j = 0; j <= 3; j++) {
				if(pos_y + j < taille){
					visible[pos_x][pos_y + j] = true;
				}
				if(pos_x + j < taille){
					visible[pos_x + j][pos_y] = true;
				}
				if(pos_y - j > 0){
					visible[pos_x][pos_y - j] = true;
				}
				if(pos_x - j > 0){
					visible[pos_x - j][pos_y] = true;
				}
			}
			for (int j = 0; j <= 2; j++) {
				if(pos_y + j < taille){
					visible[pos_x + 1][pos_y + j] = true;
				}
				if(pos_y + j < taille){
					visible[pos_x - 1][pos_y + j] = true;
				}
				if(pos_x + j < taille){
					visible[pos_x + j][pos_y + 1] = true;
				}
				if(pos_x + j < taille){
					visible[pos_x + j][pos_y - 1] = true;
				}
				if(pos_y - j > 0){
					visible[pos_x + 1][pos_y - j] = true;
				}
				if(pos_y - j > 0){
					visible[pos_x - 1][pos_y - j] = true;
				}
				if(pos_x - j > 0){
					visible[pos_x - j][pos_y + 1] = true;
				}
				if(pos_x - j > 0){
					visible[pos_x - j][pos_y - 1] = true;
				}
			}
		}
	}
}

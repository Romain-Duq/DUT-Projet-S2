import java.util.Random;

public class Carte {
	Parcelle[][] parcelles;
	int taille, nbRocher;
	
	Bateau[] bateau = new Bateau[2];

	public Carte(int taille, float proportion) {
		this.taille = taille;
		this.nbRocher = (int) ((taille - 2) * (taille - 2) * (proportion / 100));
		genCarte(taille);
	}
	

	public void genCarte(int taille) {
		this.parcelles = new Parcelle[taille][taille];
		Random random = new Random();
		int tour = 0;
		do {
			tour++;
			for (int i = 0; i < this.parcelles.length; i++) {
				for (int j = 0; j < this.parcelles.length; j++) {
					if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
						this.parcelles[i][j] = new Parcelle(Parcelle.EAU, false, i, j);
					} else if (i > 1 && j == taille - 2 && i < taille - 2) {
						this.parcelles[i][j] = new Parcelle(Parcelle.PLAGEBas, true, i, j);
					} else if( i > 1 && j == 1 && i < taille - 2){
						this.parcelles[i][j] = new Parcelle(Parcelle.PLAGEHaut, true, i, j);
					}else if( j > 1 && i == 1 && j < taille - 2){
						this.parcelles[i][j] = new Parcelle(Parcelle.PLAGEGauche, true, i, j);
					}else if( j > 1 && i == taille - 2 && j < taille - 2){
						this.parcelles[i][j] = new Parcelle(Parcelle.PLAGEDroite, true, i, j);
					}else {
						this.parcelles[i][j] = new Parcelle(Parcelle.HERBE, true, i, j);
					}
				}
			}
			
			this.parcelles[1][1] = new Parcelle(Parcelle.PLAGEANGLEHautGauche, true, 1, 1);
			this.parcelles[taille - 2][1] = new Parcelle(Parcelle.PLAGEANGLEHautDroite, true, taille - 2, 1);
			this.parcelles[1][taille - 2] = new Parcelle(Parcelle.PLAGEANGLEBasGauche, true, 1, taille - 2);
			this.parcelles[taille - 2][taille - 2] = new Parcelle(Parcelle.PLAGEANGLEBasDroite, true, taille - 2, taille - 2);
			
			int pos_x = random.nextInt(taille - 4) + 2;
			bateau[0] = new Bateau(Bateau.BATEAUFace,  pos_x, 1);
			parcelles[pos_x][1].addContenu(bateau[0], true);
			
			pos_x = random.nextInt(taille - 4) + 2;
			bateau[1] = new Bateau(Bateau.BATEAUDos, pos_x, taille - 2);
			parcelles[pos_x][taille - 2].addContenu(bateau[1], true);
			
			int rocher = 0;
			int cle = random.nextInt(nbRocher);
			int coffre = random.nextInt(nbRocher);
			while (rocher < nbRocher) {
				int x = random.nextInt(taille - 2) + 1;
				int y = random.nextInt(taille - 2) + 1;
				if (parcelles[x][y].contenu.image == null && !bateauACote(x,y)) {
					parcelles[x][y].addContenu(new Rocher(), false);
					if(rocher == cle){
						((Rocher) parcelles[x][y].contenu).contenu = 1;
					}else if(rocher == coffre){
						((Rocher) parcelles[x][y].contenu).contenu = 2;
					}
					rocher++;
				}
			}
			System.out.println("debug : genMap : "+ tour);
		} while (!genDone());
		
	}

	private boolean genDone() {
		int[][] tableau = new int[taille - 2][taille - 2];
		boolean bateau1 = false;
		boolean bateau2 = false;
		tableau[0][0] = 1; 
		boolean estBloque = true;
		while (estBloque) { 
			estBloque = true;
			for (int i = 0; i < tableau.length; i++) {
				for (int j = 0; j < tableau.length; j++) {
					if (this.parcelles[i + 1][j + 1].contenu instanceof Rocher) {
						if (this.parcelles[i + 2][j + 1].traversable == false && this.parcelles[i][j + 1].traversable == false
								&& this.parcelles[i + 1][j + 2].traversable == false
								&& this.parcelles[i + 1][j].traversable == false) {
							return false; 
						}
						tableau[i][j] = 2; 
						estBloque = false;
					}
					if (tableau[i][j] == 1) {
						if (this.parcelles[i + 2][j + 1].traversable && tableau[i + 1][j] == 0) { 
							tableau[i + 1][j] = 1; 
							estBloque = false; 
						}
						if (this.parcelles[i][j + 1].traversable && tableau[i - 1][j] == 0) {
							tableau[i - 1][j] = 1;
							estBloque = false;
						}
						if (this.parcelles[i + 1][j + 2].traversable && tableau[i][j + 1] == 0) { 
							tableau[i][j + 1] = 1;
							estBloque = false;
						}
						if (this.parcelles[i + 1][j].traversable && tableau[i][j - 1] == 0) {
							tableau[i][j - 1] = 1; 
							estBloque = false;
						}
						if (this.parcelles[i + 1][j + 2].contenu instanceof Bateau) {
							bateau1 = true;
						}
						if (this.parcelles[i + 1][j].contenu instanceof Bateau) {
							bateau2 = true;
						}
					}
				}
			}
		}
		int cpt = 0;
		for (int k = 0; k < taille - 2; k++) {
			for (int l = 0; l < taille - 2; l++) {
				if (tableau[k][l] == 2) {
					int voisin = 0;
					if (k == 0) {
						voisin += 2;
					} else {
						voisin += tableau[k - 1][l];
					}
					if (k == taille - 3) {
						voisin += 2;
					} else {
						voisin += tableau[k + 1][l];
					}

					if (l == 0) {
						voisin += 2;
					} else {
						voisin += tableau[k][l - 1];
					}
					if (l == taille - 3) {
						voisin += 2;
					} else {
						voisin += tableau[k][l + 1];
					}
					if (voisin < 4 || voisin >= 8) {
						return false;
					}
					cpt++; 
				}
			}
		}
		return cpt == nbRocher && bateau1 && bateau2;
	}
	private boolean bateauACote(int x, int y){
		return parcelles[x-1][y].contenu instanceof Bateau || parcelles[x+1][y].contenu instanceof Bateau || parcelles[x][y-1].contenu instanceof Bateau || parcelles[x][y+1].contenu instanceof Bateau;
	}
}

import java.util.Random;

public class SuperPlateau {
	private Plateau map;
	int taille;
	int nbRocher;
	private Parcelle[][] parcelle;

	public SuperPlateau(String[] images, int taille, float proportion) {
		this.taille = taille;
		this.nbRocher = (int)((taille-2)*(taille-2)*(proportion/100));
		this.parcelle = genMap(taille);
		this.map = new Plateau(images, taille);
	}

	public void setJeu() {
		int[][] type = new int[taille][taille];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				type[i][j] = parcelle[i][j].numImage;
			}
		}
		this.map.setJeu(type);
	}

	public void setParcelle(Parcelle parcelle, int x, int y) {
		this.parcelle[x][y] = parcelle;
	}

	public void afficher() {
		this.map.affichage();
	}

	private Parcelle[][] genMap(int taille) {
		Parcelle[][] parcelle = new Parcelle[taille][taille];
		do {
			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					if (j == 0 || i == 0 || i == taille - 1 || j == taille - 1) {
						parcelle[i][j] = new Parcelle("mer");
					} else if(j == 1 || i == 1 || i == taille - 2 || j == taille - 2){
						parcelle[i][j] = new Parcelle("plage");
					}else {
						parcelle[i][j] = new Parcelle("herbe");
					}
				}
			}
			int rocher = 0;
			while(rocher < nbRocher){
				Random rdm = new Random();
				int x = rdm.nextInt(taille-4) + 2;
				int y = rdm.nextInt(taille-4) + 2;
				if(parcelle[x][y].getNumImage()!=3){
					parcelle[x][y].changerParcelle(new Parcelle(Parcelle.ROCHER));
					rocher++;
				}
			}
		}while (!genDone(parcelle));
		
		Random random = new Random();
		int cpt = 0;
		int tresor,coffre;
		do{
			tresor = random.nextInt(nbRocher);
			coffre = random.nextInt(nbRocher);
		}while(tresor == coffre);
		
		for (int i = 0; i < parcelle.length; i++) {
			for (int j = 0; j < parcelle.length; j++) {
				if(parcelle[i][j].getNumImage() == 3){
					if(tresor == cpt){
						parcelle[i][j].contenu = "clé";
					}else if(coffre == cpt){
						
					}
					cpt++;
				}
			}
		}
		
		return parcelle;
	}

	private boolean genDone(Parcelle[][] parcelle) {
		int[][] tableau = new int[taille - 2][taille - 2];

		tableau[0][0] = 1;
		boolean estBloque = true;
		while (estBloque) {
			estBloque = true;
			for (int i = 0; i < tableau.length; i++) {
				for (int j = 0; j < tableau.length; j++) {
					if (parcelle[i + 1][j + 1].getNumImage() == 3) {
						if(parcelle[i+2][j+1].getTraversable()==false && parcelle[i][j+1].getTraversable()==false && parcelle[i+1][j+2].getTraversable()==false && parcelle[i+1][j].getTraversable()==false){
							return false;
						}
						tableau[i][j] = 2;
						estBloque = false;
					}
					if (tableau[i][j] == 1) {
						if (parcelle[i + 2][j + 1].getTraversable() && tableau[i + 1][j] == 0) {
							tableau[i + 1][j] = 1;
							estBloque = false;
						}
						if (parcelle[i][j + 1].getTraversable() && tableau[i - 1][j] == 0) {
							tableau[i - 1][j] = 1;
							estBloque = false;
						}
						if (parcelle[i + 1][j + 2].getTraversable() && tableau[i][j + 1] == 0) {
							tableau[i][j + 1] = 1;
							estBloque = false;
						}
						if (parcelle[i + 1][j].getTraversable() && tableau[i][j - 1] == 0) {
							tableau[i][j - 1] = 1;
							estBloque = false;
						}
					}
				}
			}
		}
		for (int k = 0; k < taille - 2; k++) {
			for (int l = 0; l < taille - 2; l++) {
				if (tableau[k][l] == 0)
					return false;
			}
		}
		return true;
	}
}

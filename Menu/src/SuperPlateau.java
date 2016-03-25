import java.util.Random;

public class SuperPlateau {
	private Plateau map;
	int taille;
	int nbRocher;
	private Parcelle[][] parcelle;
	
	/**
     * Cr�ation du plateau
     * 
     * @param images
     *            Tableau d'images
     * @param taille
     * 			  Taille du plateau
     * @param proportion
     * 			   Proportion des rochers
     */
	public SuperPlateau(String[] images, int taille, float proportion) {
		this.taille = taille;
		this.nbRocher = (int) ((taille - 2) * (taille - 2) * (proportion / 100));
		this.parcelle = genMap(taille);
		this.map = new Plateau(images, taille);
	}
	
	/**
     * Modification du plateau
     */

	public void setJeu() {
		int[][] type = new int[taille + 2][taille + 2];
		for (int i = 0; i < taille; i++) {
			for (int j = 0; j < taille; j++) {
				type[i][j] = parcelle[i][j].getNumImage();
			}
		}
		this.map.setJeu(type);
	}

	/**
     * Affichage du plateau
     */
	public void afficher() {
		this.map.affichage();
	}
	
	/**
     * R�partition des objets
     * 
     * @param taille
     * 			  Taille du plateau
     * @return Tableau contenant le plateau de jeu rempli
     */

	private Parcelle[][] genMap(int taille) {
		Parcelle[][] parcelle = new Parcelle[taille][taille];
		do {
			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					if (j == 0 || i == 0 || i == taille - 1 || j == taille - 1) { // Permet
																					// de
																					// faire
																					// un
																					// contour
																					// de
																					// mer
						parcelle[i][j] = new Parcelle(Parcelle.EAU);
					} else {
						parcelle[i][j] = new Parcelle(Parcelle.HERBE); // Le
																		// reste
																		// de
																		// l'ile
																		// est
																		// de
																		// l'herbe
					}
				}
			}
			Random rdm = new Random();
			parcelle[rdm.nextInt(taille - 2) + 1][0] = new Parcelle(4); // une
																		// position
																		// random
																		// en
																		// haut
																		// sera
																		// pour
																		// le
																		// bateau
																		// 1
			parcelle[rdm.nextInt(taille - 2) + 1][taille - 1] = new Parcelle(5); // une
																					// position
																					// random
																					// en
																					// bas
																					// sera
																					// pour
																					// le
																					// bateau
																					// 2
			int rocher = 0;
			while (rocher < nbRocher) {
				int x = rdm.nextInt(taille - 2) + 1;
				int y = rdm.nextInt(taille - 2) + 1;
				if (parcelle[x][y].getNumImage() != 3
						&& !(parcelle[x][y - 1].isBateau())
						&& !(parcelle[x][y + 1].isBateau())) { // Si il n'y a
																// pas déjà de
																// rocher et
																// qu'il ne
																// bloque pas un
																// bateau.
					parcelle[x][y]
							.changerParcelle(new Parcelle(Parcelle.ROCHER));
					rocher++;
				}
			}
			System.out.println("debug");
		} while (!genDone(parcelle)); // tant que toutes les cases ne sont pas
										// accessible, on redispose les rochers

		Random random = new Random();
		int cpt = 0;
		int tresor, coffre;
		do {
			tresor = random.nextInt(nbRocher);
			coffre = random.nextInt(nbRocher);
		} while (tresor == coffre); // on ne peut pas disposer le coffre et le
									// trésor sur le meme rocher

		for (int i = 0; i < parcelle.length; i++) {
			for (int j = 0; j < parcelle.length; j++) {
				if (parcelle[i][j].getNumImage() == 3) {
					if (tresor == cpt) {
						parcelle[i][j].putIn("clé"); // on met la clé dans un
														// rocher
					} else if (coffre == cpt) {
						parcelle[i][j].putIn("coffre"); // on met le coffre dans
														// un rocher
					}
					cpt++;
				}
			}
		}

		return parcelle;
	}

	/**
     * V�rification de l'accessabilit� du plateau
     * 
     * @param parcelle
     *            Tableau de parcelle repr�sentant le plateau
     *  @return 
     *  		   Vrai ou Faux
     */
	private boolean genDone(Parcelle[][] parcelle) {
		int[][] tableau = new int[taille - 2][taille - 2];
		boolean bateau1 = false;
		boolean bateau2 = false;
		tableau[0][0] = 1; // ceci représente un joueur virtuel qui va parcourir
							// le terrain pour verifier que toutes les parcelles
							// soient accessible
		boolean estBloque = true;
		while (estBloque) { // tant que notre "personne" a bougé on essaie
							// d'avancer
			estBloque = true;
			for (int i = 0; i < tableau.length; i++) {
				for (int j = 0; j < tableau.length; j++) {
					if (parcelle[i + 1][j + 1].getNumImage() == 3) {
						if (parcelle[i + 2][j + 1].getTraversable() == false
								&& parcelle[i][j + 1].getTraversable() == false
								&& parcelle[i + 1][j + 2].getTraversable() == false
								&& parcelle[i + 1][j].getTraversable() == false) {
							System.out.println("debug2");
							return false; // si c'est un rocher et qu'il est
											// inaccessible, on recommence la
											// disposition des rochers
						}
						tableau[i][j] = 2; // si le rocher n'est pas bloqué, on
											// le note par l'indice 2
						estBloque = false;
					}
					if (tableau[i][j] == 1) {
						if (parcelle[i + 2][j + 1].getTraversable()
								&& tableau[i + 1][j] == 0) { // si la case du
																// dessus est de
																// l'herbe
							tableau[i + 1][j] = 1; // on note la case par
													// l'indice 1 qui représente
													// le fait que notre joueur
													// virtuel a su aller jusque
													// là
							estBloque = false; // nous ne sommes pas bloqués car
												// une case s'est trouvée
												// accessible par le joueur.
						}
						if (parcelle[i][j + 1].getTraversable()
								&& tableau[i - 1][j] == 0) { // si la case du
																// dessous est
																// de l'herbe
							tableau[i - 1][j] = 1; // IDEM qu'au dessus
							estBloque = false;
						}
						if (parcelle[i + 1][j + 2].getTraversable()
								&& tableau[i][j + 1] == 0) { // si la case de
																// droite est de
																// l'herbe
							tableau[i][j + 1] = 1;// IDEM qu'au dessus
							estBloque = false;
						}
						if (parcelle[i + 1][j].getTraversable()
								&& tableau[i][j - 1] == 0) { // si la case de
																// gauche est de
																// l'herbe
							tableau[i][j - 1] = 1; // IDEM qu'au dessus
							estBloque = false;
						}
						if (parcelle[i + 1][j + 2].isBateau()) {
							bateau1 = true;
						}
						if (parcelle[i + 1][j].isBateau()) {
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
					cpt++; // Si on trouve un 0, cela veut dire que l'on a pas
							// sû l'atteindre comme un rocher (2) ou de l'herbe
							// (1)
				}
			}
		}
		return cpt == nbRocher && bateau1 && bateau2;
	}
}
import java.util.Random;

import javax.swing.JOptionPane;

//import java.util.Random;

public class Carte {
	Parcelle[][] parcelles;
	Joueur[] joueurs = new Joueur[2];
	int nbRocher;

	public Carte(int taille, float proportion, Joueur joueur1, Joueur joueur2) {
		this.nbRocher = (int) ((taille - 2) * (taille - 2) * (proportion / 100));
		this.joueurs[0] = joueur1;
		this.joueurs[1] = joueur2;
		int cpt = 0;
		do {
			this.parcelles = new Parcelle[taille][taille];

			for (int i = 0; i < taille; i++) {
				for (int j = 0; j < taille; j++) {
					if (i == 0 || i == taille - 1 || j == 0 || j == taille - 1) {
						parcelles[i][j] = new Parcelle(Parcelle.EAU, false);
					} else {
						parcelles[i][j] = new Parcelle(Parcelle.HERBE, true);
					}
				}
			}

			// Random random = new Random();

			parcelles[joueur1.posX_Bateau][joueur1.posY_Bateau].ajouterContenu(new Contenu(Contenu.BATEAU1), true);
			parcelles[joueur2.posX_Bateau][joueur2.posY_Bateau].ajouterContenu(new Contenu(Contenu.BATEAU2), true);

			Random random = new Random();

			int rocher = 0;
			int cle = random.nextInt(nbRocher);
			int coffre = random.nextInt(nbRocher);
			while (rocher < nbRocher) {
				int x = random.nextInt(taille - 2) + 1;
				int y = random.nextInt(taille - 2) + 1;
				if (parcelles[x][y].contenu == null && !bateauACote(x, y)) {
					if (rocher == cle) {
						parcelles[x][y].ajouterContenu(new Rocher(true, false), false);
					} else if (rocher == coffre) {
						parcelles[x][y].ajouterContenu(new Rocher(false, true), false);
					} else {
						parcelles[x][y].ajouterContenu(new Rocher(false, false), false);
					}
					rocher++;
				}
			}
			cpt++;
		} while (!genDone());
		System.out.println("nb generation : " + cpt);
	}

	private boolean genDone() {
		int taille = parcelles.length;
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
						if (this.parcelles[i + 2][j + 1].traversable == false
								&& this.parcelles[i][j + 1].traversable == false
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

						if (this.parcelles[i + 1][j + 2].contenu != null
								&& this.parcelles[i + 1][j + 2].contenu.nom == Contenu.BATEAU2) {
							bateau1 = true;
						}
						if (this.parcelles[i + 1][j].contenu != null
								&& this.parcelles[i + 1][j].contenu.nom == Contenu.BATEAU1) {
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

	private boolean bateauACote(int x, int y) {
		for (int i = 0; i < 2; i++) {
			if (x == joueurs[i].posX_Bateau && (y + 1 == joueurs[i].posX_Bateau || y - 1 == joueurs[i].posX_Bateau)) {
				return true;
			}
		}
		return false;
	}

	public boolean mouveBas(Personnage perso) {
		if (parcelles[perso.pos_x][perso.pos_y + 1].traversable) {
			mouvePersonage(perso, perso.pos_x, perso.pos_y + 1);
			return true;
		}
		return false;
	}

	public boolean mouveHaut(Personnage perso) {
		if (parcelles[perso.pos_x][perso.pos_y - 1].traversable) {
			mouvePersonage(perso, perso.pos_x, perso.pos_y - 1);
			return true;
		}
		return false;
	}

	public boolean mouveDroite(Personnage perso) {
		if (parcelles[perso.pos_x + 1][perso.pos_y].traversable) {
			mouvePersonage(perso, perso.pos_x + 1, perso.pos_y);
			return true;
		}
		return false;
	}

	public boolean mouveGauche(Personnage perso) {
		if (parcelles[perso.pos_x - 1][perso.pos_y].traversable) {
			mouvePersonage(perso, perso.pos_x - 1, perso.pos_y);
			return true;
		}
		return false;
	}

	public void mouvePersonage(Personnage perso, int pos_x, int pos_y) {
		boolean dead = false;
		if(parcelles[pos_x][pos_y].contenu instanceof Piege){
			if(((Piege)parcelles[pos_x][pos_y].contenu).numEquipe != perso.numEquipe){
				perso.vie-=40;
				JOptionPane.showMessageDialog(null, "Aaaah ! Je viens de tomber dans un pi�ge !");
			}else{
				JOptionPane.showMessageDialog(null, "Oh mince, je viens de remplir le trou de terre...");
			}
		}else if(parcelles[pos_x][pos_y].contenu instanceof Cle){
			JOptionPane.showMessageDialog(null, "Et bien ma fois.. Une cl� par terre, ce n'est pas si mal !");
			perso.cle = true;
		}else if(parcelles[pos_x][pos_y].contenu instanceof Tresor){
			JOptionPane.showMessageDialog(null, "Je ne comprends pas bien pourquoi le tr�sor est-ici mais cela ne m'inspire qu'une chose : partir !");
			perso.tresor = true;
		}
		if (!((joueurs[0].posX_Bateau == perso.pos_x && joueurs[0].posY_Bateau == perso.pos_y
				)||( joueurs[1].posX_Bateau == perso.pos_x && joueurs[1].posY_Bateau == perso.pos_y))) {
			parcelles[perso.pos_x][perso.pos_y].videContenu();
		}
		if (!(joueurs[0].posX_Bateau == pos_x && joueurs[0].posY_Bateau == pos_y
				|| joueurs[1].posX_Bateau == pos_x && joueurs[1].posY_Bateau == pos_y)) {
			parcelles[pos_x][pos_y].ajouterContenu(perso, false);
			perso.dansLeBateau = false;
		}else{
			if(perso.tresor){
				joueurs[perso.numEquipe].gagne = true;
				JOptionPane.showMessageDialog(null, "Le joueur "+(perso.numEquipe+1)+" a gagn� !");
			}
			perso.dansLeBateau = true;
		}
		if(!dead){
			perso.pos_x = pos_x;
			perso.pos_y = pos_y;
		}
		if(perso.vie<=0){
			JOptionPane.showMessageDialog(null, "Je suis mort...");
			if (perso.cle) {
				parcelles[pos_x][pos_y].contenu = Cle.CLE;
			}else if(perso.tresor){
				parcelles[pos_x][pos_y].contenu = Tresor.TRESOR;
			}else{
				parcelles[pos_x][pos_y].videContenu();
			}
		}
	}

	public void interaction(Personnage perso) {
		Parcelle test = new Parcelle();
		if (perso.oriantaion == Personnage.FACE)
			test = parcelles[perso.pos_x][perso.pos_y + 1];
		if (perso.oriantaion == Personnage.DOS)
			test = parcelles[perso.pos_x][perso.pos_y - 1];
		if (perso.oriantaion == Personnage.GAUCHE)
			test = parcelles[perso.pos_x - 1][perso.pos_y];
		if (perso.oriantaion == Personnage.DROITE)
			test = parcelles[perso.pos_x + 1][perso.pos_y];
		if(perso.cle && test.contenu instanceof Coffre){
			perso.cle = false;
			perso.tresor = true;
			test.videContenu();
		}else if (perso.typePerso == Personnage.EXPLORATEUR && test.contenu instanceof Rocher) {
			perso.energie -= 10;
			if (((Rocher) test.contenu).cleIci) {
				if (((Rocher) test.contenu).cle) {
					((Rocher) test.contenu).cle = false;
					perso.cle = true;
					JOptionPane.showMessageDialog(null, "J'ai trouv� la cl� !");
				} else {
					JOptionPane.showMessageDialog(null, "Je vois des traces de rouilles sur ce rocher !");
				}
			} else if (((Rocher) test.contenu).coffreIci) {
				if (((Rocher) test.contenu).coffre) {
					if (perso.cle) {
						perso.tresor = true;
						perso.cle = false;
						((Rocher) test.contenu).coffre = false;
						JOptionPane.showMessageDialog(null,
								"J'ai r�cup�r� le tr�sor, cassons nous de cette �le maudite !");
					} else {
						JOptionPane.showMessageDialog(null,
								"J'ai trouv� le coffre, je pense qu'il est bloqu� par un m�canisme ancien de type serrure � cl� !");
					}
				} else {
					JOptionPane.showMessageDialog(null, "J'ai trouv� le coffre ouvert... Mais il est vide !");
				}
			} else {
				Random rdm = new Random();
				String[] punchline = { "Je ne vois que des insectes l� dessous !",
						"Il n'y a rien dedans comme dans la t�te du guerrier ! ",
						"C'est le vide l� dessous, comme mon compte en banque d'ailleurs !",
						"C'est vide, comme l'inspiration de mon d�veloppeur !" };
				JOptionPane.showMessageDialog(null, punchline[rdm.nextInt(4)]);
			}
		} else if (perso.typePerso == Personnage.VOLEUR && test.contenu instanceof Personnage) {
			if (((Personnage)test.contenu).numEquipe != perso.numEquipe) {
				if (((Personnage) test.contenu).cle) {
					perso.cle = true;
					((Personnage) test.contenu).cle = false;
					JOptionPane.showMessageDialog(null, "J'ai vol� la cl�, trouvons le tr�sor !");
				} else if (((Personnage) test.contenu).tresor) {
					perso.tresor = true;
					((Personnage) test.contenu).tresor = false;
					JOptionPane.showMessageDialog(null, "J'ai vol� le tr�sor, nous pouvons s'en aller !");
				} else {
					JOptionPane.showMessageDialog(null, "Il a les poches vide, quel pauvre !");
				}
				perso.energie -= 20;
			}else{
				if (perso.cle && ((Personnage)test.contenu).typePerso == Personnage.EXPLORATEUR) {
					perso.cle=false;
					((Personnage) test.contenu).cle = true;
					JOptionPane.showMessageDialog(null, "Tiens mon pote, prends la cl� !");
					perso.energie -= 20;
				}else{
					JOptionPane.showMessageDialog(null, "Tu n'as rien a me donner, tu essaies de me voler ?");
				}
			}
		}else if(perso.typePerso == Personnage.GUERRIER && test.contenu instanceof Personnage){
			if (((Personnage)test.contenu).numEquipe != perso.numEquipe) {
				((Personnage)test.contenu).vie -= 40;
				if(((Personnage)test.contenu).vie<=0){
					test.traversable=true;
					if (((Personnage)test.contenu).cle) {
						JOptionPane.showMessageDialog(null, "j'ai tu� l'ennemi, il transportait la cl� !");
						test.contenu = Cle.CLE;
					}else if(((Personnage)test.contenu).tresor){
						JOptionPane.showMessageDialog(null, "j'ai tu� l'ennemi, il transportait le tr�sor !");
						test.contenu = Tresor.TRESOR;
					}else{
						JOptionPane.showMessageDialog(null, "j'ai tu� l'ennemi !");
						test.videContenu();
					}
				}
				perso.energie -= 30;
			}else{
				JOptionPane.showMessageDialog(null, "Wow fait attention, tu aurais pû me blesser !");
			}
		}else if(perso.typePerso == Personnage.GUERRIER && test.contenu instanceof Rocher){
			test.videContenu();
			if (((Rocher) test.contenu).cle) {
				JOptionPane.showMessageDialog(null, "J'ai cass� le rocher, j'y ai vu la cl� ! !");
				test.contenu = Cle.CLE;
			}else if(((Rocher) test.contenu).coffre){
				JOptionPane.showMessageDialog(null, "J'ai cass� le rocher, je peux y voir le coffre ! !");
				test.contenu = Coffre.COFFRE;
			}else{
				JOptionPane.showMessageDialog(null, "J'ai cass� le rocher ! !");
			}
		}else if(perso.typePerso == Personnage.PIEGEUR && test.contenu == null){
			test.contenu = new Piege(perso.numEquipe);
			perso.energie -= 30;
		}
		
	}
}

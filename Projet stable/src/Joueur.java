import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Joueur {
	String pseudo;
	String color;
	Bateau bateau;
	Carte carte;
	ArrayList<Personnage> personnages = new ArrayList<>();
	Personnage explorateur;
	boolean win, cle, coffre = false;
	
	static int EXPLORATEUR = 0;
	
	static String[] couleur = new String[]{"Rouge","bleu"};
	
	public Joueur(String pseudo, int numJoueur, Carte carte){
		this.pseudo = pseudo;
		this.color = couleur[numJoueur];
		this.carte = carte;
		this.personnages.add(new Personnage(Personnage.EXPLORATEUR, 0 + (1 * numJoueur), this.color));
		this.personnages.add(new Personnage(Personnage.EXPLORATEUR, 0 + (1 * numJoueur), this.color));
		this.personnages.add(new Personnage(Personnage.EXPLORATEUR, 0 + (1 * numJoueur), this.color));
		this.bateau = carte.bateau[numJoueur];
		for (int i = 0; i < personnages.size(); i++) {
			if(carte.parcelles[bateau.pos_x][bateau.pos_y + 1].traversable){
				this.personnages.get(i).setPosition(bateau.pos_x, bateau.pos_y + 1);
				carte.parcelles[bateau.pos_x][bateau.pos_y + 1].addContenu( this.personnages.get(i), false);
			}else if(carte.parcelles[bateau.pos_x][bateau.pos_y - 1].traversable){
				this.personnages.get(i).setPosition(bateau.pos_x, bateau.pos_y - 1);
				carte.parcelles[bateau.pos_x][bateau.pos_y - 1].addContenu( this.personnages.get(i), false);
			}else if(carte.parcelles[bateau.pos_x + 1][bateau.pos_y].traversable){
				this.personnages.get(i).setPosition(bateau.pos_x + 1, bateau.pos_y);
				carte.parcelles[bateau.pos_x + 1][bateau.pos_y].addContenu( this.personnages.get(i), false);
			}else if(carte.parcelles[bateau.pos_x - 1][bateau.pos_y].traversable){
				this.personnages.get(i).setPosition(bateau.pos_x - 1, bateau.pos_y);
				carte.parcelles[bateau.pos_x - 1][bateau.pos_y].addContenu( this.personnages.get(i), false);
			}
		}
	}
	
	public void mouvePersonne(int numPersonage, int pos_x, int pos_y){
		this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y].contenu = new Element(Element.VIDE);
		this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y].traversable = true;
		this.carte.parcelles[pos_x][pos_y].addContenu(this.personnages.get(numPersonage), false);
		this.personnages.get(numPersonage).setPosition(pos_x, pos_y);
	}
	
	public boolean moouveBas(int numPersonage){
		if(this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y + 1].traversable){
			mouvePersonne(numPersonage, this.personnages.get(numPersonage).pos_x, this.personnages.get(numPersonage).pos_y + 1);
			return true;
		}
		return false;
	}
	
	public boolean mouveHaut(int numPersonage){
		if(this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y - 1].traversable){
			mouvePersonne(numPersonage, this.personnages.get(numPersonage).pos_x, this.personnages.get(numPersonage).pos_y - 1);
			return true;
		}
		return false;
	}
	public boolean moouveDroit(int numPersonage){
		if(this.carte.parcelles[this.personnages.get(numPersonage).pos_x - 1][this.personnages.get(numPersonage).pos_y].traversable){
			mouvePersonne(numPersonage, this.personnages.get(numPersonage).pos_x - 1, this.personnages.get(numPersonage).pos_y);
			return true;
		}
		return false;
	}
	
	public boolean mouveGauche(int numPersonage){
		if(this.carte.parcelles[this.personnages.get(numPersonage).pos_x + 1][this.personnages.get(numPersonage).pos_y].traversable){
			mouvePersonne(numPersonage, this.personnages.get(numPersonage).pos_x + 1, this.personnages.get(numPersonage).pos_y);
			return true;
		}
		return false;
	}
	
	public boolean interaction(int numPersonage){
			Element test = new Element(Element.VIDE);
			if(this.personnages.get(numPersonage).getOrientation() == 0){
				test = this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y+1].contenu;
			}else if(this.personnages.get(numPersonage).getOrientation() == 1){
				test = this.carte.parcelles[this.personnages.get(numPersonage).pos_x][this.personnages.get(numPersonage).pos_y-1].contenu;
			}else if(this.personnages.get(numPersonage).getOrientation() == 2){
				test = this.carte.parcelles[this.personnages.get(numPersonage).pos_x-1][this.personnages.get(numPersonage).pos_y].contenu;
			}else if(this.personnages.get(numPersonage).getOrientation() == 3){
				test = this.carte.parcelles[this.personnages.get(numPersonage).pos_x+1][this.personnages.get(numPersonage).pos_y].contenu;
			}	
			if(test instanceof Rocher){
				if(((Rocher)test).getContenu() ==1){
					JOptionPane.showMessageDialog(null,"Vous avez récupéré la clé");
					cle=true;
				}
				if(((Rocher)test).getContenu() ==2){
					JOptionPane.showMessageDialog(null,"Vous avez récupéré le coffre");
					coffre=true;
				}
				return true;
			}
			return false;
	}
}

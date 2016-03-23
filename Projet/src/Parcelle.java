public class Parcelle{
	int numImage;
	boolean traversable = false;
	String contenu = "";

	final static int EAU = 1;
	final static int HERBE = 2;
	final static int ROCHER = 3;

	/**
	 * CrÈation des parcelles de jeu
	 * 
	 * @param type
	 *           Type de parcelle(eau,herbe,rocher)
	 */

	public Parcelle(int type){
		this.numImage = type;
		if(type == 2){this.traversable = true;}
	}
	
	/**
	 * Changement du type de parcelle
	 * 
	 * @param parcelle
	 *           Parcelle a changer
	 */

	public void changerParcelle(Parcelle parcelle){
		this.numImage = parcelle.numImage;
		this.traversable = parcelle.traversable;
		this.contenu = parcelle.contenu;
	}
	
	/**
	 *NumÈro d'image
	 * 
	 * @return le chiffre correspondant au type de parcelle
	 */


	public int getNumImage(){
		if(this.contenu.length() ==0){
			return this.numImage;
		}else if(contenu.equals("coffre")){
			return 6;
		}else if(contenu.equals("cl√©")){
			return 7;
		}
		return 0;
	}

	/**
	 * Savoir si c'est traversable
	 * 
	 * @return Vrai pour traverser, faux sinon
	 */
	public boolean getTraversable(){
		return traversable;
	}
	
	
	/**
	 * Savoir si c'est un bateau
	 * 
	 * @return Vrai si c'est un bateau, faux sinon
	 */
	public boolean isBateau(){
		return this.numImage==4||this.numImage==5;
	}
	
	/**
	 *Ajouter un contenu
	 *
	 *@param s
	 *		Ce que l'on veut ajouter
	 */

	public void putIn(String s){
		this.contenu = s; // pour ajouter des coffres/cl√©s/pi√®ges etc..
	}
}


public class Parcelle{
		int numImage;
		boolean traversable = false;
 		String contenu = "";
 
 		final static int EAU = 1;
 		final static int HERBE = 2;
	 	final static int ROCHER = 3;
	 	
	 	final static int BATEAUTeam1 = 4;
	 	final static int BATEAUTeam2 = 5;
 	
 	public Parcelle(int type){
 			this.numImage = type;
			if(type == 2){this.traversable = true;}
 	}
		
	public void changerParcelle(Parcelle parcelle){
		this.numImage = parcelle.numImage;
		this.traversable = parcelle.traversable;
		this.contenu = parcelle.contenu;
	}
	
	
	public int getNumImage(){
		if(this.contenu.length() ==0){
			return this.numImage;
		}else if(contenu.equals("coffre")){
			return 6;
		}else if(contenu.equals("clé")){
			return 7;
		}
		return 0;
	}
	
	public boolean getTraversable(){
		return traversable;
	}
	
	public boolean isBateau(){
		return this.numImage==4||this.numImage==5;
	}
	
	public String getContenu(){
		return this.contenu;
	}
	
	public void setContenu(String s){
		this.contenu = s; // pour ajouter des coffres/clés/pièges etc..
	}
	
	public boolean isRocher(){
		if(this.numImage == Parcelle.ROCHER){
			return true;
		}
		return false;
	}
}
 	

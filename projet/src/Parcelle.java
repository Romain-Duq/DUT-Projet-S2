

public class Parcelle {
	int numImage = 0;
	boolean traversable = false;
	String contenu = "";
	
	final static String ROCHER = "rocher";
	final static String EAU = "eau";
	final static String HERBE = "herbe";
	final static String PLAGE = "plage";
	
	public Parcelle(String type){
		if(type.equals("herbe")){
			this.numImage = 2;
			this.traversable = true;
		}else if(type.equals("mer")){
			this.numImage = 1;
		}else if(type.equals("rocher")){
			this.numImage = 3;
			this.traversable = false;
		}else if(type.equals("plage")){
			this.numImage = 4;
			this.traversable = true;
		}
	}
	
	public Parcelle(int numImage){
		this.numImage = numImage;
	}
	
	public void changerParcelle(Parcelle parcelle){
		this.numImage = parcelle.numImage;
		this.traversable = parcelle.traversable;
		this.contenu = parcelle.contenu;
	}
	
	
	public int getNumImage(){
		return numImage;
	}
	
	public boolean getTraversable(){
		return traversable;
	}
}

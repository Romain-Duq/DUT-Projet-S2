import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelAction extends JPanel implements ActionListener, KeyListener{
	
	boolean finTour;
	int cpt;
	Carte carte;
	Personnage perso;
	PanelGraphique panelGraphique;
	JButton bas, haut, gauche, droite, passerTour, action;
	JLabel label;
	
	public PanelAction(Personnage perso, Carte carte) {
		finTour = false;
		int taille  = carte.parcelles.length;
		this.setSize(taille * 32, 66);
		this.setLayout(null);
		this.perso = perso;
		this.carte = carte;
		
		cpt = 0;
		
		bas = new JButton(new ImageIcon("ressource/button/flecheBas.png"));
		bas.addActionListener(this);
		bas.setBounds( 42, 33, 32, 32);
		bas.setFocusable(false);
		this.add(bas);
			
		haut = new JButton(new ImageIcon("ressource/button/flecheHaut.png"));
		haut.addActionListener(this);
		haut.setBounds( 42, 1, 32, 32);
		haut.setFocusable(false);
		this.add(haut);
		
		gauche = new JButton(new ImageIcon("ressource/button/flecheDroite.png"));
		gauche.addActionListener(this);
		gauche.setBounds( 9, 17, 32, 32);
		gauche.setFocusable(false);
		this.add(gauche);
			
		droite = new JButton(new ImageIcon("ressource/button/flecheGauche.png"));
		droite.addActionListener(this);
		droite.setBounds( 75, 17, 32, 32);
		droite.setFocusable(false);
		this.add(droite);
		
		passerTour = new JButton(new ImageIcon("tour"));
		passerTour.addActionListener(this);
		passerTour.setBounds( (this.getWidth() - 110), 30, 100, 25);
		passerTour.setFocusable(false);
		passerTour.setText("passer");
		this.add(passerTour);
	
		action = new JButton("action");
		action.addActionListener(this);
		action.setBounds( (this.getWidth() - 110), 3, 100, 25);
		action.setFocusable(false);
		this.add(action);
		
		panelGraphique = new PanelGraphique(this.perso);
		panelGraphique.setBounds((this.getWidth() - 120) / 2, 5, panelGraphique.getWidth(), panelGraphique.getHeight());
		this.add(panelGraphique);
	}
	
	public void setPersonnage(Personnage perso){
		this.perso.select = false;
		this.perso = perso;
		this.perso.select = true;
		this.perso.pm = this.perso.pmInitiale;
		panelGraphique.perso = perso;
		panelGraphique.repaint();
		cpt = 0;
		setActionBouton();
		testDead();
	}
	
	public void setActionBouton(){
		HashMap<String, String> map = new HashMap<>();
		map.put(Personnage.EXPLORATEUR, "Fouiller");
		map.put(Personnage.GUERRIER, "Attaque");
		map.put(Personnage.PIEGEUR, "Pieger");
		map.put(Personnage.VOLEUR, "vol�");
		map.put(Personnage.VOLEUR + "1", "donner cle");
		
		if(perso.typePerso == Personnage.VOLEUR){
			Parcelle test = new Parcelle();
			if (perso.oriantaion == Personnage.FACE)
				test = carte.parcelles[perso.pos_x][perso.pos_y + 1];
			if (perso.oriantaion == Personnage.DOS)
				test = carte.parcelles[perso.pos_x][perso.pos_y - 1];
			if (perso.oriantaion == Personnage.GAUCHE)
				test = carte.parcelles[perso.pos_x - 1][perso.pos_y];
			if (perso.oriantaion == Personnage.DROITE)
				test = carte.parcelles[perso.pos_x + 1][perso.pos_y];
			if( perso.cle && test.contenu instanceof Personnage && ((Personnage) test.contenu).typePerso == Personnage.EXPLORATEUR && ((Personnage) test.contenu).numEquipe == perso.numEquipe){
				action.setText(map.get(Personnage.VOLEUR + "1"));
			}else{
				action.setText(map.get(perso.typePerso));
			}
		}else{
			action.setText(map.get(perso.typePerso));
		}
	}

	public void mouveBas(){
		if(cpt < this.perso.pmInitiale){
			boolean abouge = carte.mouveBas(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
		perso.changerOriantation(Personnage.FACE);
	}
	
	public void mouveHaut(){
		if(cpt < perso.pmInitiale){
			boolean abouge = carte.mouveHaut(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
		perso.changerOriantation(Personnage.DOS);
	}
	
	public void mouveGauche(){
		if(cpt < perso.pmInitiale){
			boolean abouge = carte.mouveGauche(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
		perso.changerOriantation(Personnage.GAUCHE);
	}
	
	public void mouveDroite(){
		if(cpt < perso.pmInitiale){
			boolean abouge = carte.mouveDroite(perso);
			if(abouge){
				cpt++;
				this.perso.pm--;
			}
		}
		perso.changerOriantation(Personnage.DROITE);
	}
	
	public void inBateau(){
		if(perso.energie < perso.energieMax && perso.dansLeBateau){
			perso.energie+=10;
			if(perso.energie>perso.energieMax)perso.energie = perso.energieMax;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bas){
			mouveBas();
			testDead();
		}
		if(e.getSource() == this.haut){
			mouveHaut();
			testDead();
		}
		if(e.getSource() == this.gauche){
			mouveGauche();
			testDead();
		}
		if(e.getSource() == this.droite){
			mouveDroite();
			testDead();
		}
		if(e.getSource() == this.passerTour){
			finTour = true;
			inBateau();
		}
		if(e.getSource() == this.action){
			carte.interaction(perso);
		}
		setActionBouton();
	}
	void testDead(){
		if(perso.vie<=0){
			finTour=true;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			mouveBas();
		}else if(e.getKeyCode() == KeyEvent.VK_UP){
			mouveHaut();
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			mouveGauche();
		}else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			mouveDroite();
		}else if(e.getKeyCode() == KeyEvent.VK_SPACE){
			carte.interaction(perso);
		}else if(e.getKeyCode() == KeyEvent.VK_ENTER){
			finTour = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MenuJeux extends JPanel implements ActionListener{
	
	Joueur joueur;
	boolean continu = true;
	int numeroPersonnage;
		
	JButton droit = new JButton(new ImageIcon("images/button/flecheDroite.png"));
	JButton haut = new JButton(new ImageIcon("images/button/flecheHaut.png"));
	JButton action = new JButton(new ImageIcon("images/button/flecheAction.png"));
	JButton bas = new JButton(new ImageIcon("images/button/flecheBas.png"));
	JButton gauche = new JButton(new ImageIcon("images/button/flecheGauche.png"));
	JButton passer = new JButton("Passer son tour !");
	
	public MenuJeux(){
		this.setLayout(null);
		this.droit.setBounds(30, 82, 32, 32);
		this.haut.setBounds( 62, 50, 32, 32);
		this.action.setBounds(62, 82, 32, 32);
		this.bas.setBounds( 62, 112, 32, 32);
		this.gauche.setBounds(94, 82, 32, 32);
		this.passer.setBounds( 7, 7, 137, 32);
		
		this.droit.addActionListener(this);
		this.haut.addActionListener(this);
		this.action.addActionListener(this);
		this.bas.addActionListener(this);
		this.gauche.addActionListener(this);
		this.passer.addActionListener(this);
				
		this.add(droit);
		this.add(haut);
		this.add(action);
		this.add(bas);
		this.add(gauche);
		this.add(passer);
	}
	
	public void paintComponent(Graphics g){
		//g.drawImage(new ImageIcon("images/menu/FondButton.png").getImage(), 0, 0, null);
		g.setColor(new Color(223, 192, 118));
		g.fillRect( 0, 0, 156, this.getHeight());
		g.setColor(Color.white);
		g.draw3DRect(5, 5, 140, 146, false);
		g.draw3DRect(6, 6, 138, 144, false);
		g.fillRect( 7, 7, 136, 142);
		for (int i = 0; i < 3; i++) {
			dessinFichePersonnage(g, i);
		}
	}
	
	private void dessinFichePersonnage(Graphics g, int num){
		if(this.joueur.personnages.get(num).estSelect && this.joueur.personnages.get(num).color == Joueur.couleur[0]){
			g.setColor(Color.RED);
		}else if(this.joueur.personnages.get(num).estSelect && this.joueur.personnages.get(num).color == Joueur.couleur[1]){
			g.setColor(Color.BLUE);
		}else{
			g.setColor(Color.WHITE);
		}
		g.draw3DRect( 6, 160 + (74 * num), 140, 64, false);
		g.draw3DRect( 7, 161 + (74 * num), 138, 62, false);
		
		g.setColor(Color.WHITE);
		g.fillRect( 8, 162 + (74 * num), 136, 60);
		
		g.setColor(Color.black);
		g.drawImage(DessinCarte.IMAGE.get(this.joueur.personnages.get(num).image), 10, 185 + (74 * num), null);
		g.setFont(new Font("Verdana", Font.BOLD, 12));
		g.drawString(this.joueur.personnages.get(num).nom, 10, 180 + (74 * num));
		g.drawString("PM : " + this.joueur.personnages.get(num).nbdeplacement, 70, 200 + (74 * num));
		
	}
			
	public void setJoueur(Joueur joueur, int numeroPersonnage){
		this.joueur = joueur;
		this.numeroPersonnage = numeroPersonnage;
		continu = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.action){
			joueur.interaction(this.numeroPersonnage);
		}
		if(e.getSource() == this.passer){
			this.joueur.personnages.get(numeroPersonnage).nbdeplacement = 0;
		}else if(this.joueur.personnages.get(numeroPersonnage).nbdeplacement  > 0){
			if(e.getSource() == this.bas){
				boolean aBouger = joueur.moouveBas(this.numeroPersonnage);
				this.joueur.personnages.get(numeroPersonnage).setOrientation(Personnage.BAS);
				if(aBouger){
					this.joueur.personnages.get(numeroPersonnage).nbdeplacement--;
				}	
			}else if(e.getSource() == this.haut){
				boolean aBouger = joueur.mouveHaut(this.numeroPersonnage);
				this.joueur.personnages.get(numeroPersonnage).setOrientation(Personnage.HAUT);
				if(aBouger){
					this.joueur.personnages.get(numeroPersonnage).nbdeplacement--;
				}
			}else if(e.getSource() == this.gauche){
				boolean aBouger = joueur.mouveGauche(this.numeroPersonnage);
				this.joueur.personnages.get(numeroPersonnage).setOrientation(Personnage.GAUCHE);
				if(aBouger){
					this.joueur.personnages.get(numeroPersonnage).nbdeplacement--;
				}
			}else if(e.getSource() == this.droit){
				this.joueur.personnages.get(numeroPersonnage).setOrientation(Personnage.DROITE);
				boolean aBouger = joueur.moouveDroit(this.numeroPersonnage);
				if(aBouger){
					this.joueur.personnages.get(numeroPersonnage).nbdeplacement--;
				}
			}
		}
		if(this.joueur.personnages.get(numeroPersonnage).nbdeplacement  <= 0){
			this.continu = false;
		}
	}
}

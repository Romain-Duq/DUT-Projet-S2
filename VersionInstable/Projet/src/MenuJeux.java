import java.awt.Color;
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
	int nbDeplacement, conteurDeplacement, numeroPersonnage;
		
	JButton droit = new JButton(new ImageIcon("images/button/flecheDroite.png"));
	JButton haut = new JButton(new ImageIcon("images/button/flecheHaut.png"));
	JButton action = new JButton(new ImageIcon("images/button/flecheAction.png"));
	JButton bas = new JButton(new ImageIcon("images/button/flecheBas.png"));
	JButton gauche = new JButton(new ImageIcon("images/button/flecheGauche.png"));
	JButton passer = new JButton("Passer sont tour !");
	
	public MenuJeux(){
		this.setLayout(null);
		this.setBackground(new Color(251, 228, 176));
		this.setBackground(Color.blue);

		this.droit.setBounds(30, 82, 32, 32);
		this.haut.setBounds( 62, 50, 32, 32);
		this.action.setBounds(62, 82, 32, 32);
		this.bas.setBounds( 62, 112, 32, 32);
		this.gauche.setBounds(94, 82, 32, 32);
		this.passer.setBounds(12, 12, 135, 32);
		
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
		System.out.println();
		g.drawImage(new ImageIcon("images/menu/FondButton.png").getImage(), 0, 0, null);
		g.drawImage(new ImageIcon("images/menu/FondPersonnage.png").getImage(), 0, 156, null);
		g.drawImage(DessinCarte.IMAGE.get(joueur.personnages.get(this.numeroPersonnage).image), 10, 170, null);
		g.drawString("PM : " + (this.nbDeplacement - this.conteurDeplacement), 70, 170);
	}
		
	public void setJoueur(Joueur joueur, int nbDeplacement, int numeroPersonnage){
		this.joueur = joueur;
		this.nbDeplacement = nbDeplacement;
		this.numeroPersonnage = numeroPersonnage;
		this.conteurDeplacement = 0;
		continu = true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.action){
			System.exit(0);
		}
		if(e.getSource() == this.passer){
			conteurDeplacement = nbDeplacement + 1;
		}else if(conteurDeplacement <= nbDeplacement){
			if(e.getSource() == this.bas){
				boolean aBouger = joueur.moouveBas(this.numeroPersonnage);
				if(aBouger){
					conteurDeplacement++;
				}	
			}else if(e.getSource() == this.haut){
				boolean aBouger = joueur.mouveHaut(this.numeroPersonnage);
				if(aBouger){
					conteurDeplacement++;
				}
			}else if(e.getSource() == this.gauche){
				boolean aBouger = joueur.mouveGauche(this.numeroPersonnage);
				if(aBouger){
					conteurDeplacement++;
				}
			}else if(e.getSource() == this.droit){
				boolean aBouger = joueur.moouveDroit(this.numeroPersonnage);
				if(aBouger){
					conteurDeplacement++;
				}
			}
		}
		if(conteurDeplacement >= nbDeplacement){
			this.continu = false;
		}
	}
}

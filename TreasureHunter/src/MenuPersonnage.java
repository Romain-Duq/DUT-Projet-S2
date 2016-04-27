import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPersonnage {

	Joueur joueur = null;
	
	JPanel equipe;
	
	int cpt = 0;
	int nb = 0;
	ArrayList<Personnage> perso = new ArrayList<>();
	
	public MenuPersonnage(int numEquipe, int taille) {
		Random random = new Random();
		int pos_x = random.nextInt(taille - 4) + 2;
		int pos_y;
		int nbPerso = 3;
		if(numEquipe == 1){
			pos_y = taille - 2;
		}else{
			pos_y = 1;
		}
		
		JFrame win = new JFrame("choix des personnage");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.setSize(620, 380);
		win.setLayout(null);
		
		JLabel nom = new JLabel("nom du joueur :");
		nom.setBounds(10, 10, 90, 20);
		win.add(nom);
		
		JTextField nomField = new JTextField();
		nomField.setText("Joueur " + (numEquipe + 1));
		nomField.setBounds(100, 10, 100, 20);
		win.add(nomField);
		
		JButton explorateur = new JButton("EXPLORATEUR");
		explorateur.setBounds(10, 40, 137, 200);
		explorateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cpt < nbPerso){
					perso.add(new Personnage(Personnage.EXPLORATEUR, Personnage.FACE, pos_x, pos_y, numEquipe));
					dessinEquipe();
					equipe.repaint();
					cpt++;
				}
			}
		});
		win.add(explorateur);
		
		JButton guerrie = new JButton("GUERRIE");
		guerrie.setBounds(157, 40, 137, 200);
		guerrie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cpt < nbPerso){
					perso.add(new Personnage(Personnage.GUERRIER, Personnage.FACE, pos_x, pos_y, numEquipe));
					dessinEquipe();
					equipe.repaint();
					cpt++;
				}
			}
		});
		win.add(guerrie);
		
		JButton voleur = new JButton("VOLEUR");
		voleur.setBounds(304, 40, 137, 200);
		voleur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cpt < nbPerso){
					perso.add(new Personnage(Personnage.VOLEUR, Personnage.FACE, pos_x, pos_y, numEquipe));
					dessinEquipe();
					equipe.repaint();
					cpt++;
				}
			}
		});
		win.add(voleur);
		
		JButton piegeur = new JButton("PIEGEUR");
		piegeur.setBounds(451, 40, 137, 200);
		piegeur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cpt < nbPerso){
					perso.add(new Personnage(Personnage.PIEGEUR, Personnage.FACE, pos_x, pos_y, numEquipe));
					dessinEquipe();
					equipe.repaint();
					cpt++;
				}
			}
		});
		win.add(piegeur);
		
		JLabel labelEquipe = new JLabel("Votre equipe :");
		labelEquipe.setBounds(10, 250, 90, 20);
		win.add(labelEquipe);
		
		equipe = new JPanel();
		equipe.setSize(400, 55);
		equipe.setBounds(10, 280, equipe.getWidth(), equipe.getHeight());
		win.add(equipe);
		
		JButton vide = new JButton("Vider");
		vide.setBounds(451, 305, 137, 32);
		vide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perso.clear();
				dessinEquipe();
				equipe = new JPanel();
				equipe.setSize(400, 55);
				equipe.setBounds(10, 280, equipe.getWidth(), equipe.getHeight());
				win.add(equipe);
				equipe.repaint();
				cpt = 0;
			}
		});
		win.add(vide);
		
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(perso.size() != nbPerso){
					JOptionPane.showMessageDialog(win, "Vous devez avoir " + nbPerso + " personnage !");
				}else{
					joueur = new Joueur(pos_x, pos_y, new Personnage[]{perso.get(0), perso.get(1), perso.get(2)}, nomField.getText(),numEquipe);
					win.setVisible(false);
				}
			}
		});
		valider.setBounds(451, 270, 137, 32);
		win.add(valider);
		win.setVisible(true);
	}
	
	public void dessinEquipe(){
		for (int i = 0; i < perso.size(); i++) {
			JPanel panel  = new PanelGraphique(this.perso.get(i));
			panel.setBounds(i * panel.getWidth() + i * 10, 0, panel.getWidth(), panel.getHeight());
			equipe.add(panel);
		}
	}
}

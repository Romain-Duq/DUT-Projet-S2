import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Launcher extends JFrame{
	boolean finLauncher;
	JTextField taille, pourcentage;
	int tailleCarte, pourcentageRocher;
	
	public Launcher() {	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Launcher");
		this.setUndecorated(true);
		this.setSize(404, 286);
		this.setLayout(null);
		this.setVisible(true);
		this.getContentPane().setBackground(Color.BLUE);
		
		JLabel imageFond = new JLabel(new ImageIcon("ressource/launcher.png"));
		imageFond.setBounds(2, 2, 400, 225);
		this.add(imageFond);
		
		JButton jouer = new JButton("Jouer");
		jouer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					tailleCarte = Integer.valueOf(taille.getText());
					pourcentageRocher = Integer.valueOf(pourcentage.getText());
					finLauncher = true;
				} catch (Exception e) {
					erreur();
				}
			}
		});
		jouer.setBounds(2, this.getHeight() - 34, (this.getWidth() - 4) / 2, 32);
		this.add(jouer);
		
		JButton quitter = new JButton("Quitter");
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		quitter.setBounds( (this.getWidth() / 2), this.getHeight() - 34, (this.getWidth() - 4) / 2, 32);
		this.add(quitter);
		
		JLabel label1 = new JLabel("Taille de la carte :");
		label1.setBounds(10, this.getHeight() - 56, 100, 20);
		label1.setForeground(Color.WHITE);
		this.add(label1);
		taille = new JTextField();
		taille.setText("15");
		taille.setBounds(120, this.getHeight() - 56, 20, 20);
		this.add(taille);
		
		
		JLabel label2 = new JLabel("Pourcentage de rocher :");
		label2.setBounds(160, this.getHeight() - 56, 140, 20);
		label2.setForeground(Color.WHITE);
		this.add(label2);
		pourcentage = new JTextField();
		pourcentage.setText("10");
		pourcentage.setBounds(307, this.getHeight() - 56, 20, 20);
		this.add(pourcentage);
	}
	
	private void erreur(){
		JOptionPane.showMessageDialog(this, "Erreur dans les argument (la taille et le pourcentage doit étre un nombre).");
	}
}

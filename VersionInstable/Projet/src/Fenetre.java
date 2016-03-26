import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	public Fenetre(String titre, JPanel carte, JPanel menu){
		this.setTitle(titre);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setUndecorated(true);
		this.setResizable(false);
		this.setVisible(true);
		this.setLayout(null);

		
		Dimension dimMenu = new Dimension( 156, carte.getHeight());
		menu.setSize( dimMenu);
		menu.setPreferredSize(dimMenu);
		menu.setMaximumSize(dimMenu);
		menu.setMaximumSize(dimMenu);
		
		Dimension dimCarte = new Dimension(carte.getWidth(), carte.getHeight());
		carte.setPreferredSize(dimCarte);
		carte.setMaximumSize(dimCarte);
		carte.setMaximumSize(dimCarte);
		
		carte.setBounds(0, 0, carte.getWidth(), carte.getHeight());
		menu.setBounds(carte.getWidth() - 2, 0, menu.getWidth(), menu.getHeight());
		
		this.add(menu);
		this.add(carte);
			
		this.setSize(carte.getWidth() + menu.getWidth(), carte.getHeight());
	}
	
	public void fullScrean(){
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dimension);
	}
}

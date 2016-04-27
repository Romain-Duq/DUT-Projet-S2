import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	
	PanelAction panelAction;
	
	public Fenetre(DessinCarte carte, PanelAction panelAction, String titre) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle(titre);
		this.setSize(carte.getWidth() + 6, carte.getHeight() + 28 + panelAction.getHeight());
		this.setLayout(null);
		carte.setBounds(0, 0, carte.getWidth(), carte.getHeight());
		this.add(carte);
		panelAction.setBounds( 0, carte.getHeight(), panelAction.getWidth(), panelAction.getHeight());
		this.panelAction = panelAction;
		this.add(this.panelAction);
		this.addKeyListener(panelAction);
	}
}

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelGraphique extends JPanel{
	
	Personnage perso;
	
	public PanelGraphique(Personnage perso){
		this.setSize(100, 55);
		this.perso = perso;
	}
	
	public void paint(Graphics g) {
		g.draw3DRect(0, 0, this.getWidth(), this.getHeight(), false);
		g.setColor(Color.BLUE);
		g.fillRect(1, 1, 98, 53);
		g.drawImage(perso.image, 65, 8, null);
		g.setColor(Color.WHITE);
		g.drawString("VIE : " + perso.vie, 5, 15);
		g.drawString("PM : " + perso.pm, 5, 30);
		g.drawString("NRJ : " + perso.energie, 5, 45);
	}
}

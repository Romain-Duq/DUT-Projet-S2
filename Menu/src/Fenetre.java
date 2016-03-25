import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements ActionListener{
	private Panneau pan = new Panneau();
	private JButton bouton = new JButton("Jouer");
	private JButton bouton2 = new JButton("Paramètres");
	private JPanel container = new JPanel();
	private Paramètres  pa = new Paramètres();
	private Main ma;



	public Fenetre(){
		this.setTitle("Menu : Treasure Hunt");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);

		bouton.addActionListener(this);
		bouton2.addActionListener(this);

		JPanel south = new JPanel();
		south.add(bouton);
		south.add(bouton2);
		container.add(south, BorderLayout.SOUTH);

		this.setContentPane(container);
		this.setVisible(true);


	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == bouton2){
			pa.setParametre();
		}

		if(arg0.getSource() == bouton){
			ma = new Main(pa.getTaille(),pa.getProportion());
		}
	}
}
//…

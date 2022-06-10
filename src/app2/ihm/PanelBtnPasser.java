import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PanelBtnPasser extends JPanel implements ActionListener
{
	private JButton btnPasser;
	private JLabel  lblNTour;

	public PanelBtnPasser(Controleur ctrl)
	{
		this.setLayout(new FlowLayout());

		//création des composants
		this.lblNTour  = new JLabel ("Tour numero 1");
		this.btnPasser = new JButton("Passer au tour suivant");

		//positionnement des composants
		this.add(this.lblNTour);
		this.add(this.btnPasser);

		//activation des composants
		this.btnPasser.addActionListener(this);
	}

	public void ActionPerformed(ActionEvent e)
	{
		ctrl.passerAuTourSuivant();
	}
}
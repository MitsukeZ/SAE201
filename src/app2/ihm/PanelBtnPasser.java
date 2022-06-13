import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

public class PanelBtnPasser extends JPanel implements ActionListener
{
	private JButton btnPasser;
	private JLabel  lblNTour;
	private int 	nbTour = 1;

	public PanelBtnPasser(Controleur ctrl)
	{
		this.setLayout(new FlowLayout());

		//création des composants
		this.lblNTour  = new JLabel ("Tour numero " + this.nbTour);
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
		this.lblNTour.setText("Tour numero " + this.nbTour);
	}
}
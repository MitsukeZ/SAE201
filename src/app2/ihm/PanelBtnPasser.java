package app2.ihm;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

import app2.Controleur;

public class PanelBtnPasser extends JPanel implements ActionListener
{
	private JButton    btnPasser;
	private JLabel     lblNTour;
	private int 	   nbTour = 1;
	private Controleur ctrl;

	public PanelBtnPasser(Controleur ctrl)
	{
		this.ctrl = ctrl;

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

	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == this.btnPasser)
		{
			this.ctrl.passerAuTourSuivant();
			this.lblNTour.setText("Tour numero " + ++this.nbTour);
			this.ctrl.majIHM();

		}
		
	}
}
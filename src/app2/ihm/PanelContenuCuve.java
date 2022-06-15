package app2.ihm;

import app2.Controleur;

import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class PanelContenuCuve extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JTextField txtContenu;
	private JTextField txtCuve;
	private JButton    btnValider ;
	private JLabel     lblValInvalide;

	public PanelContenuCuve(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		//création des composants
		this.txtContenu    = new JTextField(3);
		this.txtCuve       = new JTextField(3);
		this.btnValider    = new JButton("Valider");
		JLabel lblTitre    = new JLabel("Remplissage manuel :");
		JPanel panelHaut   = new JPanel();
		JPanel panelDroite = new JPanel();
		JPanel panelGauche = new JPanel();

		lblTitre.setForeground(Color.WHITE);
		//positionnement des composants
		panelGauche.setLayout(new GridLayout(2,2));

		panelGauche.add (new JLabel("Cuve à remplir:"),LEFT_ALIGNMENT);
		panelGauche.add (this.txtCuve   );
		panelGauche.add (new JLabel("Quantite:"),LEFT_ALIGNMENT);
		panelGauche.add (this.txtContenu);

		panelDroite.add (this.btnValider);
		panelHaut.setBackground(new Color(58,58,58));
		this.add(panelHaut, BorderLayout.NORTH);
		panelHaut.add(lblTitre);
		panelHaut.add(panelGauche   );
		panelHaut.add(panelDroite   );


		//activation des composants
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.lblValInvalide = new JLabel("Valeurs Invalides");
		this.add (this.lblValInvalide);
		if ( this.ctrl.remplirCuve (this.txtCuve.getText(), this.txtContenu.getText() ))
		{
			this.ctrl.majIhm();
			this.remove (this.lblValInvalide);
		}
		else
		{
			this.revalidate();
		}
	}
}

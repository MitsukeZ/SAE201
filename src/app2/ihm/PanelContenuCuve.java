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

	private JPanel     panelHaut;

	public PanelContenuCuve(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		//création des composants
		this.txtContenu    = new JTextField(3);
		this.txtCuve       = new JTextField(3);
		this.btnValider    = new JButton("Valider");
		this.panelHaut     = new JPanel();

		JLabel lblTitre    = new JLabel("Remplissage manuel :");
		JPanel panelDroite = new JPanel();
		JPanel panelGauche = new JPanel();

		this.lblValInvalide = new JLabel("Valeurs Invalides");

		lblTitre.setForeground(Color.WHITE);
		//positionnement des composants
		panelGauche.setLayout(new GridLayout(2,2));

		panelGauche.add (new JLabel("Cuve à remplir:"),LEFT_ALIGNMENT);
		panelGauche.add (this.txtCuve   );
		panelGauche.add (new JLabel("Quantite:"),LEFT_ALIGNMENT);
		panelGauche.add (this.txtContenu);

		panelDroite.add (this.btnValider);

		this.panelHaut.setBackground(new Color(58,58,58));

		this.panelHaut.add(lblTitre);
		this.panelHaut.add(panelGauche   );
		this.panelHaut.add(panelDroite   );

		this.add(panelHaut, BorderLayout.NORTH);


		//activation des composants
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		this.removeAll();

		this.add(panelHaut, BorderLayout.NORTH);

		if (!this.ctrl.remplirCuve (this.txtCuve.getText(), this.txtContenu.getText() ))
			this.add(this.lblValInvalide, JLabel.NORTH);

		this.revalidate();
		this.repaint();
		this.ctrl.majIhm();
	}
}

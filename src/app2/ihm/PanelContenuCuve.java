package app2.ihm;

import app2.Controleur;
import app2.metier.*;
import app2.ihm.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

import java.awt.event.BorderLayout;
import java.awt.event.GridLayout;

public class PanelContenuCuve extends JPanel
{
	private Controleur ctrl;
	private JTextField txtContenu;
	private JTextField txtCuve;
	private JButton    btnValider ;

	public PanelBtnPasser(Controleur ctrl)
	{
		this.ctrl = ctrl;


		//création des composants
		this.txtContenu    = new JTextField(3);
		this.txtCuve       = new JTextField(3);
		this.btnValider    = new JButton("Valider");
		JPanel panelCentre = new JPanel();
		JPanel panelBas    = new JPanel();

		//positionnement des composants
		this.panelCentre.setLayout(new GridLayout(2,2));
		this.setLayout(new BorderLayout());

		this.panelCentre.add (new JLabel("Contenu:"),LEFT_ALIGNMENT);
		this.panelCentre.add (this.txtContenu);
		this.panelCentre.add (new JLabel("Cuve à remplir:"),LEFT_ALIGNMENT);
		this.panelCentre.add (this.txtCuve   );

		this.panelBas   .add (this.btnValider);

		this.add(this.panelCentre, BorderLayout.CENTER);
		this.add(this.panelBas   , BorderLayout.SOUTH );


		//activation des composants
		this.btnValider.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}
}

package app2.ihm;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import app2.Controleur;


public class FrameReseau extends JFrame
{
	private PanelReseau      panelReseau;
	private PanelBtnPasser   panelBtnPasser;
	private PanelContenuCuve panelContenuCuve;

	private JScrollPane      scrollPane;

	public FrameReseau(Controleur ctrl)
	{		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (int) tailleEcran.getWidth ();		
		int y = (int) tailleEcran.getHeight();

		//Propriétés de la Frame
		this.setSize                 (x, y);
		this.setLocationRelativeTo   (null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//création des composants
		this.panelReseau      = new PanelReseau     (ctrl);
		this.panelBtnPasser   = new PanelBtnPasser  (ctrl);
		this.panelContenuCuve = new PanelContenuCuve(ctrl);

		this.scrollPane       = new JScrollPane(this.panelReseau);

		//positionnement des composants

		this.add(this.scrollPane      , BorderLayout.CENTER);
		this.add(this.panelBtnPasser  , BorderLayout.SOUTH);
		this.add(this.panelContenuCuve, BorderLayout.EAST);
		
		this.setVisible(true);

	}

	public void majIhm()
	{
		this.panelReseau.majIhm();
	}

	public void reconstruction()
	{
		/* Cette méthode est appelée quand l'on ouvre un fichier, afin que le JScrollPane s'adapte au réseau généré */
		this.panelBtnPasser.reset();
		this.scrollPane.revalidate();
		this.repaint();
	}
}
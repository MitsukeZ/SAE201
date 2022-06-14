package app2.ihm;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import app2.Controleur;


public class FrameReseau extends JFrame
{
	private PanelReseau    panelReseau;
	private PanelBtnPasser panelBtnPasser;

	public FrameReseau(Controleur ctrl)
	{
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (int) tailleEcran.getWidth ();		
		int y = (int) tailleEcran.getHeight();

		this.setSize(x, y);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		//création des composants

		this.panelReseau 	= new PanelReseau   (ctrl);
		this.panelBtnPasser = new PanelBtnPasser(ctrl);

		//positionnement des composants

		this.add(panelReseau   , BorderLayout.CENTER);
		this.add(panelBtnPasser, BorderLayout.SOUTH);
		
		this.setVisible(true);

	}


}
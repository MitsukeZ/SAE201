package app2.ihm;

import app2.ihm.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Graphics;

import app2.Controleur;


public class FrameReseau extends JFrame
{
	private PanelReseau    panelReseau;
	private PanelBtnPasser panelBtnPasser;

	public FrameReseau(Controleur ctrl)
	{
		this.setSize(700, 400);
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
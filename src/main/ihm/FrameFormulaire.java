package main.ihm;
import  main.Controleur;
import  main.metier.*;
import  java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.plaf.ActionMapUIResource;

import java.awt.event.*;

public class FrameFormulaire extends JFrame implements ActionListener
{
	private Controleur        ctrl;
	private JPanel            panelValider;
	private PanelInformations panelInfos;
	private JButton           btnValider;
	private int               compteur;
	private int               nbCuves;
	private int               indCuves;

	public FrameFormulaire (Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Information sur les cuves");
		this.setLocation(200,200);
		this.setSize ( 500, 250 );

		// CrÃ©ation des composants
		this.panelValider = new JPanel();
		this.panelInfos   = new PanelInformations(this.ctrl);
		this.btnValider   = new JButton("Valider la saisie");
		this.compteur = 1;
		// Positionnement des composants
		this.add(panelValider, BorderLayout.SOUTH);
		this.add(panelInfos, BorderLayout.CENTER);

		this.panelValider.add(this.btnValider);


		//activation des composants
		this.btnValider.addActionListener(this);


		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}   

	public void actionPerformed (ActionEvent e)
	{
		if (e.getSource() == this.btnValider)
		{
			if (this.compteur == 1)
			{
				this.compteur ++;
				this.nbCuves  = this.panelInfos.getNbCuve();
				this.indCuves = 1;
				this.panelInfos.passageEtapeSupp(2);
			}
			else
			{
				if (this.compteur == 2)
				{
					if (this.indCuves < this.nbCuves)
					{
						this.ctrl.creerCuve(this.panelInfos.getCapacite(), this.panelInfos.getPosX(), this.panelInfos.getPosY(), this.panelInfos.getPosInfo());
						this.indCuves ++;
						this.panelInfos.remiseZero();
					}
					else
					{
						this.compteur ++;
						this.panelInfos.passageEtapeSupp(3);
					}
				}
				else
				{
					if (this.compteur == 3 )
					{
						this.ctrl.creerTube(this.panelInfos.getCuve1(), this.panelInfos.getCuve2(), this.panelInfos.getEpaisseur());
					}
				}
			}
		}
	}

	public int getCpt()
	{
		return this.compteur;
	}
}
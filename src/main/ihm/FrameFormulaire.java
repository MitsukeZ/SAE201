package main.ihm;
import java.awt.BorderLayout;

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
	
    public FrameFormulaire (Controleur ctrl)
    {
        this.ctrl = ctrl;

		this.setTitle("Information sur les cuves");
		this.setLocation(200,200);
		this.setSize ( 500, 250 );

        // CrÃ©ation des composants
        this.panelValider = new JPanel();
        this.panelInfos   = new PanelInformations(this.ctrl, this);
        this.btnValider   = new JButton("Valider la saisie");
        this.compteur = 0;
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
        if (compteur == 0)
        {

        }
    }

    public int getCpt()
    {
        return this.compteur;
    }
}
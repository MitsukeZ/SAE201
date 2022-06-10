import java.awt.BorderLayout;

import javax.swing.*;

public class FrameFormulaire extends JFrame
{
	private Controleur        ctrl;
    private JPanel            panelValider;
    private PanelInformations panelInfos;
    private JButton           btnValider;
	
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

        // Positionnement des composants
        this.add(panelValider, BorderLayout.SOUTH);
        this.add(panelInfos, BorderLayout.CENTER);

        this.panelValider.add(this.btnValider);


        //activation des composants



        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }   
}
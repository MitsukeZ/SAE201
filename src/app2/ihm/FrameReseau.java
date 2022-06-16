package app2.ihm;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

import app2.Controleur;


public class FrameReseau extends JFrame
{
	private Controleur ctrl;
	
	private PanelReseau      panelReseau;
	private PanelBtnPasser   panelBtnPasser;
	private PanelContenuCuve panelContenuCuve;

	private JScrollPane      scrollPane;

	public FrameReseau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (int) tailleEcran.getWidth ();		
		int y = (int) tailleEcran.getHeight();

		this.setSize(x, y);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		//création des composants
		this.panelReseau      = new PanelReseau     (ctrl);
		this.panelBtnPasser   = new PanelBtnPasser  (ctrl);
		this.panelContenuCuve = new PanelContenuCuve(ctrl);

		this.scrollPane = new JScrollPane(this.panelReseau);

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
		System.out.println("Reconstruction Incoming !");
		
		/* Cette méthode est appelée quand l'on ouvre un fichier, afin que le JScrollPane s'adapte au réseau généré */
		this.scrollPane.revalidate();
		this.repaint();

		System.out.println("finito");
	}
}
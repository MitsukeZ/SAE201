package app2.ihm;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;

import java.io.File;

import app2.Controleur;

public class PanelBtnPasser extends JPanel implements ActionListener
{
	private JButton      btnPasser;
	private JButton      btnOuvrir;

	private JFileChooser jfc;

	private JLabel       lblNTour;
	private int 	     nbTour = 1;
	private Controleur   ctrl;

	private boolean      fichierOuvert;

	public PanelBtnPasser(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new FlowLayout());

		//création des composants
		this.lblNTour  = new JLabel ("Tour numero " + this.nbTour);
		this.btnPasser = new JButton("Passer au tour suivant");
		this.btnOuvrir = new JButton("Ouvrir un fichier");

		this.jfc = new JFileChooser(new File("./"));

		this.fichierOuvert = false;

		//positionnement des composants
		this.add(this.btnOuvrir);
		this.add(this.lblNTour );
		this.add(this.btnPasser);

		//activation des composants
		this.btnPasser.addActionListener(this);
		this.btnOuvrir.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnOuvrir)
		{
			int valeurJFC = this.jfc.showOpenDialog(this);

			if (valeurJFC == JFileChooser.APPROVE_OPTION) 
			{
				File fichier = jfc.getSelectedFile();
				String nomFichier = fichier.getName();

				if (nomFichier.substring(nomFichier.lastIndexOf(".")).equals(".data")) {
					this.ctrl.ouvrirFichier(fichier.getAbsolutePath());
					this.fichierOuvert = true;
				}
			}

			return;
		}

		if (!this.fichierOuvert) {return;}
		
		if ( e.getSource() == this.btnPasser)
		{
			this.ctrl.passerAuTourSuivant();
			this.lblNTour.setText("Tour numero " + ++this.nbTour);
			return;
		}
	}

	public void reset() 
	{
		this.nbTour = 0;
		this.lblNTour.setText("Tour numero " + ++this.nbTour);
	}
}
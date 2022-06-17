package app1.ihm.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.GridLayout;

public class PanelNbCuves extends JPanel 
{
	private JTextField txtNbCuves;
	private String     msgErreur;

	public PanelNbCuves(String msgErreur) 
	{
		this.msgErreur = msgErreur;

		//Création des composants
		this.txtNbCuves = new JTextField();

		this.setLayout(new GridLayout(0, 2, 50, 50));

		//Ajout des composants
		this.add(new JLabel("Nombre de cuves :"));
		this.add(this.txtNbCuves);

		this.add(new JLabel(this.msgErreur));
		this.add(new JLabel());

		this.add(new JLabel());
		this.add(new JLabel());

		this.add(new JLabel());
		this.add(new JLabel());
	}

	public int getNbCuves() 
	{
		try
		{
			return Integer.parseInt(this.txtNbCuves.getText());
		} catch (NumberFormatException e) {return -1;}
	}
}

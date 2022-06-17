package app1.ihm.panels;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.GridLayout;

public class PanelCreerCuve extends JPanel 
{
	private String        msgErreur;

	private JTextField    txtCapacite;
	private JTextField    txtPosX;
	private JTextField    txtPosY;
	private JList<String> lstPosInfo;

	public PanelCreerCuve(String msgErreur, int numCuve) 
	{
		this.msgErreur = msgErreur;

		//Création des composants
		this.txtCapacite = new JTextField();
		this.txtPosX     = new JTextField();
		this.txtPosY     = new JTextField();
		this.lstPosInfo  = new JList<String>(new String[] {"Haut", "Bas", "Gauche", "Droite"});

		this.setLayout(new GridLayout(0, 2, 20, 20));

		//Ajout des composants
		this.add(new JLabel("Capacité de la cuve " + numCuve + " :"));
		this.add(this.txtCapacite);

		this.add(new JLabel("Position X :"));
		this.add(this.txtPosX);

		this.add(new JLabel("Position Y :"));
		this.add(this.txtPosY);

		this.add(new JLabel("Placement des informations :"));
		this.add(new JScrollPane(this.lstPosInfo));

		this.add(new JLabel(this.msgErreur));
	}

	public int getCapacite()
	{
		try
		{
			return Integer.parseInt(this.txtCapacite.getText());
		} catch (Exception e) {return -1;}
	}

	public int getPosX()
	{
		try
		{
			return Integer.parseInt(this.txtPosX.getText());
		} catch (Exception e) {return -1;}
	}

	public int getPosY()
	{
		try
		{
			return Integer.parseInt(this.txtPosY.getText());
		} catch (Exception e) {return -1;}
	}

	public String getPosInfo()
	{
		return this.lstPosInfo.getSelectedValue();
	}
}

package main.ihm;
import  main.Controleur;
import  main.metier.*;
import  javax.swing.*;
import  java.awt.GridLayout;

public class PanelInformations extends JPanel
{
	private Controleur      ctrl;
	private JPanel          panelGeneral;
	private JTextField      txtNbElt;
	private JTextField      txtCapacite;
	private JTextField      txtPosX;
	private JTextField      txtPosY;
	private JTextField      txtPosInfo;
	private JList           lstCuve1;
	private JList           lstCuve2;
	private JTextField      txtEpaisseur;


	public PanelInformations (Controleur ctrl)
	{
		this.ctrl      = ctrl;

		// crÃ©ation des composants
		this.txtNbElt = new JTextField(20);
		this.txtEpaisseur = new JTextField(20);
		this.panelGeneral = new JPanel();
		this.panelGeneral.setLayout(new GridLayout(4,2) );
		// Positionnement des composants

		this.panelGeneral.add(new JLabel("Nombre de cuves :"), RIGHT_ALIGNMENT);
		this.panelGeneral.add(this.txtNbElt);
		this.add (this.panelGeneral);

	}

	public int getCapacite()
	{
		return Integer.parseInt(this.txtCapacite.getText());
	}
	public int getNbCuve()
	{
		return Integer.parseInt(this.txtNbElt.getText());
	}

	public int getPosX ()
	{
		return Integer.parseInt(this.txtPosX.getText()); 
	} 

	public int getPosY ()
	{
		return Integer.parseInt(this.txtPosY.getText()); 
	} 


	public String getPosInfo ()
	{
		return this.txtPosInfo.getText(); 
	} 	

	public Cuve getCuve1()
	{
		return (Cuve) this.lstCuve1.getSelectedValue();
	}
	public Cuve getCuve2()
	{
		return (Cuve) this.lstCuve2.getSelectedValue();
	}

	public int getEpaisseur()
	{
		return Integer.parseInt(this.txtEpaisseur.getText()); 
	}
	public void passageEtapeSupp (int nrEtape)
	{   
		if ( nrEtape == 2 )
		{
			this.txtCapacite = new JTextField(20);
			this.txtPosX     = new JTextField(20);
			this.txtPosY     = new JTextField(20);
			this.txtPosInfo  = new JTextField(20);
			this.panelGeneral.removeAll();
			this.panelGeneral.add(new JLabel("Capacité de la cuve"));
			this.panelGeneral.add(this.txtCapacite);
			this.panelGeneral.add(new JLabel("Position X"));
			this.panelGeneral.add(this.txtPosX);
			this.panelGeneral.add(new JLabel("Position Y"));
			this.panelGeneral.add(this.txtPosY);
			this.panelGeneral.add(new JLabel("Position du texte"));
			this.panelGeneral.add(this.txtPosInfo);

			this.panelGeneral.revalidate();
			
		}

		if ( nrEtape == 3 )
		{
			this.panelGeneral.removeAll();
			this.lstCuve1     = new JList (this.ctrl.getCuves().toArray());
			this.lstCuve2     = new JList (this.ctrl.getCuves().toArray());
			this.txtEpaisseur = new JTextField (20);

			this.panelGeneral.add(new JLabel ("Cuve 1"));
			this.panelGeneral.add(this.lstCuve1);
			this.panelGeneral.add(new JLabel ("Cuve 2"));
			this.panelGeneral.add(this.lstCuve2);
			this.panelGeneral.add(new JLabel ("Epaisseur"));
			this.panelGeneral.add(this.txtEpaisseur);
			this.panelGeneral.revalidate();
		}
	}


	public void remiseZero()
	{
		this.txtCapacite.setText("");
		this.txtEpaisseur.setText("");
		this.txtNbElt.setText("");
		this.txtPosInfo.setText("");
		this.txtPosX.setText("");
		this.txtPosY.setText("");
	}
}
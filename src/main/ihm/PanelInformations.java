package main.ihm;
import  main.Controleur;
import javax.swing.*;
import java.awt.GridLayout;

public class PanelInformations extends JPanel
{
	private Controleur      ctrl;
	private FrameFormulaire frameMere;
	private JPanel          panelGeneral;
	private JTextField      txtNbElt;
	private JTextField      txtCapacite;
	private JTextField      txtPosX;
	private JTextField      txtPosY;
	private JTextField      txtPosInfo;
	private JList           lstCuve1;
	private JList           lstCuve2;


	public PanelInformations (Controleur ctrl, FrameFormulaire frame)
	{
		this.ctrl      = ctrl;
		this.frameMere = frame;

		// crÃ©ation des composants
		this.txtNbElt = new JTextField(20);
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


	public int getPosInfo ()
	{
		return Integer.parseInt(this.txtPosInfo.getText()); 
	} 	



	public void passageEtapeSupp (int nrEtape)
	{   
		if ( nrEtape == 2 )
		{
			this.panelGeneral.removeAll();
			this.txtCapacite = new JTextField(20);
			this.txtPosX     = new JTextField(20);
			this.txtPosY     = new JTextField(20);
			this.txtPosInfo  = new JTextField(20);

			this.panelGeneral.add(new JLabel("Capacité de la cuve"));
			this.panelGeneral.add(this.txtCapacite);
			this.panelGeneral.add(new JLabel("Position X"));
			this.panelGeneral.add(this.txtPosX);
			this.panelGeneral.add(new JLabel("Position Y"));
			this.panelGeneral.add(this.txtPosY);
			this.panelGeneral.add(new JLabel("Position du texte"));
			this.panelGeneral.add(this.txtPosInfo);
		}

		if ( nrEtape == 3 )
		{
			this.lstCuve1 = new JList ();
			this.lstCuve2 = new JList ();


		}
	}

}
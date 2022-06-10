package main.ihm;
import javax.swing.*;
import java.awt.GridLayout;

public class PanelInformations extends JPanel
{
	private Controleur      ctrl;
	private FrameFormulaire frameMere;
	private JPanel          panelGeneral;
	private JTextField      txtNbElt;
	private JTextField      txtCapacite;

	public PanelInformations (Controleur ctrl, FrameFormulaire frame)
	{
		this.ctrl      = ctrl;
		this.frameMere = frame;

		// crÃ©ation des composants
		this.txtNbElt = new JTextField(20);
		this.panelGeneral = new JPanel();
		this.panelGeneral.setLayout(new GridLayout(1,2) );
		// Positionnement des composants

		this.panelGeneral.add(new JLabel("Nombre de cuves :"), RIGHT_ALIGNMENT);
		this.panelGeneral.add(this.txtNbElt);
		this.add (this.panelGeneral);

	}


	public void passageEtapeSupp (int nrEtape)
	{   
		if ( nrEtape == 2 )
		{
			this.panelGeneral.removeAll();
			this.txtCapacite = new JTextField(20);

			this.panelGeneral.add(new JLabel("Capacité de la cuve"));
			this.panelGeneral.add(this.txtCapacite);
		}

		if ( nrEtape == 3 )
		{

		}
	}
>
}
package app1.ihm.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import app1.ihm.FramePrincipale;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelValider extends JPanel implements ActionListener
{
	private FramePrincipale frame;
	private JButton btnValider;

	public PanelValider(FramePrincipale frame)
	{
		this.frame = frame;

		//Création des composants
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(this);

		//Ajout des composants
		this.add(this.btnValider);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() != this.btnValider) {return;}

		if (this.frame.verification())
		{
			this.frame.maj();
			return;
		}

		this.frame.erreur();
	}
}

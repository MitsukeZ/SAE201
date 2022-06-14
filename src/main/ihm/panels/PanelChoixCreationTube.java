package main.ihm.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.ihm.FramePrincipale;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelChoixCreationTube extends JPanel implements ActionListener
{
    private JButton btnCreerTube;
    private JButton btnGenererFichier;

    private FramePrincipale frame;
    
    public PanelChoixCreationTube(FramePrincipale frame)
    {
        this.frame = frame;
        
        this.setLayout(new GridLayout(5,1));

        //Création des composants
        this.btnCreerTube      = new JButton("Créer un tube");
        this.btnGenererFichier = new JButton("Générer les fichiers");

        this.btnCreerTube.addActionListener(this);
        this.btnGenererFichier.addActionListener(this);

        //Ajout des composants
        this.add(new JLabel("Choisissez une option : ", JLabel.CENTER));
        this.add(this.btnCreerTube);
        this.add(this.btnGenererFichier);
    }    

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnCreerTube)
        {
            this.frame.maj();
            return;
        }

        if (e.getSource() == this.btnGenererFichier)
        {
            this.frame.setEtape(FramePrincipale.GENERATION);
            this.frame.maj();
            return;
        }
    }
}

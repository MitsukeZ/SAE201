package main.ihm.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.ihm.FramePrincipale;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelChoixStructure extends JPanel implements ActionListener
{
    private FramePrincipale frame;
    
    private JButton btnAdjacence;
    private JButton btnMatrice;
    private JButton btnMatOpti;

    public PanelChoixStructure(FramePrincipale frame)
    {
        this.frame = frame;
        
        this.setLayout(new GridLayout(4,1));

        //Création des composants
        this.btnAdjacence = new JButton("Liste d'adjacence"        );
        this.btnMatrice   = new JButton("Matrice de coût"          );
        this.btnMatOpti   = new JButton("Matrice de coût optimisée");

        this.btnAdjacence.addActionListener(this);
        this.btnMatrice  .addActionListener(this);
        this.btnMatOpti  .addActionListener(this);

        //Ajout des composants
        this.add(new JLabel("Choisissez une option :"));
        this.add(this.btnAdjacence);
        this.add(this.btnMatrice);
        this.add(this.btnMatOpti);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnAdjacence) {this.frame.generer('L'); this.frame.maj(); return;}
        if (e.getSource() == this.btnMatrice  ) {this.frame.generer('M'); this.frame.maj(); return;}
        if (e.getSource() == this.btnMatOpti  ) {this.frame.generer('O'); this.frame.maj(); return;}
    }
}

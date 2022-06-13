package main.ihm.panels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import main.metier.*;

import javax.swing.JLabel;

import java.awt.GridLayout;

public class PanelCreationTube extends JPanel
{
    private JTextField txtCuve1;
    private JTextField txtCuve2;
    private JTextField txtEpaisseur;
    
    public PanelCreationTube(String msgErreur)
    {
        this.txtCuve1     = new JTextField();
        this.txtCuve2     = new JTextField();
        this.txtEpaisseur = new JTextField(); 
    
        this.setLayout(new GridLayout(0, 2));

        this.add(new JLabel("Numéro de la première cuve (à partir de 1) :"));
        this.add(this.txtCuve1);

        this.add(new JLabel("Numéro de la seconde  cuve (à partir de 1) :"));
        this.add(this.txtCuve2);

        this.add(new JLabel("Épaisseur du tube :"));
        this.add(this.txtEpaisseur);

        this.add(new JLabel(msgErreur));
    }   

    public int getCuve1() {
        try {return Integer.parseInt(this.txtCuve1.getText());}
        catch (Exception e) {return -1;}
    }

    public int getCuve2() {
        try {return Integer.parseInt(this.txtCuve2.getText());}
        catch (Exception e) {return -1;}
    }

    public double getEpaisseur() {
        try {return Double.valueOf(this.txtEpaisseur.getText());}
        catch (Exception e) {return -1;}
    }
}

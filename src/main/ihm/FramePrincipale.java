package main.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.Controleur;
import main.ihm.panels.*;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

public class FramePrincipale extends JFrame 
{
    public static final int MENU_TUBE  = 1;
    public static final int GENERATION = 3;
    
    private Controleur ctrl;
    private int etape;

    private int nbCuves;
    private int compteur;

    //Panels
    private PanelValider           panelValider;
    private PanelNbCuves           panelNbCuves;
    private PanelCreerCuve         panelCreerCuves;
    private PanelChoixCreationTube panelChoixCreation;
    
    public FramePrincipale(Controleur ctrl) 
    {
        this.etape    = -1;
        this.compteur = 1;
        
        this.ctrl     = ctrl;
        
        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.setSize(800, 400);

        this.panelValider = new PanelValider(this);
        
        //Fenêtre centrée sur l'écran
        this.setLocation((int) (tailleEcran.getWidth()/2-400) , (int) (tailleEcran.getHeight()/2-200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.maj();

        this.setVisible(true);
    }

    public void setTitre()
    {
        switch (this.etape)
        {
            case 0: this.setTitle("Création des cuves"); break;
            case 2: this.setTitle("Choix d'une action"); break;
            case 3: this.setTitle("Création d'un tube"); break;
        }
    }

    public void setPanelsAdequats()
    {
        this.getContentPane().removeAll();
        
        switch (this.etape) 
        {
            case 0: 
                this.panelNbCuves = new PanelNbCuves("");    
                this.add(this.panelNbCuves, BorderLayout.CENTER); 
                break;
            case 1:
                this.panelCreerCuves = new PanelCreerCuve("", this.compteur);
                this.add(this.panelCreerCuves);
                break;
            case 2:
                this.panelChoixCreation = new PanelChoixCreationTube(this);
                this.add(this.panelChoixCreation);
                this.revalidate();
                return;
            default:
                this.add(new JLabel("FINITO"), BorderLayout.CENTER);
                break;
        }

        
        this.add(this.panelValider, BorderLayout.SOUTH);
        this.revalidate();
    }

    public boolean verification()
    {
        boolean condition;
        
        switch (this.etape) 
        {
            case 0: this.nbCuves = this.panelNbCuves.getNbCuves(); return this.nbCuves > -1;
            
            case 1: condition =  (this.panelCreerCuves.getCapacite() != -1 &&
                                  this.panelCreerCuves.getPosX()     != -1 &&
                                  this.panelCreerCuves.getPosY()     != -1 &&
                                  this.ctrl.creerCuve(this.panelCreerCuves.getCapacite(), 
                                                      this.panelCreerCuves.getPosX(), 
                                                      this.panelCreerCuves.getPosY(), 
                                                      this.panelCreerCuves.getPosInfo()));
                    if (condition) {this.compteur++;} else {return false;}
                    if (this.compteur <= this.nbCuves) {this.etape--;}
                    return condition;
        }
        
        
        return true;
    }

    public void maj() 
    {
        System.out.println("maje");
        this.etape++;
        this.setTitre();
        this.setPanelsAdequats();
    }

    public void erreur() 
    {
        System.out.println("erreur");
        this.getContentPane().removeAll();
        
        switch (this.etape) 
        {
            case 0: 
                this.panelNbCuves = new PanelNbCuves("Valeur Invalide !");    
                this.add(this.panelNbCuves, BorderLayout.CENTER); 
                break;
            case 1:
                this.panelCreerCuves = new PanelCreerCuve("Valeurs Invalides !", this.compteur);
                this.add(this.panelCreerCuves);
                break;
        }

        
        this.add(this.panelValider, BorderLayout.SOUTH);
        this.revalidate();
    }

    public void generer(char structure)
    {
        this.ctrl.generer(structure);
    }

    public void setEtape(int etape)
    {
        if (etape < -1 || etape > 3) {return;}

        this.etape = etape;
    }
}

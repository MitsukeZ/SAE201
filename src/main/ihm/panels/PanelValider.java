package main.ihm.panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import main.ihm.FramePrincipale;

public class PanelValider extends JPanel implements ActionListener
{
    private FramePrincipale frame;
    private JButton btnValider;

    public PanelValider(FramePrincipale frame) {
        this.frame = frame;

        this.btnValider = new JButton("Valider");
        this.btnValider.addActionListener(this);

        this.add(this.btnValider);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() != this.btnValider) {return;}

        if (this.frame.verification()) {
            this.frame.maj();
            return;
        }

        this.frame.erreur();
        
    }
}

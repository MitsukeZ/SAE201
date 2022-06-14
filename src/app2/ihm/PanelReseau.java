package app2.ihm;

import app2.Controleur;
import app2.metier.*;
import app2.ihm.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class PanelReseau extends JPanel
{
	private Controleur ctrl;

	public PanelReseau(Controleur ctrl)
	{
		this.ctrl       = ctrl;
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawString("Réseau",20,20); 

		//dessin des tubes
		for (Tube t : this.ctrl.getTubes())
		{
			g.setColor(Color.GRAY);

			g.drawLine((t.getCuve1().getPosX()+(t.getCuve1().getCapacite()/10)/2), (t.getCuve1().getPosY()+(t.getCuve2().getCapacite()/10)/2), 
					   (t.getCuve2().getPosX()+(t.getCuve2().getCapacite()/10)/2), (t.getCuve2().getPosY()+(t.getCuve2().getCapacite()/10)/2));
			
			//étiquettes des épaisseurs des tubes
			g.setFont(new Font("name", (int) Font.BOLD, 18));
			g.drawString(""+t.getEpaisseur(), ((t.getCuve2().getPosX() + t.getCuve1().getPosX())/2), ((t.getCuve2().getPosY() + t.getCuve1().getPosY()))/2);
			
			//dessin des épaisseurs des tubes
			for (int cpt = 1; cpt < t.getEpaisseur(); cpt++)
			{
				g.drawLine( (t.getCuve1().getPosX()+(t.getCuve1().getCapacite()/10)/2)+cpt, (t.getCuve1().getPosY()+(t.getCuve2().getCapacite()/10)/2)+cpt, 
					   		(t.getCuve2().getPosX()+(t.getCuve2().getCapacite()/10)/2)+cpt, (t.getCuve2().getPosY()+(t.getCuve2().getCapacite()/10)/2)+cpt);
			}

		}
		

		for ( Cuve c : this.ctrl.getCuves())
		{
			//dessin de l'intérieur des cuves
			g.setColor(this.getColor(c));
			g.fillOval(c.getPosX(), c.getPosY(), (int) (c.getCapacite()/10), (int) (c.getCapacite()/10));

			//dessins de l'exterieur des cuves en contour noir
			g.setColor(Color.BLACK);
			g.drawOval(c.getPosX(), c.getPosY(), (int) (c.getCapacite()/10), (int) (c.getCapacite()/10));
			
			//placement des étiquettes des tubes en fonction de l'info du positionnement
			switch (c.getPosInfo()) 
			{
				case "HAUT"   -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(), c.getPosX(),                          (c.getPosY()-10)); 
				case "BAS"    -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(), c.getPosX(),                          (c.getPosY())+(c.getCapacite()/10)+10);
				case "GAUCHE" -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(),(c.getPosX()-(c.getCapacite()/5)-50), (c.getPosY()+20));
				case "DROITE" -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(),(c.getPosX())+(c.getCapacite()/10),    (c.getPosY()+40));
				
			}
			
		}

	}

	public Color getColor (Cuve c)
	{
		
		double contenu = c.getContenu();		
		Color[] tabColor = new Color[501]; //on crée un tableau de Color pour avoir les nuancés de rouge en fonction du contenu du la cuve
		int r, g,  b ;                     //nous sert à initialiser les dégradés de rouge
		r = g = b = 255;                   //initialisation à 255 pour commencer par la couleur blanche

		for (int cpt = 0; cpt < 255; cpt++)
		{
			//System.out.println("rouge:" + r + "  vert:" + g + "  bleu:" + b);
			tabColor[cpt] = new Color (r , g , b);
			if (g > 0 && b > 0)
			{
				g--;
				b--;
			}
		}

		for (int cpt = 255; cpt < tabColor.length ; cpt++)
		{
			//System.out.println("rouge:" + r + "  vert:" + g + "  bleu:" + b);
			tabColor[cpt] = new Color (r , g , b);
			if (r > 0)
				r--;
		}
		
		return new Color(tabColor[(int)contenu].getRGB());
	}

	public String afficher()
	{
		String sRet="";

		for ( Cuve c : this.ctrl.getCuves())
		{
			sRet += c.toString() + "\n";

		}

		return sRet;
	}
}

/*------------------------------------ */
/*Crée le liens entre les cuve         */
/*  
	on crée d'abord la ligne en partant du centre de la première cuve :
	x1 = X de cuve 1 + son rayon
	y1 = Y de cuve 1 + son rayon

	x2 = X de cuve 2 + son rayon
	y2 = Y de cuve 2 plus son rayon
	
	g.drawLine(130+40, 130+40,400+30 , 80+30); 

	g.setColor(Color.RED);

	g.fillOval(130, 130, 80, 80);
	g.fillOval(400, 80, 60, 60); 
*/

/*-------------------------------------*/

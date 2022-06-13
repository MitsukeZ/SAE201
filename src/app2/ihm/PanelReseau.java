package app2.ihm;

import app2.Controleur;
import app2.metier.*;
import app2.ihm.*;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class PanelReseau extends JPanel
{
	private Controleur ctrl;
	private ArrayList<Cuve> listeCuves;
	private ArrayList<Tube> listeTubes;

	public PanelReseau(Controleur ctrl)
	{
		//this.ctrl       = ctrl;
		this.listeCuves = new ArrayList<Cuve>() = this.ctrl.getCuves();
		this.listeTubes = new ArrayList<Tube>() = this.ctrl.getTubes();
	}

	public void paint(Graphics g)
	{
		super.paint(g);


		for (Tube t : this.listeTubes)
		{
			g.setColor(Color.GRAY);

			g.drawLine(t.getCuve1().getPosX(), t.getCuve1().getPosY(), t.getCuve2().getPosX() , t.getCuve2().getPosY()); 

		}

		for ( Cuve c : this.listeCuves)
		{
			g.setColor(this.getColor(c));
			g.fillOval(c.getPosX(), c.getPosY(), (c.getContenu()/5), (c.getContenu()/5));

		}

	}


	public Color getColor (Cuve c)
	{
		
		double contenu = c.getContenu();		
		Color[] tabColor = new Color[500]; //on crée un tableau de Color pour avoir les nuancés de rouge en fonction du contenu du la cuve
		float r, g,  b ;                   //nous sert à initialiser les dégradés de rouge
		r = g = b = 255;                   //initialisation à 255 pour commencer par la couleur blanche

		for (int cpt = 0; cpt <= 255; cpt++)
		{
			tabColor[cpt] = new Color (r , g , b);
			g--;
			b--;
		}

		for (int cpt = 256; cpt < tabColor.length ; cpt++)
		{
			tabColor[cpt] = new Color (r , g , b);
			r--;
		}

		
		return new Color(tabColor[(int)contenu].getRGB()/2);
	}

	public void majIhm()
	{
		
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

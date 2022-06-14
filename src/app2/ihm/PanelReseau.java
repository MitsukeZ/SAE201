package app2.ihm;

import app2.Controleur;
import app2.metier.*;
import app2.ihm.*;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class PanelReseau extends JPanel
{
	private Controleur ctrl;
	private List<Cuve> listeCuves;
	private List<Tube> listeTubes;

	private Color[] tabColor;		// nouveau

	public PanelReseau(Controleur ctrl)
	{
		this.ctrl       = ctrl;
		this.listeCuves = new ArrayList<Cuve>();
		
		for (Cuve c: this.ctrl.getCuves())
			this.listeCuves.add(c);


		this.listeTubes = new ArrayList<Tube>();

		for (Tube t: this.ctrl.getTubes())
			this.listeTubes.add(t);


		System.out.println(this.afficher());

		// nouveau

		this.tabColor = new Color[500];

		for (int i = 0; i < this.tabColor.length; i++)
		{
			if (i < 250)
				tabColor[i] = new Color( Color.HSBtoRGB(0.0f, (float)(0.004*i), 1.0f) );
			else
				tabColor[i] = new Color( Color.HSBtoRGB(0.0f, 1.0f, (float)(1.0 - 0.004* (i - 250))) );
		}
		
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		g.drawString("Réseau",20,20); 
		
		for (Tube t : this.ctrl.getTubes())
		{
			g.setColor(Color.GRAY);

			g.drawLine((t.getCuve1().getPosX()+(t.getCuve1().getCapacite()/10)/2), (t.getCuve1().getPosY()+(t.getCuve2().getCapacite()/10)/2), 
					   (t.getCuve2().getPosX()+(t.getCuve2().getCapacite()/10)/2), (t.getCuve2().getPosY()+(t.getCuve2().getCapacite()/10)/2));
			
			//g.drawString(t.getEpaisseur(), (t.getCuve2().getPosX() + 50), (t.getCuve1().getPosY()+50));
			 
			for (int cpt = 1; cpt < t.getEpaisseur(); cpt++)
			{
				g.drawLine( (t.getCuve1().getPosX()+(t.getCuve1().getCapacite()/10)/2)+cpt, (t.getCuve1().getPosY()+(t.getCuve2().getCapacite()/10)/2)+cpt, 
					   		(t.getCuve2().getPosX()+(t.getCuve2().getCapacite()/10)/2)+cpt, (t.getCuve2().getPosY()+(t.getCuve2().getCapacite()/10)/2)+cpt);
			}

		}
		

		for ( Cuve c : this.ctrl.getCuves())
		{
			
			g.setColor(this.getColor(c));
			g.fillOval(c.getPosX(), c.getPosY(), (int) (c.getCapacite()/10), (int) (c.getCapacite()/10));

			g.setColor(Color.BLACK);
			g.drawOval(c.getPosX(), c.getPosY(), (int) (c.getCapacite()/10), (int) (c.getCapacite()/10));
			
			
			switch (c.getPosInfo()) 
			{
				case "HAUT"   -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(), c.getPosX(),                          (c.getPosY()-(c.getCapacite()/10))); 
				case "BAS"    -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(), c.getPosX(),                          (c.getPosY())+(c.getCapacite()/10)+10);
				case "GAUCHE" -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(),(c.getPosX()-(c.getCapacite()/10)-20), (c.getPosY()+10));
				case "DROITE" -> g.drawString(c.getIdentifiant() +"   "+ c.getContenu() + "/" + c.getCapacite(),(c.getPosX())+(c.getCapacite()/10),    (c.getPosY()+20));
					
			}
			
		}

	}



	public Color getColor (Cuve c)
	{
		int contenu = (int) c.getContenu();

		if (contenu == 0)
			return this.tabColor[0];
		else
			return this.tabColor[(contenu / 2) - 1];

		return null;

		/*
		
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

		*/
	}



	public String afficher()
	{
		String sRet="";

		for ( Cuve c : this.listeCuves)
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

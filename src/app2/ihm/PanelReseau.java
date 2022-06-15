package app2.ihm;

import app2.Controleur;
import app2.metier.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Rectangle;

public class PanelReseau extends JPanel implements Scrollable
{
	private static final int DECALAGE_X = 25;
	private static final int DECALAGE_Y = 100;
	
	private Controleur ctrl;

	private Color[] tabColor;		// nouveau

	public PanelReseau(Controleur ctrl)
	{
		this.ctrl = ctrl;

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

	public void paint(Graphics gr)
	{
		super.paint(gr);

		Graphics2D g = (Graphics2D) gr;

		//création Font 
		g.setFont(new Font("name", (int) Font.BOLD, 18));

		g.drawString("Réseau",20,20); 

		//dessin des tubes
		for (Tube t : this.ctrl.getTubes())
		{
			
			
			g.setColor(Color.GRAY);
			g.setStroke(new BasicStroke((float) (t.getEpaisseur())));
			g.drawLine(PanelReseau.DECALAGE_X + (t.getCuve1().getPosX() + (int) ((t.getCuve1().getCapacite()/10)/2)), 
					   PanelReseau.DECALAGE_Y + (t.getCuve1().getPosY() + (int) ((t.getCuve1().getCapacite()/10)/2)), 
					   PanelReseau.DECALAGE_X + (t.getCuve2().getPosX() + (int) ((t.getCuve2().getCapacite()/10)/2)), 
					   PanelReseau.DECALAGE_Y + (t.getCuve2().getPosY() + (int) ((t.getCuve2().getCapacite()/10)/2)));
			
			//étiquettes des épaisseurs des tubes
			g.drawString(""+t.getEpaisseur(), PanelReseau.DECALAGE_X + ((t.getCuve2().getPosX() + t.getCuve1().getPosX())/2), 
			                                  PanelReseau.DECALAGE_Y + ((t.getCuve2().getPosY() + t.getCuve1().getPosY())/2));

		}
		
		g.setStroke(new BasicStroke(1));
		
		for ( Cuve c : this.ctrl.getCuves())
		{
			//dessin de l'intérieur des cuves
			System.out.println("Paint cuve " + c.getIdentifiant());
			g.setColor(this.getColor(c));
			g.fillOval(PanelReseau.DECALAGE_X + c.getPosX(), 
			           PanelReseau.DECALAGE_Y + c.getPosY(), 
					   (int) (c.getCapacite()/10), 
					   (int) (c.getCapacite()/10));

			//dessins de l'exterieur des cuves en contour noir
			g.setColor(Color.BLACK);
			g.drawOval(PanelReseau.DECALAGE_X + c.getPosX(), 
					   PanelReseau.DECALAGE_Y + c.getPosY(), 
					   (int) (c.getCapacite()/10), 
					   (int) (c.getCapacite()/10));
			
			//placement des étiquettes des tubes en fonction de l'info du positionnement
			switch (c.getPosInfo()) 
			{
				case "HAUT"   -> g.drawString(c.getIdentifiant() +"   "+ String.format("%6.2f", c.getContenu()) + "/" + c.getCapacite(), PanelReseau.DECALAGE_X +  c.getPosX(),                          PanelReseau.DECALAGE_Y + (c.getPosY()-10)); 
				case "BAS"    -> g.drawString(c.getIdentifiant() +"   "+ String.format("%6.2f", c.getContenu()) + "/" + c.getCapacite(), PanelReseau.DECALAGE_X +  c.getPosX(),                          PanelReseau.DECALAGE_Y + (c.getPosY())+(c.getCapacite()/10)+10);
				case "GAUCHE" -> g.drawString(c.getIdentifiant() +"   "+ String.format("%6.2f", c.getContenu()) + "/" + c.getCapacite(), PanelReseau.DECALAGE_X + (c.getPosX()-(c.getCapacite()/5)-50),  PanelReseau.DECALAGE_Y + (c.getPosY()+20));
				case "DROITE" -> g.drawString(c.getIdentifiant() +"   "+ String.format("%6.2f", c.getContenu()) + "/" + c.getCapacite(), PanelReseau.DECALAGE_X + (c.getPosX())+(c.getCapacite()/10),    PanelReseau.DECALAGE_Y + (c.getPosY()+40));
				
			}
			
		}
		
	}

	public void update(Graphics g)
	{
		this.paint(g);
	}



	public Color getColor (Cuve c)
	{
		
		System.out.println("Contenu avant le cast " + c.getContenu() );	
		int contenu = (int) c.getContenu();
		System.out.println("Contenu de la cuve " + c.getIdentifiant() + " : " + contenu);

		if (contenu == 0)
			return this.tabColor[0];
		else
			return this.tabColor[(contenu / 2) - 1];

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

		for ( Cuve c : this.ctrl.getCuves())
		{
			sRet += c.toString() + "\n";

		}

		return sRet;
	}

	public void majIhm()
	{
		this.repaint();
	}

	//Méthodes de l'interface Scrollable
	//Cette interface permet de mettre un panel avec une méthode paint dans un JScrollPane 
	
	public Dimension getPreferredSize() {
		return new Dimension(this.ctrl.getPosXMax() + 300,
		                     this.ctrl.getPosYMax() + 300);
	}
	
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(1280, 720);
	}

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 128;
	}

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 128;
	}

	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width
						<= getParent().getSize().width;
	}

	public boolean getScrollableTracksViewportHeight() {
		return getPreferredSize().height
						<= getParent().getSize().height;
	}
}


/*------------------------------------ */
/*Crée le lien entre les cuves         */
/*  
	on crée d'abord la ligne en partant du centre de la première cuve :
	x1 = X de cuve 1 + son rayon
	y1 = Y de cuve 1 + son rayon

	x2 = X de cuve 2 + son rayon
	y2 = Y de cuve 2 plus son rayon
	
	g.drawLine(130+40, 130+40,400+30 , 80+30);
	 
	puis on construit les cuves 
	g.setColor(Color.RED);

	g.fillOval(130, 130, 80, 80);
	g.fillOval(400, 80, 60, 60); 
*/

/*-------------------------------------*/

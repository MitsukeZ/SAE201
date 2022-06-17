package app2.ihm;

import app2.Controleur;
import app2.metier.*;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.Scrollable;

public class PanelReseau extends JPanel implements Scrollable
{
	private static final int DECALAGE_X = 25;
	private static final int DECALAGE_Y = 100;

	private Controleur ctrl;
	private Color[] tabColor;

	public PanelReseau(Controleur ctrl)
	{
		this.ctrl     = ctrl;
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
			g.setColor(this.getColor(c);

			g.fillOval(130, 130, 80, 80);
			g.fillOval(400, 80, 60, 60); 
		*/

		/*-------------------------------------*/

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

	public void update(Graphics g) {this.paint(g);}

	public Color getColor (Cuve c)
	{
		int contenu = (int) c.getContenu();

		if (contenu == 0)
			return this.tabColor[0];
		else
			return this.tabColor[(contenu / 2) - 1];
	}

	public String afficher()
	{
		String sRet="";

		for ( Cuve c : this.ctrl.getCuves())
			sRet += c.toString() + "\n";

		return sRet;
	}

	public void majIhm() { this.repaint(); }

	//Méthodes de l'interface Scrollable
	//Cette interface permet de mettre un panel avec une méthode paint dans un JScrollPane 

	public Dimension getPreferredSize()
	{
		return new Dimension(this.ctrl.getPosXMax() + 300,
		                     this.ctrl.getPosYMax() + 300);
	}

	public Dimension getPreferredScrollableViewportSize() { return new Dimension(1280, 720); }

	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) { return 128; }

	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) { return 128; }

	public boolean getScrollableTracksViewportWidth()
	{
		return getPreferredSize().width
		 <= getParent().getSize().width;
	}

	public boolean getScrollableTracksViewportHeight()
	{
		return getPreferredSize().height
		 <= getParent().getSize().height;
	}
}

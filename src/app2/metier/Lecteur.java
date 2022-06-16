package app2.metier;

import app2.*;

import java.util.Scanner;
import java.io.FileReader;

public class Lecteur
{
	private Controleur   ctrl;
	
	public Lecteur(Controleur ctrl, String cheminFichier)
	{
		Scanner  sc;
		int      cptCuves = 0; //Sert à compter le nombre de cuves crées, au cas ou le fichier spécifierait une liste ou une matrice avec des cuves inexistantes
		String[] ligne;
		String   structure = "";

		//Informations de la cuve
		int    capacite, posX, posY;
		String posInfo;
		
		this.ctrl = ctrl;

		//Création d'un Scanner pour le fichier
		try                 {sc = new Scanner(new FileReader(cheminFichier));} 
		catch (Exception e) {return;}

		//Création des cuves
		while (sc.hasNextLine())
		{
			ligne = sc.nextLine().split("\t");

			if (!(ligne.length == 1 || ligne.length == 4)) {continue;}

			//Si la ligne annonce la structure, c'est qu'il n'y a plus de cuves à créer
			if (ligne[0].equals("liste d'adjacence"        ) || 
				ligne[0].equals("matrice de cout"          ) || 
				ligne[0].equals("matrice de cout optimisee")) 
			{
				structure = ligne[0];
				break;
			}

			//Vérifications si les données spécifiées sont correctes
			try
			{
				capacite   = Integer.parseInt(ligne[0]);
				posX       = Integer.parseInt(ligne[1]);
				posY       = Integer.parseInt(ligne[2]);
				posInfo    =                  ligne[3] ;
			}
			catch (Exception e) {System.out.println("Valeurs Invalides !"); continue;}

			//Vérification finale via la fabrique
			if (this.ctrl.creerCuve(capacite, posX, posY, posInfo)) {cptCuves++;}
		}

		if (structure.isEmpty()) {return;}
		
		//Création des tubes
		if (structure.equals("liste d'adjacence")) {this.lireListeAdjacence(sc, cptCuves);}
		else                                       {this.lireMatrice       (sc, cptCuves);}
	}
	
	public void lireListeAdjacence(Scanner sc, int cptCuves) 
	{
		String[] ligne;
		char     idCuve1, idCuve2;
		double   epaisseur;
		int      indexCuve1, indexCuve2;
		
		while (sc.hasNextLine()) 
		{
			ligne = sc.nextLine().split("\t");

			if (ligne.length != 3) {continue;}
			
			//Récupération des informations
			try 
			{
				idCuve1   = ligne[0].charAt(0       );
				idCuve2   = ligne[1].charAt(0       );
				epaisseur = Double.valueOf (ligne[2]);
			}
			catch (Exception e) {continue;}

			//Vérification de la validité des cuves
			indexCuve1 = idCuve1 - 'A';
			indexCuve2 = idCuve2 - 'A';
			
			if (indexCuve1 >= cptCuves) {continue;}
			if (indexCuve2 >= cptCuves) {continue;}

			//Création du tube
			this.ctrl.creerTube(this.ctrl.getCuves().get(indexCuve1), 
			                    this.ctrl.getCuves().get(indexCuve2), epaisseur);

		}
	}


	public void lireMatrice(Scanner sc, int cptCuves) 
	{
		String[] ligne;
		double epaisseur;
		int numLigne = 0;

		while (sc.hasNextLine())
		{
			ligne = sc.nextLine().split("\t");

			for (int cpt = 0; cpt < numLigne; cpt++)
			{
				//Vérification de la validité des valeurs
				if  (cpt >= ligne.length)                                         {break;   }
				try {epaisseur = Double.valueOf(ligne[cpt]);} catch (Exception e) {continue;}
				
				if (!ligne[cpt].equals("0"))
				{
					this.ctrl.creerTube(this.ctrl.getCuves().get(numLigne), 
					                    this.ctrl.getCuves().get(cpt     ), epaisseur);
				}
			}
			numLigne++;

			if (numLigne >= cptCuves) {break;}
		}
		
	}

}

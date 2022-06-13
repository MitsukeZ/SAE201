package main.ihm;

import iut.algo.Clavier;
import java.util.ArrayList;

import main.Controleur;

public class FormulaireConsole 
{
	private Controleur ctrl;
	
	public FormulaireConsole(Controleur ctrl)
	{
		this.ctrl = ctrl;
		
		//Variables
		int nbCuves = 0, nbTubes = 0, capaciteTmp, posXTmp, posYTmp, cuve1, cuve2, nbTubesMax;
		double epaisseur;
		String positionInfo;
		char message, structure;

		//Instructions
		do
		{
			System.out.print("Veuillez entrer le nombre de Cuves a generer : ");
			nbCuves = Clavier.lire_int();

			if (nbCuves <= 0) 
			{
				System.out.println("La valeur entrée n'est pas valide");
			}
		}
		while (nbCuves <= 0);

		for (int i = 1; i < nbCuves + 1; i++) 
		{
			System.out.print("Veuillez entrer la capacite de la Cuve "+ i +"   : ");
			capaciteTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position X de la Cuve "+ i +" : ");
			posXTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position Y de la Cuve "+ i +" : ");
			posYTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position des informations de la Cuve "+ i +" : ");
			positionInfo = Clavier.lireString();
			
			if (!this.ctrl.creerCuve(capaciteTmp, posXTmp, posYTmp, positionInfo)) 
			{
				System.out.println("Les valeurs saisies sont invalides ! Veuillez réessayer.");
				i--;
			}
		}

		nbTubesMax = (nbCuves*(nbCuves-1))/2; //Formule permettant de connaitre le nombre d'arêtes d'un graphe complet
		                                                                    //Utile pour que l'utilisateur ne créé pas trop de tubes et soit bloqué dans la console
		do 
		{
			System.out.print("Souhaitez-vous creer un tube (O/N) : ");
			message = Clavier.lire_char();
			if (message != 'N')
			{
				System.out.print("Veuillez entrer le numero de la premiere cuve a relier : ");
				cuve1 = Clavier.lire_int();

				System.out.print("Veuillez entrer le numero de la deuxieme cuve a relier : ");
				cuve2 = Clavier.lire_int();

				System.out.print("Veuillez entrer l'épaisseur du tube (utiliser un . pour separer les decimaux) : ");
				epaisseur = Clavier.lire_double();

				if (cuve1 <= 0 || cuve2 <= 0 || !this.ctrl.creerTube(this.ctrl.getCuves().get(cuve1-1), this.ctrl.getCuves().get(cuve2-1), epaisseur)) {
					System.out.println("Valeurs Invalides !");
				}
			}
		} 
		while (message != 'N' && nbTubes < nbTubesMax);

		do 
		{
			System.out.print("Veuillez entrer le type de structure (L pour liste, M pour matrice, O pour matrice optimisee) : ");
			structure = Clavier.lire_char();
		} 
		while (structure != 'L' && structure != 'M' && structure != 'O');

		this.ctrl.generer(structure);
	}
}

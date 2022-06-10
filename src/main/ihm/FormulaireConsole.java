package main.ihm;
import iut.algo.Clavier;
import java.util.ArrayList;

public class FormulaireConsole 
{
	public FormulaireConsole(Controleur ctrl)
	{
		//Variables
		int nbCuves = 0, capaciteTmp, posXTmp, posYTmp, cuve1, cuve2, sectionTuyau;
		String positionInfo;
		Cuve[] tabCuves;
		char message, structure;

		//Instructions
		do
		{
			System.out.print("Veuillez entrer le nombre de Cuves a generer : ");
			nbCuves = Clavier.lire_int();

			if (nbCuves < 0) 
			{
				System.out.println("La valeur entree n'est pas valide");
			}
		}
		while (nbCuves < 0);
		tabCuves = new Cuve[nbCuves];

		for (int i = 1; i < nbCuves + 1; i++) 
		{
			System.out.print("Veuillez entrer la capacite de la Cuve "+ i +" : ");
			capaciteTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position X de la Cuve "+ i +" : ");
			posXTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position Y de la Cuve "+ i +" : ");
			posYTmp = Clavier.lire_int();

			System.out.print("Veuillez entrer la position des informations de la Cuve "+ i +" : ");
			positionInfo = Clavier.lireString();

			tabCuves[i-1] = Cuve.fabrique(capaciteTmp, posXTmp, posYTmp, positionInfo);
			ctrl.ajouterCuve(capaciteTmp, posXTmp, posYTmp, positionInfo);
		}

		do 
		{
			System.out.print("Souhaitez-vous creer un tuyau (O/N) : ");
			message = Clavier.lire_char();
			if (message != 'N')
			{
				System.out.print("Veuillez entrer le numero de la premiere cuve a relier : ");
				cuve1 = Clavier.lire_int();

				System.out.print("Veuillez entrer le numero de la deuxieme cuve a relier : ");
				cuve2 = Clavier.lire_int();

				System.out.print("Veuillez entrer la section du tuyau : ");
				sectionTuyau = Clavier.lire_int();

				ctrl.ajouterTube(tabCuves[cuve1], tabCuves[cuve2], sectionTuyau);
			}
		} 
		while (message != 'N');

		do 
		{
			System.out.print("Veuillez entrer le type de structure (L pour liste, M pour matrice, O pour matrice optimisee) : ");
			structure = Clavier.lire_char();
		} 
		while (structure != 'L' && structure != 'M' && structure != 'O');

		ctrl.generer(structure);
	}
}

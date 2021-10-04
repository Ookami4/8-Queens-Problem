package HillClimbing;

import java.util.Arrays;
import java.util.Random;

public class HillClimbing{

	public int random(){
		// Le constructeur Random crée un nouveau générateur de nombres
		Random r = new Random();
		// La méthode next(8) génère un nombre uniformément situé entre 0 et 7.
		int random = r.nextInt(8);
		return random;
	}
	public void generateRandomly(int[][] board, int[] state) {
		//Itération dans les indices des colonnes
		for (int i = 0; i < 8; i++)
		{
			// Obtention d'un index de ligne aléatoirement
			state[i] = random();
			// Placer une reine à la place obtenue sur l'échiquier.
			board[state[i]][i] = 1;
		}
	}

	// Une fonction qui imprime la matrice en deux dimensions "board".
	public void displayChessBoard(int[][] board)
	{

		for (int i = 0; i < 8; i++)
		{
			System.out.println("+-----+-----+-----+-----+-----+-----+-----+-----+");
			for (int j = 0; j < 8; j++)
			{
				if (board[i][j] == 1) {
					System.out.print("|  Q  ");
				}
				if (board[i][j] == 0){
					System.out.print("|     ");
				}
				if (j == 7){
					System.out.print("|");
				}
			}
			System.out.print("\n");
		}
		System.out.println("+-----+-----+-----+-----+-----+-----+-----+-----+");
	}


	//Remplissage de la matrice "board" avec les valeurs "value"
	public void fillInBoard(int[][] board, int value)
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				board[i][j] = value;
			}
		}
	}

	//calculons la valeur objective de l'état (les dames s'attaquent entre elles)
	//Fonction d'évaluation heuristique - le nombre de reines qui s'attaquent les unes aux autres.
	public int hOfAttackPair(int[][] board, int[] state)
	{
		/*
		   Pour chaque reine dans une colonne, nous vérifions si d'autres reines
		   tombent dans la ligne de notre reine actuelle et si nous en trouvons,
		   n'importe laquelle, alors nous incrémentons la variable d'attaque count.
		*/

		// Nombre de reines qui s'attaquent entre elles,
		int attacking = 0;

		//le nombre de paires de reines qui s'attaquent entre elles.
		int pairs;

		// Variables permettant d'indexer une ligne et une colonne particulières sur la matrice.
		int row;
		int col;

		for (int i = 0; i < 8; i++)
		{
			//A chaque colonne 'i', la reine est placée à la ligne 'state[i]'

			//A gauche de la même ligne
			row = state[i];
			col = i - 1;
			while (col >= 0 && board[row][col] != 1)
			{
				col--;
			}
			if (col >= 0 && board[row][col] == 1)
			{
				attacking++;
			}

			//À droite de la même ligne
			row = state[i];
			col = i + 1;
			while (col < 8 && board[row][col] != 1)
			{
				col++;
			}
			if (col < 8 && board[row][col] == 1)
			{
				attacking++;
			}
			//En diagonale vers Nord-Est
			row = state[i] - 1;
			col = i + 1;
			while (col < 8 && row >= 0 && board[row][col] != 1)
			{
				col++;
				row--;
			}
			if (col < 8 && row >= 0 && board[row][col] == 1)
			{
				attacking++;
			}

			//En diagonale vers Nord-Ouest
			row = state[i] - 1;
			col = i - 1;
			while (col >= 0 && row >= 0 && board[row][col] != 1)
			{
				col--;
				row--;
			}
			if (col >= 0 && row >= 0 && board[row][col] == 1)
			{
				attacking++;
			}

			//En diagonale vers Sud-Est
			row = state[i] + 1;
			col = i + 1;
			while (col < 8 && row < 8 && board[row][col] != 1)
			{
				col++;
				row++;
			}
			if (col < 8 && row < 8 && board[row][col] == 1)
			{
				attacking++;
			}

			//En diagonale vers Sud-Ouest
			row = state[i] + 1;
			col = i - 1;
			while (col >= 0 && row < 8 && board[row][col] != 1)
			{
				col--;
				row++;
			}
			if (col >= 0 && row < 8 && board[row][col] == 1)
			{
				attacking++;
			}

		}
		pairs = attacking / 2;
		//System.out.print(pairs+"\t");
		return pairs;
	}
	//génère une configuration du matrice Board en fonction de l'état.
	public void generateBoard(int[][] board, int[] state)
	{
		fillInBoard(board, 0);
		for (int i = 0; i < 8; i++)
		{
			board[state[i]][i] = 1;
		}
	}

	//copie le contenu de l'état 2 à l'état 1
	public void copy(int[] state1, int[] state2)
	{
		for (int i = 0; i < 8; i++)
		{
			state1[i] = state2[i];
		}
	}

	//Pour obtient le voisin de l'état actuel ayant la plus petite valeur objective parmi tous les voisins ainsi que ainsi que l'état actuel.
	public void getNeighbour(int[][] board, int[] state)
	{
		//Déclarer et initialiser le board optimal et état avec le board actuel et l'état comme point de départ.

		int[][] optimalBoard = new int[8][8];
		int[] optimalState = new int[8];

		copy(optimalState, state);
		generateBoard(optimalBoard, optimalState);

		// Initialisation de la valeur optimale de l'objectif
		int optimalObjective = hOfAttackPair(optimalBoard, optimalState);



		int[][] NeighbourBoard = new int[8][8];
		int[] NeighbourState = new int[8];

		//initialisation du tableau et de l'état temporaires pour les besoins du calcul.
		copy(NeighbourState, state);
		generateBoard(NeighbourBoard, NeighbourState);

		//Itération parmi tous les voisins possibles de la matrice (Board).
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{

				//Condition pour sauter l'état actuel
				if (j != state[i])
				{

					// Initialisation du voisin temporaire avec le voisin actuel.
					NeighbourState[i] = j;
					NeighbourBoard[NeighbourState[i]][i] = 1;
					NeighbourBoard[state[i]][i] = 0;

					//Calculation de la valeur objective du voisin.
					int temp = hOfAttackPair(NeighbourBoard, NeighbourState);

					/*
					  Comparer les objectifs temporaires et optimaux des voisins et,
					  si les objectifs temporaires sont inférieurs aux objectifs optimaux,
					  les mettre à jour en conséquence.
					*/
					if (temp <= optimalObjective)
					{
						optimalObjective = temp;
						copy(optimalState, NeighbourState);
						generateBoard(optimalBoard, optimalState);
					}

					// revenir à la configuration de base pour la nouvelle itération.

					NeighbourBoard[NeighbourState[i]][i] = 0;
					NeighbourState[i] = state[i];
					NeighbourBoard[state[i]][i] = 1;
				}
			}
		}


		copy(state, optimalState);
		fillInBoard(board, 0);
		generateBoard(board, state);
		/*System.out.println("##########################");
		displayChessBoard(board);
		System.out.println("##########################");*/

	}

	public void hillClimbing(int[][] board, int[] state)
	{

		int[][] neighbourBoard = new int[8][8];
		int[] neighbourState = new int[8];

		//initialiser la matrice et l'état voisins avec la matrice board et l'état actuels
		copy(neighbourState, state);
		generateBoard(neighbourBoard, neighbourState);

		boolean Continue = true;
		do
		{
			/*
			puisqu'un voisin devient courant après le saut.
			nous copions la matrice board et l'état voisins vers
			la matrice board et l'état courants
			*/

			copy(state, neighbourState);
			generateBoard(board, state);

			// Récupération du voisin optimal

			getNeighbour(neighbourBoard, neighbourState);

			// Condition vérifier si les deux tableaux sont égaux
			if (Arrays.equals(state, neighbourState))
			{

				 /*
				 Si l'état du voisin et l'état réel sont égaux,
				 alors il n'existe pas de voisin optimal et par conséquent
				 le résultat est affiché
				 */

				displayChessBoard(board);

				//Changer Continue de true à false pour quitter la boucle
				//La boucle sera interrompue
				Continue = false;
			}
			else if (hOfAttackPair(board, state) == hOfAttackPair(neighbourBoard, neighbourState))
			{
				/*
				   Si le voisin et le courant ne sont pas égaux
				   mais que leurs objectifs sont égaux,
				   alors nous approchons soit d'un shoulder
				   soit d'un optimum local. Dans tous les cas,
				   il faut sauter vers un voisin aléatoire pour y échapper.
				*/

				// Voisin aléatoire
				neighbourState[random()] = random();
				generateBoard(neighbourBoard, neighbourState);
			}
		} while (Continue);
	}
}

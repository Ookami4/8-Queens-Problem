package HillClimbing;

public class Main{

	public static void main(String[] args){

        HillClimbing hillClimbing = new HillClimbing();
		int[] state = new int[8];
		int[][] board = new int[8][8];


        // Générer une situation de départ en configurant la matrice board de façon aléatoire.
		hillClimbing.generateRandomly(board, state);
		System.out.println("_________________________________________________");
        System.out.println("\t\t\t Etat initial");
		System.out.println("-------------------------------------------------");
		//affichage de la matrice board générer aléatoirement
		hillClimbing.displayChessBoard(board);

		//affichage de la solution
        System.out.println("_________________________________________________");
        System.out.println("\t\t\t\t Etat Final");
        System.out.println("-------------------------------------------------");

		hillClimbing.hillClimbing (board, state);


	}

}

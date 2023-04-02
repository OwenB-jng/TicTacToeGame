// Raleigh Desmond 101374701
// Harlan Ferguson 101133838
// Owen Beattie 101379668
import java.util.Scanner;
import java.util.Random;

public class main {
    public static void main(String[] args){
        Random rand = new Random();
        int aiX = rand.nextInt(3);
        int aiY = rand.nextInt(3);
        TicTacToe game = new TicTacToe();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Please enter player 1's name: ");
        String player1Name = scanner.nextLine();
        
        System.out.println("Please enter player 2's name. Please type: AI if you want to play against the AI: ");
        String player2Name = scanner.nextLine();

        System.out.println("Please choose " + player1Name + "'s symbol either 'X' or 'O'");
        char player1Symbol = scanner.next().toUpperCase().charAt(0);
        char player2Symbol;

        //if p1 symbol x, assign o to p2 else assign x to p2.
        if(player1Symbol == 'X'){
            player2Symbol = 'O';
        }
        else{
            player2Symbol = 'X';
        }


        int difficulty = 0;

        if(player2Name.equalsIgnoreCase("AI")){
            System.out.println("Please choose AI difficulty. Type 1 for randomized, 2 for minimax");
            difficulty = scanner.nextInt();
        }

        boolean isGameOver = false;
        boolean player1Turn = true;

        System.out.println("Would " + player1Name + " like to move first? Enter 1 to go first, or 2 to go second.");
        int p1First = scanner.nextInt();
        if (p1First == 2){
            player1Turn = false;
        }
        //end of user input setups

        GUI goo = new GUI(game, player1Name,player2Name, player1Symbol,player2Symbol, difficulty, player1Turn, aiX, aiY);
        scanner.close();     
    }
}
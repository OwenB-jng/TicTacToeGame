import java.util.Scanner;
import java.util.Random;

public class main {
    public static void main(String[] args){
        TicTacToe game = new TicTacToe();
        GUI goo = new GUI(game, "owen","ai", 'X','O', true);
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

        while(!isGameOver){
            game.printBoard();

            if (player1Turn) {
                System.out.println(player1Name + "'s turn.");
            } else {
                System.out.println(player2Name + "'s turn.");
            }

            if (!player2Name.equalsIgnoreCase("AI") || player1Turn) {
                System.out.println("Enter row and column (1-3) separated by space: ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                char currentPlayerSymbol;
                if(player1Turn){
                    currentPlayerSymbol = player1Symbol;
                }
                else{
                    currentPlayerSymbol = player2Symbol;
                }

                while (!game.play(currentPlayerSymbol, row, col)) {
                    System.out.println("Invalid move. Try again.");
                    row = scanner.nextInt() - 1;
                    col = scanner.nextInt() - 1;
                }

                if (game.checkWin(currentPlayerSymbol)) {
                    game.printBoard();
                    if(player1Turn){
                        System.out.println(player1Name + " wins!");
                    }
                    else{
                        System.out.println(player2Name + " wins!");
                    }
                    isGameOver = true;
                } else if (game.checkDraw()) {
                    game.printBoard();
                    System.out.println("It's a draw!");
                    isGameOver = true;
                } else {
                    player1Turn = !player1Turn;
                }
            } else {
                if (difficulty == 1) {
                    int row, col;
                    do {
                        row = random.nextInt(3);
                        col = random.nextInt(3);
                    } while (!game.play(player2Symbol, row, col));
                } else { //implement minimax
                    int[] move = game.findBestMove(player2Symbol);
                    game.play(player2Symbol, move[0], move[1]);
                }

                if (game.checkWin(player2Symbol)) {
                    game.printBoard();
                    System.out.println("Computer wins!");
                    isGameOver = true;
                } else if (game.checkDraw()) {
                    game.printBoard();
                    System.out.println("It's a draw!");
                    isGameOver = true;
                } else {
                    player1Turn = !player1Turn;
                }
            }          
        }
        scanner.close();     
    }
}
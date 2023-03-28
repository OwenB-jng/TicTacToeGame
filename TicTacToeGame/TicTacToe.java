public class TicTacToe {
    private char[][] board;
    private static final char EMPTY = '-'; //final allows for EMPTY to be constant. use to fill board at beginning

    public TicTacToe(){
        //when initializing the game, every position on board set to EMPTY (-)
        board = new char[3][3];
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                board[x][y] = EMPTY;
            }
        }
    }

    //check that move is within bounds and on an available space
    public boolean play(char symbol, int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == EMPTY) {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    //there are 8 ways to win. Fill a column, fill a row, or fill a diagonal. 
    public boolean checkWin(char symbol) {
        for (int row = 0; row < 3; row++) {
            if ((board[row][0] == symbol && board[row][1] == symbol && board[row][2] == symbol) ||
                (board[0][row] == symbol && board[1][row] == symbol && board[2][row] == symbol)) {
                return true;
            }
        }
    
        // Check the top left to bottom right diagonal
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
    
        // Check the top right to bottom left diagonal
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }
    
        return false;
    }

    public boolean checkDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    return false;
                }
            }
        }
        return true; //if the board is filled and checkWin returned false, game is a draw
    }

    public int[] findBestMove(char symbol) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[2];

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    board[row][col] = symbol;
                    int score = minimax(false, symbol);
                    board[row][col] = EMPTY;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean maximizing, char symbol) {
        char player2Symbol;
        //if p1 symbol x, assign o to p2 else assign x to p2.
        if(symbol == 'X'){
            player2Symbol = 'O';
        }
        else{
            player2Symbol = 'X';
        }

        if (checkWin(symbol)) {
            return 1;
        } else if (checkWin(player2Symbol)) {
            return -1;
        } else if (checkDraw()) {
            return 0;
        }

        int bestScore;
        if(maximizing){
            bestScore = Integer.MIN_VALUE;
        }
        else{
            bestScore = Integer.MAX_VALUE;
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == EMPTY) {
                    if(maximizing){
                        board[row][col] = symbol;
                    }
                    else{
                        board[row][col] = player2Symbol;
                    }
                    int score = minimax(!maximizing, symbol);
                    board[row][col] = EMPTY;
                    if (maximizing) {
                        bestScore = Math.max(score, bestScore);
                    }
                    else{
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    public void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}

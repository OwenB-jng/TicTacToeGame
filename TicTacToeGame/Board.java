import java.util.*;

public class Board {

    public int[][] state;
    public Player user1;
    public Player user2;

    public int moveCounter = 0;


    public Board() {
        state = new int[3][3];
        initializeBoard();
    }
    //sets all values in matrix to 0
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = 0;
            }
        }
    }

    public void display() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(state[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }


    public int evaluateBoard() {
        for (int row = 0; row < 3; row++) {
            if (state[row][0] == state[row][1] && state[row][1] == state[row][2]) {
                if (state[row][0] == user1.symbol) {
                    return -1;
                } else if (state[row][0] == user2.symbol) {
                    return 1;
                }
            }
        }
        return 0;
    }
    public void move(Player user, int x, int y){
        if(user.symbol == 1){
            state[x][y] = 1;
        }
        if(user.symbol == 2){
            state[x][y] = 2;
        }

// move(user1, 2, 2)


    }

    public static void main(String[] args){
        
    }

}

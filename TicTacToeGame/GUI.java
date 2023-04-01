import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GUI extends JFrame implements ActionListener {
    public TicTacToe gameBoard;
    private JButton[][] buttons;
    private String player1Name;
    private String player2Name;
    private char player1Symbol;
    private char player2Symbol;
    public int difficulty;
    private boolean player1Turn;
    public boolean buttonFound = false;
    public boolean gameDone = false;
    public int buttonX = 0;
    public int buttonY = 0;
    public Label victor = new Label("");

    public GUI(TicTacToe gameBoard, String player1Name, String player2Name, char player1Symbol, char player2Symbol, int difficulty, boolean player1Turn, int aiX, int aiY){
        this.gameBoard = gameBoard;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
        this.player1Turn = player1Turn;
        this.difficulty = difficulty;
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int xMid = (screenDimension.width - 500) / 2;
        int yMid = (screenDimension.height - 500) / 2;

        JFrame frame = new JFrame();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));

        frame.setSize(500,500);
        frame.setLocation(xMid, yMid);

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD,50));
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }
        if (player2Name.equalsIgnoreCase("ai") && !player1Turn){
            buttons[aiX][aiY].setText(String.valueOf(player2Symbol));
            gameBoard.play(player2Symbol, aiX, aiY);
        }

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Random rand = new Random();

        if (!(button.getText() == "")){
            return;
        }
        // PVE
        if (player2Name.equalsIgnoreCase("ai")){
            if (difficulty == 1){
                while (!gameDone){
                    button.setText(String.valueOf(player1Symbol));
                    while (!buttonFound){
                        if (button == buttons[buttonX][buttonY]){
                            gameBoard.play(player1Symbol, buttonX, buttonY);
                            buttonFound = true;
                        }
                        else{
                            buttonY++;
                            if (buttonY == 3){
                                buttonX++;
                                buttonY = 0;
                            }
                        }
                    }
                    buttonY = 0;
                    buttonX = 0;
                    buttonFound = false;
                    player1Turn = false;
                    if (gameBoard.checkWin(player1Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player1Name + "!");
                        break;
                    }
                    int row, col;
                    boolean placed = false;
                    while (!placed){
                        row = rand.nextInt(3);
                        col = rand.nextInt(3);
                        if (buttons[row][col].getText().equals("")){
                            buttons[row][col].setText(String.valueOf(player2Symbol));
                            gameBoard.play(player2Symbol, row, col);
                            placed = true;
                            System.out.println("O placed in " + row + col);
                            break;
                        }
                    }
                    if (gameBoard.checkWin(player2Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player2Name + "!");
                        break;
                    }
                    for (int x = 0; x < 3; x++){
                        for (int y = 0; y < 3; y++){
                            if (buttons[x][y].getText().equals("")){
                                return;
                            }
                            if (x == 2 && y == 2){
                                gameDone = true;
                                victor.setText("Draw!");
                            }
                        }
                    }
                }

                if (gameDone){
                    JFrame winnerFrame = new JFrame();
                    JPanel victorPanel = new JPanel();
                    victorPanel.add(victor);
                    winnerFrame.add(victorPanel);
                    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int xMid = (screenDimension.width - 300) / 2;
                    int yMid = (screenDimension.height - 100) / 2;
                    winnerFrame.setSize(300,100);
                    winnerFrame.setLocation(xMid, yMid);
                    winnerFrame.setVisible(true);
                }
                return;
            }

            // ^ Random / MinMax v

            else{
                while (!gameDone){
                    button.setText(String.valueOf(player1Symbol));
                    while (!buttonFound){
                        if (button == buttons[buttonX][buttonY]){
                            gameBoard.play(player1Symbol, buttonX, buttonY);
                            buttonFound = true;
                        }
                        else{
                            buttonY++;
                            if (buttonY == 3){
                                buttonX++;
                                buttonY = 0;
                            }
                        }
                    }
                    buttonY = 0;
                    buttonX = 0;
                    buttonFound = false;
                    player1Turn = false;
                    if (gameBoard.checkWin(player1Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player1Name + "!");
                        break;
                    }
                    boolean placed = false;
                    while (!placed){
                        int[] move = gameBoard.findBestMove(player2Symbol);
                        if (buttons[move[0]][move[1]].getText().equals("")) {
                            buttons[move[0]][move[1]].setText(String.valueOf(player2Symbol));
                            gameBoard.play(player2Symbol, move[0], move[1]);
                            placed = true;
                        }
                    }

                    if (gameBoard.checkWin(player2Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player2Name + "!");
                        break;
                    }
                    for (int x = 0; x < 3; x++){
                        for (int y = 0; y < 3; y++){
                            if (buttons[x][y].getText().equals("")){
                                return;
                            }
                            if (x == 2 && y == 2){
                                gameDone = true;
                                victor.setText("Draw!");
                            }
                        }
                    }
                }

                if (gameDone){
                    JFrame winnerFrame = new JFrame();
                    JPanel victorPanel = new JPanel();
                    victorPanel.add(victor);
                    winnerFrame.add(victorPanel);
                    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int xMid = (screenDimension.width - 300) / 2;
                    int yMid = (screenDimension.height - 100) / 2;
                    winnerFrame.setSize(300,100);
                    winnerFrame.setLocation(xMid, yMid);
                    winnerFrame.setVisible(true);
                }
                return;
            }
        }

        // PVP
        else{
            while (!gameDone){
                if (player1Turn){
                    button.setText(String.valueOf(player1Symbol));
                    while (!buttonFound){
                        if (button == buttons[buttonX][buttonY]){
                            gameBoard.play(player1Symbol, buttonX, buttonY);
                            buttonFound = true;
                        }
                        else{
                            buttonY++;
                            if (buttonY == 3){
                                buttonX++;
                                buttonY = 0;
                            }
                        }
                    }
                    buttonY = 0;
                    buttonX = 0;
                    buttonFound = false;
                    player1Turn = false;
                    if (gameBoard.checkWin(player1Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player1Name + "!");
                    }
                }
                else{
                    button.setText(String.valueOf(player2Symbol));
                    while (!buttonFound){
                        if (button == buttons[buttonX][buttonY]){
                            gameBoard.play(player2Symbol, buttonX, buttonY);
                            buttonFound = true;
                        }
                        else{
                            buttonY++;
                            if (buttonY == 3){
                                buttonX++;
                                buttonY = 0;
                            }
                        }
                    }
                    buttonY = 0;
                    buttonX = 0;
                    buttonFound = false;
                    player1Turn = true;
                    if (gameBoard.checkWin(player2Symbol)){
                        gameDone = true;
                        victor.setText("The winner is " + player2Name + "!");
                    }
                }
                for (int x = 0; x < 3; x++){
                    for (int y = 0; y < 3; y++){
                        if (buttons[x][y].getText().equals("")){
                            return;
                        }
                        if (x == 2 && y == 2){
                            gameDone = true;
                            victor.setText("Draw!");
                        }
                    }
                }
                if (gameDone){
                    JFrame winnerFrame = new JFrame();
                    JPanel victorPanel = new JPanel();
                    victorPanel.add(victor);
                    winnerFrame.add(victorPanel);
                    Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
                    int xMid = (screenDimension.width - 300) / 2;
                    int yMid = (screenDimension.height - 100) / 2;
                    winnerFrame.setSize(300,100);
                    winnerFrame.setLocation(xMid, yMid);
                    winnerFrame.setVisible(true);
                }
                return;
            }
        }
    }
}
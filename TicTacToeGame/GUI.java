import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    public TicTacToe gameBoard;
    private JButton[][] buttons;
    private String player1Name;
    private String player2Name;
    private char player1Symbol;
    private char player2Symbol;
    private boolean player1Turn;
    public boolean buttonFound = false;
    public int buttonX = 0;
    public int buttonY = 0;

    public GUI(TicTacToe gameBoard, String player1Name, String player2Name, char player1Symbol, char player2Symbol, boolean player1Turn){
        this.gameBoard = gameBoard;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Symbol = player1Symbol;
        this.player2Symbol = player2Symbol;
        this.player1Turn = player1Turn;
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int xMid = (screenDimension.width - 500) / 2;
        int yMid = (screenDimension.height - 500) / 2;
        JFrame frame = new JFrame();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,3));

        frame.setSize(500,500);
        frame.setLocation(xMid, yMid);

        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j] = new JButton("");
                buttons[i][j].addActionListener(this);
                buttonPanel.add(buttons[i][j]);
            }
        }

        Label whichTurn = new Label(String.valueOf(player1Symbol)); buttonPanel.add(whichTurn);

        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (!(button.getText() == "")){
            return;
        }

        while (!gameBoard.checkWin('X') && !gameBoard.checkWin('O')){
            if (player1Turn){
                button.setText(String.valueOf(player1Symbol));
                while (!buttonFound){
                    if (button == buttons[buttonX][buttonY]){
                        gameBoard.play(player2Symbol,buttonX,buttonY);
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
                return;
            }
            else{
                button.setText(String.valueOf(player2Symbol));
                while (!buttonFound){
                    if (button == buttons[buttonX][buttonY]){
                        gameBoard.play(player2Symbol,buttonX,buttonY);
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
                return;
            }
        }
    }
}
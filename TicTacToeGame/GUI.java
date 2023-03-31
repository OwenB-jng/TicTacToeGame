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

    public GUI(TicTacToe gameBoard, String player1Name, String player2Name, char player1Symbol, char player2Symbol, boolean player1Turn){
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


        if (player1Turn){
            button.setText(String.valueOf(player1Symbol));
            player1Turn = false;
        }
        else{
            button.setText(String.valueOf(player2Symbol));
            player1Turn = true;
        }
    }
}
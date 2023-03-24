import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

    Random weakAI = new Random();
    private JButton[][] buttons;
    private JLabel statusLabel;
    String playerSymbol = "X";
    String firstSymbol = "X";
    private boolean xTurn = true;
    public GUI(String nameChoice, int playerSymbolChoice, int aiStrengthChoice, int firstSymbolChoice) {

        setTitle(nameChoice);

        if (playerSymbolChoice == 0){
            playerSymbol = "O";
        }
        if (firstSymbolChoice == 0){
            firstSymbol = "O";
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3,3));
        buttons = new JButton[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if (!button.getText().equals("")) {
            return;
        }

        else if (playerSymbol == firstSymbol) {
            button.setText(playerSymbol);
            if (firstSymbol == "X"){
                firstSymbol = "O";
            }
            else {
                firstSymbol = "X";
            }

        }

        else {
            int[] randomChoiceSquare = new int[]{0,1,2};
            // Lost right here, currently if we run it it crashes, not knowing what to do
            // because if you click a square it busts. lets talk abt it
            boolean run = true;
            while (run){
                int randomChoiceX = randomChoiceSquare[weakAI.nextInt(2)];
                int randomChoiceY = randomChoiceSquare[weakAI.nextInt(2)];
                if (!buttons[randomChoiceX][randomChoiceY].equals("")){
                    continue;
                }
                else {
                    if (playerSymbol == "X"){
                        buttons[randomChoiceX][randomChoiceY].equals("O");
                        break;
                    }
                    else{
                        buttons[randomChoiceX][randomChoiceY].equals("X");
                        break;
                    }
                }
            }
            button.setText(firstSymbol);
            if (firstSymbol == "X"){
                firstSymbol = "O";
            }
            else {
                firstSymbol = "X";
            }
        }
    }
    public void printGrid(){
        for (int x = 0; x < buttons.length; x++){
            for (int y = 0; y < buttons[x].length; y++){
                System.out.println(buttons[y].toString());
            }
        }
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    public TicTacToe gameBoard;
    public GUI(TicTacToe gameBoard){
        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int xMid = (screenDimension.width - 500) / 2;
        int yMid = (screenDimension.height - 500) / 2;
        JFrame frame = new JFrame();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,3));

        frame.setSize(500,500);
        frame.setLocation(xMid, yMid);

        JButton button11 = new JButton(""); buttonPanel.add(button11);
        JButton button12 = new JButton(""); buttonPanel.add(button12);
        JButton button13 = new JButton(""); buttonPanel.add(button13);
        JButton button21 = new JButton(""); buttonPanel.add(button21);
        JButton button22 = new JButton(""); buttonPanel.add(button22);
        JButton button23 = new JButton(""); buttonPanel.add(button23);
        JButton button31 = new JButton(""); buttonPanel.add(button31);
        JButton button32 = new JButton(""); buttonPanel.add(button32);
        JButton button33 = new JButton(""); buttonPanel.add(button33);
        Label whichTurn = new Label("Player 1 turn"); buttonPanel.add(whichTurn);


        frame.add(buttonPanel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
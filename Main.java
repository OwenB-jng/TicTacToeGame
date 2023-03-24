import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter your name.");
        String name = scan.next();
        System.out.println("Would you like to play as O or X? Enter 0 for O, or 1 for X.");
        int playerSymbol = scan.nextInt();
        System.out.println("Would you like to play against the Random or Intelligent AI? Enter 0 for Random, or 1 for Intelligent.");
        int aiStrength = scan.nextInt();
        System.out.println("Would you like O or X to go first? Enter 0 for O, or 1 for X.");
        int firstSymbol = scan.nextInt();
        GUI gui = new GUI(name, playerSymbol, aiStrength, firstSymbol);


        gui.printGrid();
    }
}
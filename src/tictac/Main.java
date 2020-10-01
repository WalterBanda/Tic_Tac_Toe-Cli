package tictac;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String winning = "Congrats You Won 🎉";
        String losing = "Sorry You Lost 😿 :(";
        String draw = "Draw 👐🏿";
        App App = new App();
        Scanner scan = new Scanner(System.in);
        char symbol;
        do {
            System.out.print("Choose your symbol: \n" +
                    "x or o or c :for custom player and cpu symbols\nn -> for custom messages and player and cpu symbols\n" +
                    "d -> default \n" +
                    "Enter your Symbol: ");
            symbol = scan.next().charAt(0);
            switch (symbol) {
                case 'x', 'd' -> App.app(losing, winning, draw, 'x', 'x', 'o');
                case 'o' -> App.app(losing, winning, draw, 'o', 'o', 'x');
                case 'c' -> {
                    System.out.print("Enter your player symbol: ");
                    char player = scan.next().charAt(0);
                    System.out.print("\nEnter your cpu symbol: ");
                    char cpu = scan.next().charAt(0);
                    App.app(losing, winning, draw, 'c', player, cpu);
                }
                case 'n' -> {
                    System.out.print("Enter your custom player symbol: ");
                    char player = scan.next().charAt(0);
                    System.out.print("\nEnter your cpu symbol: ");
                    char cpu = scan.next().charAt(0);
                    System.out.print("Enter your Win message: ");
                    winning = scan.next();
                    System.out.print("\nEnter your Lose message: ");
                    losing = scan.next();
                    System.out.print("\nEnter your Draw message: ");
                    draw = scan.next();
                    App.app(losing, winning, draw, 'c', player, cpu);
                }
                default -> System.out.println("Unexpected value: " + symbol);
            }
            if (App.result.equals(winning)||App.result.equals(losing)||App.result.equals(draw)) {
                break;
            }
        }while (!(symbol == 'x' || symbol == 'o' || symbol == 'c' || symbol == 'n'));
    }

}
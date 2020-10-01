package tictac;

import java.util.*;

public class App {
    int ran,pos;
    String result,losing,winning,draw;
    char player,cpu;
    public void app(String losing,String winning, String draw,char symbol,char player,char cpu){
        switch (symbol) {
            case 'x' -> {
                this.player = 'x';
                this.cpu = 'o';
            }
            case 'o' -> {
                this.player = 'o';
                this.cpu = 'x';
            }
            case 'c' -> {
                this.player = player;
                this.cpu = cpu;
            }
            default -> System.out.println("Wrong Mode entered");
        }
        this.losing = losing;this.winning = winning;this.draw = draw;
        this.losing = losing;this.winning = winning;this.draw = draw;
        while (true){
            result = checkWinner();
            if (result.equals(winning)||result.equals(losing)||result.equals(draw)){
                System.out.println(result);
                break;
            }
            player();
            result = checkWinner();
            if (result.equals(winning)||result.equals(losing)||result.equals(draw)){
                System.out.println(result);
                break;
            }
            cpu();
            printGameBoard();
            result = checkWinner();
            if (result.equals(winning)||result.equals(losing)||result.equals(draw)){
                System.out.println(result);
                break;
            }
        }
    }
    private final char[][] gameBoard = {{' ', '|', ' ', '|', ' ',},{'-', '+', '-', '+', '-',},{' ', '|', ' ', '|', ' ',},{'-', '+', '-', '+', '-',},{' ', '|', ' ', '|', ' ',},};
    private final ArrayList<Integer> playerPositions = new ArrayList<>();
    private final ArrayList<Integer> cpuPositions = new ArrayList<>();
    private void printGameBoard() {
        System.out.println("#---#");
        for (char[] row : gameBoard) {
            for (char character : row) {
                System.out.print(character);
            }
            System.out.println();
        }
        System.out.println("#---#");
    }
    private void insertPiece(int pos,String user,char symbol) {
        switch (user) {
            case "cpu" -> cpuPositions.add(pos);
            case "player" -> playerPositions.add(pos);
            default -> System.out.println("Unknown user");
        }
        switch (pos) {
            case 1 -> gameBoard[0][0] = symbol;
            case 2 -> gameBoard[0][2] = symbol;
            case 3 -> gameBoard[0][4] = symbol;
            case 4 -> gameBoard[2][0] = symbol;
            case 5 -> gameBoard[2][2] = symbol;
            case 6 -> gameBoard[2][4] = symbol;
            case 7 -> gameBoard[4][0] = symbol;
            case 8 -> gameBoard[4][2] = symbol;
            case 9 -> gameBoard[4][4] = symbol;
            default -> System.out.println("Out of range");
        }
    }
    private String checkWinner(){
        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> botRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);
        List<List<Integer>> winningComb = new ArrayList<>();
        winningComb.add(topRow);
        winningComb.add(midRow);
        winningComb.add(botRow);
        winningComb.add(leftCol);
        winningComb.add(rightCol);
        winningComb.add(midCol);
        winningComb.add(cross1);
        winningComb.add(cross2);
        for (List<Integer> l : winningComb) {
            if (cpuPositions.containsAll(l)) return losing;
            else if (playerPositions.containsAll(l)) return winning;
            else if (playerPositions.size() + cpuPositions.size() == 9) return draw;
        }
        return "";
    }
    private void player() {
        Scanner input = new Scanner(System.in);
        ValidInputPlayer(input);
        while ((playerPositions.contains(pos) || cpuPositions.contains(pos))) {
            System.out.println("Position Taken Choose another");
            ValidInputPlayer(input);
        }
        insertPiece(pos,"player",player);
    }
    private void ValidInputPlayer(Scanner input) {
        do {
            System.out.print("Enter a number (1-9): ");
            pos = input.nextInt();
            if (!((pos > 0) && (pos < 10))) {
                System.out.println("Number Out of Range");
            }
        }while (!((pos > 0) && (pos < 10)));
    }
    private void cpu() {
        Random input = new Random();
        do {
            ran = input.nextInt(9)+1;
        }while (!((ran > 0) && (ran < 10)));
        while ((cpuPositions.contains(ran) || playerPositions.contains(ran))){
            do {
                ran = input.nextInt(9)+1;
            }while (!((ran > 0) && (ran < 10)));
        }
        System.out.println("Cpu Playing");
        insertPiece(ran,"cpu",cpu);
    }
}

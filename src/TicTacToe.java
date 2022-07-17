import java.util.*;

public class TicTacToe {
    static ArrayList<Integer> playerPositiont = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositiont = new ArrayList<Integer>();
    public static void main(String[] args) {
        char [][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        printGameBoard(gameBoard);
        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement (1-9):");
            int playerPos = scan.nextInt();
            while(playerPositiont.contains(playerPos) || cpuPositiont.contains(playerPos)) {
                System.out.println("Position taken! Enter a correct Position!");
                playerPos = scan.nextInt();
            }
            placePiece(gameBoard, playerPos, "player");

            String result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while(playerPositiont.contains(cpuPos) || cpuPositiont.contains(cpuPositiont)) {
                System.out.println("Position taken! Enter a correct Position!");
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if(result.length() > 0) {
                System.out.println(result);
                break;
            }
        }
    }
    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
    public static void placePiece(char [][] gameBoard, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPositiont.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositiont.add(pos);
        }
        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
        }
    }
    public static String checkWinner() {
        List topPow = Arrays.asList(1,2,3);
        List midPow = Arrays.asList(4,5,6);
        List botPow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List RightCol = Arrays.asList(3,6,9);
        List majDiag = Arrays.asList(1,5,9);
        List minDiag = Arrays.asList(7,5,3);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topPow);
        winningConditions.add(midPow);
        winningConditions.add(botPow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(RightCol);
        winningConditions.add(majDiag);
        winningConditions.add(minDiag);

        for(List l : winningConditions) {
            if(playerPositiont.containsAll(l)) {
                return "Congratulations you won!";
            } else if (cpuPositiont.containsAll(l)){
                return "CPU wins!";
            } else if (playerPositiont.size() + cpuPositiont.size() == 9) {
                return "CAT";
            }
        }

        return "";
    }
}

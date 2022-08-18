import java.util.Random;
import java.util.Scanner;

public class Main {

    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        String draw = "Draw";


        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();

            if (checkWin(DOT_X)) {
                System.out.println("Human WIN!");
                break;
            }

            if (isMapFull()) {
                System.out.println(draw);
                break;
            }

            aiTurn();
            printMap();

            if (checkWin(DOT_0)) {
                System.out.println("AI WIN!");
                break;
            }

            if (isMapFull()) {
                System.out.println(draw);
                break;
            }
        }
        System.out.println("Game Over");
    }

    public static boolean checkWin(char symb) {
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        return false;
    }

    /**
     * @return false if map is not full.
     */
    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    /**
     * AI turn method.
     */
    public static void aiTurn() {
        int x, y; // coordinates for AI

        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("AI choose coordinates: " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_0;
    }

    public static void humanTurn() {
        int x, y; // coordinates for Human
        do {
            System.out.println("Write coordinates in format X Y");
            x = sc.nextInt() - 1; // -1 is for boundaries
            y = sc.nextInt() - 1; // -1 is for boundaries
        } while (!isCellValid(x, y));
        map[y][x] = DOT_X;
    }

    /**
     *
     * @param x coordinate X
     * @param y coordinate y
     * @return true if cell is valid to X or 0.
     */
    public static boolean isCellValid(int x, int y) {

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    /**
     * Create a map with dots in free cells.
     */
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Print map method.
     */
    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        } System.out.println();
    }
}
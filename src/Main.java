import java.io.IOException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] solution0 =
            {{5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}};

        //set the board
        int[][] game = new int[solution0.length][solution0[0].length];
        for(int i= 0; i < game.length; i++){
            game[i] = solution0[i].clone();
        }
        int difficulty = 1;
        for(int i = 0; i < difficulty; i++){
            game[randomInt()][randomInt()] = 0;
        }
        //game play
        while(!equal(game, solution0)){
            clearScreen();
            print2D(game);
            check(solution0, game);
            getInput(game);
        }
        System.out.println("Congratulations!!");
    }
    public static int randomInt(){
        return ThreadLocalRandom.current().nextInt(0, 9);
    }
    public static void check(int[][] solution, int[][] game){
        for(int i = 0; i < solution.length; i++){
            for(int x = 0; x < solution[i].length;x++) {
                if((game[x][i] != 0) && (game[x][i] != solution[x][i])){
                    System.out.println("Error at " + i + " " + x);
                }
            }
        }
    }
    public static void getInput(int[][] game){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter x y val");
        String input = scanner.nextLine();
        int x = Integer.parseInt(input.substring(0, 1));
        int y = Integer.parseInt(input.substring(1,2));
        int value = Integer.parseInt(input.substring(2,3));
        if (validate(new int[]{x, y, value})){
            game[y][x] = value;
        }
    }
    public static boolean validate(int[] inputs){
        for (int input : inputs) {
            if (input < 0 || input > 9) {
                return false;
            }
        }
        return true;
    }
    public static boolean equal(final int[][] arr1, final int[][] arr2) {
        if (arr1 == null) {
            System.out.println("\n001");
            return (arr2 == null);
        }
        if (arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (!Arrays.equals(arr1[i], arr2[i])) {
                return false;
            }
        }
        return true;
    }
    public static void print2D(int[][] mat) {
        for (int[] row : mat)
            System.out.println(Arrays.toString(row));
    }
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

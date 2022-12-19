import java.io.IOException;
import java.util.*;

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
        int[][] game = solution0;
        for (int[] row : game)
            Arrays.fill(row, 0);
        while(equal(solution0,game)){
            clearScreen();
            print2D(game);
            getInput(game);

            System.out.println("ROWS");
            for (int[] row : game) {
                countFreq(row);
                System.out.println();
            }
            System.out.println("COLS");
            for (int i = 0; i < game.length; i++){
                int[] column = getColumn(game, i);
                countFreq(column);
                System.out.println("\n");
            }
            countBlockFreq(getBlock(game, 0, 0));
            countBlockFreq(getBlock(game, 3, 0));
            countBlockFreq(getBlock(game, 6, 0));
            countBlockFreq(getBlock(game, 0, 3));
            countBlockFreq(getBlock(game, 3, 3));
            countBlockFreq(getBlock(game, 6, 3));
            countBlockFreq(getBlock(game, 0, 6));
            countBlockFreq(getBlock(game, 3, 6));
            countBlockFreq(getBlock(game, 6, 6));
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
    public static int[][] getBlock(int[][] game, int x, int y){
        int[][] a = new int[game.length][];
        int i = y;
        while (i < game.length && i < y+3){
            a[i] = Arrays.copyOfRange(game[i], x, x + 3);
            i++;
        }
        int[][] values = new int[3][3];
        int count = 0;
        for(int[] data: a) {
            int count2 = 0;
            if(data != null) {
                for(int z: data){
                    values[count][count2] = z;
                    count2++;
                }
                count++;
            }
        }
        print2D(values);
        System.out.println("\n");
        return values;
    }
    public static boolean countBlockFreq(int[][] block){
        for(int[] i : block){
            if(!countFreq(i))
                return false;
        }
        return true;
    }
    public static boolean countFreq(int[] arr){
        return countFreq(arr, arr.length);
    }
    public static boolean countFreq(int[] arr, int n)
    {
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        // Traverse through array elements and
        // count frequencies
        for (int i = 0; i < n; i++) {
            // Skip this element if already processed
            if (visited[i])
                continue;
            // Count frequency
            int count = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[i] == arr[j]) {
                    visited[j] = true;
                    count++;
                //System.out.println("\n------------");
                }
            }
                //System.out.println("\n------------");
            //System.out.println(arr[i] + " " + count);
            if (count != 1)
                System.out.println("Error\n");
                return false;
        }
        return true;
    }
    public static int[] getColumn(int[][] array, int index){
        int[] column = new int[array[0].length]; // Here I assume a rectangular 2D array!
        for(int i=0; i<column.length; i++){
            column[i] = array[i][index];
        }
        return column;
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

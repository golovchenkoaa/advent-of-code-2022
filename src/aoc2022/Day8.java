package aoc2022;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day8 {

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day8.txt"));
        var lines = Arrays.asList("""
                30373
                25512
                65332
                33549
                35390
                """.split("\n"));
        int size = lines.size();
        int[][] grid = lines.stream()
                .map(s -> s.chars().map(i -> i - '0').toArray())
                .toArray(int[][]::new);
        
        int visible = 0;
        long max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (isVisible(grid, i, j)) {
                    visible++;
                }
                max = Math.max(max, caclScenicScore(grid, i, j));
            }  
        }
        System.out.println("Total part 1: " + visible);
        System.out.println("Total part 2: " + max);
    }
    
    private static long caclScenicScore(int[][] grid, int i, int j) {
        int a = 0, b = 0, c = 0, d = 0;
        int val = grid[i][j];
        for (int ii = i - 1; ii >= 0; ii--) {
            a++; 
            if (grid[ii][j] >= val) {
                break;
            }
        }
        for (int ii = i + 1; ii < grid.length; ii++) {
            b++; 
            if (grid[ii][j] >= val) {
                break;
            }       
        }
        for (int jj = j - 1; jj >= 0; jj--) {
            c++;
            if (grid[i][jj] >= val) {
                break;
            }
        }
        for (int jj = j + 1; jj < grid[0].length; jj++) {
           d++;
           if (grid[i][jj] >= val) {
               break;
           }
        }
        return ((long) a) * b * c * d;
    }
    
    private static boolean isVisible(int[][] grid, int i, int j) {
       if (i == 0 || i == (grid.length - 1) || j == 0 || j == (grid[0].length - 1)) {
           return true;
       }
       int val = grid[i][j];
       boolean visible = true;
       for (int ii = 0; ii < i && visible; ii++) {
           visible &= grid[ii][j] < val;
       }
       if (visible) {
           return true;
       }
       visible = true;
       for (int ii = i + 1; ii < grid.length && visible; ii++) {
           visible &= grid[ii][j] < val;
       }
       if (visible) {
           return true;
       }
       visible = true;
       for (int jj = 0; jj < j && visible; jj++) {
           visible &= grid[i][jj] < val;
       }
       if (visible) {
           return true;
       }
       visible = true;
       for (int jj = j + 1; jj < grid[0].length && visible; jj++) {
           visible &= grid[i][jj] < val;
       }
       return visible;
    }
}

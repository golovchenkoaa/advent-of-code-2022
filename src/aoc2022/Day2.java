package aoc2022;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    
    private static final int[][][] strategies = {
            {{3 + 1, 6 + 2, 0 + 3}, 
             {0 + 1, 3 + 2, 6 + 3}, 
             {6 + 1, 0 + 2, 3 + 3}},
            {{0 + 3, 3 + 1, 6 + 2}, 
             {0 + 1, 3 + 2, 6 + 3}, 
             {0 + 2, 3 + 3, 6 + 1}}
            };
   
    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day2.txt"));
        var lines = Arrays.asList("""
                A Y
                B X
                C Z
                """.split("\n"));
       
        System.out.println("Total: " + calc(strategies[0], lines));
        System.out.println("Total: " + calc(strategies[1], lines));
    }
    
    public static long calc(int[][] roundScore, List<String> lines) {
        long total = 0;
        for (String line : lines) {    
            int opponent = line.charAt(0) - 'A';
            int you = line.charAt(2) - 'X';
            int round = roundScore[opponent][you];
            total += round;
        }
        return total;
    }
}

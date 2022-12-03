import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day2 {

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("day2.txt"));
        var lines = Arrays.asList("""
                A Y
                B X
                C Z
                """.split("\n"));
        long total = 0;
        int[][] roundScore = {
                       {3 + 1, 6 + 2, 0 + 3}, 
                       {0 + 1, 3 + 2, 6 + 3}, 
                       {6 + 1, 0 + 2, 3 + 3}};
        for (String line : lines) {    
            int opponent = line.charAt(0) - 'A';
            int you = line.charAt(2) - 'X';
            int round = roundScore[opponent][you];
            total += round;
        }
        System.out.println("Total: " + total);
    }
}

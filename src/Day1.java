import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day1 {

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day1.txt"));
        var lines = Arrays.asList("""
                1000
                2000
                3000

                4000

                5000
                6000

                7000
                8000
                9000
                
                10000
                """.split("\n", -1));
        long elfSum = 0, max = 0;
        for (String line : lines) {    
            if (line.isBlank()) {
//                System.out.println(elfSum);
                max = Math.max(max, elfSum);
                elfSum = 0;
            } else {
                elfSum += Integer.parseUnsignedInt(line);
            }
        }
        max = Math.max(max, elfSum);
        System.out.println("Max: " + max);
    }
}

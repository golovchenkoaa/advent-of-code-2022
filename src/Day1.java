import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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
        long elfSum = 0;
        List<Long> totals = new ArrayList<>(100);
        for (String line : lines) {    
            if (line.isBlank()) {
                totals.add(elfSum);
                elfSum = 0;
            } else {
                elfSum += Integer.parseUnsignedInt(line);
            }
        }
        totals.sort(Comparator.<Long>naturalOrder().reversed());
        System.out.println("Max1: " + totals.get(0));
        System.out.println("Max3: " + (totals.get(0) + totals.get(1) + totals.get(2)));
    }
}

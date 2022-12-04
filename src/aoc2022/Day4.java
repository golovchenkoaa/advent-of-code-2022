package aoc2022;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Character.isLowerCase;

public class Day4 {

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day4.txt"));
        var lines = Arrays.asList("""
                2-4,6-8
                2-3,4-5
                5-7,7-9
                2-8,3-7
                6-6,4-6
                2-6,4-8
                """.split("\n"));
       
        int totalPart1 = 0, totalPart2 = 0;
        for (String line : lines) {      
            String[] pair = line.split(",");
            int[] range1 = getPairRange(pair[0]);
            int from1 = range1[0];
            int to1 = range1[1];
            
            int[] range2 = getPairRange(pair[1]);
            int from2 = range2[0];
            int to2 = range2[1];
            
            if (matchPart1Condition(from1, to1, from2, to2)) {
                totalPart1++;
            }
            if (matchPart2Condition(from1, to1, from2, to2)) {
                totalPart2++;
            }
        }
        System.out.println("Total part 1: " + totalPart1);
        System.out.println("Total part 2: " + totalPart2);
    }
    
    private static int[] getPairRange(String rangeString) {
        String[] range1 = rangeString.split("\\-");
        return new int[] {Integer.parseUnsignedInt(range1[0]), Integer.parseUnsignedInt(range1[1])};
    }
    
    public static boolean matchPart1Condition(int from1, int to1, int from2, int to2) {
       return (from1 >= from2 && to1 <= to2) || (from2 >= from1 && to2 <= to1);
    } 
    
    public static boolean matchPart2Condition(int from1, int to1, int from2, int to2) {
        return from1 <= to2 && to1 >= from2;
    }
}

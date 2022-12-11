package aoc2022;
import java.io.IOException;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Day6 {
    
    public static void main(String[] args) throws Exception {        
//        var lines = Collections.singletonList(Files.readString(Path.of("input/day6.txt")));
        var lines = Arrays.asList(
                """            
                bvwbjplbgvbhsrlpgdmjqwftvncz
                nppdvjthqldpwncqszvftbrmjlhg
                nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg
                zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw
                """.split("\n"));
        for (String line : lines) { 
            System.out.println("Part 1 answer: " +  findSignalStartPosition(line, 4));
            System.out.println("Part 2 answer: " +  findSignalStartPosition(line, 14));
        }
    }
    
    private static int findSignalStartPosition(String input, int len) throws IOException {
        for (int offset = 0; offset < input.length(); offset++) {
            if (input.subSequence(offset, offset + len).chars().distinct().count() == len) {
                return offset + len;
            }  
        }
        return -1;
    }
}

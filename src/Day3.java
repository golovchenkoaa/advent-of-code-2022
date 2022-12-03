import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Character.isLowerCase;

public class Day3 {

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day3.txt"));
        var lines = Arrays.asList("""
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """.split("\n"));
        long total = 0;
        for (String line : lines) {      
            int commonItem = findCommonChar(line.substring(0, line.length() / 2), 
                                            line.substring(line.length() / 2));
            int priority = calcPriority(commonItem);
            total += priority;
        }
        System.out.println("Total part 1: " + total);
        total = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            int commonItem = findCommonChar(lines.get(i), 
                                            lines.get(i + 1), 
                                            lines.get(i + 2));
            total += calcPriority(commonItem);
        }
        System.out.println("Total part 2: " + total);
    }
    
    public static int calcPriority(int commonItem) {
        if (isLowerCase(commonItem)) {
            return commonItem - 'a' + 1;
        } else {
            return commonItem - 'A' + 27;
        }
    }
    
    public static int findCommonChar(String... lines) {
        Set<Integer> comp1 = lines[0].chars().boxed().collect(Collectors.toSet());
        for (int i = 1; i < lines.length; i++) {
            Set<Integer> comp2 = lines[i].chars().boxed().collect(Collectors.toSet());
            comp1.retainAll(comp2);
            
        }
        assert(comp1.size() == 1);    
        return comp1.iterator().next();
    }
}

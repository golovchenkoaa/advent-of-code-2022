import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Character.isLowerCase;

public class Day3 {

    public static void main(String[] args) throws Exception {        
        var lines = Files.readAllLines(Path.of("input/day3.txt"));
//        var lines = Arrays.asList("""
//                vJrwpWtwJgWrhcsFMMfFFhFp
//                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
//                PmmdzqPrVvPwwTWBwg
//                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
//                ttgJtRGJQctTZtZT
//                CrZsJsPPZsGzwwsLwLmpwMDw
//                """.split("\n"));
        long total = 0;
        for (String line : lines) {    
            Set<Integer> comp1 = line.substring(0, line.length() / 2).chars().boxed().collect(Collectors.toSet());
            Set<Integer> comp2 = line.substring(line.length() / 2).chars().boxed().collect(Collectors.toSet());
            comp1.retainAll(comp2);
            assert(comp1.size() == 1);    
            int commonItem = comp1.iterator().next();
            int priority;
            if (isLowerCase(commonItem)) {
                priority = commonItem - 'a' + 1;
            } else {
                priority = commonItem - 'A' + 27;
            }
//            System.out.println(priority);
            total += priority;
        }
        System.out.println("Total: " + total);
    }
}

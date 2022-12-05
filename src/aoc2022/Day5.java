package aoc2022;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Stack;

public class Day5 {
    
    private static class CargoCrane {
        private final Stack<String>[] stacks;
        private final int maxAtOnce;

        public CargoCrane(Stack<String>[] stacks, int maxAtOnce) {
            this.stacks = stacks;
            this.maxAtOnce = maxAtOnce;
        }
        
        public void move(int count, int from, int to) {
            var source = stacks[from - 1];
            var target = stacks[to - 1];
            if (maxAtOnce > 0) {
                while (count-- > 0) {
                    target.push(source.pop());     
                }
            } else {          
                int start = source.size() - count;
                for (int i = count; i > 0; i--) {
                    target.push(source.get(start++));
                }
                for (int i = count; i > 0; i--) {
                    source.pop();
                }
            }
//            System.out.println("After " + count + ' ' + from + ' ' + to + ' ' + Arrays.toString(stacks));
        }

        public Stack<String>[] getStacks() {
            return stacks;
        }   
        
        public String getTopCrates() {
            StringBuilder sb = new StringBuilder();
            for (Stack<String> s : stacks) {
                if (!s.isEmpty()) {
                    sb.append(s.peek());
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day5.txt"));
        var lines = Arrays.asList(
                """            
                    [D]    
                [N] [C]    
                [Z] [M] [P]
                 1   2   3 
                
                move 1 from 2 to 1
                move 3 from 1 to 3
                move 2 from 2 to 1
                move 1 from 1 to 2
                """.split("\n"));
        
        var it = lines.listIterator();
        int commandsIndex = -1, headerEndIndex = -1;
        while (it.hasNext()) {
            String line = it.next();
            if (line.isEmpty()) {
                headerEndIndex = it.previousIndex();
                commandsIndex = it.nextIndex();
                break;      
             }        
        }
        it = lines.listIterator(commandsIndex);
        CargoCrane crane1 = buildCrate(lines, headerEndIndex, 1);
        CargoCrane craneUnlim = buildCrate(crane1.getStacks(), 0);
        while (it.hasNext()) {
            int[] commands = parseCommands(it.next());
            crane1.move(commands[0], commands[1], commands[2]);
            craneUnlim.move(commands[0], commands[1], commands[2]);
        }
        System.out.println("Part 1 answer: " + crane1.getTopCrates());
        System.out.println("Part 2 answer: " + craneUnlim.getTopCrates());
    }
    
    private static int[] parseCommands(String line) {
        return Arrays.stream(line.split("move | from | to "))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .mapToInt(Integer::parseUnsignedInt)
            .toArray();
    }
    
    private static CargoCrane buildCrate(Stack<String>[] stacksToClone, int maxAtOnce) {
        Stack<String>[] stacks = new Stack[stacksToClone.length];
        for (int i = 0; i < stacksToClone.length; i++) {
            stacks[i] = (Stack<String>) stacksToClone[i].clone();
        }
        return new CargoCrane(stacks, maxAtOnce); 
    }
    
    private static CargoCrane buildCrate(List<String> lines, int headerEndIndex, int maxAtOnce) {
        ListIterator<String> it = lines.listIterator(headerEndIndex);
        String numbers = it.previous(); 
        int stackCout = Integer.parseUnsignedInt(numbers.substring(numbers.lastIndexOf("   ")).trim());
        Stack<String>[] stacks = new Stack[stackCout];
        for (int i = 0; i < stackCout; i++) {
            stacks[i] = new Stack<>();            
        }
        while (it.hasPrevious()) {
            char[] line = it.previous().toCharArray();
            for (int i = 1, stack = 0; i < line.length; i += 4, stack++ ) {
                if (line[i] != ' ') {
                    stacks[stack].push(String.valueOf(line[i]));
                } 
            } 
        }
//        System.out.println(Arrays.toString(stacks));
        return new CargoCrane(stacks, maxAtOnce);
    }
}

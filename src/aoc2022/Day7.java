package aoc2022;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static aoc2022.Day7.TreeItem.DIR;

public class Day7 {
    
    public static class TreeItem {
        
        public static final String DIR = "dir";
        public static final String FILE = "file";
        
        private long size;
        private final String name;
        private final String type;
        private final TreeItem parent;
        private final List<TreeItem> children = new ArrayList<>();

        public TreeItem(String line, TreeItem parent) {
            this.parent = parent;
            String[] split = line.split(" ");
            name = split[1];
            if (split[0].equals(DIR)) {
                type = DIR;
                size = 0;
            } else {
                type = FILE;
                size = Long.parseUnsignedLong(split[0]);
            }   
        }
        
        public long calcSize() {
            if (type.equals(DIR)) {
                for (TreeItem ch : children) {
                    size += ch.calcSize();
                }  
            }
            return size; 
        }
        
        public long findDirsBySizeAtMost(long limit) {
            long total = 0L;
            if (type.equals(DIR)) {
                if (size <= limit) {
                    total += size;
                }
                for (TreeItem ch : children) {
                    total += ch.findDirsBySizeAtMost(limit);
                }      
            }
            return total; 
        }

        public long getSize() {
            return size;
        }
        
        @Override
        public String toString() {
            return buildLine("", this);
        }
        
        private String buildLine(String prefix, TreeItem item) {
            StringBuilder sb = new StringBuilder(prefix);
            sb.append("- ");
            sb.append(item.name).append(" (").append(item.type);
            sb.append(", size=").append(item.size).append(')');
            for (TreeItem ch : item.children) {
                sb.append('\n').append(buildLine(prefix + "  ", ch));
            }
            return sb.toString();
        }
    }
    
    public static void main(String[] args) throws Exception {        
//        var lines = Files.readAllLines(Path.of("input/day7.txt"));
        var lines = Arrays.asList(
                """            
                $ cd /
                $ ls
                dir a
                14848514 b.txt
                8504156 c.dat
                dir d
                $ cd a
                $ ls
                dir e
                29116 f
                2557 g
                62596 h.lst
                $ cd e
                $ ls
                584 i
                $ cd ..
                $ cd ..
                $ cd d
                $ ls
                4060174 j
                8033020 d.log
                5626152 d.ext
                7214296 k
                """.split("\n"));
        
        Map<String, TreeItem> dirs = new HashMap<>();
        TreeItem current = null;   
        for (String line : lines) {
            if (line.startsWith("$ cd")) {
               String name = line.substring(5);
               String fullName = getFullName(current, name);
               current = cd(current, name);
               dirs.put(fullName, current);
            } else if (line.startsWith("$ ls")) {
                // ignore
            } else {
                current.children.add(new TreeItem(line, current));
            }
        }
        TreeItem root = dirs.get("/");
        root.calcSize();
//        System.out.println("Dirs tree:\n" + root);   

        System.out.println("Part 1 answer: " + root.findDirsBySizeAtMost(100_000L));

        long unused  = 70_000_000 - root.size;
        long required = 30_000_000 - unused;
        long dirSize = dirs.values().stream()
                .filter(e -> e.type.equals(DIR))
                .mapToLong(TreeItem::getSize)
                .sorted() 
                .filter(size -> size >= required)
                .findFirst().orElseThrow();
        System.out.println("Part 2 answer: " + dirSize);
    }
    
    private static String getFullName(TreeItem current, String name) {
        if (current == null) {
            return name;
        }
        return getFullName(current.parent, "") + "/" + name;
    }
    
    private static TreeItem cd(TreeItem current, String name) {
        if (name.equals("/")) {
            return new TreeItem("dir /", null);
        }
        if (name.equals("..")) {
            return current.parent != null ? current.parent : current;
        }
        return current.children.stream()
                .filter(i -> i.type.equals(DIR) && i.name.equals(name))
                .findFirst().orElseThrow();
    }
}

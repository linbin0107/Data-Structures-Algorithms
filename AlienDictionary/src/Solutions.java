import javax.print.DocFlavor;
import java.util.*;

/**
 * Created by blin on 8/15/17.
 */

/**
 * There is a new alien language which uses the latin alphabet. However, the order among
 * letters are unknown to you. You receive a list of words from the dictionary, where words
 * are sorted lexicographically by the rules of this new language. Derive the order of letters
 * in this language
 * Assumption: 1. all letters are in lowercase; 2. if the order is invalid, return an empty
 * string; 3. there may be multiple valid order of letters, return any one of them.
 */

public class Solutions {
    // Solution 1: topological sort.
    public String alienOrder(String []words) {
        HashMap<Character, HashSet<Character>> graph = new HashMap<>();
        HashMap<Character, Integer> indegree = new HashMap<>();

        // 1. initialize the graph and the in-degree of each character
        initialize(words, graph, indegree);

        // 2. build the graph and calculate the actual in-degree of each character
        build(words, graph, indegree);

        // 3. topological sort
        String res = topologicalSort(graph, indegree);

        return res.length() == indegree.size() ? res : "";

    }

    // Solution 2: Iterate all words directly, from the first character to the last one
    public String alienOrder2(String []words) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();

        int max = 0;
        for (String word : words) {
            max = Math.max(max, word.length());
        }

        int index = 0;
        while (index < max) {
            for (String word : words) {
                if (index >= word.length()) {
                    continue;
                }

                if (set.contains(word.charAt(index))) {
                    continue;
                }

                set.add(word.charAt(index));
            }

            ++index;
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Character> iter = set.iterator();
        while (iter.hasNext()) {
            sb.append(iter.next());
        }

        return sb.toString();
    }

    private String topologicalSort(HashMap<Character, HashSet<Character>> graph,
                                   HashMap<Character, Integer> indegree) {
        StringBuilder sb = new StringBuilder();

        Queue<Character> queue = new LinkedList<>();
        // add all characters that has 0 in-degree
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()) {
            Character from = queue.poll();
            HashSet<Character> to = graph.get(from);

            sb.append(from);

            for (Character ch : to) {
                Integer degree = indegree.get(ch);
                --degree;
                indegree.put(ch, degree);

                if (degree == 0) {
                    queue.offer(ch);
                }
            }
        }

        return sb.toString();
    }

    private void build(String[] words, HashMap<Character, HashSet<Character>> graph,
                       HashMap<Character, Integer> indegree) {
        for(int i = 0; i < words.length - 1; ++i) {
            String w1 = words[i];
            String w2 = words[i + 1];
            for (int j = 0; j < w1.length() && j < w2.length(); ++j) {
                char from = w1.charAt(j);
                char to = w2.charAt(j);

                if (from == to) {
                    continue;
                }

                HashSet<Character> set = graph.get(from);
                if (set == null) {
                    set = new HashSet<>();
                }

                if (!set.add(to)) {
                    break;
                }

                graph.put(from, set);
                indegree.put(to, indegree.get(to) + 1);

                break;
            }
        }
    }

    private void initialize(String[] words, HashMap<Character, HashSet<Character>> graph,
                            HashMap<Character, Integer> indegree) {
        for (String word : words) {
            for(char ch : word.toCharArray()) {
                HashSet<Character> set = graph.get(ch);
                if (set == null) {
                    graph.put(ch, new HashSet<>());
                }

                Integer degree = indegree.get(ch);
                if (degree == null) {
                    indegree.put(ch, 0);
                }
            }
        }
    }

    public static void main(String []args) {
        Solutions solutions = new Solutions();

        String []words = {"wrt", "wrf", "er", "ett", "rftt"};

        System.out.println(solutions.alienOrder(words));
        System.out.println(solutions.alienOrder2(words));
    }
}

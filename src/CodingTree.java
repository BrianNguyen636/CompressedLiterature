import java.util.*;

public class CodingTree {
    Map<Character, String> codes;
    String bits;
    PriorityQueue<Node> queue;
    Map<Character, Integer> freqMap;

    void CodingTree(String message) {
        char[] text = message.toCharArray();
        for (char c : text) {
            if (!freqMap.containsKey(c)) {
                freqMap.put(c,1);
            } else {
                freqMap.put(c, freqMap.get(c) + 1);
            }
        }

        queue = new PriorityQueue<>(1, new AscendingComparator());
        for (Character c : freqMap.keySet()) {
            queue.add(new Node(c,freqMap.get(c)));
        }



    }
    class AscendingComparator implements Comparator<Node>  {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.weight - o1.weight;
        }
    }

    class Node {
        char character;
        int weight;
        Node left = null;
        Node right = null;

        Node(char theCharacter, int theWeight) {
            character = theCharacter;
            weight = theWeight;
        }
    }
}


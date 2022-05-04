import java.util.*;

public class CodingTree {
    Map<Character, String> codes;
    String bits;
    PriorityQueue<Node> queue;
    Map<Character, Integer> freqMap;

    public CodingTree(String message) {

        freqMap = countFreq(message);

        queue = populateQueue(freqMap);

        mergeTrees(queue);

    }

    public void mergeTrees(PriorityQueue<Node> theQueue) {
        while (theQueue.size() != 1) {
            Node left = theQueue.poll();
            Node right = theQueue.poll();
            Node root = new Node(left.weight + right.weight);
            root.left = left;
            root.right = right;
            theQueue.add(root);
        }
    }

    public PriorityQueue<Node> populateQueue(Map<Character, Integer> freq) {
        PriorityQueue<Node> result = new PriorityQueue<>(freq.size(), new AscendingComparator());
        for (Character c : freq.keySet()) {
            result.add(new Node(c,freq.get(c)));
        }
        return result;
    }

    public Map<Character, Integer> countFreq(String message) {
        char[] text = message.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text) {
            if (!map.containsKey(c)) {
                map.put(c,1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        return map;
    }

    class AscendingComparator implements Comparator<Node>  {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.weight - o2.weight;
        }
    }

    class Node {
        Character character;
        int weight = 0;
        Node left = null;
        Node right = null;

        Node(Character theCharacter, int theWeight) {
            character = theCharacter;
            weight = theWeight;
        }
        Node(int theWeight) {
            weight = theWeight;
        }
    }
}


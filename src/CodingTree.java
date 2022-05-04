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

        while (queue.size() != 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node root = new Node(left.weight + right.weight);
            root.left = left;
            root.right = right;
            queue.add(root);
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
        int weight = 0;
        Node left = null;
        Node right = null;

        Node(char theCharacter, int theWeight) {
            character = theCharacter;
            weight = theWeight;
        }
        Node(int theWeight) {
            weight = theWeight;
        }
    }
}


import java.util.*;

public class CodingTree {
    Map<Character, String> codes = new HashMap<>();
    byte[] bits;
    PriorityQueue<Node> queue;
    Map<Character, Integer> freqMap;

    public CodingTree(String message) {
        char[] text = message.toCharArray();
        freqMap = new HashMap<>();
        for (char c : text) {
            if (!freqMap.containsKey(c)) {
                freqMap.put(c,1);
            } else {
                freqMap.put(c, freqMap.get(c) + 1);
            }
        }
        queue = populateQueue(freqMap);
        mergeTrees(queue);

        mapCodes(codes, queue.peek());

        BitSet bitset = new BitSet(text.length);
        int i = 0;
        for (Character c : text) {
            String codeString = codes.get(c);
            for (int j = 0; j < codeString.length(); j++) {
                bitset.set(i + j, codeString.charAt(j) != '0');
            }
            i += codeString.length();
        }
//        String codeString = "";
//        for (Character c : text) {
//            codeString += codes.get(c);
//        }
//        for (int i = 0; i < codeString.length(); i++) {
//            bitset.set(i, codeString.charAt(i) != '0');
//        }
        bits = bitset.toByteArray();
    }

    private void mapCodes(Map<Character,String> map, Node tree) {
        mapCodes(map, tree, "");
    }
    /*
    Recursive function, if left and right are null, then map code to character.
     */
    private void mapCodes(Map<Character,String> map, Node current, String code) {
        if (current.left == null && current.right == null) {
            map.put(current.character, code);
        } else {
            if (current.left != null) {
                mapCodes(map, current.left, code + "0");
            }
            if (current.right != null) {
                mapCodes(map, current.right, code + "1");
            }
        }
    }

    private void mergeTrees(PriorityQueue<Node> theQueue) {
        while (theQueue.size() != 1) {
            Node left = theQueue.poll();
            Node right = theQueue.poll();
            Node root = new Node(left.weight + right.weight);
            root.left = left;
            root.right = right;
            theQueue.add(root);
        }
    }

    private PriorityQueue<Node> populateQueue(Map<Character, Integer> freq) {
        PriorityQueue<Node> result = new PriorityQueue<>(freq.size(), new AscendingComparator());
        for (Character c : freq.keySet()) {
            result.add(new Node(c,freq.get(c)));
        }
        return result;
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


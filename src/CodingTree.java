import java.util.*;
/*
@Author Brian Nguyen
 */
public class CodingTree {
    Map<Character, String> codes = new HashMap<>();
    byte[] bits;
    PriorityQueue<Node> queue;
    Map<Character, Integer> freqMap;

    public CodingTree(String message) {
        freqMap = new HashMap<>();
        for (int i = 0; i < message.length(); i++) {
            Character c = message.charAt(i);
            if (!freqMap.containsKey(c)) {
                freqMap.put(c,1);
            } else {
                freqMap.put(c, freqMap.get(c) + 1);
            }
        }
        queue = populateQueue(freqMap);
        mergeTrees(queue);

        mapCodes(codes, queue.peek());

        BitSet bitset = new BitSet();
        int x = 0;
        for (int i = 0; i < message.length(); i++) {
            Character c = message.charAt(i);

            String codeString = codes.get(c);

            for (int j = 0; j < codeString.length(); j++) {
                bitset.set(x + j, codeString.charAt(j) != '0');
            }
            x += codeString.length();
        }
        bits = bitset.toByteArray();
    }

//    String decode(String bits, Map<Character, String> codes) {
//        String result = "";
//        Node root = new Node(0);
//        for (Character c: codes.keySet()) {
//            String codeString = codes.get(c);
//            for (int i = 0; i < codeString.length(); i++) {
//                if (codeString.charAt(i) == '0') {
//
//                } else {
//
//                }
//            }
//        }
//        return result;
//    }


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
        Node(Character theCharacter) {character = theCharacter;}
    }
}


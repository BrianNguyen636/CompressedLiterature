import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        testCodingTree();
        long start = System.currentTimeMillis();
        File file = new File("WarAndPeace.txt");
        String text = "";
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                text += scan.nextLine();
            }
            CodingTree tree = new CodingTree(text);
            File compressed = new File("compressed.txt");
            OutputStream writer = new FileOutputStream(compressed);

            writer.write(tree.bits);
            writer.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Runtime: " + (end - start) + " milliseconds.");

    }
    public static void testCodingTree() {
        CodingTree test = new CodingTree("drift tonight");
        System.out.println(test.bits);
    }
}

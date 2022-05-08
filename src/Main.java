import java.io.*;
import java.util.Scanner;
/*
@Author Brian Nguyen
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
        testCodingTree();
        long start = System.currentTimeMillis();
        File file = new File("WarAndPeace.txt");
//        File file = new File(args[0]);
        String text = "";
        long filesize = 0;
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                text += scan.nextLine();
            }
            CodingTree tree = new CodingTree(text);

            File codes = new File("codes.txt");
            OutputStream codeWriter = new FileOutputStream(codes);
            for (Character c : tree.codes.keySet()) {

            }

            File compressed = new File("compressed.txt");
            OutputStream writer = new FileOutputStream(compressed);
            writer.write(tree.bits);
            writer.close();
            filesize = compressed.length() / 1024;


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("Original file size: " + file.length() / 1024 + " kibibytes.");
        System.out.println("Compressed file size: " + filesize + " kibibytes.");
        System.out.println("Runtime: " + (end - start) / 1000 + " seconds.");



    }

    public static void testCodingTree() {
        CodingTree test = new CodingTree("drift tonight");
    }
}

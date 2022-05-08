import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
/*
@Author Brian Nguyen
 */
public class Main {

    public static void main(String[] args) {
	// write your code here
//        testCodingTree();
        long start = System.currentTimeMillis();
        File file = new File("WarAndPeace.txt");
//        File file = new File(args[0]);
        StringBuilder text = new StringBuilder();
        long filesize = 0;
        try {
            Scanner scan = new Scanner(file);
            System.out.println("Reading input");
            while (scan.hasNextLine()) {
                text.append(scan.nextLine());
            }

            CodingTree tree = new CodingTree(text.toString());

            File codes = new File("codes.txt");
            OutputStream codeWriter = new FileOutputStream(codes);
            codeWriter.write(tree.codes.toString().getBytes(StandardCharsets.UTF_8));

            File compressed = new File("compressed.txt");
            OutputStream writer = new FileOutputStream(compressed);

            System.out.println("Writing to file");

            byte[] bit = new byte[tree.bits.size()];
            for (int i = 0; i < tree.bits.size(); i++) {
                bit[i] = tree.bits.get(i);
            }
            writer.write(bit);
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
        System.out.println("Runtime: " + (end - start) / 1000 + " seconds. (" +(end - start)+" ms)");



    }

    public static void testCodingTree() {
        CodingTree test = new CodingTree("drift tonight");
    }
}

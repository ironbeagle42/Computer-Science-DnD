import java.io.IOException;

public class HuffmanTester {
    public static void main(String[] args) {
        Huffman test = new Huffman();
        System.out.println(Huffman.intToBinary(50));
        try {
            Huffman.encodeFile("file.txt");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

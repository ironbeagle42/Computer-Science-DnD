import java.io.IOException;

public class HuffmanTester {
    public static void main(String[] args) {
        Huffman test = new Huffman();
        // System.out.println(Huffman.intToBinary(50));
        try {
            // Huffman.encodeFile("file.txt");
            // Huffman.encodeFile("file.txt.huff");
            // Huffman.decodeFile("file.txt.huff.huff");
            // Huffman.decodeFile("file.txt.huff.huff.decoded");
            Huffman.encodeFile("thegreatgatsbyzipped.txt");
            Huffman.decodeFile("thegreatgatsbyzipped.txt.huff");
            // Huffman.decodeFile("file.txt.huff.huff.decoded");
            // Huffman.encodeFile("thegreatgatsby.txt");
            // Huffman.decodeFile("thegreatgatsby.txt.huff");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

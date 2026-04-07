import java.io.IOException;

public class RLETester {
    public static void main(String[] args) {
        RLECompression text = new RLECompression();
        try {
            RLECompression.encode("file.txt");
            RLECompression.decode("file.txt.rle");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

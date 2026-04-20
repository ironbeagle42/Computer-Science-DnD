import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

public class Huffman {

    public static void encodeFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".huff");
        HashMap<Character, Integer> hash = new HashMap();
        while (br.ready()) {
            char c = (char) br.read();
            // pw.write(intToBinary(c));
            if (hash.containsKey(c)) {
                hash.put(c, hash.get(c) + 1);
            } else {
                hash.put(c, 1);
            }
        }
        ArrayList<ListNode> list = new ArrayList<>();
    }

    public static String intToBinary(int num) {
        int times = num / 64;
        String ret = "" + times;
        num -= 64 * times;
        for (int i = 32; i > 1; i /= 2) {
            times = num / i;
            ret += "" + times;
            num -= i * times;
        }
        if (num == 1) {
            ret += "" + num;
        } else {
            ret += "0";
        }
        if (ret.length() < 8) {
            while (ret.length() != 8) {
                ret = "0" + ret;
            }
        }
        return ret;

    }
}

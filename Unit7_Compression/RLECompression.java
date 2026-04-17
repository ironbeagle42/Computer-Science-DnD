import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class RLECompression {

    // Creates a compressed version with fileName + ".rle.bw"
    public static void compress(String fileName) throws IOException {
        bwTransform(fileName);
        encode(fileName + ".bw");
    }

    // Decompresses a .rle.bw file
    public static void decompress(String fileName) throws IOException {
        decode(fileName);
        invertBWTransform(fileName.substring(0, fileName.length() - 4));
    }

    // Encodes the contents of a file using the RLE compression scheme:
    // single characters are left alone, and runs of 2+ characters are encoded as
    // that letter twice, followed by the length of the run, cast as a char
    public static void encode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".rle");

        char previousChar = (char) br.read();
        int count = 1;

        while (br.ready()) {
            char c = (char) br.read();
            if (c == previousChar) {
                count++;
            } else if (count >= 2) {
                pw.write("" + previousChar + previousChar + count);
                count = 1;
            } else {
                pw.write(previousChar);
            }
            if (!br.ready() && count >= 2) {
                pw.write("" + c + c + count);
            }
            previousChar = c;
        }

        br.close();
        pw.close();
    }

    // Decodes the above scheme
    public static void decode(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 4));

        char previousChar = (char) br.read();

        while (br.ready()) {
            char c = (char) br.read();
            if (previousChar == c) {
                int num = Integer.parseInt(Character.toString((char) br.read()));
                for (int i = 0; i < num; i++) {
                    pw.write(c);
                }
                previousChar = (char) br.read();
            } else {
                pw.write(previousChar);
                previousChar = c;
            }
        }

        br.close();
        pw.close();
    }

    public static void bwTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        // Add a null character at the beginning, as a
        // "beginning of file" character
        StringBuilder originalText = new StringBuilder("" + '~');

        while (br.ready()) {
            char c = (char) br.read();
            originalText.append(c);
        }
        br.close();

        String[] rotations = new String[originalText.length()];
        rotations[0] = originalText.toString();
        String newStr = "";
        String tempStr = "";
        for (int i = 1; i < originalText.toString().length(); i++) {
            tempStr = rotations[i - 1];
            newStr = tempStr.substring(tempStr.length() - 1);
            newStr += tempStr.substring(0, tempStr.length() - 1);
            rotations[i] = newStr;
        }
        PrintWriter pw = new PrintWriter(fileName + ".bw");
        ArrayList<String> firstArr = new ArrayList<String>();
        for (String j : rotations) {
            firstArr.add(j);
        }
        firstArr.sort(null);
        for (int i = 0; i < rotations.length; i++) {
            pw.write(firstArr.get(i).substring(firstArr.get(i).length() - 1));
        }
        // And then write the transformation into a file
        pw.close();
    }

    public static void invertBWTransform(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String originalText = "";

        while (br.ready()) {
            char c = (char) br.read();
            originalText += "" + c;
        }
        br.close();

        String[] reconstructions = new String[originalText.length()];
        for (int i = 0; i < reconstructions.length; i++) {
            reconstructions[i] = "" + originalText.charAt(i);
        }
        ArrayList<String> firstArr = new ArrayList<String>();
        ArrayList<String> nextArr = new ArrayList<String>();
        for (String j : reconstructions) {
            firstArr.add(j.toString());
            nextArr.add(j.toString());
        }
        nextArr.sort(null);
        for (int a = 0; a < firstArr.size() - 1; a++) {
            for (int b = 0; b < firstArr.size(); b++) {
                nextArr.set(b, firstArr.get(b) + nextArr.get(b));
            }
            if (nextArr.get(0).length() <= originalText.length() + 1) {
                nextArr.sort(null);
            }
        }
        String ret = nextArr.get(nextArr.size() - 1);
        ret = ret.substring(1);


        // TO-DO
        // And write the appropriate reconstruction into the file, without the null char
        PrintWriter pw = new PrintWriter(fileName.substring(0, fileName.length() - 3));
        pw.write(ret);
        pw.close();
    }
}

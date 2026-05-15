import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IO;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.ArrayList;

public class Huffman {

    public static void encodeFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(fileName + ".huff", StandardCharsets.UTF_8);
        PrintWriter dict = new PrintWriter(fileName + ".dict", StandardCharsets.UTF_8);
        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        while (br.ready()) {
            char c = (char) br.read();
            // pw.write(intToBinary(c));
            if (hash.containsKey(c)) {
                hash.put(c, hash.get(c) + 1);
            } else {
                hash.put(c, 1);
            }
            hash.put((char) 3, 1);
        }
        ArrayList<Character> keys = new ArrayList<Character>(hash.keySet());
        ArrayList<BinaryNode<Character, Integer>> list = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            list.add(new BinaryNode<Character, Integer>(keys.get(i), hash.get(keys.get(i))));
        }
        list = sortArray(list);
        while (list.size() >= 2) {
            int num = list.get(list.size() - 2).getFreq() + list.get(list.size() - 1).getFreq();
            BinaryNode<Character, Integer> newNode = new BinaryNode<>(null, num);
            newNode.setLeft(list.get(list.size() - 2));
            newNode.setRight(list.get(list.size() - 1));
            list.removeLast();
            list.removeLast();
            for (int i = 0; i < list.size(); i++) {
                if (newNode.getFreq() >= list.get(i).getFreq()) {
                    list.add(i, newNode);
                    i = list.size();
                } else if (i == list.size() - 1) {
                    list.add(newNode);
                    i = list.size();
                }
            }
            if (list.size() == 0) {
                list.add(newNode);
            }
        }
        BinaryNode<Character, Integer> head = list.get(0);
        String dictionaryString = createDictionary(head, "");
        dict.write(dictionaryString.substring(0, dictionaryString.length() - 1));
        dict.close();
        ArrayList<Character> str = new ArrayList<>();
        BufferedReader brStr = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        BufferedReader dictReader =
                new BufferedReader(new FileReader(fileName + ".dict", StandardCharsets.UTF_8));
        HashMap<Integer, String> dictHash = new HashMap<Integer, String>();
        String tempString = "";
        while (dictReader.ready()) {
            tempString = dictReader.readLine();
            if (!tempString.equals("")) {
                String[] strings = tempString.split(" ");
                String str1 = strings[1];

                Integer int1 = Integer.parseInt(strings[0]);
                dictHash.put(int1, str1);
            } else {
                tempString = dictReader.readLine();
                dictHash.put(10, tempString.substring(1));
            }
        }
        while (brStr.ready()) {
            str.add((char) brStr.read());
        }
        str.add((char) 3);
        String binary = "";
        for (int i = 0; i < str.size(); i++) {
            char temp = str.get(i);
            if (dictHash.get((int) temp) != null) {
                binary += dictHash.get((int) temp);
            } else {
                int hello = 2;
            }
        }
        int num = 8 - binary.length() % 8;
        for (int n = 0; n < num; n++) {
            binary += "0";
        }
        String binaryToChars = "";;
        while (binary.length() != 0) {
            if (binary.length() < 300) {
                System.out.print(".");
            }
            String eight = binary.substring(0, 8);
            binary = binary.substring(8, binary.length());
            binaryToChars += (char) Integer.parseInt(eight, 2);
        }
        brStr.close();
        dictReader.close();
        BufferedReader dictReader2 =
                new BufferedReader(new FileReader(fileName + ".dict", StandardCharsets.UTF_8));
        while (dictReader2.ready()) {
            pw.write((char) dictReader2.read());
        }
        dictReader2.close();
        pw.write("\nDICTEND\n");
        pw.write(binaryToChars);
        pw.close();
    }

    public static String createDictionary(BinaryNode<Character, Integer> head, String binary)
            throws IOException {
        String dict = "";
        if (head.hasLeft()) {
            dict += createDictionary(head.getLeft(), binary + "0");
        }
        if (head.hasRight()) {
            dict += createDictionary(head.getRight(), binary + "1");
        }
        if (head.getValue() != null) {
            dict += "" + ((int) head.getValue()) + " " + binary + "\n";
            return dict;
        }
        return dict.substring(0, dict.length());
    }

    public static ArrayList<BinaryNode<Character, Integer>> sortArray(
            ArrayList<BinaryNode<Character, Integer>> list) {
        ArrayList<BinaryNode<Character, Integer>> newArr = new ArrayList<>();
        int num = 0;
        int listSize = list.size();
        while (newArr.size() != (listSize)) {
            num = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFreq() > num) {
                    num = list.get(i).getFreq();
                }
            }
            for (int j = 0; j < list.size(); j++) {
                int a = list.get(j).getFreq();
                if (a == num) {
                    newArr.add(new BinaryNode<Character, Integer>(list.get(j).getValue(), a));
                    list.remove(j);
                    j = list.size() + 10;
                }
            }
        }
        return newArr;

    }

    public static String intToBinary(int num) {
        int times = num / 128;
        String ret = "" + times;
        num -= 128 * times;
        for (int i = 64; i > 1; i /= 2) {
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

    public static void decodeFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8));
        PrintWriter pw = new PrintWriter(fileName + ".decoded", StandardCharsets.UTF_8);
        String file = "";
        String fileInBinary = "";
        HashMap<String, Integer> dictionary = new HashMap<String, Integer>();
        String tempString = "";
        while (!tempString.equals("DICTEND")) {
            tempString = (br.readLine());
            if (!tempString.equals("DICTEND")) {
                if (!tempString.equals("")) {
                    String[] strings = tempString.split(" ");
                    String str1 = strings[1];
                    Integer int1 = Integer.parseInt(strings[0]);
                    dictionary.put(str1, int1);
                } else {
                    tempString = br.readLine();
                    dictionary.put(tempString.substring(1), 10);
                }
            }
        }
        while (br.ready()) {
            file += (char) br.read();
        }
        for (int j = 0; j < file.length(); j++) {
            fileInBinary += intToBinary((int) file.charAt(j));
        }
        String tempChars = "";
        String decodedFile = "";
        // System.out.println(fileInBinary.length());
        for (int i = 0; i < fileInBinary.length(); i++) {
            tempChars += fileInBinary.charAt(i);
            if (dictionary.containsKey(tempChars)) {
                if (dictionary.get(tempChars) == 3) {
                    if (fileInBinary.length() - i <= 24) {
                        // pw.write(decodedFile);
                        i = fileInBinary.length();
                    } else {
                        decodedFile += (char) (int) dictionary.get(tempChars);
                        tempChars = "";
                    }
                } else {
                    decodedFile += (char) (int) dictionary.get(tempChars);
                    tempChars = "";
                }

            }
            // System.out.print(".");
        }
        if (decodedFile.endsWith(Character.toString(((char) 3)))) {
            decodedFile = decodedFile.substring(0, decodedFile.length() - 1);
        }

        pw.write(decodedFile);
        br.close();
        pw.close();
    }
}

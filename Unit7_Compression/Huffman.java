import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.ArrayList;

public class Huffman {

    public static void encodeFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        PrintWriter pw = new PrintWriter(fileName + ".huff");
        PrintWriter dict = new PrintWriter(fileName + ".dict");
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
                } else if (i == list.size()) {
                    list.add(newNode);
                }
            }
            if (list.size() == 0) {
                list.add(newNode);
            }
        }
        BinaryNode<Character, Integer> head = list.get(0);
        dict.write(createDictionary(head, ""));
        dict.close();

    }

    public static String createDictionary(BinaryNode<Character, Integer> head, String binary) throws IOException {
        String dict = "";
        if (head.hasLeft()) {
            dict += createDictionary(head.getLeft(), binary + "0");
        }
        if (head.hasRight()) {
            dict += createDictionary(head.getRight(), binary + "1");
        }
        if (head.getValue() != null) {
            dict += " " + head.getValue().toString() + " " + binary + "\n";
        }
        return dict;
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

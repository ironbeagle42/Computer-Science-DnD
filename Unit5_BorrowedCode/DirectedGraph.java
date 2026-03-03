import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {


    public static List<String> readAllLines() {
        List<String> names = new ArrayList<String>();
        try {


            // Each element is one line from the file
            Path p = Paths.get("BorrowedCode.txt");
            return (List<String>)Files.readAllLines(p);
        } catch (Exception e) {
            System.out.println("Couldn’t read file");
        }
        return names;
    }

    public static void main(String[] args) {
        System.out.println(readAllLines());
    }

}



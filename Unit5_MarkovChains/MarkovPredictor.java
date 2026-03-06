import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MarkovPredictor {

    // Please define your own variables and data structures
    private HashMap<String, ArrayList<String>> map = new HashMap<>();

    //
    public ArrayList<String[]> readData(String filePath) {
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String [] arr = line.split(",");
                if (!map.containsKey(arr[0])) {
                    map.put(arr[0], new ArrayList<String>());
                }
                ArrayList<String> temp = map.get(arr[0]);
                temp.add(arr[1]);
                map.put(arr[0], temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Method to predict the next state given a current state
    public String predictNextState(String currentState) {
        

    }

}

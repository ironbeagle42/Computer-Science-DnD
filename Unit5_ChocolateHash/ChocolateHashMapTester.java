public class ChocolateHashMapTester {
    public static  void main(String[] args) {
        ChocolateHashMap<String, String> test = new ChocolateHashMap(10, .75);
        System.out.println(test.isEmpty());
        System.out.println(test.put("LOT-70", "DARK"));
        System.out.println(test.put("LOT-99", "WHITE"));
        System.out.println(test.isEmpty());
        System.out.println(test.toString());
        test.rehash(20);
        System.out.println(test.toString());
        System.out.println(test.put("LOT-20", "MILK"));
        test.containsKey("LOT-70");
        System.out.println(test.getObjectCount());
        System.out.println(test.currentLoadFactor());
        test.get("LOT-20");
        test.containsValue("MILK");
        System.out.println(test.remove("LOT-70"));
        System.out.println(test.toString());


    }
}

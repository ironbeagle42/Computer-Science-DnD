public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT tester = new MiniGPT("math.txt", 8);
        tester.generateText("math2.txt", 10000);
    }
}

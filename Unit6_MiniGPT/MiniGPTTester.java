public class MiniGPTTester {
    public static void main(String[] args) {
        MiniGPT tester = new MiniGPT("thegreatgatsby.txt", 10);
        tester.generateText("greatGatsby2.txt", 2000);
    }
}

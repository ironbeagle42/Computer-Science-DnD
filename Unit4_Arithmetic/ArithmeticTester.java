public class ArithmeticTester {
    public static void main(String[] args) {
        String temp = "( 2 + ( ( 3 * 5 ) / ( 11 - 6 ) ) )";
        System.out.println(Arithmetic.evaluate(temp));
    }
}

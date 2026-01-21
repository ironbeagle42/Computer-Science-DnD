
public class Arithmetic {

	// Evaluates a String exp that has an arithmetic expression, written in classic notation
	public static int evaluate(String exp) {
		return evaluateStout(convertClassicToStout(exp));
	}

	// Returns the result of doing operand1 operation operand2,
	// e.g. operate(5, 2, "-") should return 3
	public static int operate(int operand1, int operand2, String operation) {
		if (operation == null) {
			throw new NullPointerException();
		}
		if (operation.equals("+")) {
			return operand1 + operand2;
		}
		if (operation.equals("-")) {
			return operand1 - operand2;
		}
		if (operation.equals("*")) {
			return operand1 * operand2;
		}
		if (operation.equals("/")) {
			return operand1 / operand2;
		}
		if (operation.equals("%")) {
			return operand1 % operand2;
		}
		throw new IllegalArgumentException();
	}

	// Evaluates a String exp that has an arithmetic expression written in STOUT notation
	public static int evaluateStout(String exp) {
		String[] strings = exp.split(" ");

		return 0;
	}

	public static String convertClassicToStout(String exp) {
		String[] strings = exp.split(" ");
		MyStack<String> stack = new MyStack<String>();
		String ret = "";
		int index = 0;
		ret += (strings[index] + " ");
		index++;
		while (index < strings.length) {
			if (strings[index].equals("(")) {
				stack.add(new ListNode<String>("("));

			} else if (strings[index].equals(")")) {
				stack.pop();
				while (!stack.peek().equals("(")) {
					ret += stack.peek() + " ";
					stack.pop();
				}
				stack.pop();

			} else if (toInt(strings[index]) == 0) {
				ret += strings[index] + " ";

			} else {
				if (stack.empty()) {
					stack.add(new ListNode<String>(strings[index]));
				} else {
					if (toInt(strings[index]) > toInt(stack.peek())) {
						stack.add(new ListNode<String>(strings[index]));
					} else {
						ret += stack.peek() + " ";
						stack.pop();
						stack.add(new ListNode<String>(strings[index]));
					}
				}
			}
			index++;

		}
		return ret.substring(0, ret.length() - 1);
	}

	public static int toInt(String op) {
		if (op == null) {
			throw new NullPointerException();
		}
		if (op.equals("+") || op.equals("-")) {
			return 1;
		}
		if (op.equals("*") || op.equals("/") || op.equals("%")) {
			return 2;
		}
		if (op.equals("^")) {
			return 3;
		}
		return 0;
	}


}

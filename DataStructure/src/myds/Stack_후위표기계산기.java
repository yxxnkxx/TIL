package myds;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class Stack_후위표기계산기 {
	static Stack<Character> stack = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	static HashMap<Character, Integer> operator = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		operator.put('+', 1); // 우선순위
		operator.put('-', 1);
		operator.put('*', 2);
		operator.put('/', 2);
		operator.put('(', 0);
		operator.put(')', 0);
		String postfix = toPostfix(input);
		System.out.println(postfix);
		System.out.println(cal(postfix));

	}

	static String toPostfix(String input) {
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);

			if (c == '(')
				stack.push(c); // 좌괄호는 그냥 push

			// 우괄호는 좌괄호가 나올 때까지 pop하여 sb에 추가
			else if (c == ')') {
				while (stack.peek() != '(') {
					sb.append(stack.pop());
				}
				stack.pop(); // 좌괄호는 그냥 pop
			}

			else if (operator.containsKey(c)) {

				if (stack.isEmpty()) // 비어 있으면 push
					stack.push(c);
				else {
					// 비어있지 않으면 stack의 peek의 우선순위가 자신보다 크거나 같으면 pop
					// 작을때는 그냥 push
					while (!stack.isEmpty() && operator.get(c) <= operator.get(stack.peek())) {
						sb.append(stack.pop());
					}
					stack.push(c);
				}

			} else { // 연산자가 아닐 때 출력
				sb.append(c);
			}
		}

		while (!stack.isEmpty()) // stack에 있는 것 모두 pop
			sb.append(stack.pop());

		return sb.toString();
	}

	static int cal(String postfix) {
		Stack<Integer> numStack = new Stack<>();
		for (int i = 0; i < postfix.length(); i++) {
			char c = postfix.charAt(i);

			if (c >= '0' && c <= '9')
				// 숫자일 경우 stack에 push
				numStack.push(c - '0');
			else {
				int num1 = numStack.pop();
				int num2 = numStack.pop();
				switch (c) {
				case '+':
					numStack.push(num2 + num1);
					break;

				case '-':
					numStack.push(num2 - num1);
					break;

				case '*':
					numStack.push(num2 * num1);
					break;
				case '/':
					numStack.push(num2 / num1);
					break;

				}
			}

		}
		return numStack.pop();

	}
}

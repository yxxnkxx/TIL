package myds;

import java.util.Scanner;
import java.util.Stack;

public class Stack_괄호검사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String input = sc.next();
			if (check(input))
				System.out.println("YES");
			else
				System.out.println("NO");
		}

	}

	static boolean check(String input) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == '(')
				stack.push(c); // 왼쪽 괄호일 때는 push
			else {
				if (stack.isEmpty())
					return false;
				// 오른쪽 괄호인데 stack이 비어 있으면 false
				stack.pop(); // 오른쪽 괄호일 때 peek가 (이면 pop
			}
		}
		if (!stack.isEmpty())
			return false;// for문이 끝나고 stack이 비어있지 않으면 false
		return true;
	}

}

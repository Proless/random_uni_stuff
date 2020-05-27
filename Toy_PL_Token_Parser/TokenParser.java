package task3;

import java.util.List;

public class TokenParser {

	public static String currentToken = "";
	public static int tokenIndex = 0;
	public static List<String> inputList = null;

	public static Node functionParser(List<String> input) {
		reset();
		// get the first token from the input list and store the list reference in a
		// static variable for later use.
		inputList = input;
		currentToken = inputList.get(0);

		// construct the start/root of the parse tree
		Node result = new Node(0);

		// start parsing the input
		try {
			start(result);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result = invalid();
		}

		return result;
	}

	// Grammar production methods
	public static void start(Node tree) throws Exception {

		// Tree construction logic
		// Idea: make each production method return its own Node of the tree instead of
		// passing in the node of its parent.

		// Parser logic
		if (currentToken.equals("id") || currentToken.equals("read") || currentToken.equals("write")
				|| currentToken.equals("input_end")) {
			stmt_list();
			match("input_end");
		} else {
			parse_error();
		}
	}

	public static void stmt_list() throws Exception {

		// Tree construction logic

		// Parser logic
		if (currentToken.equals("id") || currentToken.equals("read") || currentToken.equals("write")) {
			stmt();
			stmt_list();
		} else if (currentToken.equals("ε")) {
			match("ε");
		} else {
			parse_error();
		}
	}

	public static void stmt() throws Exception {
		// Tree construction logic

		// Parser logic
		if (currentToken.equals("id")) {
			match("id");
			match(":=");
			expr();
		} else if (currentToken.equals("read")) {
			match("read");
			match("id");
		} else if (currentToken.equals("write")) {
			match("write");
			expr();
		} else {
			parse_error();
		}
	}

	public static void expr() throws Exception {
		// Tree construction logic

		// Parser logic
		if (currentToken.equals("id") || currentToken.equals("digit") || currentToken.equals("(")) {
			term();
			term_tail();
		} else {
			parse_error();
		}
	}

	public static void term() throws Exception {

		// Tree construction logic

		// Parser logic
		if (currentToken.equals("id") || currentToken.equals("digit") || currentToken.equals("(")) {
			factor();
			factor_tail();
		} else {
			parse_error();
		}
	}

	public static void term_tail() throws Exception {

		// Tree construction logic

		// Parser logic
		if (currentToken.equals("+") || currentToken.equals("-")) {
			add_op();
			term();
			term_tail();

		} else if (currentToken.equals(")") || currentToken.equals("read") || currentToken.equals("write")
				|| currentToken.equals("input_end")) {
			match("ε");
		} else {
			parse_error();
		}
	}

	public static void factor() throws Exception {

		// Tree construction logic

		// Parser logic
		if (currentToken.equals("id")) {
			match("id");
		} else if (currentToken.equals("digit")) {
			match("digit");
		} else if (currentToken.equals("(")) {
			match("(");
			expr();
			match(")");
		} else {
			parse_error();
		}
	}

	public static void factor_tail() throws Exception {
		// Tree construction logic

		// Parser logic
		if (currentToken.equals("*") || currentToken.equals("/")) {
			mult_op();
			factor();
			factor_tail();

		} else if (currentToken.equals("+") || currentToken.equals("-") || currentToken.equals(")")
				|| currentToken.equals("id") || currentToken.equals("write") || currentToken.equals("read")
				|| currentToken.equals("input_end")) {
			match("ε");
		} else {
			parse_error();
		}
	}

	public static void add_op() throws Exception {
		// Tree construction logic

		// Parser logic
		if (currentToken.equals("+")) {
			match("+");
		} else if (currentToken.equals("-")) {
			match("-");
		} else {
			parse_error();
		}
	}

	public static void mult_op() throws Exception {

		// Tree construction logic

		// Parser logic
		if (currentToken.equals("*")) {
			match("*");
		} else if (currentToken.equals("/")) {
			match("/");
		} else {
			parse_error();
		}
	}

	// Helper methods
	public static Node invalid() {
		Node invalidNode = new Node(0);
		invalidNode.setLabel("invalid");
		return invalidNode;
	}

	public static void match(String expected) throws Exception {
		if (currentToken == expected) {
			tokenIndex++;
			currentToken = inputList.get(tokenIndex);
		} else {
			parse_error();
		}

	}

	public static void reset() {
		currentToken = "";
		tokenIndex = 0;
		inputList = null;
	}

	public static void parse_error() throws Exception {
		throw new Exception("Parsing error, the input is not valid.");
	}
}

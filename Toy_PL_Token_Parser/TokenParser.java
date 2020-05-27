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
		start(result);

		return result;
	}

	// Grammar production methods
	public static void start(Node tree) {

		// Tree construction logic
		tree.setLabel("start");

		// Parser logic

		if (currentToken.equals("id") || currentToken.equals("read") || currentToken.equals("write")
				|| currentToken.equals("input_end")) {
			stmt_list(tree);
			if (match("input_end")) {
				addTokenNode(tree, "input_end");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void stmt_list(Node tree) {

		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("stmt_list");
		tree.addChild(nonTerminalNode);

		// Parser logic

		if (currentToken.equals("id") || currentToken.equals("read") || currentToken.equals("write")) {
			stmt(nonTerminalNode);
			stmt_list(nonTerminalNode);
		} else if (currentToken.equals("ε")) {
			if (match("ε")) {
				addTokenNode(tree, "epsilon");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void stmt(Node tree) {
		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("stmt");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("id")) {
			if (match("id")) {
				addTokenNode(tree, "id");
			} else {
				tree = invalid();
			}
			if (match(":=")) {
				addTokenNode(tree, ":=");
			} else {
				tree = invalid();
			}
			expr(nonTerminalNode);
		} else if (currentToken.equals("read")) {
			if (match("read")) {
				addTokenNode(tree, "read");
			} else {
				tree = invalid();
			}
			if (match("id")) {
				addTokenNode(tree, "id");
			} else {
				tree = invalid();
			}
		} else if (currentToken.equals("write")) {
			if (match("write")) {
				addTokenNode(tree, "write");
			} else {
				tree = invalid();
			}
			expr(nonTerminalNode);
		} else {
			tree = invalid();
		}
	}

	public static void expr(Node tree) {
		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("expr");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("id") || currentToken.equals("digit") || currentToken.equals("(")) {
			term(nonTerminalNode);
			term_tail(nonTerminalNode);
		} else {
			tree = invalid();
		}
	}

	public static void term(Node tree) {

		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("term");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("id") || currentToken.equals("digit") || currentToken.equals("(")) {
			factor(nonTerminalNode);
			factor_tail(nonTerminalNode);
		} else {
			tree = invalid();
		}
	}

	public static void term_tail(Node tree) {

		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("term_tail");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("+") || currentToken.equals("-")) {
			add_op(nonTerminalNode);
			term(nonTerminalNode);
			term_tail(nonTerminalNode);

		} else if (currentToken.equals(")") || currentToken.equals("read") || currentToken.equals("write")
				|| currentToken.equals("input_end")) {
			if (match("ε")) {
				addTokenNode(tree, "epsilon");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void factor(Node tree) {

		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("factor");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("id")) {
			if (match("id")) {
				addTokenNode(tree, "id");
			} else {
				tree = invalid();
			}
		} else if (currentToken.equals("digit")) {
			if (match("digit")) {
				addTokenNode(tree, "digit");
			} else {
				tree = invalid();
			}
		} else if (currentToken.equals("(")) {
			if (match("(")) {
				addTokenNode(tree, "(");
			} else {
				tree = invalid();
			}
			expr(nonTerminalNode);
			if (match(")")) {
				addTokenNode(tree, ")");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void factor_tail(Node tree) {
		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("factor_tail");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("*") || currentToken.equals("/")) {
			mult_op(nonTerminalNode);
			factor(nonTerminalNode);
			factor_tail(nonTerminalNode);

		} else if (currentToken.equals("+") || currentToken.equals("-") || currentToken.equals(")")
				|| currentToken.equals("id") || currentToken.equals("write") || currentToken.equals("read")
				|| currentToken.equals("input_end")) {
			if (match("ε")) {
				addTokenNode(tree, "epsilon");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void add_op(Node tree) {
		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("add_op");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("+")) {
			if (match("+")) {
				addTokenNode(tree, "+");
			} else {
				tree = invalid();
			}
		} else if (currentToken.equals("-")) {
			if (match("-")) {
				addTokenNode(tree, "-");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	public static void mult_op(Node tree) {

		// Tree construction logic
		Node nonTerminalNode = new Node(tree.getId() + 1);
		nonTerminalNode.setLabel("mult_op");
		tree.addChild(nonTerminalNode);

		if (currentToken.equals("*")) {
			if (match("*")) {
				addTokenNode(tree, "*");
			} else {
				tree = invalid();
			}
		} else if (currentToken.equals("/")) {
			if (match("/")) {
				addTokenNode(tree, "/");
			} else {
				tree = invalid();
			}
		} else {
			tree = invalid();
		}
	}

	// Helper methods
	public static Node invalid() {
		Node invalidNode = new Node(0);
		invalidNode.setLabel("invalid");
		return invalidNode;
	}

	public static boolean match(String expected) {
		if (currentToken == expected) {
			tokenIndex++;
			if (tokenIndex != inputList.size()) {
				currentToken = inputList.get(tokenIndex);
			}
			return true;
		} else {
			return false;
		}
	}

	public static void reset() {
		currentToken = "";
		tokenIndex = 0;
		inputList = null;
	}

	public static void addTokenNode(Node tree, String token) {
		Node tokenNode = new Node(tree.getId() + 1);
		tokenNode.setLabel(token);
		tree.addChild(tokenNode);
	}
}

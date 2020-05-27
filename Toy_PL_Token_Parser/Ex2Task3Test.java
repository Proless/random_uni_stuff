package task3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class Ex2Task3Test {

//For grading, we evaluate the shape of the tree and the label fields associated with each node,
// all remaining fields are provided for your convenience.


	@Test
	void test1() {
		List<String> input = Arrays.asList("id", ":=", ":=", "digit", "input_end");
		
		Node result = TokenParser.functionParser(input);
		
		assertEquals(0, result.getChildren().size());
		assertEquals("invalid", result.getLabel());

	}
	
	@Test
	void test2() {
		List<String> input = Arrays.asList("write", "id", "+","id", "input_end");
		
		Node result = TokenParser.functionParser(input);
		
		assertEquals(2, result.getChildren().size());
		assertEquals("stmt_list", result.getChildren().get(0).getLabel());
		assertEquals("input_end", result.getChildren().get(1).getLabel());
		assertEquals(2, result.getChildren().get(0).getChildren().size());
	
		// add more assertions to check rest of tree
	}


}

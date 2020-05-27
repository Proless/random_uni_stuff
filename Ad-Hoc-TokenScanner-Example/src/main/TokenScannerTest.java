package main;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.rules.Timeout;

class TokenScannerTest {

	@Rule
	public Timeout globalTimeout = Timeout.seconds(10);
	// The timeout is to avoid infinite loops. Do not worry about the performance of
	// your code.

	@Test
	public void test1() {
		List<String> list1 = Arrays.asList("while", "(", "ifvar", "1", "<", "0", ")", "{", "y", "=", "7", ";", "}");

		List<String> results1 = TokenScanner.scanner("while (ifvar1 <0){\ny=  7;}");
		assertEquals(list1, results1);

	}

	@Test
	public void test3() {
		List<String> list = Arrays.asList("if", "(", "k", ">", "&&", "0", ")", "{", "var", "=", "0", ";", "}");

		List<String> results = TokenScanner.scanner("if(k> && 0 ) { var=0;}");

		assertEquals(list, results);
	}

	@Test
	public void test2() {

		List<String> wrong = Arrays.asList("invalid");

		List<String> results2 = TokenScanner.scanner("return v@r;");

		assertEquals(wrong, results2);

	}

	@Test
	public void test4() {
		List<String> list = Arrays.asList("if", "ergdg", "=", "(", "k", ">", "&&", "0", ")", "{", "var", "=", "0", ";");

		List<String> results = TokenScanner.scanner("if ergdg=(k>&& 0 ){\n var = 0;");

		assertEquals(list, results);
	}

}

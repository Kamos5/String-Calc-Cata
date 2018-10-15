import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import static org.testng.Assert.assertEquals;

@Test
public class TestAdd {

	private Calculator calculator;
	
	@BeforeTest
	public void init(){
		calculator = new Calculator();
	}
	
	public void emptyStringReturnsZero() {
		
		assertEquals(calculator.Add(""), 0);
	}
	
	public void sigleValueIsReplied() {
		assertEquals(calculator.Add("1"), 1);
	}
	
	public void twoValuesAreSummed()  {
		assertEquals(calculator.Add("1,2"), 3);
	}
	
	public void twoValuesAreSummedNewLineDelimited() {
		assertEquals(calculator.Add("1\n2"), 3);
	}
	
	public void threeValuesAreSummed() {
		assertEquals(calculator.Add("1\n2,3"), 6);
	}
	
	public void separatorIsFirst() {
		assertEquals(calculator.Add(",2,3"), 5);
	}
	
	@Test
	public final void newLineAtTheEndRuntimeExceptionIsThrown() {
	    RuntimeException exception = null;
	    try {
	        calculator.Add("3,2\n");
	    } catch (RuntimeException e) {
	        exception = e;
	    }
	    assertEquals("new line symbol cannot be at the end", exception.getMessage());
	}
	
	@Test
	public final void negativeNumbersUsedRuntimeExceptionIsThrown() {
	    RuntimeException exception = null;
	    try {
	        calculator.Add("-3");
	    } catch (RuntimeException e) {
	        exception = e;
	    }
	    assertEquals("Negative not allowed: [-3]", exception.getMessage());
	}
	
	public void ignoreNumbersGreaterThan1000() {
		assertEquals(calculator.Add("1,1001,1"), 2);
	}
	
	@Test
	public final void negativeNumbersUsedThenRuntimeExceptionIsThrown() {
	    RuntimeException exception = null;
	    try {
	        calculator.Add("-3,-2");
	    } catch (RuntimeException e) {
	        exception = e;
	    }
	    assertEquals("Negative not allowed: [-3, -2]", exception.getMessage());
	}
	
	public void changeSeparator() {
		assertEquals(calculator.Add("//;1\n2;3"), 6);
	}
	public void changeSeparatorWithNewLineStart() {
		assertEquals(calculator.Add("//;\n2;3"), 5);
	}
	
	public void longSeparators() {
		assertEquals(calculator.Add("//;;;\n2;;;;3"), 5);
	}
	
	public void separatorsWithBracket() {
		assertEquals(calculator.Add("//[;]1;;4;;;3"), 8);
	}
	
	public void longSeparatorsWithBracket() {
		assertEquals(calculator.Add("//[;;]1;;4;;;;3"), 8);
	}
	
	//public void longTwoOrMoreSeparators() {
	//	assertEquals(calculator.Add("//[;][=]1;;===4;;;3"), 8);
	//}
}

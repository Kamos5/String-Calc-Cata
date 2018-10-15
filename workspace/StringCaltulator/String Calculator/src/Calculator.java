import java.util.ArrayList;

public class Calculator {

	String delimiter =",|\n";
	
	public int Add(String input) {
		
		Integer temp = 1;
		ifNewLineAtTheEndExp(input);
		
		if (input.startsWith("//")){
			
			//TODO test code for last test functionality
			//temp = input.indexOf("["); 
			
			//while (temp>-1){
			
				
			if (input.substring(2, 3).equals("[")){
				delimiter = input.substring(3, input.indexOf("]"))+ "|\n|,";
				input = input.substring(input.indexOf("]")+1);
			//	temp = input.indexOf("[");
			}
			else{
			int delimiterIndex = input.indexOf("//")+2;
				delimiter = input.substring(delimiterIndex, delimiterIndex+1)+ "|\n|,";
				input = input.substring(input.indexOf("//")+2);
			}
			//}
		}
				
		
		String[] numbers = input.split(delimiter);
		
		if (isEmpty(input)){
			return 0;
		}
		for (int i=0;i<numbers.length;i++){
			if (numbers[i].equals("")){
				numbers[i]="0";
			}
		}
		
		if (input.length() == 1){
			return stringToInt(input);
		}
		else {
				return getSum(numbers);
			}
			
		
	}
	
	private void ifNewLineAtTheEndExp(String input){
		
		if (input.endsWith("\n")){
			throw new RuntimeException ("new line symbol cannot be at the end");
		}
	}
	
	
	private void findNegativeException(String [] numbers) {
		
		ArrayList <Integer> negatives = new ArrayList<Integer>(); 
		for (String current:numbers){
			if (stringToInt(current)<0){
				negatives.add(stringToInt(current));	
			}
		}
		if (negatives.size()>0){
			throw new RuntimeException ("Negative not allowed: " + negatives.toString());
		}
		
		
	}
	
	private int getSum(String [] numbers ) {
		
		findNegativeException(numbers);
				
		return	calculateValues(numbers); 		
	}
	
	private int calculateValues(String [] numbers){
		
		int sum = 0;
		for (String current:numbers){
			if (stringToInt(current) >= 1000){
				continue;
			}
			sum += stringToInt(current);
		}
		return sum;
	}
	
	private boolean isEmpty(String input) {
		
		return input.isEmpty();
	}
	
	private int stringToInt(String input){
		
		return Integer.parseInt(input);
	}
	
}

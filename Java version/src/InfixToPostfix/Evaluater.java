package InfixToPostfix;

public class Evaluater
{
	public static long result = 0;						//initialize the final result
	static Stack<Long> numberStack = new Stack();		//a stack for the operands
	
	public static void evaluate(String end) throws StackUnderFlowException	//constructor to evaluate a postfix eqn.
	{
		int index = 0;	//an index to count through the postfix string
		
		while(index < end.length())	//the loop to traverse the postfix string
		{
			//if it sees a number add it to the operand
			if(end.charAt(index) == '0' ||end.charAt(index) == '1' ||end.charAt(index) == '2' ||end.charAt(index) == '3' ||
			   end.charAt(index) == '4' ||end.charAt(index) == '5' ||end.charAt(index) == '6' ||end.charAt(index) == '7' ||
			   end.charAt(index) == '8' ||end.charAt(index) == '9')
			{
				long operand = 0;	//the operand starts at 0
				do
				{
					String token = end.charAt(index) + "";				//string token is needed to get the number
					operand = operand * 10 + Integer.parseInt(token);	//operand will increase according to the current number
					index++;											//if it still continues increase the radix
				}while(end.charAt(index) != ' ');						//by one until the number is complete 
				numberStack.push(operand);								//then push it onto the stack
			}
			
			else if(end.charAt(index) == ' ');		//when the loop approaches a space, ignore it!
			else 
			{
				char operator = end.charAt(index);	//if none of the above is what it is, it's an operator
				processOperation(operator);			//call method to process the operator
			}
			index++;	//increment the loop
		}
		System.out.println("Result: " + result);	//and finally print the final result
	}

	//this method evaluates the postfix expression thus far until the final result is found
	//that will be when we reach the end of the string
	public static long processOperation(char operator) throws StackUnderFlowException
	{
		long partialResult = 0;	//this variable is used for each partial result found by each operator
		
		if(operator == '+')	//operator was a plus sign
		{
			long operand1 = numberStack.getTop();	//get the top of the stack
			numberStack.pop();
			long operand2 = numberStack.getTop();	//do it again
			numberStack.pop();
			
			partialResult = operand2 + operand1;	//solve for the operands
			numberStack.push(partialResult);		//then push its result onto the stacck
		}
		//the same comments above is the same for the below operations
		else if(operator == '-')	//operator was a minus sign
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 - operand1;
			numberStack.push(partialResult);
		}
		else if(operator == '*')	//operator was a multiplication sign
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 * operand1;
			numberStack.push(partialResult);
		}
		else if(operator == '/')	//operator was a division sign
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 / operand1;
			numberStack.push(partialResult);
		}
		else if(operator == '%')	//operator was a mod operator
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 % operand1;
			numberStack.push(partialResult);
		}
		else if(operator == '^')	//operator was an exponential
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = (long) Math.pow(operand2, operand1);
			numberStack.push(partialResult);
		}
		else if(operator == 'Q')	//operator was a square root
		{
			long operand1 = numberStack.getTop();	//since Q is a unary operator we only need to pop one operand
			numberStack.pop();
			
			partialResult = (long) Math.sqrt(operand1);
			numberStack.push(partialResult);
		}
		else if(operator == 'C')	//operator was a cube root
		{
			long operand1 = numberStack.getTop();	//C is also a unary operator
			numberStack.pop();
			
			partialResult = (long) Math.pow(10, Math.log(operand1 / 3));
			numberStack.push(partialResult);
		}
		else if(operator == '>')	//operator was a right shift
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 >> operand1;
			numberStack.push(partialResult);
		}
		else if(operator == '<')	//operator was a left shift
		{
			long operand1 = numberStack.getTop();
			numberStack.pop();
			long operand2 = numberStack.getTop();
			numberStack.pop();
			
			partialResult = operand2 << operand1;
			numberStack.push(partialResult);
		}
		
		if(numberStack.isEmpty() != true)	//if the stack is empty
		{
			result = numberStack.getTop();	//get the top of the stack(should be the only number in the stack)
			return result;					//then return the result
		}
		else
			throw new StackUnderFlowException("Top attempted on an empty stack. Not enough operands.");	//the stack was empty
	}
	
	

}

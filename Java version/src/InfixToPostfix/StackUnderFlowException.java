package InfixToPostfix;
//exception class
//made to check for mostly empty stacks

public class StackUnderFlowException extends Exception	
{
	public StackUnderFlowException()
	{
		super();
	}
	public StackUnderFlowException(String message)
	{
		super(message);
	}
}

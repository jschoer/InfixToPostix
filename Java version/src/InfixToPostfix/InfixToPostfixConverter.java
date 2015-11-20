package InfixToPostfix;

//Code by Joshua Schoerverth
//EECS 2500
//This code is designed to take an infix equation and convert it to postfix.
//Then take the postfix equation and solve it.

import java.util.Scanner;
import java.lang.StringBuilder;
public class InfixToPostfixConverter
{
	public String infix;									//string for the infix equation
	public String end;										//the final postfix equation
	public StringBuilder postfix = new StringBuilder();	//string builder to get postfix equation into string end
	Stack<Character> operatorStack = new Stack();			//the operator stack
	
	public static void main(String args[]) throws StackUnderFlowException	//main
	{
		Object InfixToPostfixConverter = new InfixToPostfixConverter();	//calls the constructor, so variables won't be static
		
	}//end main
	
	public InfixToPostfixConverter() throws StackUnderFlowException	//constructor to process the code
	{
		System.out.println("Enter an Infix equation: ");		//prompt user for an infix equation
		Scanner input = new Scanner(System.in);					//get their input
		infix = input.nextLine();								//take the input and put it into the string infix
		Converter();											//converter method for infix to postfix
		System.out.println("Postfix: " + postfix.toString());	//print the postfix equation
		end = postfix.toString();								//String end is now equal to what the string builder made
		Evaluater.evaluate(end);								//evaluate the result, see class Evaluater
	}
	
	
	
	public String Converter() throws StackUnderFlowException	//method to convert an infix equation to postfix
	{	
		for(int i = 0; i < infix.length(); i++)	//loop to search through the whole string
		{
			char token = infix.charAt(i);			//save the character into a variable, token
			
			//search for an operator
			if(token == '+' || token == '-' || token == '*' || token == '/' || token == '^' ||
			   token == '%' || token == '<' || token == '>' || token == 'Q' || token == 'C')
			{
				postfix.append(' ');
				operatorStack.push(token);				//push the operator
														//problem with processOperator?
				//processOperator(token);				//if token was an operator send it to processOperator
			}
			else if(token == '(')					//check for a left parenthesis
			{
				operatorStack.push(token);			//if left parenthesis push it onto the stack
			}
			else if(token == ')')					//check for a right parenthesis
			{
				processRightParen();				//if right parenthesis call processRightParen
			}
			else if(token == ' ');
			else
				postfix.append(token);				//if its none of the above it is a number, add it to postfix
		}//end for loop
		
		while(!operatorStack.isEmpty())				//loop to add the last tokens on the stack to postfix
		{
			postfix.append(' ');					//add a space to postfix
			postfix.append(operatorStack.getTop()); //add the operator to postfix
			operatorStack.pop();					//pop the operator
		}
		return postfix.toString();					//return the postfix expression
	}//end converter
	
	
	public void processRightParen() throws StackUnderFlowException	//algorithm for dealing with right parenthesis 
	{
			while(operatorStack.getTop() != '(')	//loop to pop all operators until a left parenthesis
			{
				postfix.append(' ');					//add a space
				postfix.append(operatorStack.getTop()); //add the operator
				operatorStack.pop();					//pop the operator
			}
		operatorStack.pop(); 							//pop left parenthesis when loop is done
	}//end processRightParen
	
}//end class

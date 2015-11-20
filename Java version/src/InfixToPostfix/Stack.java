package InfixToPostfix;

public class Stack<T> 
{
	public LLNode top;	//makes top node
	
	/*public Stack()	//set the  and top
	{
		top = null;	//not needed?
	}
	*/
	public void push(T element)	//push the element to the top
	{
		LLNode<T> newNode = new LLNode<T>(element);	//makes a new node
		
		newNode.setInfo(element);	//sets the new nodes info
		newNode.setLink(top);		//sets its link
		
		top = newNode;				//top is now the new node
	}
	
	public void pop() throws StackUnderFlowException	//take the top of the stack and push it
	{
		if (isEmpty() != true)	//if stack is not empty
		{
			top = top.getLink();	//get the link
		}
		else
			throw new StackUnderFlowException("Pop attempted on an empty stack.");	//if the stack is empty stop
	}
	
	public T getTop() throws StackUnderFlowException	//return the top node
	{
		if(!isEmpty())
		{
			return (T) top.getInfo();	//would not get info properly without the cast
		}
		else 
			throw new StackUnderFlowException("Top attempted on an empty stack.");	//if stack is empty stop
	}

	public boolean isEmpty()	//checks to see if the stack is empty
	{
		if(top == null)	//if top is null
		{
			return true;	//that means stack is empty
		}
		else return false;	//if not it has nodes
	}
	
}//end Stack





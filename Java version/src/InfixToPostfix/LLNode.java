package InfixToPostfix;
//class to set the nodes
public class LLNode<T>
{
	private T info;
	private LLNode link;
	
	public LLNode(T info)	//makes the frst node
	{
		this.info = info;
		link = null;
	}
	
	public void setInfo(T info)	//sets the elemnt from the stack as the info for the node
	{
		this.info = info;
	}
	
	public T getInfo()	//retrieves the info from the top stack
	{
		return info;
	}
	
	public void setLink(LLNode<T> link)	//sets link to the node
	{
		this.link = link;
	}
	
	public LLNode<T> getLink()	//gets the link of the top node
	{
		return link;
	}
}

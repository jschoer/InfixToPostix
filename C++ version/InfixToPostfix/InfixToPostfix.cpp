// Joshua Schoerverth
// EECS 2510 Project 2
// Infix to Postfix and evaluater, converts an infix expression to postfix,
// then evaluates the postfix expression using a binary tree.
// Infix expression is taken from the Infix.txt; only evaluates 1 expression.
// The postfix expression and answer is put into the Postfix.txt.
// All three also show up on the console for faster viewing.

#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <stack>
using namespace std;

struct node				//a struct to build each node needed for the btree
{
	char info;			//each token of the postfix expression is placed here
	node *left;			//the left branch
	node *right;		//the right branch
}Node;
node *root = new node;	//the root of this tree if needed
node *temp = new node;	//a temporary node to traverse said tree

int evaluate(node* temp);	//method to evaluate the tree!

int main()
{
	ifstream file;							//create ifstream for a file
	file.open("infix.txt", ios::binary);	//open the process text file
	string infix;		//string to hold the infix expression
	string postfix;		//string to hold the postfix expression

	if (file.is_open())
	{
		getline(file,infix);					//get the infix from file
	}
	else
	{
		cout << "Could not open input file.";
	}
	file.close();	//close file!

	stack<char> mystack;							//make my stack for conversion
	for (int i = 0; i <= infix.length(); i++)	
	{
		char token = infix[i];		//store char into a variable
		cout << token;
		//if it is a operand we add it to the stack
		if (token == '+' || token == '-' || token == '/' || token == '*')
		{
			postfix += ' ';			//add a space to the postfix
			mystack.push(token);	//push the operand
		}//same with the left paren
		else if (token == '(')
		{
			mystack.push(token);	//push the left paren
		}
		//when we reach the right paren we pop until we reach the left paren
		else if (token == ')')
		{
			while (mystack.top() != '(')
			{
				postfix += ' ';				//add a space before
				postfix += mystack.top();	//adding the operand
				mystack.pop();				//then remove it from the stack
			}
			mystack.pop();					//finally remove the left paren
		}
		else if (token == ' ')
		{
			continue;	//ignore spaces
		}
		else
		{
			postfix += token;	//add number to postfix
		}
	}

	//make a binary tree for the postfix epression
	stack<node> Btree;
	for (int i = 0; i <= postfix.length(); i++)
	{
		char token = postfix[i];
		if (token == '0' || token == '1' || token == '2' || token == '3' || token == '4' ||
			token == '5' || token == '6' || token == '7' || token == '8' || token == '9')
		{
			//make a node of the constants
			Node.info = token;	//apply the number
			Node.left = NULL;	//left branch SHOULD BE NOTHING
			Node.right = NULL;	//same with right
			Btree.push(Node);	//push it to the stack
		}
		else if (token == '+' || token == '-' || token == '*' || token == '/')
		{
			//make node for operators
			Node.info = token;					//apply the operator
			Node.right = new node(Btree.top());	//right branch
			Btree.pop();						//remove the data just added
			Node.left = new node(Btree.top());	//left branch
			Btree.pop();						//remove here as well
			Btree.push(Node);					//push the newly built tree
		}
		else if (token == ' ')
		{
			continue;	//ignore spaces obviously
		}
	}

    //evaluate
	int total = 0;
	root = new node(Btree.top());		//set the root to our tree waiting in the stack
	Btree.pop();						//then pop it, we don't need the stack anymore
	temp = root;						//set the temp node to the root
	total = evaluate(temp) - '0';		//get the evaluated total

	cout << "\n" << postfix << "\n";	//post postfix to console
	cout << total << "\n";				//and its value

	//output answers
	ofstream outfile;
	outfile.open("Postfix.txt");
	if (outfile.is_open())
	{
		//puting the postfix expression into output file
		string value = to_string(total);
		outfile << postfix << "= " << value;
	}
	else
	{
		cout << "Could not open output file.";
	}
	outfile.close();
	system("pause");

	return 0;
}

int evaluate(node* temp)
{
	if (temp->left == NULL && temp->right == NULL)
	{
		return temp->info;		//we found a number return it to respected l or r
	}
	else
	{
		int val = 0;			//the running value of each branch until the top
		int l;					//a variable to hold numbers on the left
		int r;					//the one that holds numbers on the right
		switch (temp->info) 
		{
		case '+':						//if the node was a plus
			l = evaluate(temp->left);	//get the left number or keep looking
			r = evaluate(temp->right);	//get the right number or keep looking
			val = (l-'0') + (r-'0');	//evaluate the new value
			break;
		case '-':						//if the node was a minus
			l = evaluate(temp->left);
			r = evaluate(temp->right);
			val = (l - '0') - (r - '0');
			break;
		case '*':						//if the node was multiply
			l = evaluate(temp->left);
			r = evaluate(temp->right);
			val = (l - '0') * (r - '0');
			break;
		case '/':						//if the node was divide
			l = evaluate(temp->left);
			r = evaluate(temp->right);
			val = (l - '0') / (r - '0');
			break;
		}
		return val + '0';		//return the new value
	}
}

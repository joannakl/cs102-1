import java.util.Scanner;

//Jeff Ma
public class PostfixCalculator {
	public static double calclate(Converter infix) 
	{
		LinkedStack<Double> nums = new LinkedStack<>();
		Scanner scan = new Scanner(infix.getPostFix());
		String s = null;
		while (scan.hasNext()) 
		{
			s = scan.next();
			if(s.equals("+")) 
			{
				nums.push(nums.pop() + nums.pop());
				continue;
			}
			if(s.equals("-")) 
			{
				nums.push(-(nums.pop() - nums.pop()));
				continue;
			}
			if(s.equals("*")) 
			{
				nums.push(nums.pop() * nums.pop());
				continue;
			}
			if(s.equals("/")) 
			{
				nums.push(1/(nums.pop() / nums.pop()));
				continue;
			}
			if(s.equals("^")) 
			{
				Double n2 = nums.pop();
				Double n1 = nums.pop();
				nums.push(Math.pow(n1, n2));
				continue;
			}
			nums.push(Double.valueOf(s));
		}
		scan.close();	
		return nums.pop();
	}
	
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		System.out.println("type your infix expression or quit to quit:");
		String s = null;
		while (!(s=in.nextLine()).equals("quit")) {
			Converter postfix = new Converter(s);
			System.out.println("converted to postfix: " + postfix.getPostFix());
			System.out.println("answer is " + calclate(postfix));
			System.out.println("type your infix expression or quit to quit:");
		}		
		System.out.println("Good Bye");
		in.close();
	}
}

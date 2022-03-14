import java.util.List;

//Jeff Ma
public class Converter {
	private String postFix;
	private String infix;
	
	public Converter(String infix) {
		this.infix = infix;
		this.postFix = toPostFix(this.infix);
	}
	
	public String getPostFix() {
		return postFix;
	}

	public static String toPostFix(String infix) {	
		List<String> in = ParserHelper.parse(infix.toCharArray());
		LinkedStack<String> operator = new LinkedStack<>();
		StringBuilder sb = new StringBuilder();
		for (String r: in ) {
			if(r.equals("(")) {
				operator.push(r);
				continue;
			}
			
			if (r.equals(")")) {
				while (!operator.isEmpty() && !operator.top().equals("(")){
					sb.append(operator.pop());
					sb.append(" ");
				}
				operator.pop();
				continue;
			}
			
			if (r.equals("^")) {
				operator.push(r);
				continue;
			}
			
			//pop / or * or ^
			if (r.equals("/") || r.equals("*")) {
				while ((!operator.isEmpty() && !operator.top().equals("(")) && (operator.top().equals("/") || operator.top().equals("*") || operator.top().equals("^"))){
					sb.append(operator.pop());
					sb.append(" ");
				}
				operator.push(r);
				continue;
			}
			
			//pop all others
			if (r.equals("+") || r.equals("-")) {
				while (!operator.isEmpty() && !operator.top().equals("(")){
					sb.append(operator.pop());
					sb.append(" ");
				}
				operator.push(r);
				continue;
			}
			
			//append operands
			sb.append(r);
			sb.append(" ");
		}
		while ( !operator.isEmpty()){
			sb.append(operator.pop());
			sb.append(" ");
		}
		
		return sb.toString();
	}
	
}

import java.util.regex.Pattern;

public class Regex {

	
	public static void main(String args[]){
		//short:	signed
		String regex="(\\-[1-3]{0,1}[0-2]{0,1}[0-7]{0,1}[0-6]{0,1}[0-8]{0,1})|([1-3]{0,1}[0-2]{0,1}[0-7]{0,1}[0-6]{0,1}[0-7]{0,1})";
		String uregex="[1-6]{0,1}[0-5]{0,2}[0-3]{0,1}[0-5]{0,1}";//65535
		String tf="true|false";
		
		String value="falsee";
		System.out.println(
				Pattern.compile(tf).matcher(value).matches()
				);
	}
}

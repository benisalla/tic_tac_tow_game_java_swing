package game_X_O;

import java.util.ArrayList;

public class test {
	//---------------------------------------------------------------------
	static boolean swap(String[]str,String a,String b, String c)
	{
		int test = 0;
		for(String i : str)
		{
			if(i==a || i==b || i==c)
				test++;
			System.out.println(i);
		}
		System.out.println("---------|"+str[0]+str[1]+str[2]+"||"+a+b+c+"|test = "+test);
		if(test == 3)
			return true;
		else
			return false;
	}
	
	static boolean IsWinner(ArrayList<String> Player)
	{
		String str[] = new String[3];
		if(Player.size()>=3)
		{
			str[2] = Player.get(Player.size()-1);
			str[1] = Player.get(Player.size()-2);
			str[0] = Player.get(Player.size()-3);
			if(swap(str,"1","2","3"))
				return true;
			else if(swap(str,"4","5","6"))
				return true;
			else if(swap(str,"7","8","9"))
				return true;
			else if(swap(str,"1","4","7"))
				return true;
			else if(swap(str,"2","5","8"))
				return true;
			else if(swap(str,"3","6","9"))
				return true;
			else if(swap(str,"1","5","9"))
				return true;
			else if(swap(str,"3","5","7"))
				return true;
			else
				return false;
		}
		return false;
	}
	//----------------------------------------------------------------
	
	public static void main(String[] args) {
		ArrayList<String> str = new ArrayList<String>();
		str.add("9");str.add("6");str.add("1");str.add("2");str.add("3");
		System.out.println(IsWinner(str));
	}
}

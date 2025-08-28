import java.util.Scanner;

import org.testng.annotations.Test;

public class Natwest

{
	@Test(priority = 16)

	public void palindromeNumber()

	{
		

		int num = 454;
		int rev = 0;
		int temp = num;
	

		while (num > 0)

		{
			
			rev = rev * 10 + num%10;
			num = num / 10;
		}
		
		if(temp==rev)
			
		{
			System.out.println(Number is Palindrome);
		}
		
		
		else
			
		{
			System.out.println(Number is not palindrome);
		}
	}
}

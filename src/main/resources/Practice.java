package Java_Introduction;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

public class Programs

{
	@Test(priority = 1)

	public void factorialProgram()

	{
		int n = 2;
		int factorial = 1;
		for (int i = 1; i <= n; i++)

		{
			factorial = factorial * i;// Multiply current value with loop variable

		}

		System.out.println(factorial);

	}

	@Test(priority = 2)

	public void compressedString()

	{
		String str = "sharanabasappa";
		String result = "";
		int count = 0;

		for (int i = 0; i < str.length(); i++)// This loop checks each character and compares it with the previous one.

		{
			if (i == 0)

			{

				// Appends the first character to result
				result = result + str.charAt(i);

			}

			else if (str.charAt(i) == str.charAt(i - 1))

			{
				// increments the count when the character matches the one before it
				count++;
			}

			else if (str.charAt(i) != str.charAt(i - 1))

			{
				// Appends count and the new character to the result,then resets count
				result = result + count + str.charAt(i);
				count = 1;
			}

			if (i == str.length() - 1)

			{
				result = result + count;// Add the final count to result once the loop ends
			}

		}

		System.out.println(result);

	}

	@Test(priority = 3)

	public void compressedString1()

	{
		String str = "aabbbca";
		String result = "";
		int count = 1;

		for (int i = 1; i < str.length(); i++)

		{
			if (str.charAt(i) == str.charAt(i - 1))

			{
				
				count++;// increments the count when the character matches the one before it
			}

			else

			{
				
				result = result + str.charAt(i - 1) + count;// append the previous character and its count to result ,then reset the count
				count = 1;
			}

		}
		

		result = result + str.charAt(str.length() - 1) + count;// This line is appending the last character of the string

		System.out.println(result);

	}

	@Test(priority = 4)

	public void fibonanciSeries()

	{
		int n = 10, n1 = 0, n2 = 1;

		for (int i = 1; i <= n; i++)

		{
			System.out.print(n1 + "  ");
			int n3 = n1 + n2;
			n1 = n2;
			n2 = n3;

		}

	}

	@Test(priority = 5)

	public void vowelsAndConsonants()

	{
		String str = " We are learning java";
		int count = 0;
		int vCount = 0;
		int cCount = 0;

		str = str.toLowerCase();

		for (int i = 0; i < str.length(); i++)

		{

			if (str.charAt(i) != ' ')
				count++;

			if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
					|| str.charAt(i) == 'u')

			{
				vCount++;
			}

			else if (str.charAt(i) > 'a' && str.charAt(i) < 'z')

			{
				cCount++;
			}

		}

		System.out.println("Total characters (excluding spaces): " + count);
		System.out.println("Vowels: " + vCount);
		System.out.println("Consonants: " + cCount);

	}

	@Test(priority = 5)

	public void uniqueNumber()

	{
		List<Integer> val = Arrays.asList(1, 2, 2, 5, 8, 2, 9, 28, 53, 7, 9, 8, 4);
		val.stream().distinct().sorted().forEach(s -> System.out.print(s));

		List<Integer> li = val.stream().distinct().sorted().collect(Collectors.toList());

		System.out.println(li.get(4));

	}

	@Test(priority = 6)

	public void palindrome()

	{
		String str = "Shara";

		for (int i = str.length() - 1; i >= 0; i--)

		{
			System.out.print(str.charAt(i));
		}

		if (str.equals("madam"))

		{
			System.out.println("The String is palindrome");
		}

		else

		{
			System.out.println("The String is not palindrome");
		}
	}

	@Test(priority = 7)

	public void min_Number()

	{
		int[][] a = { { 2, 4, 7 }, { 8, 9, 7 }, { 7, 9, 10 } };
		int min = a[0][0];

		for (int i = 0; i < 3; i++)

		{
			for (int j = 0; j < 3; j++)

			{
				if (a[i][j] < min)
					min = a[i][j];
			}
		}

		System.out.println("The minimun number of above matrix: " + min);
	}

	@Test(priority = 8)

	public void max_Number()

	{
		int[][] a = { { 2, 4, 7 }, { 8, 9, 7 }, { 7, 9, 10 } };

		int max = a[0][0];

		for (int i = 0; i < 3; i++)

		{
			for (int j = 0; j < 3; j++)

			{
				if (a[i][j] > max)

				{
					max = a[i][j];
				}
			}
		}

		System.out.println("The maximum  number of above matrix: " + max);

	}

	@Test(priority = 9)

	public void largest_Number()

	{
		int[] a = { 2, 4, 7, 8, 9, 7, 9, 10 };
		int largest = a[0];

		for (int i = 0; i < a.length; i++)

		{
			if (a[i] > largest)

			{
				largest = a[i];
			}
		}

		System.out.println("The largest number is : " + largest);
	}

	@Test(priority = 10)

	public void smallest_Number()

	{
		int[] a = { 2, 4, 7, 8, 9, 7, 9, 10 };

		int smallest = a[0];

		for (int i = 0; i < a.length; i++)

		{
			if (a[i] < smallest)

				smallest = a[i];
		}

		System.out.println("The smallest number is : " + smallest);

	}

	@Test(priority = 11)

	public void pyramid_Triangle_Example1()

	{
		int k = 1;
		for (int i = 0; i <= 4; i++)

		{
			for (int j = 1; j <= 4 - i; j++)

			{
				System.out.print(k);
				System.out.print("\t");
				k++;
			}
			System.out.println(" ");
		}

	}

	@Test(priority = 12)

	public void pyramid_Triangle_Example2()

	{
		int k = 1;
		for (int i = 1; i < 5; i++)

		{
			for (int j = 1; j <= i; j++)

			{
				System.out.print(k);
				System.out.print("\t");
				k++;
			}
			System.out.println(" ");
		}
	}

	@Test(priority = 13)

	public void RemoveDuplicates()

	{

		int[] a = { 2, 4, 7, 8, 9, 7, 9, 10, 2, 7 };

		HashSet<Integer> hs = new HashSet<Integer>();

		for (int num : a)

		{
			hs.add(num);
		}

		System.out.println(hs);
	}

	@Test(priority = 14)

	public void secondLargestElementArray()

	{
		int[] a = { 2, 4, 7, 8, 9, 7, 9, 10, 2, 7 };

		int first = a[0];
		int second = a[0];

		for (int i = 0; i < a.length; i++)

		{
			if (a[i] > first)

			{
				second = first;
				first = a[i];
			}

			else if (a[i] > second)

			{
				second = a[i];
			}
		}

		System.out.println("The Second largest elemnt is : " + second);
	}

	@Test(priority = 15)

	public void convertUppertoLowerCaseLetter()

	{
		StringBuffer str = new StringBuffer("sHaRaNaBaSaPpA");
		for (int i = 0; i < str.length(); i++)

		{
			Character c = str.charAt(i);

			if (Character.isLowerCase(c))

			{
				str.setCharAt(i, Character.toUpperCase(c));
			}

			else

			{
				str.setCharAt(i, Character.toLowerCase(c));
			}
		}

		System.out.println(str);
	}

	@Test(priority = 16)

	public void palindromeNumber()

	{
		int num = 16461;
		int rev = 0;

		while (num != 0)

		{
			rev = rev * 10 + num % 10;
			num = num / 10;
		}

		System.out.println(rev);

	}
	
	
	

}

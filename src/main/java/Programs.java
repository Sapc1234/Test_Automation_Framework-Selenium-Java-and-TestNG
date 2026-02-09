
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.annotations.Test;

public class Programs

{
	@Test(priority = 1)

	public void factorialProgram()

	{
		int n = 10;
		int factorial = 1;
		for (int i = 1; i <= n; i++)

		{
			factorial = factorial * i;// Multiply current value with loop variable

		}

		System.out.println("Factorial of " + n + " is: " + factorial);

	}

	@Test(priority = 2, enabled = false)

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
		System.out.println("compressedString1compressedString1");
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

				result = result + str.charAt(i - 1) + count;// append the previous character and its count to result
															// ,then reset the count
				count = 1;
			}

		}

		result = result + str.charAt(str.length() - 1) + count;// This line is appending the last character of the
																// string

		System.out.println(result);

	}

	@Test(priority = 4)

	public void fibonanciSeries()

	{
		int n = 10;
		int n1 = 0;// first Fibonacci number
		int n2 = 1;// second Fibonacci number.

		for (int i = 1; i <= n; i++)

		{
			System.out.print(n1 + "  ");// prints the current value of n1
			int n3 = n1 + n2;// sum of the previous two numbers.
			n1 = n2;// Updates the n1 and n2
			n2 = n3;

		}

	}

	@Test(priority = 5)

	public void vowelsAndConsonants()

	{
		String str = "We are learning java";
		int count = 0;
		int vCount = 0;
		int cCount = 0;

		str = str.toLowerCase();// convert entire string to lowercase

		for (int i = 0; i < str.length(); i++)

		{

			if (str.charAt(i) != ' ')
				count++;// count all characters except spaces

			if (str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o'
					|| str.charAt(i) == 'u')

			{
				vCount++;
			}

			else if (str.charAt(i) > 'a' && str.charAt(i) < 'z')

			// If the character is between a and z (alphabetic) but not a vowel → increment
			// cCount.
			{
				cCount++;
			}

		}

		System.out.println("Total characters (excluding spaces): " + count);
		System.out.println("Vowels: " + vCount);
		System.out.println("Consonants: " + cCount);

	}

	@Test(priority = 6)

	public void uniqueNumber()

	{

		List<Integer> val = Arrays.asList(1, 2, 2, 5, 8, 2, 9, 28, 53, 7, 9, 8, 4);
		val.stream().distinct().sorted().forEach(s -> System.out.print(s));

		List<Integer> li = val.stream().distinct().sorted().collect(Collectors.toList());
		System.out.println(li.get(4));

		int[] ab = { 2, 4, 7, 8, 9, 7, 9, 10, 2, 7 };

		// Using Java 8 Streams to get unique numbers
		List<Integer> uniqueNumbers = Arrays.stream(ab) // IntStream
				.distinct() // remove duplicates
				.boxed() // convert int → Integer
				.collect(Collectors.toList());
		Collections.reverse(uniqueNumbers);

		System.out.println(uniqueNumbers);

		System.out.println(uniqueNumbers);

	}

	@Test(priority = 7)

	public void palindromeString()

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

	@Test(priority = 8)

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

	@Test(priority = 9)

	public void max_Number()

	{
		int[][] a = { { 2, 4, 7 }, { 8, 9, 7 }, { 7, 9, 10 } };

		int max = a[0][0];

		for (int i = 0; i < a.length; i++)

		{
			for (int j = 0; j < a[i].length; j++)
			// All columns in each row are covered.

			{
				if (a[i][j] > max)

				{
					max = a[i][j];
				}
			}
		}

		System.out.println("The maximum  number of above matrix: " + max);

	}

	@Test(priority = 10)

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

	@Test(priority = 11)

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

	@Test(priority = 12)

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

	@Test(priority = 13)

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

	@Test(priority = 14)

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

	@Test(priority = 15)

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

	@Test(priority = 16)

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

	@Test(priority = 17)

	public void palindromeNumber()

	{

		int num = 4525;
		int rev = 0;
		int temp = num;

		while (num > 0)

		{

			rev = rev * 10 + num % 10;
			num = num / 10;
		}
		
		System.out.println(rev);

		if (temp == rev)

		{
			System.out.println("Number is Palindrome");
		}

		else

		{
			System.out.println("Number is not palindrome");
		}
	}

	@Test(priority = 18)

	public void patternStar1()

	{
		int n = 5;

		for (int i = 1; i <= n; i++)

		{
			for (int j = 1; j <= i; j++)

			{
				System.out.print("*");
			}

			System.out.println(" ");
		}
	}

	@Test(priority = 19)

	public void patternStar2()

	{
		int n = 5;

		for (int i = 5; i >= 1; i--)

		{
			for (int j = 1; j <= i; j++)

			{
				System.out.print("*");
			}

			System.out.println(" ");
		}
	}

	@Test(priority = 20)

	public void patternStar3()

	{
		int n = 5;

		for (int i = 1; i <= n; i++)

		{
			for (int j = 1; j <= n; j++)

			{
				if (i == 1 || i == n || j == 1 || j == n)

				{
					System.out.print("*");
				}

				else

				{
					System.out.print(" ");
				}

			}

			System.out.println(" ");
		}
	}

	@Test(priority = 21)

	public void uniqueElementsCounter()

	{
		// Initialize the list with given elements
		List<Integer> l1 = Arrays.asList(1, 1, 2, 5, 2, 9, 8, 2, 4, 7, 5);

		// Get the unique elements using stream and distinct()
		l1.stream().distinct().sorted().collect(Collectors.toList()).forEach(s -> System.out.println(s));

		// Count occurrences of each elements using stream and groupingBy

		// Groups elements of the stream by themselves (i.e., each unique integer
		// becomes a key) and
		// For each group, it counts how many times that element appears in the list.

		Map<Integer, Long> dic1 = l1.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		System.out.println(dic1);

		System.out.println("Using Normal Java");

		// Initialize an empty list and map to hold unique elements and count

		List<Integer> uni = new ArrayList<Integer>();
		Map<Integer, Integer> dic = new HashMap<Integer, Integer>();

		for (int i : l1)

		{
			if (!uni.contains(i))

			// if the element is not already in the uni ,add it and initialize the count in
			// dic

			{
				uni.add(i);
				dic.put(i, 1);
			}

			else

			{
				// if the elements is already in uni ,increments the count in dic
				dic.put(i, dic.get(i) + 1);
			}
		}

		System.out.println(uni);
		System.out.println(dic);

	}
	
	@Test(priority = 22)

	public void PrimeNumbers()

	{
		int n=50;
		
		for(int i=2;i<=n;i++)
			
		{
			boolean isPrime = true;
			
			for(int j=2;j<=i/2;j++)
				
			{
				if(i%j==0)
					
				{
					isPrime = false;
				}
			}
			
			if(isPrime)
				
			{
				System.out.println(i);
			}
		}
	}
	
	@Test(priority = 23)
	
	public void Reverse12()
	
	{
		
		String str = "I LOVE YOU";
		
		String[] words = str.split(" ");//split the string by spaces into an array
		
		StringBuilder reversed = new StringBuilder();//Use a string builder to efficiently build the result
		
		for(int i = words.length-1;i>=0;i--)//loop through the array from the end to beginning
			
		{
			reversed.append(words[i]);
			
			if(i>0)
				
			{
				reversed.append(" ");//add the space between words,but not after the last one
			}
		}
		
		System.out.println(reversed.toString());
		
		//-----------------------------------------
		
		//Remove the space from the string
		
		
		
	}
	
	
	@Test(priority = 24)
	
	public void Reversed21()
	
	{
		String str = "I LOVE YOU";
		String withoutSapce =  str.replace(" ", "");
		String reversed  = new StringBuilder(withoutSapce).reverse().toString();
		System.out.println(reversed);
	}
	
	@Test(priority = 25)
	
	public void splitTwoStrings()
	
	{
		String str = "abc-2019,xyz";
		
		String[] formatedString =  str.split("[-|,]");
		
		for(int i=0;i<formatedString.length;i++)
			
		{
			System.out.println(formatedString[i]);
		}
	}
	
	
	

}

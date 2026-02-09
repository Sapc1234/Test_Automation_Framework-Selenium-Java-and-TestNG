
public class Assignment

{

	public static void main(String[] args)

	{
			int n=50;
			
			for(int num =2;num<=n;num++)
				
			{
				boolean isPrime = true;
				
				for(int i=2;i<=num/2;i++)
					
				{
					
					if(num %i==0)
						
					{
						 isPrime = false;
					}
				}
				
				if (isPrime) {
	                System.out.print(num + " ");
	            }


			}
			
			
			int n1 = 50;
			
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
					System.out.print(i+" ");
				}
			}
				
				
	}

}

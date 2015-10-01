import java.util.*;

public class RandomDist 
{
	private float[][] ranDist;
	private int dimension;
	
	RandomDist(int size, int d)
	{
		dimension = d;
		ranDist = new float[size][d+1];
	    
		getDist();
	}
	
	// method to construct a uniform random distribution of real numbers
	private float[][] getDist()
	{
		Random randomN = new Random();
		for (int i = 0; i < ranDist.length; i++)
		{
			for (int j = 0; j <= dimension; j++)
			{
				ranDist[i][j] =  (randomN.nextFloat() - randomN.nextFloat())*3.0f;
			}
			ranDist[i][dimension] = (float)  (Math.pow((1 - ranDist[i][0]), 2) + 
					100*(Math.pow((ranDist[i][1] - Math.pow(ranDist[i][0], 2) ), 2) )
					);
		}
		
		return ranDist;
	}
	
	// method to display distribution 
	void printDist()
	{
		for (int i = 0; i < ranDist.length; i++)
		{
			for (int j = 0; j < dimension+1; j++)
			{
				System.out.print(ranDist[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	//calculate output for Rosenbrock function which takes in dimension and a vector x of inputs 
	public float Rosenbrock(int n, float [] x)
	{
		float output = 0;
		
		return output;
	}
}

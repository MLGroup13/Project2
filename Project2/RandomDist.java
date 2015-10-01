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
		
		/*for (int i = 0; i < ranDist.length; i++)
		{
			ranDist[i] =  (randomN.nextFloat() - randomN.nextFloat())*5.0f;
			
		}*/
		
		return ranDist;
	}
	
	// method to display distribution 
	void printDist()
	{
		for (int i = 0; i < ranDist.length; i++)
		{
			System.out.println(ranDist[i]);
		}
	}
	
	//calculate output for Rosenbrock function which takes in dimension and a vector x of inputs 
	public float Rosenbrock(int n, float [] x)
	{
		float output = 0;
		
		return output;
	}
}

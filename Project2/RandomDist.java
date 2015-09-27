import java.util.*;

public class RandomDist 
{
	private float[] ranDist;
	private float min;
	private float max;
	
	RandomDist(int size)
	{
		ranDist = new float[size];
	    min = -5.0f;
		max = 5.0f;
		getDist();
	}
	
	// method to construct a uniform random distribution of real numbers
	float[] getDist()
	{
		Random randomN = new Random();
		for (int i = 0; i < ranDist.length; i++)
		{
			ranDist[i] =  randomN.nextFloat();
		}
		
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
}

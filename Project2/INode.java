package mlproject2;
import java.util.Random;

public class INode 
{
	private float input;
	private float[] weight;
	
	// create random generator object
	Random randomN = new Random();
	
	public INode(float in)
	{
		input = in;
	}
	
	// initialize the weights to a random real between 0.0 and 1.0
	public void initWeight(int w)
	{
		weight = new float[w];
		
		for(int i = 0; i < w; i++)
		{
			weight[i] = randomN.nextFloat() - randomN.nextFloat();
			System.out.print("w" + i + "=" + weight[i]);
		}
	}
	
	public float[] getWeight()
	{
		return weight;
	}
	
	public void setWeight(float[] w)
	{
		weight = w;
		for(int i = 0; i < w.length; i++)
		{
			weight[i] = (float) weight[i];
			System.out.print("w" + i + " = " + weight[i]);
		}
	}
	
	public float getInput()
	{
		return input;
	}
	
	public void setInput(float in)
	{
		input = in;
	}
}

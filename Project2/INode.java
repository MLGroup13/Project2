import java.util.Random;

public class INode 
{
	private float input;
	private float[] weight;
	
	public INode(float in)
	{
		input = in;
	}
	
	// initialize the weights to a random real between 0.0 and 1.0
	public void setWeight(int w)
	{
		weight = new float[w];
		Random randomN = new Random();
		for(int i = 0; i < w; i++)
		{
			weight[i] = randomN.nextFloat();
			System.out.print("w" + i + "=" + weight[i]);
		}
	}
	
	public float getInput()
	{
		return input;
	}
}

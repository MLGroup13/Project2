
public class INode 
{
	private float input;
	private float[] weight;
	
<<<<<<< HEAD
	INode(float in, float[] w)
=======
	// create random generator object
	Random randomN = new Random();
	
	public INode(float in)
>>>>>>> James_Branch
	{
		input = in;
		weight = w;
	}
	
<<<<<<< HEAD
	void setWeight(float[] w)
	{
		
=======
	
	
	// initialize the weights to a random real between 0.0 and 1.0
	public void initWeight(int w)
	{
		weight = new float[w];
		
		for(int i = 0; i < w; i++)
		{
			weight[i] = randomN.nextFloat();
			System.out.print("w" + i + "=" + weight[i]);
		}
>>>>>>> James_Branch
	}
	
	
	
	public float[] getWeight()
	{
		return weight;
	}
	
	public float getInput()
	{
		return input;
	}
}

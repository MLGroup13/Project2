import java.util.Random;

public class ONode 
{
	private float output;
	private float bias;
	
	Random randomN = new Random();
	
	public ONode()
	{
		
	}
	
	// initialize the bias to a random real between 0.0 and 1.0
	public void initBias()
	{
		bias = randomN.nextFloat();
	}
		
	public float getBias()
	{
		return bias;
	}
	
	public void setOutput(float out)
	{
		output = out;
	}
	
	public float getOutput()
	{
		return output;
	}
	
	public void calcOutput()
	{
		
	}
}

import java.util.Random;

public class ONode 
{
	private float output;
<<<<<<< HEAD
	
		/**
	 *	An activation function using a sigmoid function. 
	 *
	 */
	public float sigActivate(){
		float e = (float) Math.E;
        float t = (float) Math.pow(e, -output);
        float activate = 1 / (1 + t);
		return activate;
	}
	
	/**
	 *  An activation function using a hyperbolic tangent.
	 */
	public float tanhActivate(){
        float activate = (float) Math.tanh(output);
        return activate;
    }
=======
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
>>>>>>> James_Branch
}

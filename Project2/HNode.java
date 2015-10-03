import java.util.Random;

public class HNode 
{
	private float activation;
	private float bias;
	private float[] weight;
	
	// create random generator object
		Random randomN = new Random();
		
		public HNode()
		{
			
		}
		
		// initialize the weights to a random real between 0.0 and 1.0
		public void initWeight(int w)
		{
			weight = new float[w];
			
			for(int i = 0; i < w; i++)
			{
				weight[i] = randomN.nextFloat();
				System.out.print("w" + i + "=" + weight[i]);
			}
		}
		
		public float[] getWeight()
		{
			return weight;
		}
		
		public float getActivation()
		{
			return activation;
		}	
		
		public void setActivation(float a)
		{
			activation = a;
		}
}

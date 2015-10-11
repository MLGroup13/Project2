
public class PerceptronLearning 
{
	private float [][] examples;
	private Network network;
	private INode[] inputN;
	
	public PerceptronLearning(float [][] ex, Network net, float err)
	{
		network = net;
		examples = ex;
		inputN = network.getINodes();
	}
	
	public Network Learn(float learn_rate)
	{
		for (int i = 0; i < examples.length; i++)
		{	
			for (int j = 0; j < examples[i].length-1; j++)
			{
				float[] w = inputN[j].getWeight();
				
				for(int k=0; k < w.length; k++)
				{
					float in = w[k]*examples[i][k];
					float Err = examples[i][examples[i].length-1] - sigActivate(in);
					w[k] = w[k] + learn_rate * Err * sigDerive(in) * examples[i][k];
				}
				network.setWeights(inputN[j], w);
			}	
		}
		
		return network;
	}
	
	// sigmoid activation function 
	public float sigActivate(float x){
		float e = (float) Math.E;
        float t = (float) Math.pow(e, -x);
        float activate = 1 / (1 + t);
		return activate;
	}
	
	// sigmoid derivative 
	public float sigDerive(float x)
	{
		float e = (float) Math.E;
		float numerator = (float) Math.pow(e, x);
		float denomenator = (float) Math.pow((1 + Math.pow(e, x)), 2);
		return numerator / denomenator;
	}
	
	public void printExamples()
	{
		for (int i = 0; i < examples.length; i++)
		{
			for(int j = 0; j < examples[i].length; j++)
			{
				System.out.print(examples[i][j] + ",");
			}
			System.out.println();
		}
	}
}

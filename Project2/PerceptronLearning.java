
public class PerceptronLearning 
{
	private float [][] examples;
	private Network network;
	private float goalError;
	
	public PerceptronLearning(float [][] ex, Network net, float err)
	{
		network = net;
		examples = ex;
		goalError = err;
	}
	
	public Network Learn(float learn_rate)
	{
		for (int i = 0; i < examples.length; i++)
		{
			float Err = examples[i][examples[i].length-1] - network.getOutput();
			System.out.println(Err);
		}
		
		return network;
	}
	
	// sigmoid derivative 
	public float sigDerive(float x)
	{
		float e = (float) Math.E;
		float numerator = (float) Math.pow(e, x);
		float denomenator = (float) Math.pow(1 + Math.pow(e, -x), 2);
		return numerator / denomenator;
	}
	
	public void printExamples()
	{
		for (int i = 0; i < examples.length; i++)
		{
			for(int j = 0; j < examples[i].length; j++)
			{
				System.out.print(examples[i][j]);
			}
			System.out.println();
		}
	}
}

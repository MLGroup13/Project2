
public class PerceptronLearning 
{
	private float [][] examples;
	private Network network;
	
	public PerceptronLearning(float [][] ex, Network net)
	{
		network = net;
		examples = ex;
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

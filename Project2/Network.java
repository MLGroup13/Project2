
public class Network 
{	
	private float[] inputVector;
	private INode[] inputN;
	private ONode[] outputN;
	//private RandomDist ranDist;
	
	public Network(int in, float[] inv, int out)
	{
		inputN = new INode[in];
		inputVector = inv;
		outputN = new ONode[out];
		//ranDist = new RandomDist(in);
	}
	
	public void setupInputNodes()
	{
		/* if there are no hidden nodes the number of weights 
		of each input node is equal to the number of outputs */
		for(int i = 0; i < inputN.length; i++)
		{
			inputN[i] = new INode(inputVector[i]);
			inputN[i].setWeight(outputN.length);
		}
	}
	
	public void setupOutputNodes()
	{
		for(int i = 0; i < outputN.length; i++)
		{
			outputN[i] = new ONode();
		}
		
	}
	
	int getINodes()
	{
		return inputN.length;
	}
	
	int getONodes()
	{
		return outputN.length;
	}
	
	void printNetwork()
	{
		System.out.println("Output Nodes");
		for(int i = 0; i < inputN.length; i++)
		{
			System.out.print("N" + i + ": input= " + inputN[i].getInput() + " ");
		}
	}
}

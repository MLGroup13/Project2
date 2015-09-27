
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
		float[] w = new float[outputN.length];
		for(int i = 0; i < inputN.length; i++)
		{
			inputN[i] = new INode(inputVector[i], w);
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
			System.out.print("Node" + i + ": input= " + inputN[i].getInput() + " ");
		}
	}
}

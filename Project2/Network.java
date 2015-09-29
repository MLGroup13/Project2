
public class Network 
{	
	private float[] inputVector;
	private INode[] inputN;
	private HNode[] layer1, layer2;
	private ONode[] outputN;
	//private RandomDist ranDist;
	
	public Network(int in, float[] inv, int out)
	{
		inputN = new INode[in];
		inputVector = inv;
		outputN = new ONode[out];
	}
	
	public void setupNetwork()
	{
		setupInputNodes();
		setupOutputNodes();
	}
	
	private void setupInputNodes()
	{
		/* if there are no hidden nodes the number of weights 
		of each input node is equal to the number of outputs */
		for(int i = 0; i < inputN.length; i++)
		{
			inputN[i] = new INode(inputVector[i]);
			inputN[i].setWeight(outputN.length);
		}
	}
	
	private void setupHiddenNodes()
	{
		
	}
	
	private void setupOutputNodes()
	{
		for(int i = 0; i < outputN.length; i++)
		{
			outputN[i] = new ONode();
			calcAct(inputN, outputN[i]);
		}
		
	}
	
	// calcAct used when
	private void calcAct(INode[] preLayer, ONode curLayer)
	{
		
	}
	
	private void calcAct(HNode[] preLayer, ONode[] curLayer)
	{
		
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

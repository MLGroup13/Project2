import java.util.Random;

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
	

	/** Shane wrote original, James adjusted input parameter 
	 *	An activation function using a sigmoid function. 
	 *
	 */
	public float sigActivate(float x){
		float e = (float) Math.E;
        float t = (float) Math.pow(e, -x);
        float activate = 1 / (1 + t);
		return activate;
	}

	
	/** Shane wrote original, James adjusted input parameter
	 *  An activation function using a hyperbolic tangent.
	 */
	public float tanhActivate(float x){
        float activate = (float) Math.tanh(x);
        return activate;
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
			inputN[i].initWeight(outputN.length);
		}
	}
	
	private void setupHiddenNodes()
	{
		
	}
	
	private void setupOutputNodes()
	{
		// case for neural net with no hidden layers
		for(int i = 0; i < outputN.length; i++)
		{
			outputN[i] = new ONode();
			outputN[i].initBias();
			calcAct(inputN, outputN[i]);
		}	
	}
	
	// calcAct used when there are no hidden layers
	private void calcAct(INode[] preLayer, ONode curNode)
	{
		
		
		for (int i = 0; i < preLayer.length; i++)
		{
			float [] w = preLayer[i].getWeight();
			for(int j = 0; j < w.length; j++)
			{
				curNode.setOutput(tanhActivate(preLayer[i].getInput()*w[j] + curNode.getBias()));
			}
		}
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
		System.out.println("Input Nodes");
		for(int i = 0; i < inputN.length; i++)
		{
			System.out.print("N" + i + ": input= " + inputN[i].getInput() + " ");
		}
		
		System.out.println("Output Nodes");
		for(int i = 0; i < outputN.length; i++)
		{
			System.out.print("O" + i + ": " + "bias= " + outputN[i].getBias() +
				"output= " + outputN[i].getOutput() + " ");
		}
	}
	
	
}

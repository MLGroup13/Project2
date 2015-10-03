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
	
	// sigmoid derivative 
	public float sigDerive(float x)
	{
		float e = (float) Math.E;
		float numerator = (float) Math.pow(e, x);
		float denomenator = (float) Math.pow(1 + Math.pow(e, -x), 2);
		return numerator / denomenator;
	}
	
	/** Shane wrote original, James adjusted input parameter
	 *  An activation function using a hyperbolic tangent.
	 */
	public float tanhActivate(float x){
        float activate = (float) Math.tanh(x);
        return activate;
    }
	
	// hyperbolic tangent derivative 
	public float tanhDeriv(float x)
	{
		return x;
	}
	
	// squared error used to compare network output o(x) to actual y from Rosenbrock function 
	public float sqError(float o, float y)
	{
		float err;
		err = (float) (0.5f*(y - Math.pow(o, 2)));
		
		return err;
	}
	
	public void setupNetwork()
	{
		setupInputNodes();
		setupOutputNodes();
	}
	
	private void setupInputNodes()
	{
		float[] w = new float[outputN.length];
		for(int i = 0; i < inputN.length; i++)
		{
<<<<<<< HEAD
			inputN[i] = new INode(inputVector[i], w);
=======
			inputN[i] = new INode(inputVector[i]);
			inputN[i].initWeight(outputN.length);
>>>>>>> James_Branch
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
			System.out.print("Node" + i + ": input= " + inputN[i].getInput() + " ");
		}
		
		System.out.println("Output Nodes");
		for(int i = 0; i < outputN.length; i++)
		{
			System.out.print("O" + i + ": " + "bias= " + outputN[i].getBias() +
				"output= " + outputN[i].getOutput() + " ");
		}
	}
	
	
}

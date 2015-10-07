import java.util.Random;

public class Network 
{	
	
	private float[] inputVector;
	private float[] outputVector;
	private INode[] inputN;
	private int HLayers;
	private HNode[] layer1, layer2;
	private ONode[] outputN;
	private float bias = 1;
	//private RandomDist ranDist;
	
	public Network(int in, int hLayers, int [] hNodes, float[] inv, float[] outv, int out)
	{
		inputN = new INode[in];
		HLayers = hLayers;
		inputVector = inv;
		outputVector = outv; 
		outputN = new ONode[out];
		if (hLayers == 1)
		{
			layer1 = new HNode[hNodes[0]];
		}
		else if (hLayers == 2)
		{
			layer1 = new HNode[hNodes[0]];
			layer2 = new HNode[hNodes[1]];
		}
	}
	
	public void setupNetwork()
	{
		setupInputNodes();
		if (HLayers > 0)
		{
			setupHiddenNodes();
		}
		setupOutputNodes();
	}
	
	public void runNetwork(float[] inv, float[] outv)
	{
		// set output vector 
		outputVector = outv;
		
		// send new input into input nodes
		setInput(inv);
		if (HLayers > 0)
		{
			updateHiddenNodes();
		}
		updateOutputNodes();
	}
	
	private void setupInputNodes()
	{
		/* if there are no hidden nodes the number of weights 
		of each input node is equal to the number of outputs */
		if (HLayers == 0)
		{
			for(int i = 0; i < inputN.length; i++)
			{
				inputN[i] = new INode(inputVector[i]);
				inputN[i].initWeight(outputN.length);
			}
		}
		
		/* if there are hidden nodes the number of weights
		of each input node is equal to the number of hidden 
		nodes in the first hidden layer */
		else if (HLayers > 0)
		{
			for(int i = 0; i < inputN.length; i++)
			{
				inputN[i] = new INode(inputVector[i]);
				inputN[i].initWeight(layer1.length);
			}
		}
	}
	
	private void setupHiddenNodes()
	{
		for (int i = 0; i < HLayers; i++)
		{
			// if on the first hidden layer
			if(i == 0)
			{
				for (int j = 0; j < layer1.length; j++)
				{
					/* if there is a single hidden layer the number of weights
					of each node in the first hidden layer is equal to the 
					number of outputs */
					if (HLayers == 1)
					{
						layer1[j] = new HNode();
						layer1[j].initWeight(outputN.length);
						calcAct(inputN, layer1[j]);
					}
					
					/* if there is two hidden layers the number of weights
					of each node in the first hidden layer is equal to the
					number of nodes in the second hidden layer*/
					else if (HLayers == 2)
					{
						layer1[j] = new HNode();
						layer1[j].initWeight(layer2.length);
						calcAct(inputN, layer1[j]);
					}
				}
			}
			
			// if on the second hidden layer
			if(i == 1)
			{
				for(int j = 0; j < layer2.length; j++)
				{
					layer2[j] = new HNode();
					layer2[j].initWeight(outputN.length);
					calcAct(layer1, layer2[j]);
				}
			}
		}
	}
	
	// method to update hidden nodes
	private void updateHiddenNodes()
	{
		for (int i = 0; i < HLayers; i++)
		{
			// if on the first hidden layer
			if(i == 0)
			{
				for (int j = 0; j < layer1.length; j++)
				{
					/* if there is a single hidden layer the number of weights
					of each node in the first hidden layer is equal to the 
					number of outputs */
					if (HLayers == 1)
					{
						calcAct(inputN, layer1[j]);
					}
					
					/* if there is two hidden layers the number of weights
					of each node in the first hidden layer is equal to the
					number of nodes in the second hidden layer*/
					else if (HLayers == 2)
					{
						calcAct(inputN, layer1[j]);
					}
				}
			}
			
			// if on the second hidden layer
			if(i == 1)
			{
				for(int j = 0; j < layer2.length; j++)
				{
					calcAct(layer1, layer2[j]);
				}
			}
		}
	}
	
	private void setupOutputNodes()
	{
		// case for neural net with no hidden layers
		if (HLayers == 0)
		{
			for(int i = 0; i < outputN.length; i++)
			{
				outputN[i] = new ONode();
				calcAct(inputN, outputN[i]);
			}
		}
		
		// case for neural net with a single layer
		else if (HLayers == 1)
		{
			for(int i = 0; i < outputN.length; i++)
			{
				outputN[i] = new ONode();
				calcAct(layer1, outputN[i]);
			}
		}
		
		// case for neural net with two hidden layers
		else if (HLayers == 2)
		{
			for(int i = 0; i < outputN.length; i++)
			{
				outputN[i] = new ONode();
				calcAct(layer2, outputN[i]);
			}
		}
	}
	
	// method to update output nodes
	public void updateOutputNodes()
	{
		// case for neural net with no hidden layers
				if (HLayers == 0)
				{
					for(int i = 0; i < outputN.length; i++)
					{
						calcAct(inputN, outputN[i]);
					}
				}
				
				// case for neural net with a single layer
				else if (HLayers == 1)
				{
					for(int i = 0; i < outputN.length; i++)
					{
						calcAct(layer1, outputN[i]);
					}
				}
				
				// case for neural net with two hidden layers
				else if (HLayers == 2)
				{
					for(int i = 0; i < outputN.length; i++)
					{
						calcAct(layer2, outputN[i]);
					}
				}
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
		err = (float) (0.5f*Math.pow((y - o), 2));
		
		return err;
	}
	
	// calcAct used by output nodes when there are no hidden layers
	private void calcAct(INode[] preLayer, ONode curNode)
	{
		for (int i = 0; i < preLayer.length; i++)
		{
			float [] w = preLayer[i].getWeight();
			for(int j = 0; j < w.length; j++)
			{
				curNode.setOutput(sigActivate(preLayer[i].getInput()*w[j] + bias));
			}
		}
	}
	
	// calcAct used by output nodes when there are one or more hidden layers
	private void calcAct(HNode[] preLayer, ONode curNode)
	{
		for (int i = 0; i < preLayer.length; i++)
		{
			float [] w = preLayer[i].getWeight();
			for(int j = 0; j < w.length; j++)
			{
				curNode.setOutput(sigActivate(preLayer[i].getActivation()*w[j] + bias));
			}
		}
	}
	
	// calcAct used by first hidden layer 
	private void calcAct(INode[] preLayer, HNode curNode)
	{
		for (int i = 0; i < preLayer.length; i++)
		{
			float [] w = preLayer[i].getWeight();
			for(int j = 0; j < w.length; j++)
			{
				curNode.setActivation(sigActivate(preLayer[i].getInput()*w[j] + bias));
			}
		}
	}
	
	// calcAct used by the second hidden layer 
		private void calcAct(HNode[] preLayer, HNode curNode)
		{
			for (int i = 0; i < preLayer.length; i++)
			{
				float [] w = preLayer[i].getWeight();
				for(int j = 0; j < w.length; j++)
				{
					curNode.setActivation(sigActivate(preLayer[i].getActivation()*w[j] + bias));
				}
			}
		}
	
	// method to get input nodes
	public INode[] getINodes()
	{
		return inputN;
	}
	
	// method to set input node values
	public void setInput(float [] inv)
	{
		for (int i = 0; i < inputN.length; i++)
		{
			inputN[i].setInput(inv[i]);
		}
	}
	
	// method to set input weights
	public void setWeights(INode input, float[] w)
	{
		input.setWeight(w);
	}
	
	// method to get hidden layer 1 nodes
	public HNode[] getL1HNodes()
	{
		return layer1;
	}
	
	// method to get hidden layer 2 nodes
	public HNode[] getL2HNodes()
	{
		return layer2;
	}
	
	// method to get output nodes
	public ONode[] getONodes()
	{
		return outputN;
	}
	
	// method to get output
	public float getOutput()
	{
		return outputN[0].getOutput();
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
			System.out.println("O" + i + ": " + "output= " + outputN[i].getOutput() + " ");
			System.out.println("Actual Output= " + outputVector[i]);
			System.out.println("Error: " + sqError(outputN[i].getOutput(), outputVector[i]) );
		}
		
	}
	
	
}

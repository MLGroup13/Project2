
public class BackPropLearning 
{
	private float [][] examples;
	private Network network;
	private INode[] inputN;
	private HNode[] layer1, layer2;
	private ONode[] outputN;
	
	public BackPropLearning(float [][] ex, Network net, float err)
	{
		network = net;
		examples = ex;
		
		inputN = network.getINodes();
		if (network.getHLayers() == 1)
		{
			layer1 = network.getL1HNodes();
		}
		else if (network.getHLayers() == 2)
		{
			layer1 = network.getL1HNodes();
			layer2 = network.getL2HNodes();
		}
			outputN = network.getONodes();
	}
	
	public Network Learn(float learn_rate)
	{
		for (int i = 0; i < examples.length; i++)
		{
			float a[] = new float[inputN.length];
			for (int j = 0; j < inputN.length; j++)
			{
				a[j] = examples[i][j];
			}
			for(int hlayers = 0; hlayers < network.getHLayers(); hlayers++)
			{
				if(hlayers == 0)
				{
					
					for (int inputs = 0; inputs < layer1.length; inputs++)
					{
						
					}
				}
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

}

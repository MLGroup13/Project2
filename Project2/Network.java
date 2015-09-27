
public class Network 
{	
	float[] inputVector;
	INode[] inputN;
	ONode[] outputN;
	RandomDist ranDist;
	
	Network(int in, float[] inv, int out)
	{
		inputN = new INode[in];
		inputVector = inv;
		outputN = new ONode[out];
		ranDist = new RandomDist(in);
	}
	
	void setInput()
	{
		for(int i = 0; i < inputN.length; i++)
		{
			System.out.println(inputVector[i]);
			inputN[i].setInput(inputVector[i]);
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
			System.out.print("Node" + i + ": input= " + inputN[i].getInput());
		}
	}
}

import java.util.Scanner;

public class Test {

	public static void main(String[] args) 
	{
		// create Scanner instance input prompt user for arguments 
		Scanner input = new Scanner(System.in);
		
		int iNodes;
		System.out.println("# of input nodes?");
		iNodes = input.nextInt();
		
		float[] inputVector = new float[iNodes];
		for (int i = 0; i < iNodes; i++)
		{
			System.out.print("enter vector" + i);
			inputVector[i] = input.nextFloat();
		}
		
		int hLayers;
		int [] hNodes = new int[2];
		System.out.println("# of hidden layers?");
		hLayers = input.nextInt();
		for (int i = 0; i < hLayers; i++)
		{
			System.out.print("# of nodes in layer" + i);
			hNodes[i] = input.nextInt();
		}
		
		int oNodes;
		System.out.println("# of output nodes");
		oNodes = input.nextInt();
		
		float[] outputVector = new float[oNodes];
		for (int i = 0; i < oNodes; i++)
		{
			System.out.print("enter vector" + i);
			outputVector[i] = input.nextFloat();
		}
		
		// code to setup a a new Network 
		Network network = new Network(iNodes, hLayers, hNodes, inputVector, outputVector, oNodes);
		network.setupNetwork();
		network.printNetwork();
		
		// prompt for number of examples for training 
		int example;
		System.out.println("How many examples for learning?");
		example = input.nextInt();
		
		float [][] examples = new float[example][iNodes+1];
		for(int i = 0; i < example; i++)
		{
			System.out.println("Example" + i);
			for (int j = 0; j < iNodes; j++)
			{
				System.out.print("input" + j);
				examples[i][j] = input.nextFloat();
			}
			System.out.print("Output" + i);
			examples[i][iNodes] = input.nextFloat();
		}
		
		Network networkLearn = network;
		PerceptronLearning percept = new PerceptronLearning(examples, networkLearn);
		percept.printExamples();
		
		input.close();
	}

}

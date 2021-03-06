package mlproject2;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws Exception
	{
		/* FIRST PHASE initializes a network with random weights
		 * and just uses the first line of the data set 
		 */
		System.out.println("INITIALIZATION PHASE");
		
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
		
		
		/* LEARNING PHASE which uses training examples 
		 */
		System.out.println("LEARNING PHASE");
		
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
		
		float goalError;
		System.out.println("Enter Error used by Learning Algorithm");
		goalError = input.nextFloat(); 
		
		PerceptronLearning percept = new PerceptronLearning(examples, networkLearn, goalError);
		percept.printExamples();
		
		float learn_rate;
		System.out.println("Enter Learning Rate");
		learn_rate = input.nextFloat();
		
		int epochs = 0;
		do
		{
			System.out.println("Epoch" + epochs);
			networkLearn = percept.Learn(learn_rate);
		
			for (int i = 0; i < example; i++)
			{
				float inputV[] = new float [examples[i].length-1];
				float outputV[] = new float [oNodes];
				
				for (int j = 0; j < inputV.length; j++)
				{
					inputV[j] = examples[i][j];
				}
				outputV[0] = examples[i][iNodes];
			
				networkLearn.runNetwork(inputV, outputV);
				networkLearn.printNetwork();
			}
			
			epochs++;
		}	while(epochs < 5);
		
		/* TESTING PHASE which tests the accuracy of the approximation 
		 */
		System.out.println("TESTING PHASE");
		
		
		System.out.println("Enter test Data for " + (iNodes+1) + "D Rosenbrock");
		for (int i = 0; i < iNodes; i++)
		{
			System.out.print("vector" + i);
			inputVector[i] = input.nextFloat();
		}
		
		System.out.println("Output");
		outputVector[0] = input.nextFloat();
		
		
			networkLearn.runNetwork(inputVector, outputVector);
			networkLearn.printNetwork();
			
		
		input.close();
	}

}

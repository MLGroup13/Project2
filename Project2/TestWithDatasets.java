import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class TestWithDatasets 
{
	public static void main(String[] args) throws Exception
	{
		//Scanner used for user input
		Scanner input = new Scanner(System.in);
		
		//Input what data set you will be using 
		System.out.println("Enter Dataset name");
		String fileName = input.nextLine();
		System.out.println(fileName);
		File file = new File(fileName);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		/**
		 * Initialize Phase
		 * Initialize all nodes before testing
		 */
		//Input number of input nodes
		int iNodes;
		System.out.println("# of input nodes?");
		iNodes = input.nextInt();
		
		// tentative node input method
		float[] inputVector = new float[iNodes];
		for (int i = 0; i < iNodes; i++)
		{
			System.out.print("enter vector" + i);
			inputVector[i] = input.nextFloat();
		}
		
		//input number of hidden layers
		int hLayers;
		int [] hNodes = new int[2];
		System.out.println("# of hidden layers?");
		hLayers = input.nextInt();
		
		//tentative for loop
		for (int i = 0; i < hLayers; i++)
		{
			System.out.print("# of nodes in layer " + i);
			hNodes[i] = input.nextInt();
		}
		
		//creates output nodes
		int oNodes;
		System.out.println("# of output nodes");
		oNodes = input.nextInt();
		
		float[] outputVector = new float[oNodes];
		for (int i = 0; i < oNodes; i++)
		{
			System.out.print("enter vector" + i);
			outputVector[i] = input.nextFloat();
		}
		
		//Setup a new network
		Network network = new Network(iNodes, hLayers, hNodes, inputVector, outputVector, oNodes);
		network.setupNetwork();
		//network.printNetwork();
		
		/*
		 * Learning phase which uses training examples
		 */
		
		//Do not use all examples. The examples for learning and testing come from the same text file.
		System.out.println("How many examples are for learning?");
		int example;
		example = input.nextInt();
		
		//instantiate some new variables used to extract data from .txt file
		float [][] examples = new float[example][iNodes+1];
		String[] current = new String[iNodes];
		int position = 0;
		String temp;
		String line;

		//Read lines from specially designed .txt file
		while((line = in.readLine()) != null && position < example){
			temp = line;
			current = temp.split(",");
			for(int i = 0; i < iNodes; i++){
				examples[position][i] = Float.parseFloat(current[i]);
			}
			float sum = 0;
			sum = Float.parseFloat(current[iNodes]);
			examples[position][iNodes] = sum;
			position++;
		}		
		
		
		
		//create a network to learn from original network
		Network networkLearn = network;
		
		// Get the goal error from the user
		float goalError;
		System.out.println("Enter Error used by Learning Algorithm");
		goalError = input.nextFloat();
		
		//set up the perceptrons for learning
		PerceptronLearning percept = new PerceptronLearning(examples, networkLearn, goalError);
		percept.printExamples();
		
		//initialize learn rate
		float learn_rate;
		System.out.println("Enter Learning Rate");
		learn_rate = input.nextFloat();
		
		//Begin Learning Process
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
			}
			
			epochs++;
		}	while(epochs < 5);
		
		/*
		 * TESTING PHASE which tests the accuracy of the approximation
		 */
		//Use the remaining number of examples in the .txt file to test data
		System.out.println("TESTING PHASE");
		System.out.println("How many tests?");
		int tests = input.nextInt();
		
		position = 0;
		while((line = in.readLine()) != null && position < tests){
			temp = line;
			current = temp.split(",");
			for(int i = 0; i < iNodes; i++){
				inputVector[i] = Float.parseFloat(current[i]);
			}
			outputVector[0] = Float.parseFloat(current[iNodes]);
			position++;
			networkLearn.runNetwork(inputVector, outputVector);			
			networkLearn.printNetwork();
			networkLearn.overallAverage();
		}		
		
		networkLearn.WriteClose();
		networkLearn.averageClose();
		in.close();
		input.close();
	}
	private void ParseData(int samples, int dimension)
	{
		
	}
	
	
	
}

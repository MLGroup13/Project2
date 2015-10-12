package mlproject2;

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
		network.printNetwork();
		
		/*
		 * Learning phase which uses training examples
		 */
		System.out.println("How many examples are for learning?");
		int example;
		example = input.nextInt();
		
		//instantiate some new variables used to extract data from .txt file
		float [][] examples = new float[example][iNodes+1];
		String[] current = new String[iNodes];
		int position = 0;
		String temp;
		String line;

		while((line = in.readLine()) != null && position < example){
			for(int i = 0; i < iNodes; i++){
				temp = line;
				current = temp.split(",");	
				examples[position][i] = Float.parseFloat(current[i]);
			}
			float sum = 0;
			for(int j = 0; j < 2; j++){
				float x = examples[position][j];
				float y = examples[position][j+1];
				float output = (float)  (Math.pow((1 - x), 2) + 
						100*(Math.pow((y - Math.pow(x, 2) ), 2) )
						);	
				sum += output;			
			}
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
				networkLearn.overallAverage();
			}
			
			epochs++;
		}	while(epochs < 5);
		
		/*
		 * TESTING PHASE which tests the accuracy of the approximation
		 */
		System.out.println("TESTING PHASE");
		
		for(int i = 0; i < iNodes; i++){	
			inputVector[i] = Float.parseFloat(current[i]);
		}

		networkLearn.runNetwork(inputVector, outputVector);
		networkLearn.printNetwork();
		
		networkLearn.WriteClose();
		networkLearn.averageClose();
		in.close();
		input.close();
	}
	private void ParseData(int samples, int dimension)
	{
		
	}
	
	
	
}

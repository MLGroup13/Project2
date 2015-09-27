import java.util.Scanner;

public class Test {

	public static void main(String[] args) 
	{
		// create Scanner instance input prompt user for arguments 
		Scanner input = new Scanner(System.in);
		
		int iNodes;
		System.out.println("# of input nodes?");
		iNodes = input.nextInt();
		
		int oNodes;
		System.out.println("# of output nodes");
		oNodes = input.nextInt();
		
		float[] inputVector = new float[iNodes];
		for (int i = 0; i < iNodes; i++)
		{
			System.out.println("enter vector" + i);
			inputVector[i] = input.nextFloat();
		}
		
		// create network 
		
		Network network = new Network(iNodes, inputVector, oNodes);
		network.setInput();
		System.out.println(network.getINodes());
		System.out.println(network.getONodes());
		network.printNetwork();
		
		RandomDist distribution = new RandomDist(5);
		distribution.printDist();
		
		input.close();
	}

}

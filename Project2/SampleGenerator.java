import java.util.Scanner;

public class SampleGenerator {

	public static void main(String[] args) 
	{
		int sample_size = 0;
		int dimension = 0;
		
		// create Scanner instance input prompt user for arguments 
		Scanner input = new Scanner(System.in);
		
		// prompt user for sample size
		System.out.println("Sample size?");
		sample_size = input.nextInt(); 
		
		// prompt user for dimension for Rosenbrock function 
		System.out.println("Dimension of Rosenbrock function");
		dimension = input.nextInt();
		
		RandomDist distribution = new RandomDist(sample_size, dimension);
		distribution.printDist();
	}

}

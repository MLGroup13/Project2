import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

public class SampleGenerator {

	public static void main(String[] args) throws Exception
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
		
		PrintWriter writer = new PrintWriter(new FileWriter("randomDist" + "s" + sample_size + "d" + dimension + ".txt"));
		PrintWriter writer2 = new PrintWriter(new FileWriter("normalizedDist" + "s" + sample_size + "d" + dimension + ".txt"));
		
		RandomDist distribution = new RandomDist(sample_size, dimension);
		distribution.printDist();
		
		float[][] output = distribution.getRanDist();
		
		for (int i = 0; i < output.length; i++)
		{
			for (int j = 0; j < dimension+1; j++)
			{
				writer.print(output[i][j] + ",");
			}
			writer.println();
		}
		
		float[][] normalize = distribution.Normalize();
		for(int k = 0; k < normalize.length; k++){
			for(int l = 0; l < dimension+1; l++){
				writer2.print(normalize[k][l] + ",");
			}
			writer2.println();
		}
		writer.close();
		writer2.close();
		input.close();
	}

}
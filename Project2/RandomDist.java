import java.util.*;
import java.lang.Math.*;

public class RandomDist
{
	private float[][] ranDist;
	private int dimension;
		
	RandomDist(int size, int d)
	{
		dimension = d;
		ranDist = new float[size][d+1];
	    
		getDist();
	}
	
	float[][] getRanDist(){
		return ranDist;
	}
	
	float[][] minMax(){
		float[][] minmax = null;
		minmax = ranDist;
		float minimum = 0;
		float maximum = maximum();
		for(int i = 0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				minmax[i][j] = (ranDist[i][j] - minimum) / (maximum - minimum);
			}
		}
		return minmax;
	}
	
	float minimum(){
		float mini = 1000000000;
		for(int i = 0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				if(mini > ranDist[i][j]){
					mini = ranDist[i][j];
				}
			}
		}
		return mini;
	}
	
	float maximum(){
		float max = 0;
		for(int i = 0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				if(max < ranDist[i][j]){
					max = ranDist[i][j];
				}
			}
		}
		return max;
	}
	
	
	//Z score of ranDist
	float[][] Normalize(){
		float[][] Zscore = null;
		Zscore = ranDist;
		float average = average();
		float stdDev = stdDev();
		for(int i = 0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				Zscore[i][j] = ((ranDist[i][j]) - average) / stdDev;
			}
		}
		return Zscore;
	}
	
	//average of RanDist
	float average(){
		float aver = 0;
		for(int i = 0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				aver += ranDist[i][j];
			}
		}
		aver = aver / ranDist.length;
		return aver;
	}
	
	//standard deviation of RanDist
	float stdDev(){
		double temp = 0;
		float stdDev = 0;
		float average = average();
		for(int i =0; i < ranDist.length; i++){
			for(int j = 0; j <= dimension; j++){
				temp += ((ranDist[i][j] - average) * (ranDist[i][j] - average));
			}
		}
		temp = temp / ranDist.length;
		stdDev = (float) Math.sqrt(temp);
		return stdDev;
	}
	
	// method to construct a uniform random distribution of real numbers
	private float[][] getDist()
	{
		Random randomN = new Random();
		for (int i = 0; i < ranDist.length; i++)
		{
			for (int j = 0; j <= dimension; j++)
			{
				ranDist[i][j] =  (randomN.nextFloat() - randomN.nextFloat())*3.0f;
			}
			float sum = 0;
			for (int j = 0; j < dimension; j++)
			{
				sum = sum + Rosenbrock(ranDist[i][j], ranDist[i][j+1]);
			}
			ranDist[i][dimension] = sum;
			
		}
		
		return ranDist;
	}
	
	// method to display distribution 
	void printDist()
	{
		for (int i = 0; i < ranDist.length; i++)
		{
			for (int j = 0; j < dimension+1; j++)
			{
				System.out.print(ranDist[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	//calculate output for Rosenbrock function which takes calculate one part of sum
	public float Rosenbrock(float left, float right)
	{
		float output = (float)  (Math.pow((1 - left), 2) + 
				100*(Math.pow((right - Math.pow(left, 2) ), 2) )
				);
		return output;
	}
}
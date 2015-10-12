/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mlproject2;

import java.util.*;

/**
 *
 * @author Angelica Davis, James Beck, Shane Bauerman
 */
public class RBF {
    // (x1,x2,...,xd) input values, and corresponding target y values
    private float examples[][];
    // dimension
    private static int d;
    // number of inputs (data points)
    private static int numEx;
    // number of basis functions
    private static int k;
    // size of learning step
    private static float eta;
    // hidden, not sure yet where it is ... 
    // c, k d-dimensional centers to be learned via k-means clustering
    private float[][] c;
    // r, radius or stnd dev from c
    private float r;
    // array of weights from basis output to rbfn output; learned via gradient descent
    private float w[];
    // RBFN array of results
    private float[] output;

    /*
     * Constructor for class RBF
     * @ params         float[][] input - the input data, 
     *                  numGaussians - number of Gaussian functions (k)
     * @ initialized vars    d, numEx - dimension size, number of input examples 
     */
    public RBF(float input[][], int numGaussians, float eta) {
        examples = input;
        d = input[0].length - 1;
        numEx = input.length;
        k = numGaussians;
        eta = this.eta;
    }

    public void trainRBF() {
        // I. find basis nodes via k-means clustering
        c = findCentroids();
        // II. learn weights via gradient descent
        float[] w = new float[k]; // one weight for each basis node output
        // initialize weights to random values
        Random r = new Random();
        for (int i = 0; i < k; i++) {
            w[i] = r.nextFloat();
        }
        // get rbfn approximation with current weights
        float[] rbfOut = new float[numEx];
        rbfOut = askRBFN(examples);
        // update weights for current rbfn approximation
        for (int i = 0; i < numEx; i++) {
            // updateW(current predicted value, index of target value)
            updateW(rbfOut[i], i);
        }
        while (cost(rbfOut) > .005) {
            // get rbfn approximation with current weights
            rbfOut = askRBFN(examples);
            // update weights for current rbfn approximation
            for (int i = 0; i < numEx; i++) {
                // updateW(current predicted value, index of target value)
                updateW(rbfOut[i], i);
            }
        }
    }

    /*
     * Compute the average error for the network
     */
    public float cost(float rbfOut[]) {
        float cost = (float) Double.POSITIVE_INFINITY;
        for (int i = 0; i < numEx; i++) {
            // sum over error btwn all predicted values and target values
            cost += Math.abs(rbfOut[i] - examples[i][d]) / examples[i][d];
        }
        // divide by total number of values
        cost = cost / numEx;
        return cost;
    }

    /*
     * Update rule for learning weights, given a single output/target pair
     *  @ params    output- RBFN approximation for f(x1,x2...,xd)
     *              i- index of the target value
     */
    public void updateW(float output, int i) {
        for (int j = 0; j < k; j++) {
            // note: examples[i][d] = target value 
            // note: c[j] = center corresponding to weight w
            w[i] = w[i] + eta * (examples[i][d] - output) * h(examples[i], c[j]);
        }
    }

    /*
     * Get output approximation from the current RBFN
     *  given the input subset of data
     */
    public float[] askRBFN(float test[][]) {
        int testSize = test.length;
        float[] output = new float[testSize];
        // propogate each query through each basis node
        float[][] rbfOut = new float[testSize][k];
        for (int i = 0; i < testSize; i++) {
            for (int j = 0; j < k; j++) {
                rbfOut[i][j] = h(test[i], c[j]);
            }
        }
        // compute the linear combinations of basis outputs for each data point
        for (int i = 0; i < testSize; i++) {
            output[i] = getOutput(rbfOut[i]);
        }
        return output;
    }

    /* 
     * compute the weighted sum for a single output node
     * @ params     w[]- array of weights from hidden layer to output
     *              rbfOut[]- array of RBF outputs from the hidden layer
     */
    public float getOutput(float rbfOut[]) {
        float sum = 0;
        for (int i = 0; i < k; i++) {
            sum += w[i] * rbfOut[i];
        }
        return sum;
    }

//    /* 
//     * compute the weighted sum for a single output node
//     */
//    public float getOutput(float h[][], float w[]) {
//        float sum = 0;
//        float[] product = new float[d];
//        for (int i = 0; i < d; i++) {
//            // compute vector w * mat h
//            for (int j = 0; j < numEx; j++) {
//                product[i] += w[i] * h[j][i];
//            }
//            // compute sum(wj*hj)
//            sum += product[i];
//        }
//        return sum;
//    }
//    /*
//     * compute the activation for a single hidden node
//     *  given a single dimension???
//     */
//    public float[] h(float examples[][]) {
//        float[] out = new float[numEx];
//        float distSqrd = 0;
//        for (int i = 0; i < numEx; i++) {
//            distSqrd = (float) Math.pow(distance(examples[i], c[i]), 2);
//            out[i] = (float) Math.pow((-distSqrd / Math.pow(r, 2)), Math.E);
//        }
//        return out;
//    }

    /*
     * Compute the RBF output for a single hidden node 
     */
    public float h(float xIn[], float c[]) {
        float out = 0;
        // calculate sqrd distnce btwn x-in vector and center
        out = (float) Math.pow(distance(xIn, c), 2);
        // calculate exp[ -(sqrd dist) / 2*r^2]
        out = (float) Math.pow((-out / 2 * Math.pow(r, 2)), Math.E);
        return out;
    }

    /*
     * k-means clustering implementation for the dataset
     */
    public float[][] findCentroids() {
        System.out.println("k-means clustering: k = " + k);
        float[][] centroids = new float[k][d];
        float[][] prevC = new float[k][d];
        Random r = new Random();
        // I. initialize k clusters to randomly selected points in the dataset
        for (int i = 0; i < k; i++) {
            int index = r.nextInt(numEx);
            centroids[i] = examples[index];
        }
        while (!noChange(prevC, centroids)) {
            // initialize prevC
            prevC = centroids;
            // II. group remaining data based on the nearest centroid
            float[][][] clusters = new float[k][numEx][d];
            // find c with min distance to x
            for (int i = 0; i < numEx; i++) {
                float minDist = (float) Double.POSITIVE_INFINITY;
                System.out.print("i: " + i + " |");
                for (int j = 0; j < k; j++) {
                    System.out.print(" j: " + j);
                    float dist = distance(examples[i], centroids[j]);
                    if (minDist > dist) {
                        minDist = dist;
//                        for (int h = 0; h < d; h++) {
//                            System.out.format("\nout of bounds? cluster[%d][%d][%d] = examples[%d][%d] | %f \n",i,j,h,j,h,examples[j][h]);
//                        }
                        clusters[j][i] = examples[i];
                    }
                }
                System.out.println();
            }
            // III. find mean center of clusters found in part II & update centroids
            for (int i = 0; i < k; i++) {
                centroids[i] = average(clusters[i]);
            }
        }
        c = centroids;
        return centroids;
    }

    /*
     * Compute the radius for a single hidden node
     *  where r is defined as the standard deviation of 
     *  the current node comparative to all others
     * @ params     j- index of current centroid
     */
    public float findRadius(int j) {
        float r = 0;
        for (int i = 0; i < k; i++) {
            // sum(sqrdDist between current center, cj, and all other ci)
            r += Math.pow((distance(c[j], c[i])), 2);
        }
        // divide sum by k, then take the sqr root
        r = (float) Math.sqrt(r / k);
        return r;
    }

    /*
     * Compute the average, i.e. center, of each current cluster
     *  where the center is d-dimensional and avg is computed component wise
     */
    public float[] average(float cluster[][]) {
        System.out.println("averaging...");
        float avg[] = new float[d];
        // sum each component of all members in a cluster
        for (int i = 0; i < d; i++) {
            System.out.print("i: " + i + " |");
            for (int j = 0; j < numEx; j++) {
                System.out.print(" j: " + j);
                avg[i] += cluster[j][i];
            }
            System.out.println("sum: " + avg[i]);
        }
        // divide each component wise sum by the total number of members
        for (int i = 0; i < d; i++) {
            avg[i] = avg[i] / cluster.length;
            System.out.println("over " + cluster.length + " members = " + avg[i]);
        }
        return avg;
    }

    /*
     * Compute Euclidean distance from p1 to p2
     *  where p1,p2 are in d-dimensions
     */
    public float distance(float p1[], float p2[]) {
        float dist = 0;
        for (int i = 0; i < d; i++) {
            dist += (float) Math.pow((p2[i] - p1[i]), 2);
        }
        dist = (float) Math.sqrt(dist);
        return dist;
    }

    /*
     * Return true if no change has occured in any cluster,
     *  return false otherwise
     */
    boolean noChange(float[][] prevC, float[][] curC) {
        for (int i = 0; i < prevC.length; i++) {
            for (int j = 0; j < d; j++) {
//                System.out.format("comparison%d: %f v %f\n",j,prevC[i][j],curC[i][j]);
                if (prevC[i][j] != curC[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}

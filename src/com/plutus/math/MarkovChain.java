package com.plutus.math;


import Jama.Matrix;

import java.util.List;

import Jama.EigenvalueDecomposition;

public class MarkovChain {

    public static void main(String[] args) { 
    	
		// number of possibilities that we will create and assets OK TEST
		//List<Double[]> listWeights = MonteCarlo.allocationList(10, 10);

    	
        // the state transition matrix
        /*
        double[][] transition = { { 0.386, 0.147, 0.202, 0.062, 0.140, 0.047, 0.016},
                                  { 0.107, 0.267, 0.227, 0.120, 0.207, 0.052, 0.020},
                                  { 0.035, 0.101, 0.188, 0.191, 0.357, 0.067, 0.061},
                                  { 0.021, 0.039, 0.112, 0.212, 0.431, 0.124, 0.061},
                                  { 0.009, 0.024, 0.075, 0.123, 0.473, 0.171, 0.125},
                                  { 0.000, 0.103, 0.041, 0.088, 0.301, 0.312, 0.155},
                                  { 0.000, 0.008, 0.036, 0.083, 0.364, 0.235, 0.274}
                                };
        */
		double[][] transition = { { 0.900, 0.075, 0.025},
								  { 0.150, 0.800, 0.050},
								  { 0.250, 0.250, 0.500}
								};
                	
      // double[][] transition = new double[listWeights.size()][listWeights.size()];
        
		int N = transition.length;

        double[] sum = new double[N];
        for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			//sum[i]+=transition[i][j];
        			//sum[i]+=listWeights.get(i)[j];
        			//transition[i][j] = listWeights.get(i)[j];
        		}
        		//System.out.println("Total of line["+i+"] = "+sum[i]);
        }
      
		Matrix m = new Matrix(transition);

        // compute using 50 iterations of power method
        Matrix A = new Matrix(transition);
        A = A.transpose();
        Matrix x = new Matrix(N, 1, 1.0 / N); // initial guess for eigenvector
        
        m = calcStateDistribution(transition, 50);
        
        for (int i = 0; i < 100; i++) {
            x = A.times(x);
            x = x.times(1.0 / x.norm1());       // rescale
        }
        System.out.println("Stationary distribution using power method:");
        x.print(9, 6);
        
        
//        List<Double[]> initialDistribuition = MonteCarlo.allocationList(1, 10);
//        int n = initialDistribuition.get(0).length;
//        double [][] s = new double[n][n];
//        
//        for(int j = 0; j < n; j++) {
//        		s[0][j] = initialDistribuition.get(0)[j];
//        }
        
      double [][] s = {{1.0,0.0,0.0}};
       Matrix state = new Matrix(s);
        
        
        m.print(9, 6);
        
       m = state.times(m);
        
        m.print(9, 6);
    

/* 
        // compute by finding eigenvector corresponding to eigenvalue = 1
        EigenvalueDecomposition eig = new EigenvalueDecomposition(A);
        Matrix V = eig.getV();
        double[] real = eig.getRealEigenvalues();
        for (int i = 0; i < N; i++) {
            if (Math.abs(real[i] - 1.0) < 1E-6) {
                x = V.getMatrix(0, N-1, i, i);
                x = x.times(1.0 / x.norm1());
                System.out.println("Stationary distribution using eigenvector:");
                x.print(9, 6);
            }
        }

        // If ergordic, stationary distribution = unique solution to Ax = x
        // up to scaling factor.
        // We solve (A - I) x = 0, but replace row 0 with constraint that
        // says the sum of x coordinates equals one
        Matrix B = A.minus(Matrix.identity(N, N));
        for (int j = 0; j < N; j++)
            B.set(0, j, 1.0);
        Matrix b = new Matrix(N, 1);
        b.set(0, 0, 1.0);
        x = B.solve(b);
        System.out.println("Stationary distribution by solving linear system of equations:");
        x.print(9, 6);
*/    
    }
	
    
    public static Matrix calcStateDistribution(double[][] m, int state) {
    		
		Matrix matrix = new Matrix(m);
		Matrix matrixResult = matrix.copy();

    		for (int i = 0; i < state ; i++) {
            matrixResult = matrixResult.times(matrix);      
        }
    		 
        System.out.println("State "+state+" distribution:");
        matrixResult.print(9, 6);
        
        return matrixResult;
    }
    
    public static double[][] multiple(double[][] a, double[][] b){
		
		int m = a.length;
		int n = b[0].length;
		int k = a[0].length;
		
		double [][] matrix = new double[m][n];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				for(int p = 0; p < k; p++){
					matrix[i][j]+=a[i][p]*b[p][j];
				}	
			}	
		}
		return matrix;
	}
}
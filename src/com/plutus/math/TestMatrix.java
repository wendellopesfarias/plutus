package com.plutus.math;

import Jama.Matrix;

public class TestMatrix {

	public static void main(String[] args) {
		
		double[][] A = { 
				{ 2, 4, 1, 7},
				{ 4, 2, 7, 1},
				{ 5, 2, 2, 1},
				{ 1, 1, 1, 1}
				};
		
		double[][] B = {
				{10},
				{9},
				{3},
				{4}
				}; 	
		
		Matrix ma = new Matrix(A);
		Matrix mb = new Matrix(B);
		
		ma.print(2, 3);
	
		Matrix x = ma.solve(mb);
		
		x.print(2, 3);
		
		Matrix Residual = ma.times(x).minus(mb);
		double rnorm = Residual.normInf();

		// Solve X*A = B, which is also A'*X' = B'
		

	}

}

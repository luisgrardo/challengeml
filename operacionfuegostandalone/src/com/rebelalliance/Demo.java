package com.rebelalliance;
import java.util.Arrays;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] distances = new double[] { 8.00, 14.00, 33.00 };
		getLocation(distances);
		getMessage(new String[] {"este", "lunes", "un", "mensaje", "secreto"});

	}
	
	static String [] validWords= new String[] {"este", "es", "un", "mensaje","uno","dos", "secreto"};

	public static void getLocation(double[] distances) {
        
		double[][] positions = new double[][] { { -500.0, -200.0 }, { 100.0, -100.0 }, { 500.0, 100.0 } };
       
		NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
				new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
		Optimum optimum = solver.solve();

		double[] calculatedPosition = optimum.getPoint().toArray();
		System.out.println("posicion" + calculatedPosition[0]);

	}

	

	public static String[] getMessage(String[] messages) {
		String [] messageFinal= new String[messages.length];
		int index = 0;
		for (String w: messages) {
			if(!isWord(w)) {
				messageFinal[index]="";
				
			}else {
				messageFinal[index]= w;
			}
			
			index ++;
		}
	    
		return messageFinal;
		

	}
	
	public static boolean isWord(String w) {
		
		
			boolean contains = Arrays.stream(validWords).anyMatch(w::equals);
		
		return contains;
		
	}

}

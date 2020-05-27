/**
 * 	Raoul Ghit,	st143006@stud.uni-stuttgart.de,	3172702
	Hasan Darwish,	st149337@stud.uni-stuttgart.de,	3247569
	Benjamin Vier,	st142932@stud.uni-stuttgart.de,	3152829

 */
package solution_2;

import exercise_2.ComputeMatrixVectorProductEntry;
import exercise_2.RealSquareMatrix;
import exercise_2.RealVector;

public class ParallelRealSquareMatrix extends RealSquareMatrix {
	
	public ParallelRealSquareMatrix(double[][] entries) {
		super(entries);
	}

	@Override
	public RealVector multiply(RealVector vec) throws InterruptedException {
		
		// two new required Arrays initialized
		ComputeMatrixVectorProductEntry[] productEntrys = new ComputeMatrixVectorProductEntry[this.getDimension()];
		Thread[] threads = new Thread[this.getDimension()];
		
		// looping through to create the required objects for the two arrays
		for (int i = 0; i < this.getDimension(); i++) {
			productEntrys[i] = new ComputeMatrixVectorProductEntry(this, vec, i);
			threads[i] = new Thread(productEntrys[i]);
		}
		
		// starting each thread which will call the run method on each
		// ComputeMatrixVectorProductEntry object
		for (Thread thread : threads) {
			thread.start();
		}
		
		// Waiting for all threads to finish
		for (Thread thread : threads) {
			thread.join();
		}
		
		// array for the collected results of the parallel computation  
		double[] result = new double[this.getDimension()];
		
		// collecting the results and storing it in the above declared array
		for (int i = 0; i < productEntrys.length; i++) {
			result[i] = productEntrys[i].getResult();
		}
		
		// the final vector to return 
		RealVector vektor = new RealVector(result);
		return vektor;
	}

}

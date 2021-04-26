package mainPackege;

import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;
/*
 * Programmer:Shay Gabison	
 * 
 * An Gram–Schmidt process program.
 * The user enter the size of the matrix, and choose by menu to:
 * Orthonormalizing or do orthogonalization only.
 * 
 * Enjoy!
 * 
 * 
 */

public class GramShmidtProcess {

	// gramSchmidt is a recursive function that do the Gram Shmidt process
	public static double[][] gramSchmidt(double[][] matrix, double indexCounter, int choose) {
		if (indexCounter == matrix.length) {
			return matrix;
		} else if (indexCounter == 0) {
			if (choose == 1) {
				vectorNormlaizing(matrix[0])}
			return (gramSchmidt(matrix, indexCounter + 1, choose));
			
		}

		else {
			for (int i = 0; i < indexCounter; i++) {
				vectorNormlaizing(vectorSubtraction(matrix[(int) indexCounter],
						vectorProjactionVector2(matrix[(int) (indexCounter)], matrix[(int) i])));

			}
			return (gramSchmidt(matrix, indexCounter + 1, choose));

		}
	}

//vectorSubscription : Do vectors Subtraction -return( vector - vector2)
	public static double[] vectorSubtraction(double[] vector, double[] vector2) {
		for (int j = 0; j < vector.length; j++) {
			if (Math.abs((vector[j] - vector2[j])) < 0.000000001)
				vector[j] = Math.round(vector[j] - vector2[j]);

			else
				vector[j] = vector[j] - vector2[j];
		}

		return vector;

	}

	// vectorNormlaizing : get vector and normalized it.

	public static double[] vectorNormlaizing(double[] vector) {
		double norma = 0;
		for (int j = 0; j < vector.length; j++) {
			norma += vector[j] * vector[j];
		}
		for (int j = 0; j < vector.length; j++) {
			vector[j] = vector[j] / Math.sqrt(norma != 0 ? norma : 1);
		}
		return vector;

	}
	// vectorProjactionVector2 : gets vector and vector2 .
	// The algoritem is ((vector and vector2 Inner product)/ vector2 norma)*vector2

	public static double[] vectorProjactionVector2(double[] vector, double[] vector2) {
		double norma = 0;
		double machpela = 0;
		double[] vector3 = Arrays.copyOf(vector2, vector2.length);
		
		for (int j = 0; j < vector2.length; j++) {
			machpela += vector[j] * vector2[j];
			norma += vector2[j] * vector2[j];
		}

		for (int j = 0; j < vector2.length; j++) {
			vector3[j] = ((machpela / Math.sqrt(norma != 0 ? norma : 1)) * vector2[j]);

		}

		return vector3;

	}

	public static void main(String[] args) {
		// hard coded matrix for comfort.
		// double[][] matrix = { { 1, 2, 3, 4, 0 }, { 4, 4, 6, 0, 0 }, { 7, 5, 9, 0, 4
		// }, {
		// 4, 1, 0, 0, 4 }, { 4, 1, 0, 0, 4 } };
		int sizeOfTheMatrix = 0;
		Scanner s = new Scanner(System.in);
		System.out.println("Gram–Schmidt process");
		System.out.println("enter size of matrix: ");
		if (s.hasNextInt())// Integrity check
			sizeOfTheMatrix = s.nextInt();
		else
			while (!s.hasNextInt()) {
				System.out.println("Invalid input");
				System.out.println("Please enter the size of the matrix: ");
				s.next();
				if (s.hasNextInt()) {
					sizeOfTheMatrix = s.nextInt();
					break;
				}
			}
		double[][] matrix = new double[sizeOfTheMatrix][sizeOfTheMatrix];
		for (int i = 0; i < matrix.length; i++) {

			for (int k = 0; k < matrix.length; k++) {

				System.out.println("Please enter a number for item in:" + (i + 1) + " , " + (k + 1));
				if (s.hasNextDouble())// Integrity check
					matrix[i][k] = s.nextDouble();
				else
					while (!s.hasNextDouble()) {
						System.out.println("Invalid input");
						System.out.println("Please enter a number for item in:" + (i + 1) + " , " + (k + 1));
						s.next();
						if (s.hasNextDouble()) {
							matrix[i][k] = s.nextDouble();
							break;
						}
					}
			}

		}
		System.out.println("Choose one of the following options: ");
		System.out.println("");
		System.out.println("1 - Orthonormalize Matrix ");
		System.out.println("2 - Orthogonalization Matrix");
		boolean isItInteger = s.hasNextInt();
		int temporaryInt = 0;
		int choose = 0;
		if (isItInteger) {// Integrity check
			temporaryInt = s.nextInt();

			if (temporaryInt == 1 || temporaryInt == 2) {
				choose = temporaryInt;
			} else
				choose = chooseIntegritycheck();

		} else
			choose = chooseIntegritycheck();

		gramSchmidt(matrix, 0, choose);

		for (int i = 0; i < matrix.length; i++) {
			int counter = 0;
			for (int k = 0; k < matrix.length; k++) {
				if (matrix[i][k] == 0)
					counter++;

			}
			if (counter != matrix.length) {
				System.out.print("[");
				for (int k = 0; k < matrix.length; k++) {

					System.out.print("  " + matrix[i][k]);

				}
				System.out.println("]");

			}
		}
	}

//Helping function for integrity check of choose value.
	public static int chooseIntegritycheck() {
		boolean s1 = false;
		int temp = 0;
		int choose = 0;
		Scanner s = new Scanner(System.in);
		while (!(s1 && (temp == 1 || temp == 2)))

		{
			System.out.println("Invalid input");
			System.out.println("Choose one of the following options: ");
			System.out.println("");
			System.out.println("1 - Orthonormalize Matrix ");
			System.out.println("2 - Orthogonalization Matrix");
			s1 = s.hasNextInt();
			if (s1) {
				temp = s.nextInt();

				if (temp == 1 || temp == 2) {
					choose = temp;
					return choose;

				}
			} else
				s.next();
		}
		return choose;
	}
}

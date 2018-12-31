import linalg.LinAlgException;
import linalg.Matrix; // This is Matrix from the linear algebra package you are writing 
import linalg.Vector; // This is Vector from the linear algebra package you are writing 
 /** This is a small example of test cases.  Write your own test cases to understand all
 *  of the methods in Matrix and Vector.  To test correctness of your implementation,  
 *  see if the output on your tests matches the results of the same tests on the solution
 *  (e.g., see TestLinAlgSoln which provides results for the solution by importing 
 *         soln.Matrix and soln.Vector as opposed to linalg.Matrix and linalg.Vector
 *         that you are writing).
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public class TestLinAlg {
 	public static void main(String[] args) {
		try {
			// Note: you need to write your own tests, this is only a small sample and it does not
			//       test all cases that throw an Exception as required by JavaDoc comments.
			Vector v = new Vector("[ 1 2 3 4 5 ]");
			System.out.println("1. test constructor and toString(): " + v); // This automatically invokes v.toString()!
			System.out.println("2. test scalar addition: " + v.scalarAdd(1));
			System.out.println("3. ensure v was not modified: " + v);
			v.scalarAddInPlace(2);
			System.out.println("4. now v should be modified: " + v);
			
			Matrix m = Matrix.GetIdentity(5);
			System.out.println("5. identity matrix m:\n" + m);
			System.out.println("6. still identity after self-multiply:\n" + Matrix.Multiply(m, m)); 
			
			m.set(2, 0, 2);
			m.set(0, 2, 3);
			m.set(4, 0, 5);
 			System.out.println("7. m should be modified:\n" + m); // Remember: this automatically invokes m.toString()!
			System.out.println("8. result should not be the identity:\n" + Matrix.Multiply(m, m));
			System.out.println("9. example matrix/vector multiply: " + Matrix.Multiply(m, v));
			
			Matrix m2 = new Matrix(m);
			System.out.println("10. should be equal: " + m2.equals(m));
			m2.set(0, 1, 2d);
			System.out.println("11. should not be equal: " + m2.equals(m));
			
			Matrix m3 = new Matrix(5,4);
			m3.set(2, 1, 2.0);
			m3.set(2, 3, 3.0);
			Matrix m4 = m3.transpose();
			System.out.println("12. should be 4 X 5:\n" + m4);
			System.out.println("13. should be 5 X 5:\n" + Matrix.Multiply(m3, m4));
			System.out.println("14. should work:\n" + Matrix.Multiply(m4, v));
			System.out.println("15. should throw Exception: " + Matrix.Multiply(m3, v));
		
		} catch (LinAlgException e) {
			System.out.println("EXCEPTION: " + e.getMessage());
			System.exit(1); // Exits the program
		}
	}
 }
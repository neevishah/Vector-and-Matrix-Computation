package linalg;

/*** A class that represents a two dimensional real-valued (double) matrix
 *   and supports various matrix computations required in linear algebra.
 *   
 *   Class and method comments are in JavaDoc: https://en.wikipedia.org/wiki/Javadoc
 * 
 * @author scott.sanner@utoronto.ca, neevi.shah@mail.utoronto.ca
 * 
 */

public class Matrix {

	private int _nRows; // Number of rows in this matrix; nomenclature: _ for data member, n for integer
	private int _nCols; // Number of columns in this matrix; nomenclature: _ for data member, n for integer
	// add your own data member to represent the matrix content
	// you could use a 2D array, or an array of Vectors (e.g., for each row)
	private double [][] _data; //Contents of the matrix; nomenclature: _ for data member, d for double
	 
	
	/** Allocates a new matrix of the given row and column dimensions
	 * 
	 * @param row
	 * @param col
	 * @throws LinAlgException if either row or col is <= 0
	 */
	
	public Matrix(int row, int col) throws LinAlgException {
		
		if (row < 1 || col < 1) { //dimension error
			throw new LinAlgException("Number of rows and columns have to both be greater than 0");
		}
		
		_nRows = row;
		_nCols = col; 
		_data = new double[row][col]; //Entries will be automatically initialized to 0.0
		
	}
	
	/** Copy constructor: makes a new copy of an existing Matrix m
	 *                    (note: this explicitly allocates new memory and copies over content)
	 * 
	 * @param m
	 */
	
	public Matrix(Matrix m) {

		_nRows = m._nRows;
		_nCols = m._nCols;
		_data = new double[_nRows][_nCols]; // This allocates an array of size _nDim
		
		for (int i = 0; i < _nRows; i++) {
			for (int j = 0; j < _nCols; j++) {
					_data[i][j] = m._data[i][j];
			}
		}
	}

	/** Constructs a String representation of this Matrix
	 * 
	 */
	
	public String toString() {
		
			// We could just repeatedly append to an existing String, but that copies the String each
			// time, whereas a StringBuilder simply appends new characters to the end of the String
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < _nRows; i++) {
			sb.append("[");
				for (int j = 0; j < _nCols; j++) {
						_data[i][j] = _data[i][j];
						sb.append(String.format(" %6.3f ", _data[i][j]));
				}
		
			sb.append(" ]\n"); 
			}

			return sb.toString();
	}

	/** Tests whether another Object o (most often a matrix) is a equal to *this*
	 *  (i.e., are the dimensions the same and all elements equal each other?)
	 * 
	 * @param o the object to compare to
	 */
	
	public boolean equals(Object o) {
		// hint: see Vector.equals(), you can also use Vector.equals() for checking equality 
		//             of row vectors if you store your matrix as an array of Vectors for rows
		
		if (o instanceof Matrix) {
			Matrix m = (Matrix)o;  //they have to be the same class type for them to be equal
			
			if ((_nRows != m._nRows) || (_nCols != m._nCols)) {
				return false; //they have to have the same dimensions for them to be equal
			}
		
			for (int i = 0; i < _nRows; i++) {
				for (int j = 0; j < _nCols; j++) {
					if (_data[i][j] != m._data[i][j]) {
						return false;  //they have to have the same elements for them to be equal
					}
				}
			}
			return true; //if the dimension and then all the elements match, they are equal
		}
			
		return false; //if o and matrix do not share the same class type
	} 
	
	/** Return the number of rows in this matrix
	 *   
	 * @return _nRows
	 */
	
	public int getNumRows() {
				
		return _nRows; //return integer that holds the amount of rows of the matrix
	}

	/** Return the number of columns in this matrix
	 *   
	 * @return _nCols
	 */
	
	public int getNumCols() {
		
		return _nCols;  //return integer that holds the amount of columns of the matrix
	}

	/** Return the scalar value at the given row and column of the matrix
	 * 
	 * @param row
	 * @param col
	 * @return _data[row][col]
	 * @throws LinAlgException if row or col indices are out of bounds
	 */

	public double get(int row, int col) throws LinAlgException {
		
		if (row < 0 || col < 0 || row >= _nRows || col >= _nCols) { //out of bounds of dimensions error
			throw new LinAlgException("Index requested is out of bounds");
		}
		
		return _data[row][col]; //return the value of the matrix at the specified index
	}
	
	/** Return the Vector of numbers corresponding to the provided row index
	 * 
	 * @param row
	 * @return rowVector
	 * @throws LinAlgException if row is out of bounds
	 */
	
	public Vector getRow(int row) throws LinAlgException { 
	
		if (row < 0 || row >= _nRows) {
			throw new LinAlgException("Row requested is out of bounds");
		}
		
		Vector rowVector = new Vector(_nCols); //has to have enough space to hold all the values (number of columns) of that row of the matrix
			
		for (int j = 0; j < _nCols; j++) { 
			double val = this.get(row, j); //use 'get' from above to set val to value at the specifed index j in the requested row
			rowVector.set(row, val); //set value of our rowVector equal to val
		}
		
		return rowVector; //output the final rowVector
	}

	/** Set the row and col of this matrix to the provided val
	 * 
	 * @param row
	 * @param col
	 * @param val
	 * @throws LinAlgException if row or col indices are out of bounds
	 */
	
	public void set(int row, int col, double val) throws LinAlgException {
		
		if (row < 0 || row >= _nRows || col < 0 || col >= _nCols) { //dimension bounds exception
			throw new LinAlgException("Row or column index is out of bounds");
		}
		
		_data[row][col] = val; //set content of matrix at that index to val
	}
	
	/** Return a new Matrix that is the transpose of *this*, i.e., if "transpose"
	 *  is the transpose of Matrix m then for all row, col: transpose[row,col] = m[col,row]
	 *  (should not modify *this*)
	 * 
	 * @return transpose
	 * @throws LinAlgException
	 */
	
	public Matrix transpose() throws LinAlgException {
		Matrix transpose = new Matrix(_nCols, _nRows); //new matrix with enough rows as the original matrix's columns, and enough columns for the original matrix's rows
		
		for (int row = 0; row < _nRows; row++) {
			for (int col = 0; col < _nCols; col++) {
				transpose.set(col, row, get(row,col)); //instead of (row, col), the transpose does (col, row) and uses the value from (row, col) to put as content for (col, row)
			}
		}
		return transpose;
	}

	/** Return a new Matrix that is the square identity matrix (1's on diagonal, 0's elsewhere) 
	 *  with the number of rows, cols given by dim.  E.g., if dim = 3 then the returned matrix
	 *  would be the following:
	 *  
	 *  [ 1 0 0 ]
	 *  [ 0 1 0 ]
	 *  [ 0 0 1 ]
	 * 
	 * @param dim
	 * @return identity
	 * @throws LinAlgException if the dim is <= 0
	 */
	
	public static Matrix GetIdentity(int dim) throws LinAlgException {
			
		if (dim < 1) {
			throw new LinAlgException("Dimension cannot be less than 1");
		}
		
		Matrix identity = new Matrix(dim, dim); //creates a square matrix with dimension inputted
		
		for (int index = 0; index < dim; index++) {
			for (int j = 0; j < dim; j++) {
				if (index == j) {
						identity._data[index][j] = 1; //1's on diagonals
				}
				
				else {
						identity._data[index][j] = 0; //0's elsewhere
				}
			}
		}
           
		return identity;
	}

	/** Returns the Matrix result of multiplying Matrix m1 and m2
	 *  (look up the definition of matrix multiply if you don't remember it)
	 * 
	 * @param m1
	 * @param m2
	 * @return result
	 * @throws LinAlgException if m1 columns do not match the size of m2 rows
	 */
	
	public static Matrix Multiply(Matrix m1, Matrix m2) throws LinAlgException {
	
		if (m1._nCols != m2._nRows) { //the number of columns in matrix 1 has to equal the number of rows in matrix 2 for this multiplication
			throw new LinAlgException("Column dimension of matrix 1 does not match the dimension of matrix 2's rows");
		}
		
		Matrix result = new Matrix (m1._nRows, m2._nCols); //the result of the multiplication must have the dimensions of rows of matrix 1 and columns of the matrix 2
		double val = 0.0;
		
		for (int i = 0; i < m1._nRows; i++) { 
            for (int j = 0; j < m2._nCols; j++) { //for the each row of matrix 1, each element is multiplied with the corresponding element for each of the columns in matrix 2 and then the products of the multiplication  are added together for that row, to become the value of the element in the result matrix
                for (int k = 0; k < m2._nRows; k++) {  //that is why i cannot be >= rows of matrix 1, and why j cannot be >= columns of matrix 2. 
                	val += (m1._data[i][k] * m2._data[k][j]); //k has to be < the number of rows of matrix 2 to ensure this multiplication is possible and that the result matrix has the right dimensions as expected from the matrix multiplication formula
                }

                result.set(i,j,val); //set value of val
                val = 0.0; //reset for each iteration
            }
		}
	
		return result;
	}
		
	/** Returns the Vector result of multiplying Matrix m by Vector v (assuming v is a column vector)
	 * 
	 * @param m
	 * @param v
	 * @return resultVM
	 * @throws LinAlgException if m columns do match the size of v
	 */

	public static Vector Multiply(Matrix m, Vector v) throws LinAlgException {
		
		if (m._nCols != v.getDim()) { //the number of columns in the matrix has to equal the dimension of the column for this multiplication to occur
			throw new LinAlgException("Column dimension of the matrix does not match the dimension of the vector");
		}
		
		Vector resultVM = new Vector (m._nRows); //the result of the multiplication must have the dimensions of rows of the matrix and the columns of the vector (which is 1)
		double val = 0.0;
		
		for (int i = 0; i < m._nRows; i++) { 
            for (int j = 0; j < v.getDim(); j++) { //for the each row of the matrix, each element is multiplied with the corresponding element for the column in the vector and then the products of the multiplication  are added together for that row to become the value of the element in the result vector
                 //that is why i cannot be >= rows of the matrix, and why j cannot be >= size or dimension of the vector 
                	val += (m._data[i][j] * v.get(j)); 
            }
            	resultVM.set(i, val); //set value
            	val = 0.0; //reset for each iteration
        }
		return resultVM;
	}

}

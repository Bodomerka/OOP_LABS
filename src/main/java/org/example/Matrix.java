package org.example;

import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class Matrix implements MatrixInterface {
    private double[][] data;

    // Constructors
    public Matrix() {
        this.data = new double[0][0];
    }

    public Matrix(int rows, int cols) {
        data = new double[rows][cols];
    }

    public Matrix(double[][] data) {
        this.data = Arrays.copyOf(data, data.length);
    }

    public Matrix(ImmutableMatrix matrix){
        this.data = matrix.getData();
    }

    // Implementation of MatrixInterface methods
    @Override
    public int getRows() {
        return data.length;
    }

    @Override
    public int getCols() {
        return data.length > 0 ? data[0].length : 0;
    }

    @Override
    public String getDimensions() {
        return getRows() + "x" + getCols();
    }

    @Override
    public void printMatrix() {
        for (double[] row : data) {
            for (double value : row) {
                System.out.printf("%.2f ", value);
            }
            System.out.println();
        }
    }

    @Override
    public double[][] getData() {
        double[][] copy = new double[data.length][];
        for (int i = 0; i < data.length; i++) {
            copy[i] = Arrays.copyOf(data[i], data[i].length);
        }
        return copy;
    }

    @Override
    public double getElement(int row, int col) {
        return data[row][col];
    }

    public Matrix add(MatrixInterface other) {
        // Assuming other is of type Matrix
        Matrix matrixOther = (Matrix) other;
        if (this.getRows() != matrixOther.getRows() || this.getCols() != matrixOther.getCols()) {
            throw new IllegalArgumentException("Matrices dimensions must match for addition");
        }
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result.data[i][j] = this.data[i][j] + matrixOther.data[i][j];
            }
        }
        return result;
    }

    public Matrix multiplyByScalar(double scalar) {
        Matrix result = new Matrix(getRows(), getCols());
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                result.data[i][j] = this.data[i][j] * scalar;
            }
        }
        return result;
    }


    public Matrix multiply(MatrixInterface other) {
        // Assuming other is of type Matrix
        Matrix matrixOther = (Matrix) other;
        if (this.getCols() != matrixOther.getRows()) {
            throw new IllegalArgumentException("Matrices dimensions must be compatible for multiplication");
        }
        Matrix result = new Matrix(getRows(), matrixOther.getCols());
        for (int i = 0; i < result.getRows(); i++) {
            for (int j = 0; j < result.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    result.data[i][j] += this.data[i][k] * matrixOther.data[k][j];
                }
            }
        }
        return result;
    }

    public Matrix transpose() {
        double[][] transposedData = new double[getCols()][getRows()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                transposedData[j][i] = this.data[i][j];
            }
        }
        this.data = transposedData;
        return this;
    }

    // Utility methods for special matrices
    public static Matrix diagonal(double[] vector) {
        Matrix result = new Matrix(vector.length, vector.length);
        for (int i = 0; i < vector.length; i++) {
            result.data[i][i] = vector[i];
        }
        return result;
    }

    public void fillWithRandomValues(double min, double max, int decimalPlaces) {
        Random random = new Random();
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                double value = min + (max - min) * random.nextDouble();
                BigDecimal bd = new BigDecimal(value).setScale(decimalPlaces, RoundingMode.HALF_UP);
                data[i][j] = bd.doubleValue();
            }
        }
    }

    public static Matrix identity(int size) {
        Matrix result = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            result.data[i][i] = 1.0;
        }
        return result;
    }

    public static Matrix randomRowMatrix(int cols) {
        Matrix result = new Matrix(1, cols);
        Random rand = new Random();
        for (int j = 0; j < cols; j++) {
            result.data[0][j] = rand.nextDouble();
        }
        return result;
    }

    public static Matrix randomColumnMatrix(int rows) {
        Matrix result = new Matrix(rows, 1);
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            result.data[i][0] = rand.nextDouble();
        }
        return result;
    }

    private double determinant(double[][] matrix) {
        double det = 0;
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        for (int i = 0; i < matrix[0].length; i++) {
            double[][] subMatrix = new double[matrix.length - 1][matrix[0].length - 1];
            for (int m = 1; m < matrix.length; m++) {
                for (int n = 0; n < matrix[0].length; n++) {
                    if (n < i) {
                        subMatrix[m - 1][n] = matrix[m][n];
                    } else if (n > i) {
                        subMatrix[m - 1][n - 1] = matrix[m][n];
                    }
                }
            }
            det += matrix[0][i] * Math.pow(-1, i) * determinant(subMatrix);
        }
        return det;
    }

    public Matrix inverse() {
        double det = determinant(this.data);
        if (det == 0) {
            throw new IllegalStateException("Matrix is singular and cannot be inverted.");
        }
        double[][] adjoint = adjoint(this.data);
        Matrix inverseMatrix = new Matrix(this.getRows(), this.getCols());
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                inverseMatrix.data[i][j] = adjoint[i][j] / det;
            }
        }
        return inverseMatrix;
    }

    private double[][] adjoint(double[][] matrix) {
        double[][] adj = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                adj[i][j] = Math.pow(-1, i + j) * determinant(minor(matrix, i, j));
            }
        }
        return transpose(adj);
    }

    private double[][] minor(double[][] matrix, int row, int col) {
        double[][] minor = new double[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i != row && j != col) {
                    minor[i < row ? i : i - 1][j < col ? j : j - 1] = matrix[i][j];
                }
            }
        }
        return minor;
    }


    private double[][] transpose(double[][] matrix) {
        double[][] transposedMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                transposedMatrix[j][i] = matrix[i][j];
            }
        }
        return transposedMatrix;
    }

    public int hashCode() {
        return Arrays.deepHashCode(data);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MatrixInterface)) {
            return false;
        }
        MatrixInterface matrix = (MatrixInterface) obj;
        if (this.getRows() != matrix.getRows() || this.getCols() != matrix.getCols()) {
            return false;
        }
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                if (Double.compare(this.getElement(i, j), matrix.getElement(i, j)) != 0) {
                    return false;
                }
            }
        }
        return true;
    }


}

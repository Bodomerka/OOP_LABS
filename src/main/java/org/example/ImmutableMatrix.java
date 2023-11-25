package org.example;

import java.util.Arrays;
import java.util.Random;
import java.math.RoundingMode;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;


public final class ImmutableMatrix implements MatrixInterface {
    private final double[][] data;

    public static ImmutableMatrix of(double[][] data) {
        return new ImmutableMatrix(data);
    }

    // Constructor
    public ImmutableMatrix(int rows, int cols) {
        data = new double[rows][cols];
    }

    public ImmutableMatrix(Matrix matrix){
        this.data = matrix.getData();
    }

    // Private constructor for internal use
    private ImmutableMatrix(double[][] data) {
        this.data = cloneArray(data);
    }

    // Clone array to ensure immutability
    private static double[][] cloneArray(double[][] array) {
        double[][] newArray = new double[array.length][];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i].clone();
        }
        return newArray;
    }

    // Implementation of MatrixInterface methods
    @Override
    public int getRows() {
        return data.length;
    }

    @Override
    public int getCols() {
        return data[0].length;
    }

    @Override
    public double getElement(int row, int col) {
        return data[row][col];
    }

    @Override
    public String getDimensions() {
        return getRows() + "x" + getCols();
    }

    @Override
    public double[][] getData() {
        return cloneArray(data);
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

    public ImmutableMatrix add(MatrixInterface other) {
        // Перевірка, чи розміри матриць сумісні
        if (this.getRows() != other.getRows() || this.getCols() != other.getCols()) {
            throw new IllegalArgumentException("Matrices dimensions must match for addition");
        }

        // Створення нової матриці для результату
        double[][] resultData = new double[this.getRows()][this.getCols()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                resultData[i][j] = this.data[i][j] + other.getElement(i, j);
            }
        }

        return new ImmutableMatrix(resultData);
    }


    public ImmutableMatrix multiplyByScalar(double scalar) {
        double[][] resultData = new double[getRows()][getCols()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                resultData[i][j] = this.data[i][j] * scalar;
            }
        }
        return new ImmutableMatrix(resultData);
    }


    public ImmutableMatrix multiply(MatrixInterface other) {
        // Assuming other is of type ImmutableMatrix
        ImmutableMatrix matrixOther = (ImmutableMatrix) other;
        if (this.getCols() != matrixOther.getRows()) {
            throw new IllegalArgumentException("Matrices dimensions must be compatible for multiplication");
        }
        double[][] resultData = new double[getRows()][matrixOther.getCols()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < matrixOther.getCols(); j++) {
                for (int k = 0; k < this.getCols(); k++) {
                    resultData[i][j] += this.data[i][k] * matrixOther.data[k][j];
                }
            }
        }
        return new ImmutableMatrix(resultData);
    }


    public ImmutableMatrix transpose() {
        double[][] resultData = new double[getCols()][getRows()];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                resultData[j][i] = this.data[i][j];
            }
        }
        return new ImmutableMatrix(resultData);
    }


    public ImmutableMatrix inverse() {
        double det = determinant(this.data);
        if (det == 0) {
            throw new IllegalStateException("Детермінат матриці = 0 ");
        }
        double[][] adjoint = adjoint(this.data);
        double[][] inverseData = new double[this.getRows()][this.getCols()];
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                inverseData[i][j] = adjoint[i][j] / det;
            }
        }
        return new ImmutableMatrix(inverseData);
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

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(data);
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
    // Static utility methods
    public static ImmutableMatrix diagonal(double[] vector) {
        double[][] resultData = new double[vector.length][vector.length];
        for (int i = 0; i < vector.length; i++) {
            resultData[i][i] = vector[i];
        }
        return new ImmutableMatrix(resultData);
    }

    public static ImmutableMatrix identity(int size) {
        double[][] resultData = new double[size][size];
        for (int i = 0; i < size; i++) {
            resultData[i][i] = 1.0;
        }
        return new ImmutableMatrix(resultData);
    }

    public static ImmutableMatrix randomRowMatrix(int cols) {
        double[][] resultData = new double[1][cols];
        Random rand = new Random();
        for (int j = 0; j < cols; j++) {
            resultData[0][j] = rand.nextDouble();
        }
        return new ImmutableMatrix(resultData);
    }

    public static ImmutableMatrix randomColumnMatrix(int rows) {
        double[][] resultData = new double[rows][1];
        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            resultData[i][0] = rand.nextDouble();
        }
        return new ImmutableMatrix(resultData);
    }
}

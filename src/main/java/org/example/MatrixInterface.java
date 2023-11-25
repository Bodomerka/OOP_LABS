package org.example;
import java.util.ArrayList;

public interface MatrixInterface {
    int getRows();
    int getCols();
    double getElement(int row, int col);
    void printMatrix();
    String getDimensions();
    double[][] getData();


}

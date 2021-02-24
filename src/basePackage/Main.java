package basePackage;

// import date/time class
import java.lang.*;
import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        //INITIALIZATIONS
        final int SIZE = 10;
        long startTime;
        char zWorld[][] = createWorld(SIZE);

        //MEASURING TOTAL TIME SPENT - STARTING NOW
        startTime = System.nanoTime();

        //RANDOMIZE LOCATION FOR PATIENT ZERO
        int x = 2, y = 2;       //coordinates of Patient Zero



        populate(zWorld);       //fill array with a period character '.'
        outputWorld(zWorld);    //print the matrix
        infect(zWorld, x, y);   //place a zombie 'Z' at the location of Patient Zero


        //MEASURE TOTAL TIME SPENT
        long endTime = System.nanoTime() - startTime;
        System.out.println("Runtime in nanoseconds: " + endTime);
    }




    // write methods for createWorld, populate, outputWorld

    public static char[][] createWorld(int size) {
        char[][] population = new char[size][size];
        return population;
    }

    public static void populateWorld(char[][] world) {
        for (int i = 0; i < world.length; i++) {

            for (int j = 0; j < world[i].length; j++) {

                world[i][j] = '.';
            } //end of inner loop
        } // end of outer loop


    }

    public static void outputWorld(char[][] world) {
        for (int i = 0; i < world.length; i++) {

            for (int j = 0; j < world[i].length; j++) {

                System.out.print(world[i][j] + " ");
            } //end of inner loop

            System.out.println();
        } // end of outer loop

        System.out.println();
    }


    //MELISSA'S METHODS
    public static void infect(char matrix[][], int x, int y) {
        //method to check the nearest neighbors of a given cell, then move "out"
        int row = 0, col = 0;
        int numIterations = 1;
        int loopCount = 0, errorCount = 0;

        int furthestHorizontal = 0;
        int furthestVertical = 0;
        int distanceHorizontal = 0;
        int distanceVertical = 0;

        int maxIterations;
        float midPoint = (matrix.length - 1) * 0.5f;




        //FIND THE FURTHEST CORNER
        if (midPoint > x) {
            furthestHorizontal = matrix.length - 1;
        }

        if (midPoint > y) {
            furthestVertical = matrix.length - 1;
        }

        distanceHorizontal = furthestHorizontal - x;
        distanceVertical = furthestHorizontal - y;
        //System.out.println(distanceHorizontal + " " + distanceVertical);

        //CALCULATE APPROPRIATE REDUCTION TO MAX ITERATIONS
        if ( distanceHorizontal >= distanceVertical )
            maxIterations = distanceHorizontal;

        else if (distanceVertical > distanceHorizontal)
            maxIterations = distanceVertical;

        else //CATCH ALL
            maxIterations = matrix.length -1;



        matrix[x][y] = 'Z';    // these two lines are not necessary for logic but
        outputWorld(matrix);   // print the Patient Zero matrix

        while (numIterations <= maxIterations) {
            for (row = x - numIterations; row <= x + numIterations; row++) {
                for (col = y - numIterations; col <= y + numIterations; col++) {

                    if ( row >= 0 && row < matrix.length)  {
                        if ( col >= 0 && col < matrix.length ) {
                            if (matrix[row][col] != 'i')
                                matrix[row][col] = 'Z';

                        }


                    }

                    /* INEFFICIENT METHOD: Try always, ignore non-existant indexes.
                    try {
                        matrix[row][col] = 'Z';
                    }
                    catch (IndexOutOfBoundsException ex) {
                        errorCount++;
                        //do nothing, just keep running
                        //this method, as written, goes out of bounds a lot
                    }
                    */

                    loopCount++;
                } //end of inner for loop
            }//end of outer for loop

            numIterations++;
            outputWorld(matrix);
        }//end of while loop

        // implement loopCount and errorCount
        System.out.println("Loop Count:  " + loopCount);
        System.out.println("Exceptions:  " + errorCount);
    }

    public static void populate(char matrix[][]) {
        // pass in immunity rate as a parameter, i.e. .10 = 10% immunity
        int numImmune = 0;
        float immunityPercent = 10.00f;

        // within the loop below, devise some way to randomly assign an 'i' to some
        // elements, based on immunity rate.  So, if the immunity rate is 10%, then
        // 10 of 100 people are immune; 100 of 1000 people are immune, etc.
        // You can do this with an "immunity" method or within populate

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

                if (immunity(immunityPercent)) {
                    matrix[i][j] = 'i';
                    numImmune++;
                }

                else
                    matrix[i][j] = '.';

            }//end of inner for loop
        }//end of outer for loop

        //System.out.println("Num of immuners: " + numImmune);
    }//end of method

    public static boolean immunity(float immunityChance) {
        boolean isImmune = false;
        int randValue = (int) (Math.random() * 100 + 1);

        if (randValue >= 100 - immunityChance) {
            //System.out.printf("Pass Chance: " + (100 - immunityChance) + "\nPass Value: " + randValue + "\n");
            isImmune = true;
        }

        return isImmune;
    }//end of method


}  //end class
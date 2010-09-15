/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//TODO: ADD A CLASS THAT HANDLES, SORTS, AND PRODUCES OFFSPRING FOR TUPLES WXYZ
package _hw1_java;

import java.util.Arrays;
import java.util.Random;
//import java.util.HashMap;
//import java.util.Collections;
/**
 *
 * @author jackson
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      //VARIABLE SETUP PERTAINING TO AMOUNT OF TUPLES
      int numTuples = 8;
      int numToKeep = 4;
      int keepIndex = numTuples - numToKeep;
      //HOW MANY TIMES TO DO THIS
      int iterations = 10;
      //HOW OFTEN TO PRINT OUT A REPORT
      int printInterval = 1;
      //RANDOM SEED
      Random rand = new Random();

      //SETUP TUPLE OBJECTS, GETTING DEFAULT RANDOM VALUES ASSIGNED
      wxyz tuples[] = new wxyz[numTuples];
      for(int i = 0; i < numTuples; i++){
        tuples[i] = new wxyz();
      }

      //LOOP WHERE THE MAGIC HAPPENS
      for(int iter = 0; iter < iterations; iter++){
        //SOLUTIONS ARRAY SINCE I CANT FIGURE OUT HOW TO SORT AN OBJECT ARRAY BY OBJECT ELEMENT VALUE
        int solutions[] = new int[numTuples];

        //SHOW ALL THE TUPLES FROM THIS GENERATION
        if(iter % printInterval == 0){
          System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
          System.out.println("ALL TUPLES GENERATION: " + iter);
          System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
        }
        for (int i=0; i<numTuples; i++) {
          solutions[i] = tuples[i].getSolution();
          if(iter % printInterval == 0){
             System.out.println("SOLUTION " + i + ": " + solutions[i]);
             tuples[i].printAll();
          }
        }

        //TODO: THIS SORTING/CLEARING PART IS REALLY MESSY/EXPENSIVE
        //THERE'S GOT TO BE A BETTER WAY
        //LOOPS THROUGH SORTED SOLUTIONS AND IF WITHIN BEST SOLUTIONS {keepIndex}
        //SET STATUS TO ALIVE
        //ALSO CAUSES MORE THAN {numTopKeep} TO BE MARKED AS ALIVE SINCE SOME MAY BE EQUAL
        Arrays.sort(solutions);
        for (int i=keepIndex; i<numTuples; i++) {
          for (int j=0; j<numTuples; j++) {
            if(tuples[j].getSolution() == solutions[i]){
              tuples[j].itsAlive();
            }
          }
        }
        //HANDLING THE SURVIVING TUPLES
        wxyz goodtuples[] = new wxyz[numToKeep];
        if(iter % printInterval == 0){System.out.println("SURVIVING TUPLES");}
        int gt = 0;
        for (int i=0; i<numTuples; i++) {
          if(!tuples[i].isDead()){
            //SHOWING THE SURVIVNG TUPLES
            if(iter % printInterval == 0){System.out.println("SOLUTION " + i + ": " + tuples[i].getSolution());}
            //LET'S GO AHEAD AND RANDOMLY MUTATE, 10% CHANCE OF MUTATION
            if(rand.nextInt(10) == 0){
              tuples[i].randomMutate();
            }
            //System.out.println(gt);
            if(gt == numToKeep){
              gt = numToKeep - 1;
            }
            goodtuples[gt] = tuples[i];
            gt++;
          }
        }
        //END SORTING/CLEARING

        //GENETIC MIXING FROM THE SURVIVORS (RANDOM MIXING)
        //RESET ALL TUPLES TO DEAD
        for (int i=0; i<numTuples; i++) {
          int randW = rand.nextInt(4);
          int randX = rand.nextInt(4);
          int randY = rand.nextInt(4);
          int randZ = rand.nextInt(4);
          //System.out.println("RANDW: " + randW);
          //System.out.println(goodtuples[randW].getW());
          tuples[i].setW(goodtuples[randW].getW());
          tuples[i].setX(goodtuples[randX].getX());
          tuples[i].setY(goodtuples[randY].getY());
          tuples[i].setZ(goodtuples[randZ].getZ());
          tuples[i].dead = true;
        }
      }
    }
}
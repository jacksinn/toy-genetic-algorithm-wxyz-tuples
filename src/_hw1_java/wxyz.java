package _hw1_java;

import java.util.Random;
//import java.lang.Math;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jackson
 */
public class wxyz {
  private int w;
  private int x;
  private int y;
  private int z;
  public boolean dead;

  //default constructor, provides random numbers as base
  public wxyz (){
    Random rand = new Random();
    this.w = rand.nextInt() % 4096;
    this.x = rand.nextInt() % 4096;
    this.y = rand.nextInt() % 4096;
    this.z = rand.nextInt() % 4096;
    this.dead = true;
  }
  public wxyz (int w, int x, int y, int z) {
    this.w = w;
    this.x = x;
    this.y = y;
    this.z = z;
    this.dead = true;
  }

  //print methods
  public void printAll(){
    System.out.println("W: " + w + "\nX: " + x + "\nY: " + y + "\nZ: " + z);
  }
  public void printW(){
    System.out.println(w);
  }
  public void printX(){
    System.out.println(x);
  }
  public void printY(){
    System.out.println(y);
  }
  public void printZ(){
    System.out.println(z);
  }

  //set methods
  public void setW(int w){
    this.w = w;
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setZ(int z){
    this.z = z;
  }

  public void itsAlive(){
    this.dead = false;
  }

  //get methods
  public int getW(){
    return this.w;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public int getZ(){
    return this.z;
  }
  public boolean isDead(){
    return this.dead;
  }
  //return mathematical value
  public int getSolution(){
    int solution = 0;
    //w^2 + 100w - x^2 - 40x - y^2 + 80y - z^2 - 200z
    solution = (this.w*this.w) + (100*this.w) - (this.x*this.x) - (40*this.x) - (this.y*this.y) + (80*this.y) - (this.z*this.z) - (200*this.z);
    //best result would be  w=50, x=-20, y=40, z=-100 based on 14500 being the max (per lesson 2 slides)
    return solution;
  }

  //provide a random mutation of one of the tuple elements
  public void randomMutate(){
    Random rand = new Random();
    int r = rand.nextInt() % 4096;
    //System.out.println(r);

    int choice = rand.nextInt() % 4;

    if(choice == 0){
      this.w = r;
    } else if (choice == 1){
      this.x = r;
    } else if (choice == 2){
      this.y = r;
    } else {
      this.z = r;
    }
  }


  //zero out the tuple -- used if tuple didn't make the cut
  public void resetTuple(){
    Random rand = new Random();
    this.w = rand.nextInt() % 4096;
    this.x = rand.nextInt() % 4096;
    this.y = rand.nextInt() % 4096;
    this.z = rand.nextInt() % 4096;
    this.dead = true;
  }
}

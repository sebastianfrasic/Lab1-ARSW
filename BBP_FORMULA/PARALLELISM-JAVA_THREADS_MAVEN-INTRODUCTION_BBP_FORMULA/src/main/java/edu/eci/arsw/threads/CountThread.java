/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThread extends Thread{

    private int inicial;
    private int finall;

    public CountThread(int A, int B){
        this.inicial = A;
        this.finall = B;

    }

    @Override
    public void run() {
        for(int i = inicial; i<finall; i++){
            System.out.println(i);
        }
    }

}

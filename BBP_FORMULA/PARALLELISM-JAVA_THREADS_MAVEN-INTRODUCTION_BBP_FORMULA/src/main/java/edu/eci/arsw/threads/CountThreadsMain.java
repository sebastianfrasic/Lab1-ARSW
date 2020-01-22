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
public class CountThreadsMain {

    private CountThread hiloUno;
    private CountThread hiloDos;
    private CountThread hiloTres;

    
    public static void main(String a[]){
        CountThread hiloUno = new CountThread(0,99);
        CountThread hiloDos = new CountThread(999,1999);
        CountThread hiloTres = new CountThread(2000,2999);
        /*
        hiloUno.run();
        hiloDos.run();
        hiloTres.run();*/  //Con run() se hace secuencial

        hiloUno.start();
        hiloDos.start();
        hiloTres.start();


    }
    
}

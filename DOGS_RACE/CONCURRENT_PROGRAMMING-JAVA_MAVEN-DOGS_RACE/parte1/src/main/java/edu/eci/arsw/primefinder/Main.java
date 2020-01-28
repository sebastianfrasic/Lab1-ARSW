package edu.eci.arsw.primefinder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class Main {

    private static PrimeFinderThread[] listaDeHilos = new PrimeFinderThread[3];
    private static Timer timer = new Timer(5000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            pausarHilos();
            imprimirPrimos();
            leerEnter();
        }
    });

    public static void main(String[] args) {
        int first = 0;
        int last = 10000000;
        for (int i = 0; i < 3; i++) {
            PrimeFinderThread pft = new PrimeFinderThread(first, last);
            pft.start();
            listaDeHilos[i] = pft;
            first = last + 1;
            last += 10000000;
        }
        timer.start();
        for (int i = 0; i < 3; i++) {
            try {
                listaDeHilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Este es el resultado final...");
        imprimirPrimos();
    }

    public static void pausarHilos() {
        for (int i = 0; i < listaDeHilos.length; i++) {
            listaDeHilos[i].suspend();
        }
    }

    private static void imprimirPrimos() {
        for (int i = 0; i < 3; i++) {
            System.out.println(listaDeHilos[i].getPrimes());
        }
    }

    private static void leerEnter() {
        System.out.println("Presione Enter para ver más resultados");
        Scanner t = new Scanner(System.in);
        String enterkey = t.nextLine();
        if (enterkey.isEmpty()) {
            reanudarHilos();
        }
    }

    private static void reanudarHilos() {
        for (int i = 0; i < listaDeHilos.length; i++) {
            listaDeHilos[i].resume();
        }
    }


}

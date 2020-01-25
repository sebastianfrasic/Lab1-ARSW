/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.math;
/**
 * @author hcadavid
 */
public class Main {


    public static void main(String a[]) throws InterruptedException {

        System.out.println("Con un solo hilo: ");
        long iniUnHilo = System.currentTimeMillis();
        System.out.println(bytesToHex(PiDigits.getDigits(2, 300000)));
        long finUnHilo = System.currentTimeMillis();
        System.out.println("Con un hilo el programa se tardó: " + (finUnHilo - iniUnHilo) + " milisegundos. \n");


        int numeroDeHilos = 500;
        System.out.println("Con " + numeroDeHilos + " hilos: \n");
        long ini = System.currentTimeMillis();
        System.out.println(bytesToHex(PiDigits.getDigits(2, 3000000, numeroDeHilos)));
        long fin = System.currentTimeMillis();
        System.out.println("Con " + numeroDeHilos + " hilos el programa se tardó: " + (fin - ini) + " milisegundos.");



        Runtime r = Runtime.getRuntime();
        System.out.println(r.availableProcessors());



    }


    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hexChars.length; i = i + 2) {
            //sb.append(hexChars[i]);
            sb.append(hexChars[i + 1]);
        }
        return sb.toString();
    }

}

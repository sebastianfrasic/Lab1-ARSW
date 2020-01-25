package edu.eci.arsw.math;

public class PiDigitsThread extends Thread {
    private int start;
    private int count;
    private byte[] digitsInHex;

    public PiDigitsThread(int start, int count) {
        this.start = start;
        this.count = count;
        digitsInHex = new byte[this.count];
    }

    @Override
    public void run() {
        long ini = System.currentTimeMillis();
        System.out.println("Nuevo hilo empezando:");
        System.out.println("Start: " + this.start + ", Count: " + this.count);

        this.digitsInHex = PiDigits.getDigits(start, count);

        long fin = System.currentTimeMillis();
        System.out.println("Tiempo total del hilo: " + (fin - ini) + " milisegundo(s).\n");
    }


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public byte[] getDigitsInHex() {
        return digitsInHex;
    }

    public void setDigitsInHex(byte[] digitsInHex) {
        this.digitsInHex = digitsInHex;
    }
}



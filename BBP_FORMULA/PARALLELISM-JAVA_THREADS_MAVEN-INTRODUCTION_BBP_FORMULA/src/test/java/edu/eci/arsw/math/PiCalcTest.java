/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.eci.arsw.math;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author hcadavid
 */

public class PiCalcTest {
    private byte[] expected = new byte[]{
            0x2, 0x4, 0x3, 0xF, 0x6, 0xA, 0x8, 0x8,
            0x8, 0x5, 0xA, 0x3, 0x0, 0x8, 0xD, 0x3,
            0x1, 0x3, 0x1, 0x9, 0x8, 0xA, 0x2, 0xE,
            0x0, 0x3, 0x7, 0x0, 0x7, 0x3, 0x4, 0x4,
            0xA, 0x4, 0x0, 0x9, 0x3, 0x8, 0x2, 0x2,
            0x2, 0x9, 0x9, 0xF, 0x3, 0x1, 0xD, 0x0,
            0x0, 0x8, 0x2, 0xE, 0xF, 0xA, 0x9, 0x8,
            0xE, 0xC, 0x4, 0xE, 0x6, 0xC, 0x8, 0x9,
            0x4, 0x5, 0x2, 0x8, 0x2, 0x1, 0xE, 0x6,
            0x3, 0x8, 0xD, 0x0, 0x1, 0x3, 0x7, 0x7,};

    public PiCalcTest() throws InterruptedException {
    }

    @Before
    public void setUp() {
    }

    @Test
    public void piGenTest() throws Exception {


        for (int start = 0; start < expected.length; start++) {
            for (int count = 0; count < expected.length - start; count++) {
                byte[] digits = PiDigits.getDigits(start, count);
                assertEquals(count, digits.length);

                for (int i = 0; i < digits.length; i++) {
                    assertEquals(expected[start + i], digits[i]);
                }
            }
        }
    }

    @Test
    public void numeroParDeHilos() throws InterruptedException {
        String cadena1 = Main.bytesToHex(PiDigits.getDigits(2, 100));
        String cadena2 = Main.bytesToHex(PiDigits.getDigits(2, 100, 4));
        assertEquals(cadena1,cadena2);

        String cadena3 = Main.bytesToHex(PiDigits.getDigits(0, 1000));
        String cadena4 = Main.bytesToHex(PiDigits.getDigits(0, 1000, 8));
        assertEquals(cadena3,cadena4);

        String cadena5 = Main.bytesToHex(PiDigits.getDigits(0, 350));
        String cadena6 = Main.bytesToHex(PiDigits.getDigits(0, 350, 18));
        assertEquals(cadena5,cadena6);
    }

    public void numeroImparDeHilos() throws InterruptedException{
        String cadena1 = Main.bytesToHex(PiDigits.getDigits(1, 100));
        String cadena2 = Main.bytesToHex(PiDigits.getDigits(2, 100, 5));
        assertEquals(cadena1,cadena2);

        String cadena3 = Main.bytesToHex(PiDigits.getDigits(10, 10000));
        String cadena4 = Main.bytesToHex(PiDigits.getDigits(10, 10000, 25));
        assertEquals(cadena3,cadena4);

        String cadena5 = Main.bytesToHex(PiDigits.getDigits(20, 1200));
        String cadena6 = Main.bytesToHex(PiDigits.getDigits(20, 1200, 13));
        assertEquals(cadena3,cadena4);
    }

    @Test
    public void startCorrecto() throws InterruptedException {
        assertEquals(Main.bytesToHex(PiDigits.getDigits(1, 10, 2)), "43F6A8885A");
        assertEquals(Main.bytesToHex(PiDigits.getDigits(0, 10, 3)), "243F6A8885");
        try {
            Main.bytesToHex(PiDigits.getDigits(-1, 3, 2));
            fail("Deberia tener un intervalo invalido");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Invalid Interval");
        }


    }

    @Test
    public void countCorrecto() throws InterruptedException {
        assertEquals(Main.bytesToHex(PiDigits.getDigits(10,1,2)),"A");
        assertEquals(Main.bytesToHex(PiDigits.getDigits(100,0,2)),"");

        try{
            Main.bytesToHex(PiDigits.getDigits(1,-1,2));
            fail("Debería tener un Intervalo Invalido");
        }catch (RuntimeException e){
            assertEquals(e.getMessage(),"Invalid Interval");
        }
    }

    @Test
    public void numeroHilosCorrectos() throws InterruptedException{
        String valor = "43F6A8885A308D313198A2E03707344A4093822299F31D0082EFA98EC4E6C89452821E638D01377BE5466CF34E90C6CC0AC2";
        assertEquals(Main.bytesToHex(PiDigits.getDigits(1,100,1)),valor);

        try{
            Main.bytesToHex(PiDigits.getDigits(2,1000,0));
            fail("Debería tener un Número de Threads Inválido");
        }catch (RuntimeException e){
            assertEquals(e.getMessage(),"Invalid number of threads");
        }

        try{
            Main.bytesToHex(PiDigits.getDigits(1,1500,-1));
            fail("Debería tener un Número de Threads Inválido");
        }catch (RuntimeException e){
            assertEquals(e.getMessage(),"Invalid number of threads");
        }
    }

}

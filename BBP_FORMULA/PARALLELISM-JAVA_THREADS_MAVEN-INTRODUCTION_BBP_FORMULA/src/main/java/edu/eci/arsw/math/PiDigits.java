package edu.eci.arsw.math;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;
    private static byte[] digits;


    /**
     * Returns a range of hexadecimal digits of pi.
     *
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count) {
        verificarIntervalos(start, count);

        byte[] digits = new byte[count];
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (i % DigitsPerSum == 0) {
                sum = 4 * sum(1, start)
                        - 2 * sum(4, start)
                        - sum(5, start)
                        - sum(6, start);

                start += DigitsPerSum;
            }

            sum = 16 * (sum - Math.floor(sum));
            digits[i] = (byte) sum;
        }

        return digits;
    }


    /**
     * Returns a range of hexadecimal digits of pi.
     *
     * @param start         The starting location of the range.
     * @param count         The number of digits to return
     * @param numeroDeHilos Number of threads that the program will run
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int numeroDeHilos) throws InterruptedException {
        verificarIntervalos(start, count);
        if (numeroDeHilos <= 0) {
            throw new RuntimeException("Invalid number of threads");
        }
        byte[] listaDeDigitosFinal = new byte[count];
        PiDigitsThread[] hilos = new PiDigitsThread[numeroDeHilos];
        int inicio = 0;
        int subsecuencia = count / numeroDeHilos;
        for (int i = 0; i < numeroDeHilos; i++) {

            int numeroDeDigitosPorHilo;
            if (i == (numeroDeHilos - 1)) {
                int parteSobrante = count % numeroDeHilos;
                numeroDeDigitosPorHilo = subsecuencia + parteSobrante;
            } else {
                numeroDeDigitosPorHilo = subsecuencia;
            }
            hilos[i] = new PiDigitsThread(start, numeroDeDigitosPorHilo);
            hilos[i].start();
            hilos[i].join();

            byte[] listaDeDigitosDeLaSubsecuencia = hilos[i].getDigitsInHex();
            System.arraycopy(listaDeDigitosDeLaSubsecuencia, 0, listaDeDigitosFinal, inicio, listaDeDigitosDeLaSubsecuencia.length); //Para ir concatenando el arreglo final de bytes
            start += subsecuencia;
            inicio += subsecuencia;
        }
        return listaDeDigitosFinal;
    }

    private static double sum(int m, int n) {
        double sum = 0;
        int d = m;
        int power = n;

        while (true) {
            double term;

            if (power > 0) {
                term = (double) hexExponentModulo(power, d) / d;
            } else {
                term = Math.pow(16, power) / d;
                if (term < Epsilon) {
                    break;
                }
            }

            sum += term;
            power--;
            d += 8;
        }

        return sum;
    }

    private static int hexExponentModulo(int p, int m) {
        int power = 1;
        while (power * 2 <= p) {
            power *= 2;
        }

        int result = 1;

        while (power > 0) {
            if (p >= power) {
                result *= 16;
                result %= m;
                p -= power;
            }

            power /= 2;

            if (power > 0) {
                result *= result;
                result %= m;
            }
        }

        return result;
    }

    private static void verificarIntervalos(int start, int count) {
        if (start < 0) {
            throw new RuntimeException("Invalid Interval");
        }
        if (count < 0) {
            throw new RuntimeException("Invalid Interval");
        }
    }


}

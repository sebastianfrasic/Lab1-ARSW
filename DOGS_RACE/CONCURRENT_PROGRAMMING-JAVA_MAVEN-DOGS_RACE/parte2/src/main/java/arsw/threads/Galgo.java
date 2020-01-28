package arsw.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Un galgo que puede correr en un carril
 *
 * @author rlopez
 */
public class Galgo extends Thread {
    private int paso;
    private Carril carril;
    RegistroLlegada regl;
    private boolean estaEnPausa;

    public Galgo(Carril carril, String name, RegistroLlegada reg) {
        super(name);
        this.carril = carril;
        paso = 0;
        this.regl = reg;
    }

    public void corra() throws InterruptedException {
        while (paso < carril.size()) {
            Thread.sleep(100);
            carril.setPasoOn(paso++);
            carril.displayPasos(paso);

            if (paso == carril.size()) {
                carril.finish();
                //AtomicInteger ubicacion = regl.getUltimaPosicionAlcanzada();
                //int nuevaPosicionDeLlegada = ubicacion.getAndAdd(1);
                //int nuevaPosicionDeLlegada = ubicacion.getAndIncrement();
                int ubicacion = regl.incrementoRegistroLlegada();

                System.out.println("El galgo #" + this.getName() + " llegó en la posición " + ubicacion);
                if (ubicacion == 1) {
                    regl.setGanador(this.getName());
                }
            }

            synchronized (this) {
                while (this.estaEnPausa) {
                    wait();
                }
            }
        }
    }


    @Override
    public void run() {

        try {
            corra();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void pausar() {
        this.estaEnPausa = true;
        notifyAll();
    }

    public synchronized void reanudar() {
        this.estaEnPausa = false;
        notifyAll();
    }

}

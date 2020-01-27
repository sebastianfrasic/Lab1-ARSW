package arsw.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class RegistroLlegada {

	private AtomicInteger ultimaPosicionAlcanzada= new AtomicInteger(1);

	private String ganador=null;
	
	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	public AtomicInteger getUltimaPosicionAlcanzada() {
		return ultimaPosicionAlcanzada;
	}

	public void setUltimaPosicionAlcanzada(AtomicInteger ultimaPosicionAlcanzada) {
		this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
	}

	public int incrementoRegistroLlegada() {
		return ultimaPosicionAlcanzada.getAndIncrement();
	}

	
	
}

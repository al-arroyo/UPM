package servidor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Aviso {

	private Lock lock;
	private Condition condition;
	/**
	 * Constructor de aviso.
	 * @param condition
	 * @param lock
	 */
	public Aviso (Condition condition, Lock lock) {
		this.lock = lock;
		this.condition = condition;
	}
	/**
	 * Zona critica para avisar al Hilo Encargo despues
	 * de transcodificar el video.
	 */
	public void avisar () {
		lock.lock();
		try {
			condition.signal();
		}finally {
			lock.unlock();
		}
	}
}

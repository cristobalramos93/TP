package simulator.view;

import javax.sound.sampled.Control;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class Hilos extends Thread {
	JSpinner pasos;
	long i;
	ControlPanel control;
	Thread _thread;
	JButton botonCarpeta;
	JButton botonGravedad;
	JButton botonPlay;
	JTextField delta;
	JSpinner delay;
	
	public Hilos(JSpinner pasos, long i, ControlPanel control, Thread _thread, JButton botonCarpeta,
			JButton botonGravedad, JButton botonPlay, JTextField delta,JSpinner delay) {
		super();
		this.pasos = pasos;
		this.i = i;
		this.control = control;
		this._thread = _thread;
		this.botonCarpeta = botonCarpeta;
		this.botonGravedad = botonGravedad;
		this.botonPlay = botonPlay;
		this.delta = delta;
		this.delay = delay;
	}

	@Override
	public void run() {
		
		control.run_sim((Integer)pasos.getValue(),i);
		
		botonCarpeta.setEnabled(true);
		botonGravedad.setEnabled(true);
		botonPlay.setEnabled(true);
		pasos.setEnabled(true);
		delta.setEnabled(true);
		delay.setEnabled(true);
		_thread = null;
	}
	
}

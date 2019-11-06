package simulator.view;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class ControlPanel extends JPanel implements SimulatorObserver {

	private Controller _ctrl;
	//private boolean _stopped;
	private JToolBar  tool;
	JButton botonCarpeta;
	JButton botonGravedad;
	JButton botonPlay;
	JButton botonStop;
	JButton botonOff;
	JSpinner pasos;
	JTextField delta;
	JSpinner delay;
	JLabel etiquetaDelay;
	JLabel etiquetaPasos;
	JLabel etiquetaDelta;
	volatile Thread _thread;
	long i;
	// Añade atributos para JToolBar, botones, etc.
	
	ControlPanel(Controller ctrl) { //esto lo ha puesto solo
		_ctrl = ctrl;
		//_stopped = true;
		initGUI();
		_ctrl.addObserver(this);
		}
	
	private void initGUI() { //esto lo ha puesto solo
		//boton carpeta
		botonCarpeta = new JButton();
		botonCarpeta.setToolTipText("Inserta un fichero");
		ImageIcon carpeta = new ImageIcon("resources/icons/open.png");//comprobar ruta
		botonCarpeta.setIcon(carpeta);
		botonCarpeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
					botonCarpeta();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
            }

        });
		//boton Gravedad
		botonGravedad = new JButton();
		botonGravedad.setToolTipText("Inserta una ley fisica");
		ImageIcon gravedad = new ImageIcon("resources/icons/physics.png");//comprobar ruta
		botonGravedad.setIcon(gravedad);
		botonGravedad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonGravedad();
            }

        });
		//boton play
		botonPlay = new JButton();
		botonPlay.setToolTipText("Continua con la simulacion");
		ImageIcon run = new ImageIcon("resources/icons/run.png");//comprobar ruta
		botonPlay.setIcon(run);
		botonPlay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonPlay();
                
            }

        });
		//botonStop
		botonStop = new JButton();
		botonStop.setToolTipText("Para la simulacion");
		ImageIcon stop = new ImageIcon("resources/icons/stop.png");//comprobar ruta
		botonStop.setIcon(stop);
		botonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//_stopped = true;
            	if(_thread != null) {
            		_thread.interrupt();
            	}
            }

        });
		//boton off
		botonOff = new JButton();
		botonOff.setToolTipText("Termina la simulacion");
		ImageIcon off = new ImageIcon("resources/icons/exit.png");//comprobar ruta
		botonOff.setIcon(off);
		botonOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	botonOff();
            }

        });
		pasos = new JSpinner();
		pasos.setValue(10000);
		pasos.setMaximumSize(new Dimension(50,50));
		etiquetaPasos = new JLabel("Steps: ");
		SpinnerNumberModel sm = new SpinnerNumberModel(1,0,1000,1);//valor inicial,minimo,maximo,incremenento de 1 en 1
		delay = new JSpinner(sm);
		delay.setMaximumSize(new Dimension(50,50));
		etiquetaDelay = new JLabel("Delay: ");
		delta = new JTextField();
		delta.setText("2500");
		delta.setMaximumSize(new Dimension(50,50));
		etiquetaDelta = new JLabel("Delta-Time: ");
		//tool bar
		tool = new JToolBar();
		tool.addSeparator();
		tool.add(botonCarpeta);
		tool.addSeparator();
		tool.add(botonGravedad);
		tool.addSeparator();
		tool.add(botonPlay);
		tool.addSeparator();
		tool.add(botonStop);
		tool.addSeparator();
		tool.add(botonOff);
		tool.addSeparator();
		
		tool.add(etiquetaDelay);
		tool.add(delay);
		tool.addSeparator();
		tool.add(etiquetaPasos);
		tool.add(pasos);
		tool.addSeparator();
		tool.add(etiquetaDelta);
		tool.add(delta);
		tool.addSeparator();
		this.add(tool);
	}

		
	
	// other private/protected methods
	
	
	public void run_sim(int n, long delay)  {
		/*
		while ( n>0 &&  !Thread.currentThread().isInterrupted()) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// Muestra el errorcon una ventana JOptionPane
				// Pon enable todos los botones
				_stopped = true;
				return;
			}
			SwingUtilities.invokeLater( new Runnable() {
				@Override
				public void run() {
					run_sim(n-1);
				}
			});
		} else {
			_stopped = true;
			botonCarpeta.setEnabled(true);
			botonGravedad.setEnabled(true);
			botonPlay.setEnabled(true);
			pasos.setEnabled(true);
			delta.setEnabled(true);
			}
		*/
		
		while ( n>0 &&  !Thread.currentThread().isInterrupted()) {
			// 1. execute the simulator one step, i.e., call method
			// _ctrl.run(1) and handle exceptions if any
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// Muestra el errorcon una ventana JOptionPane
				// Pon enable todos los botones
				//_stopped = true;
				return;
			}
	
			
			// 2. sleep the current thread for ’delay’ milliseconds
			try {
				Thread.currentThread();
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
				return;
			}
			n--;
		}
		/*
		botonCarpeta.setEnabled(true);
		botonGravedad.setEnabled(true);
		botonPlay.setEnabled(true);
		pasos.setEnabled(true);
		delta.setEnabled(true);
		*/
	}
	
	// SimulatorObserver methods
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvance(List<Body> bodies, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	public void botonCarpeta() throws FileNotFoundException {
		JFileChooser fc = new JFileChooser();
		int seleccion = fc.showOpenDialog(this);
		if(seleccion == JFileChooser.APPROVE_OPTION) { //si pulsa en aceptar
			File fichero = fc.getSelectedFile();
			InputStream stream = new FileInputStream(fichero.getAbsolutePath()); //guarda la ruta del fichero en el stream
			_ctrl.reset(); //limpia el simulador
			_ctrl.loadBodies(stream);
		
		}
	}
	
	public void botonGravedad() {
		// Con JCombobox
		List<JSONObject> lista = _ctrl.getGravityLawsFactory().getInfo();
		String[] leyes = new String[lista.size()];
		for (int i = 0; i < lista.size(); i++) {
			JSONObject obj = lista.get(i);
			leyes[i] = obj.getString("desc");
		}
		String cadena = (String)JOptionPane.showInputDialog(getParent(), "Select Gravity laws to be used", "Gravity Laws Selector", JOptionPane.PLAIN_MESSAGE, null, leyes, null);
		if (cadena != null) {
			int i = 0;
			while(cadena != leyes[i]) {
				i++;
			}
			_ctrl.setGravityLaws(lista.get(i)); //envia el seleccionado

		}		
	}
	
	public void botonPlay() {
		//se desactivan los botones menos el stop
		botonCarpeta.setEnabled(false);
		botonGravedad.setEnabled(false);
		botonPlay.setEnabled(false);
		pasos.setEnabled(false);
		delta.setEnabled(false);
		delay.setEnabled(false);
		//_stopped = false;
		_ctrl.setDeltaTime(Double.parseDouble(delta.getText()));
		
		
	
		
		
		Object o = delay.getValue();
		Number n = (Number) o;
		 i = n.longValue();

		_thread = new Hilos(pasos,i,this,_thread,botonCarpeta,botonGravedad,botonPlay,delta,delay);
		_thread.start();
		//run_sim((Integer)pasos.getValue(),i);
	
	}
	
	
	
	public void botonOff() {
		JDialog.setDefaultLookAndFeelDecorated(true);
		int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Confirm",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
}

package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.control.Controller;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -8877948025931264431L;
	Controller _ctrl;
	StatusBar estado; 
	JPanel nuevoPanel;
	BodiesTable tablaDeCuerpos;
	ControlPanel control;
	Viewer viewer;
	JPanel mainPanel; 

	public MainWindow(Controller ctrl) {
		super("Physics Simulator");
		_ctrl = ctrl;
		initGUI();
	}
	
	private void initGUI() {
		estado = new StatusBar(_ctrl);
		tablaDeCuerpos = new BodiesTable(_ctrl);
		control = new ControlPanel(_ctrl);
		viewer = new Viewer(_ctrl);		
		mainPanel = new JPanel(new BorderLayout());
		setContentPane(mainPanel);
		// Completa el método para construir la GUI
		mainPanel.add(control, BorderLayout.PAGE_START);
		mainPanel.add(estado, BorderLayout.PAGE_END);
		nuevoPanel = new JPanel();
		nuevoPanel.setLayout(new BoxLayout(nuevoPanel,BoxLayout.Y_AXIS));
		mainPanel.add(nuevoPanel, BorderLayout.CENTER);
		nuevoPanel.add(tablaDeCuerpos);
		nuevoPanel.add(viewer);
		viewer.setPreferredSize(new Dimension(900,400));
		pack();
		setVisible(true);
		
	}
}

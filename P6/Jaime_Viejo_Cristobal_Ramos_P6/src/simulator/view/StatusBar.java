package simulator.view;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class StatusBar extends JPanel implements SimulatorObserver {
	// ...
	private JLabel _currTime; // for current time
	private JLabel _currLaws; // for gravity laws
	private JLabel _numOfBodies;
	private JLabel Time; // for current time
	private JLabel Laws; // for gravity laws
	private JLabel Bodies;// for number of bodies
	private JToolBar tool;
	StatusBar(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		this.setLayout( new FlowLayout( FlowLayout.LEFT ));
		this.setBorder( BorderFactory.createBevelBorder( 1 ));
		_currTime  = new JLabel("0.0"); // for current time
		_currLaws = new JLabel("Empty"); // for gravity laws
		_numOfBodies = new JLabel("0.0");
		Time = new JLabel("Time: "); // for current time
		Laws = new JLabel("Laws: "); // for gravity laws
		Bodies = new JLabel("Bodies: ");
		// Completa para construir la barra de estado
		tool = new JToolBar();
		tool.addSeparator();
		tool.add(Time);
		tool.add(_currTime);
		tool.addSeparator();
		tool.add(Bodies);
		tool.add(_numOfBodies);
		tool.addSeparator();
		tool.add(Laws);
		tool.add(_currLaws);
		tool.addSeparator();	
		this.add(tool);
	}
	// Añade private/protected methods
	
	
	
	
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));
				_currTime.setText("" + time);
				_currLaws.setText(gLawsDesc);
			}
		});

	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_numOfBodies.setText("0.0");
				_currTime.setText("0.0");
				_currLaws.setText(gLawsDesc);
			}
		});

	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_numOfBodies.setText(Integer.toString(bodies.size()));

			}
		});

	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				_currTime.setText("" + time);

			}
		});

	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_currLaws.setText(gLawsDesc);

			}
		});

	}
}

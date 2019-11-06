package simulator.view;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;


import simulator.control.Controller;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class BodiesTableModel extends AbstractTableModel implements SimulatorObserver {
	// añade los nombres de columnas
	JTable table = new JTable();
	
	
	
	private List<Body> _bodies;
	private String[] names = {"id", "mass", "position", "velocidad", "aceleracion"};
	
	
	BodiesTableModel(Controller ctrl) {
		_bodies = new ArrayList<>();
		ctrl.addObserver(this);
	}
	@Override
	public int getRowCount() {
		return _bodies.size();
		// TODO complete
	}
	@Override
	public int getColumnCount() {
		return names.length;
		// TODO complete
	}
	@Override
	public String getColumnName(int column) {
		return names[column];
		// TODO complete
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Body b = _bodies.get(rowIndex);
		String c = "";
		switch(columnIndex){
		case 0: c = b.getId();
		break;
		case 1: c = "" + b.getMass();
		break;
		case 2: c = b.getPosition().toString();
		break;
		case 3: c = b.getVelocity().toString();
		break;
		case 4: c = b.getAcceleration().toString();
		break;
		}
		return c;
	}
	// SimulatorObserver methods
	// ...
	
	
	
	//hilos
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
		});
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				_bodies = bodies;
				fireTableStructureChanged();
			}
		});
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		
	}
}

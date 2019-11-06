package simulator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import simulator.control.Controller;
import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.SimulatorObserver;

public class Viewer extends JComponent implements SimulatorObserver {
	private static final int _WIDTH = 1000;
	private static final int _HEIGHT = 1000;
	// añadir constantes para los colores
	//Color rojo = new Color(223, 45, 223); //rojo
	//Color azul=new Color(80, 13, 255);
	private int _centerX;
	private int _centerY;
	private double _scale;
	private List<Body> _bodies;
	private boolean _showHelp;
	
	Viewer(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
	}
	
	private void initGUI() {
		// TODO add border with title
		_bodies = new ArrayList<>();
		_scale = 1.0;
		_showHelp = true;
		addKeyListener(new KeyListener() {
			// ...
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyChar()) {
				case '-':
					_scale = _scale * 1.1;
					break;
				case '+':
					_scale = Math.max(1000.0, _scale / 1.1);
					break;
				case '=':
					autoScale();
					break;
				case 'h':
					_showHelp = !_showHelp;
					break;
				default:
				}
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		addMouseListener(new MouseListener() {
			// ...
			@Override
			public void mouseEntered(MouseEvent e) {
				requestFocus();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D) g;
		gr.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		gr.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// use ’gr’ to draw not ’g’
		// calculate the center
		_centerX = getWidth() / 2;
		_centerY = getHeight() / 2;
		// TODO draw a cross at center
		gr.setColor(Color.red);
		gr.drawLine(_centerX - 4,  _centerY , _centerX + 4, _centerY);
		gr.drawLine(_centerX,  _centerY + 4, _centerX, _centerY -4);

		// TODO draw bodies
		gr.setColor(Color.blue);
		for(int i = 0; i < _bodies.size(); i++) {
			int x =  _centerX+ (int) (_bodies.get(i).getPosition().coordinate(0)/_scale);
			int y = _centerY-(int) (_bodies.get(i).getPosition().coordinate(1)/_scale);
			gr.drawString(_bodies.get(i).getId(), (float)x - 7, (float)y - 9);
			gr.fillOval(x - 5, y - 5, 10, 10);//triplazo
		}
		// TODO draw help if _showHelp is true
		if(_showHelp == true) {
			gr.setColor(Color.red);
			String mensaje = "h: toggle help, +:zoom-in,-:zoom-out, =:fit";
			String mensaje2 = "Scaling ratio: " + _scale;
			gr.drawString(mensaje, 5, 13);
			gr.drawString(mensaje2, 5, 32);
			
		}
		autoScale();
	}
	// other private/protected methods
	// ...
	private void autoScale() {
		double max = 1.0;
		for (Body b : _bodies) {
			Vector p = b.getPosition();
			for (int i = 0; i < p.dim(); i++)
				max = Math.max(max,
						Math.abs(b.getPosition().coordinate(i)));
		}
		double size = Math.max(1.0, Math.min((double) getWidth(),
				(double) getHeight()));
		_scale = max > size ? 4.0 * max / size : 1.0;
	}
	// SimulatorObserver methods
	// ...
	@Override
	public void onRegister(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;//esta mal
		autoScale();
		repaint();
		
	}
	@Override
	public void onReset(List<Body> bodies, double time, double dt, String gLawsDesc) {
		_bodies = bodies;//esta mal
		autoScale();
		repaint();
		
	}
	@Override
	public void onBodyAdded(List<Body> bodies, Body b) {
		_bodies = bodies;//esta mal
		autoScale();
		repaint();
		
	}
	@Override
	public void onAdvance(List<Body> bodies, double time) {
		repaint();
		
	}
	@Override
	public void onDeltaTimeChanged(double dt) {
		
	}
	@Override
	public void onGravityLawChanged(String gLawsDesc) {
		// TODO Auto-generated method stub
		
	}
}

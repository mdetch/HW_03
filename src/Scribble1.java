
import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*; 

public class Scribble1 extends JFrame {

  public Scribble1() {
    setTitle("Scribble Pad");
    canvas = new ScribbleCanvas(); 
    canvas.setBackground(Color.white); 
    getContentPane().setLayout(new BorderLayout()); 
    getContentPane().add(canvas, BorderLayout.CENTER);
    addWindowListener(new WindowAdapter() {
	public void windowClosing(WindowEvent e) {
	  System.exit(0);
	}
      }); 
  }

  public static void main(String[] args) {
    int width = 600; 
    int height = 400; 
    JFrame frame = new Scribble1();
    frame.setSize(width, height);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setLocation(screenSize.width/2 - width/2,
		      screenSize.height/2 - height/2);
    frame.show();
  }

  protected ScribbleCanvas canvas; 

  class ScribbleCanvasListener 
  implements MouseListener, MouseMotionListener {

public ScribbleCanvasListener(ScribbleCanvas canvas) {
  this.canvas = canvas; 
}

public void mousePressed(MouseEvent e) {
  Point p = e.getPoint(); 
  canvas.mouseButtonDown = true;
  canvas.x = p.x; 
  canvas.y = p.y;       
} 

public void mouseReleased(MouseEvent e) {
  canvas.mouseButtonDown = false;       
}    

public void mouseDragged(MouseEvent e) {
  Point p = e.getPoint(); 
  if (canvas.mouseButtonDown) {
	//canvas.getGraphics().setStroke(new BasicStroke(100));
	canvas.getGraphics().drawLine(canvas.x, canvas.y, p.x, p.y); 
    canvas.x = p.x; 
    canvas.y = p.y; 
  }       
  
}

public void mouseClicked(MouseEvent e) {}
public void mouseEntered(MouseEvent e) {}  
public void mouseExited(MouseEvent e) {}
public void mouseMoved(MouseEvent e) {}     

protected ScribbleCanvas canvas; 

}
  class ScribbleCanvas extends JPanel { 

	  public ScribbleCanvas() { 
	    listener = new ScribbleCanvasListener(this); 
	    addMouseListener((MouseListener) listener); 
	    addMouseMotionListener((MouseMotionListener) listener); 
	  }

	  protected EventListener listener;
	  protected boolean mouseButtonDown = false; 
	  protected int x, y; 

	}
}

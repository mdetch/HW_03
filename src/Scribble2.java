import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.*;


public class Scribble2 
{
	
	public static void main(String[] args) {
    
		JFrame frame = new JFrame();
		frame.setLayout(null);
		final DrawingPad drawPad = new DrawingPad();
		drawPad.setBounds(0,100,800,700);
		frame.add(drawPad);
		
		Scrollbar redSlider, greenSlider, blueSlider;
		Label redValue,greenValue,blueValue;
		Label showBrushSize;
		Button incBrush, decBrush, clear;
		
		redSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		redSlider.addAdjustmentListener(new Scroll(){
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				drawPad.R=0;
			}
		});
		greenSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		greenSlider.addAdjustmentListener(new Scroll());
		blueSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		blueSlider.addAdjustmentListener(new Scroll());
		
    	frame.setSize(800, 800);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    	frame.setVisible(true);
    
  }
	
}

  class DrawingPad extends JComponent {
    Image image;
    Graphics2D graphics2D;
    int currentX, currentY, oldX, oldY;
    int R,G,B,brushSize;
    public DrawingPad() {
      setDoubleBuffered(false);
      
      addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
          oldX = e.getX();
          oldY = e.getY();
          
        }
      });
      addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
          currentX = e.getX();
          currentY = e.getY();
         graphics2D.setColor(new Color(R,G,B));
          if (graphics2D != null)
        	  graphics2D.setStroke(new BasicStroke(brushSize));
        	  graphics2D.drawLine(oldX, oldY, currentX, currentY);
          repaint();
          oldX = currentX;
          oldY = currentY;
        }
      });
    }
   
	public void paintComponent(Graphics g) {
        if (image == null) {
          image = createImage(getSize().width, getSize().height);
          //g.fillOval(20, 40, 50, 50);
          graphics2D = (Graphics2D) image.getGraphics();
          graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
              RenderingHints.VALUE_ANTIALIAS_ON);
          clear();
        }
        g.drawImage(image, 0, 0, null);
      }

      public void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
        repaint();
      }
    }
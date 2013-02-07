import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Scribble extends Applet{
	public int R,G,B,brushSize,x,y;
	Scrollbar redSlider, greenSlider, blueSlider;
	Label redValue,greenValue,blueValue;
	Label showBrushSize;
	Button incBrush, decBrush, clear;
	 DrawingPad drawPad;
	
	public void init(){
		this.setSize(800,800);
		R=0; G=0; B=0; brushSize=20;
		setLayout(null);
		x=-1;y=-1;
		setGUI();
	    drawPad = new DrawingPad();
	    drawPad.setBounds(1,100,800,700);
	    add(drawPad);
	}
	
	public void setGUI(){
		this.setBackground(null);
		setLayout(null);
		
		redValue=new Label("Red:"+R); greenValue=new Label("Green:"+G); blueValue=new Label("Blue:"+B);
		
		//Slider of 3 colors
		redSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		redSlider.addAdjustmentListener(new Scroll());
		greenSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		greenSlider.addAdjustmentListener(new Scroll());
		blueSlider = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, 256);
		blueSlider.addAdjustmentListener(new Scroll());
		
		//setBounds of slider and text
		redValue.setBounds(2,10,70,20);
		redSlider.setBounds(80,10,500,20);
		greenValue.setBounds(2,40,70,20);
		greenSlider.setBounds(80,40,500,20);
		blueValue.setBounds(2,70,70,20);
		blueSlider.setBounds(80,70,500,20);
		add(redValue);
		add(redSlider);
		add(greenValue);
		add(greenSlider);
		add(blueValue);
		add(blueSlider);
		
		//add brush buttons
		showBrushSize = new Label("Brush Size:"+brushSize);
		showBrushSize.setBounds(660,25,120,20);
		
		//initialize increasing button
		incBrush = new Button("+");
		incBrush.addActionListener(new IncBrushSize());
		incBrush.setBounds(600,25,20,20);
		
		//initialize decreasing button
		decBrush = new Button("-");
		decBrush.addActionListener(new DecBrushSize());
		decBrush.setBounds(630,25,20,20);
		
		//initialize clear button
		clear = new Button(" CLEAR ");
		clear.addActionListener(new ClearScreen());
		clear.setBounds(600,1,190,20);
		
		add(incBrush);
		add(decBrush);
		add(clear);
		add(showBrushSize);
		
		
	}
	
	public void paint(Graphics g){
		
		g.setColor(Color.WHITE);
		g.fillRect(600,50,190,45);
		g.setColor(Color.BLACK);
		g.drawOval(695-brushSize/2,70-brushSize/2,brushSize,brushSize);
		g.setColor(new Color(R,G,B));
		g.fillOval(695-brushSize/2,70-brushSize/2,brushSize,brushSize);
		
	}
	
	public void updateColorValue(){
		redValue.setText("Red:"+R);
		greenValue.setText("Green:"+G);
		blueValue.setText("Blue:"+B);
		repaint();
	}
	
	public void updateBrushSize(){
		showBrushSize.setText("Brush Size:"+brushSize);
		repaint();
	}
	
	class Scroll implements AdjustmentListener{
		public void adjustmentValueChanged(AdjustmentEvent arg0) {
			R=redSlider.getValue();
			G=greenSlider.getValue();
			B=blueSlider.getValue();
			updateColorValue();
		}
	}
	class IncBrushSize implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(brushSize>=40) {}
			else{
				brushSize++;
				updateBrushSize();
			}
		}
	}
	class DecBrushSize implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(brushSize<=1) {}
			else{
				brushSize--;
				updateBrushSize();
			}
		}
	}
	class ClearScreen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			drawPad.clear();
		}	
	}

	class DrawingPad extends JComponent {
		Image image;
		Graphics2D graphics2D;
		  int currentX, currentY, oldX, oldY;
		    
		  public DrawingPad() {
		    setDoubleBuffered(false);
		      
		    addMouseListener(new MouseAdapter() {
		        public void mousePressed(MouseEvent e) {
		          oldX = e.getX();
		          oldY = e.getY();
		          System.out.println("mousePressed"+oldX+" "+oldY);
		        }
		    });
		    addMouseMotionListener(new MouseMotionAdapter() {
		        public void mouseDragged(MouseEvent e) {
		          currentX = e.getX();
		          currentY = e.getY();
		          System.out.println(currentX+" "+currentY+" "+R);
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
			System.out.println("paintComponent called");
		       if (image == null) {
		         image = createImage(getSize().width, getSize().height);
		         //g.fillOval(20, 40, 50, 50);
		         graphics2D = (Graphics2D) image.getGraphics();
		         graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
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
}

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScribbleApplet2 extends JApplet{
	public int R,G,B,brushSize,x,y;
	Scrollbar redSlider, greenSlider, blueSlider;
	Label redValue,greenValue,blueValue;
	Label showBrushSize;
	Button incBrush, decBrush, clear;
	DrawingPanel drawing_panel;
	
	public void init(){
		this.setSize(800,800);
		R=0; G=0; B=0; brushSize=20;
		setLayout(null);
		x=-1;y=-1;
		
		/*
		Container content_pane = getContentPane();
		
		// Create an instance of DrawingPanel
		drawing_panel =  new DrawingPanel ();
		drawing_panel.setBounds(0,100,800,700);
		drawing_panel.addMouseMotionListener(new Mouse());
		
		// Add the button to the content pane. 
		content_pane.add (drawing_panel); 
		*/
		this.addMouseMotionListener(new Mouse());
		setGUI();
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
		
		if(x>0&&y>100){
			//Graphics g = this.getGraphics();
			g.setColor(new Color(R,G,B));
			g.fillOval(x,y,brushSize,brushSize);
			System.out.println("printed");
		}
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
	
	public void drawOnScreen(int x, int y){
		Graphics g = this.getGraphics();
		g.setColor(new Color(R,G,B));
		g.fillOval(x,y+100,brushSize,brushSize);
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
		}	
	}
	class Mouse implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getX()>0&&e.getY()>100){
				x=e.getX();
				y=e.getY();
				//drawing_panel.repaint();
				repaint();
			}
			//System.out.println(e.getX()+" "+e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	class DrawingPanel extends JPanel {

		  DrawingPanel () { 
		    // Set background color for the applet's panel. 
		    setBackground (Color.WHITE);
		    
		  } 
		  
		  public void paintComponent(Graphics g)   { 
		   // Paint background
		   super.paintComponent(g);
		   if(x>0&&y>100){
				//Graphics g = this.getGraphics();
				g.setColor(new Color(R,G,B));
				g.fillOval(x,y,brushSize,brushSize);
				System.out.println("printed");
			}
		  } // paintComponent

	}
}

import java.awt.Container;

import javax.swing.JApplet;

/** Illustrate basic drawing in a Swing applet. **/
public class DrawApplet extends JApplet
{
  public void init () {
	 setSize(800,800);
	 setLayout(null);
    Container content_pane = getContentPane();
    // Create an instance of DrawingPanel
    DrawingPanel drawing_panel =  new DrawingPanel ();
    drawing_panel.setBounds(1,300,200,200);
    // Add the button to the content pane. 
    content_pane.add (drawing_panel); 
  } 
} // class DrawApplet
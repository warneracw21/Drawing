/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

/**
 *
 * @author andrewwarner
 */

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import  java.lang.Math.*;

public class DrawingPanel extends JPanel{
    
    private ArrayList<MyShape> Shapes;
    
    private JLabel mousePositionLabel;
    
    private Point pointStart;
    private Point pointEnd;
    
    private Paint paint = Color.BLACK;
    private BasicStroke stroke = new BasicStroke(1);
    
    private boolean filled;    
    private String shape = "Line";
    
    private void setInitialPosition(MouseEvent e) {
        pointStart = e.getPoint();
    }
    private void updatePoints(MouseEvent e) { 
        pointEnd = e.getPoint();
    }
    private void updateMousePositionLabel(MouseEvent e) {  
        mousePositionLabel.setText(String.format("(%s, %s)", e.getX(), e.getY()));
    }
    
    private void drawLine(Graphics2D g2) {
        MyShape shape = new MyLine(paint, stroke, pointStart, pointEnd);
        shape.draw(g2);
    }
    private void drawOval(Graphics2D g2) {
        MyShape shape = new MyOval(paint, stroke, pointStart, pointEnd, filled);
        shape.draw(g2);
    }
    private void drawRectangle(Graphics2D g2) {
        MyShape shape = new MyRectangle(paint, stroke, pointStart, pointEnd, filled);
        shape.draw(g2);
    }
    
    public void setPaint(Paint paint) {
        this.paint = paint;
    }
    public void setStroke(BasicStroke stroke) {
        this.stroke = stroke;
    }
    public void setFill(boolean fill) {
        this.filled = fill;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    
    public void undo() {
        if (!Shapes.isEmpty()) {
            Shapes.remove(Shapes.size() - 1);
        }
        repaint();
    }
    public void clear() {
        Shapes = new ArrayList<MyShape>();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (MyShape s : Shapes) {
                s.draw(g2);
            }
        if (pointStart != null && pointEnd!= null) {
            switch (shape) {      
                case "Line": drawLine(g2);
                    break;
                case "Oval": drawOval(g2);
                    break;
                case "Rectangle": drawRectangle(g2);
                    break;
           }
        }
    }
     
    // Constructor
    public DrawingPanel() {
        setLayout(new BorderLayout() );
        setBackground(Color.WHITE);
        
        mousePositionLabel = new JLabel();
        
        Shapes = new ArrayList<MyShape>();
        
        
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                setInitialPosition(e);
            }
            public void mouseReleased(MouseEvent e) {
                if (pointEnd != null){
                    switch (shape) {
                        case "Line": Shapes.add(new MyLine(paint, stroke, pointStart, pointEnd));
                            break;
                        case "Oval": Shapes.add(new MyOval(paint, stroke, pointStart, pointEnd, filled));
                            break;
                        case "Rectangle": Shapes.add(new MyRectangle(paint, stroke, pointStart, pointEnd, filled));
                            break;
                    }
                }
                pointEnd = null; 
                pointStart = null;
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {                
                updatePoints(e);
                repaint();
                
            }
            public void mouseMoved(MouseEvent e) {
                updateMousePositionLabel(e);
            } 
        });
            
        add(mousePositionLabel, BorderLayout.PAGE_END);
    }
    
}

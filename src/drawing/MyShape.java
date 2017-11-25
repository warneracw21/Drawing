/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

/**
 *
 * @author andrewwarner
 */
public abstract class MyShape {
    
    private Paint paint;
    private BasicStroke stroke;
    private Point pointStart;
    private Point pointEnd;
    
    public MyShape(Paint paint, BasicStroke stroke, Point pointStart, Point pointEnd) {
        this.paint = paint;
        this.stroke = stroke;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
    }
    
    public Paint getPaint() {
        return this.paint;
    }
    
    public BasicStroke getStroke() {
        return this.stroke;
    }
    
    public Point getPointStart() {
        return this.pointStart;
    }
    
    public Point getPointEnd() {
        return this.pointEnd;
    }
    
    public abstract void draw(Graphics2D g);
    
}

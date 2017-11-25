/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;

/**
 *
 * @author andrewwarner
 */
public class MyRectangle extends MyShape {
    
    boolean fill;
    
    public MyRectangle(Paint paint, BasicStroke stroke, Point pointStart, Point pointEnd, boolean fill) {
        super(paint, stroke, pointStart, pointEnd);
        this.fill = fill;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setPaint(getPaint());
        g.setStroke(getStroke());
        Point pointStart = getPointStart();
        Point pointEnd = getPointEnd();
        if (fill == true) { g.fillRect(Math.min(pointStart.x, pointEnd.x), 
                            Math.min(pointStart.y, pointEnd.y), 
                            Math.abs(pointEnd.x - pointStart.x), 
                            Math.abs(pointEnd.y - pointStart.y));}
        else { g.drawRect(  Math.min(pointStart.x, pointEnd.x), 
                            Math.min(pointStart.y, pointEnd.y), 
                            Math.abs(pointEnd.x - pointStart.x), 
                            Math.abs(pointEnd.y - pointStart.y));}
    }
    
}

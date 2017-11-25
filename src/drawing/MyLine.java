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
public class MyLine extends MyShape {
    
    public MyLine(Paint paint, BasicStroke stroke, Point pointStart, Point pointEnd) {
        super(paint, stroke, pointStart, pointEnd);
                
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(getStroke());
        g.setPaint(getPaint());
        Point pointStart = getPointStart();
        Point pointEnd = getPointEnd();
        g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
                
    }
    
}

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
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Drawing extends JFrame{
    
    ////// JFrame Components ///////
    //JPanels
    private DrawingPanel DrawingPanel; //Panel in which User Draws
    private JPanel OptionsPanel;       // Panel where user can set options
    
    ////// JPanel Components ///////
    //JButtons
    private JButton undo;               
    private JButton clear;
    private JButton firstColorButton;
    private JButton secondColorButton;
    private JRadioButton filled;
    private JRadioButton dashed;
    private JRadioButton useGradient;
    //Combo Boxes
    private JComboBox shapeCombo;
    //JLabels
    private JLabel shapeLabel;
    private JLabel lineWidthLabel;
    private JLabel dashLengthLabel;
    //JTextField
    private JTextField lineWidthText;
    private JTextField dashLengthText;
    
    ///// Instance Variables /////
    // Colors
    private Color firstColor = Color.black;
    private Color secondColor = Color.black;
    // Combo Box Labels
    private String[] shapeOptions;
    

    
    private void setUpOptionsPanel() {
        OptionsPanel = new JPanel(new GridBagLayout());
        OptionsPanel.setBackground((Color.decode("#e0eee0")));
        
        undo = new JButton("Undo");
        clear = new JButton("Clear");
        shapeLabel = new JLabel("Shape: ");
        shapeCombo = new JComboBox(shapeOptions);
        filled = new JRadioButton("Filled");
        useGradient = new JRadioButton("Use Gradient");
        firstColorButton = new JButton("1st Color");
        secondColorButton = new JButton("2nd Color");
        lineWidthLabel = new JLabel("Line Width:");
        lineWidthText = new JTextField(3);
        dashLengthLabel = new JLabel("Dash Length");
        dashLengthText = new JTextField(3);
        dashed = new JRadioButton("Dashed");

        GridBagConstraints l = new GridBagConstraints();
        l.fill = GridBagConstraints.VERTICAL;

        l.gridy=0; l.gridx=3;       
        OptionsPanel.add(undo, l);
        l.gridy=0; l.gridx=4;
        OptionsPanel.add(clear, l);
        l.gridy=0; l.gridx=5;
        OptionsPanel.add(shapeLabel, l);
        l.gridy=0; l.gridx=6;
        OptionsPanel.add(shapeCombo, l);
        l.gridy=0; l.gridx=7;
        OptionsPanel.add(filled, l);
        l.gridy=1; l.gridx=2;
        OptionsPanel.add(useGradient, l);
        l.gridy=1; l.gridx=3;
        OptionsPanel.add(firstColorButton, l);
        l.gridy=1; l.gridx=4;
        OptionsPanel.add(secondColorButton, l);
        l.gridy=1; l.gridx=5;
        OptionsPanel.add(lineWidthLabel, l);
        l.gridy=1;l.gridx=6;
        OptionsPanel.add(lineWidthText, l);
        l.gridy=1; l.gridx=7;
        OptionsPanel.add(dashLengthLabel, l);
        l.gridy=1; l.gridx=8;
        OptionsPanel.add(dashLengthText, l);
        l.gridy=1; l.gridx=9;
        OptionsPanel.add(dashed,l);   
    }
  
    private void addActionHandlers() {
        ActionHandler handler = new ActionHandler();
        
        shapeCombo.addActionListener( handler );
        filled.addActionListener( handler );
        useGradient.addActionListener( handler );
        firstColorButton.addActionListener( handler );
        secondColorButton.addActionListener( handler );
        lineWidthText.addActionListener( handler );
        dashLengthText.addActionListener( handler );
        dashed.addActionListener( handler );
        undo.addActionListener( handler );
        clear.addActionListener( handler );
    }
    
    public Drawing() {
        // Initialize JFrame
        super("Drawing Frame");
        setLayout( new GridBagLayout() );
        setMinimumSize(new Dimension(750, 400));
        // Initialize Instance Variables
        shapeOptions = new String[] {"Line", "Oval", "Rectangle"};
        // Set Up Panels
        setUpOptionsPanel();
        DrawingPanel = new DrawingPanel();
        // Set up JFrame
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        // Add Panels to JFrame
        c.gridy = 0; c.weighty = 0.10; c.weightx = 1;
        add(OptionsPanel, c);
        c.gridy = 1; c.weighty = 2; c.weightx = 1;
        add(DrawingPanel, c);
        // Add Action Handlers
        addActionHandlers();              
    }
    
    
    ///// Methods for Handling TextFields //////
    // read dashLengthText TextField
    private int getDashLength() {
        int dashLength;
        if (dashLengthText.getText().equals("")) {
            dashLength = 10;
        } else {
            try {
                dashLength = Integer.parseInt(dashLengthText.getText());
            } catch (NumberFormatException e) {
                dashLength = 10;
            }
        }
        return dashLength;
    }
    // Read lineWidthText TextField
    private int getLineWidth() {
        int lineWidth;
        if (lineWidthText.getText().equals("")) {
            lineWidth = 1;
        } else {
            try {
                lineWidth = Integer.parseInt(lineWidthText.getText());
            } catch (NumberFormatException e) {
                lineWidth = 1;
            }  
        }
        return lineWidth;
    }  
    // build Stroke for no Dashes
    private BasicStroke buildRegStroke(int lineWidth) {
        return new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    }
    // build Stroke with Dashes
    private BasicStroke buildDashStroke(int lineWidth, int dashedLength) {
        return new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, new float[] {dashedLength}, 0);
    }
    // Set Drawing Stroke 
    private void setDrawingStroke() {
        if (dashed.isSelected() == true) {
            DrawingPanel.setStroke(buildDashStroke(getLineWidth(), getDashLength()));
        } else {
            DrawingPanel.setStroke(buildRegStroke(getLineWidth()));
        }
    }
    
    ///// Other Drawing UI Attributes /////
    // handle Color Chooser
    private Color colorSelectionPanel(Color initialColor) {
        Color c = JColorChooser.showDialog(null, "Choose a Color", initialColor);
        return c;
    }
    // build Paint with Gradient
    private GradientPaint setGradient() {
        return new GradientPaint(0, 0, firstColor, 25, 25, secondColor, true);
    }
    // Set DrawingPanel Paint Attribute
    private void setDrawingPaint() {
        if (useGradient.isSelected() == true) {
            DrawingPanel.setPaint(setGradient());
        } else {
            DrawingPanel.setPaint(firstColor);
        }     
    }
    // Set DrawingPanel Shape
    private void setDrawingShape() {
        DrawingPanel.setShape(shapeCombo.getSelectedItem().toString());
    }
    // Set DrawingPanel Fill Option
    private void setDrawingFill() {
        DrawingPanel.setFill(filled.isSelected());
    }
    //////// With Action Handler, always UPDATE USER INTERFACE!!!! //////
    private void updateUI() {
        setDrawingStroke();
        setDrawingPaint();
        setDrawingShape();
        setDrawingFill();
    }
    
    ///// Handle Actions on User Interface Attributes ////
    private class ActionHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == firstColorButton) {
                Color newColor = colorSelectionPanel(firstColor);
                if (newColor == null) {newColor = firstColor;}
                firstColor = newColor;
            } else if (e.getSource() == secondColorButton) {
                Color newColor = colorSelectionPanel(secondColor);
                if (newColor == null) {newColor = secondColor;}
                secondColor = newColor;
            }  else if (e.getSource() == dashLengthText) {
                OptionsPanel.requestFocus();
            } else if (e.getSource() == lineWidthText) {
                OptionsPanel.requestFocus();
            } else if (e.getSource() == undo) {
                DrawingPanel.undo();
            } else if (e.getSource() == clear) {
                DrawingPanel.clear();
            }
            updateUI();   /////  Always Update User Interface!!!!
        }
    }
        
    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        drawing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawing.setSize( 260, 180 );
        drawing.setVisible( true );
        
    }
}


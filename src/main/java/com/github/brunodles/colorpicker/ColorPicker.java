package com.github.brunodles.colorpicker;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * Created by bruno on 02/08/16.
 */
public class ColorPicker {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel labelColor = new JLabel("Color");
        JTextField labelValue = new JTextField();
        JButton button = new JButton("Clipboard");
        button.setText("Copy to clipboard");

        panel.add(labelColor);
        panel.add(labelValue);
        panel.add(button);

        try {
            Robot robot = new Robot();
            Color pixelColor = getColor(robot);
            final String color = toString(pixelColor);
            System.out.println(color);
            labelValue.setText(color);
            button.setAction(new AbstractAction() {
                public void actionPerformed(ActionEvent e) {
                    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clip.setContents(new StringSelection(color), null);
                }
            });

        } catch (AWTException e) {
        }

        JWindow window = new JWindow();
        window.getContentPane().add(panel);
        window.setLocation(getMouseLocation());
        window.pack();
        window.setVisible(true);

//        JFrame frame = new JFrame("Color Picker");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(panel);
//        frame.setLocation(getMouseLocation());
//        frame.pack();
//        frame.setVisible(true);
    }

    private static String toString(Color pixelColor) {
        return String.format("%02x%02x%02x", pixelColor.getRed(),
                pixelColor.getGreen(), pixelColor.getBlue());
    }

    private static Color getColor(Robot robot) {
        Point location = getMouseLocation();
        return robot.getPixelColor(location.x, location.y);
    }

    private static Point getMouseLocation() {
        return MouseInfo.getPointerInfo().getLocation();
    }
}

package com.github.brunodles.colorpicker;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Created by bruno on 02/08/16.
 */
public class ColorPicker {
    public static void main(String[] args) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

        JTextField labelValue = new JTextField();
        JPanel colorPanel = new JPanel();

        panel.add(labelValue);
        panel.add(colorPanel);

        try {
            Robot robot = new Robot();
            Color pixelColor = getColor(robot);
            final String color = toString(pixelColor);
            System.out.println(color);
            labelValue.setText(color);
            colorPanel.setBackground(pixelColor);
        } catch (AWTException e) {
        }

        JDialog dialog = new JDialog();
        dialog.setTitle("ColorPicker");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(panel);
        dialog.setLocation(getMouseLocation());
        dialog.pack();
        dialog.setVisible(true);
    }

    private static void setClipboard(String color) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        clip.setContents(new StringSelection(color), null);
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

package ua.com.yatran;

import com.formdev.flatlaf.FlatLightLaf;
import ua.com.yatran.frames.LandingFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Yatran {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize theme - using fallback!");
        }
        EventQueue.invokeLater(LandingFrame::new);
    }
}
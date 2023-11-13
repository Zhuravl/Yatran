package ua.com.yatran;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import ua.com.yatran.frames.LandingFrame;

import java.awt.*;
import java.util.Locale;

public class Yatran {
    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        FlatArcDarkIJTheme.setup();
        EventQueue.invokeLater(LandingFrame::new);
    }
}
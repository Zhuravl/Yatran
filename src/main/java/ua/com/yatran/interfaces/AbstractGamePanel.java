package ua.com.yatran.interfaces;

import ua.com.yatran.constants.Constants;
import ua.com.yatran.panels.GamePanel;

import javax.swing.*;

public abstract class AbstractGamePanel extends JPanel {

    protected GamePanel mainPanel; //The main panel to call 'start/stop game' methods on
    protected JButton[] letterBlockArray; //The array with letters block to display while the game
    protected String[] letters; //The array with letters to set as text for the 'letterBlockArray' objects

    public AbstractGamePanel(GamePanel mainPanel, String[] letters) {
        this.mainPanel = mainPanel;
        this.letters = letters;
        setBounds(0, Constants.Common.MAIN_WINDOW_HEIGHT / 8, Constants.Common.MAIN_WINDOW_WIDTH, Constants.Common.MAIN_WINDOW_HEIGHT / 2);
    }

    /**
     * Hides a letter block with the defined index to allow the Hero to move forward
     *
     * @param index block index to use
     */
    public void hideBlock(int index) {
        if (letterBlockArray != null && letterBlockArray.length > index && letterBlockArray[index] != null) {
            letterBlockArray[index].setVisible(false);
        }
    }
}

package org.example;

import org.example.customFunctions.ChangeLetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomFrame extends JFrame implements ActionListener {
    public static JLabel randomCharacterDisplay; // Displays a random character
    public static JLabel randomCharacterPositionDisplay; // Displays the random character's position
    private static CustomButton refreshButton; // Refreshes the button
    private static CustomButton toggleNumberPositionButton; // Toggles whether the position of the letter is displayed or not

    public CustomFrame() {


        randomCharacterPositionDisplay = new JLabel("");
        randomCharacterPositionDisplay.setHorizontalAlignment(JLabel.CENTER);
        randomCharacterPositionDisplay.setFont(new Font("Arial", Font.PLAIN, 50));
        randomCharacterPositionDisplay.setVisible(false);

        randomCharacterDisplay = new JLabel("");
        randomCharacterDisplay.setHorizontalAlignment(JLabel.CENTER);
        randomCharacterDisplay.setFont(new Font("Arial", Font.PLAIN, 50));

        // ==================================================================================

        refreshButton = new CustomButton("ChangeLetter");
        refreshButton.setText("Select Random Char");
        refreshButton.addActionListener(this);
        refreshButton.setFont(new Font("Arial", Font.PLAIN, 50));

        toggleNumberPositionButton = new CustomButton("TogglePosition");
        toggleNumberPositionButton.setText("Display Text Position");
        toggleNumberPositionButton.addActionListener(this);
        toggleNumberPositionButton.setFont(new Font("Arial", Font.PLAIN, 50));

        final ChangeLetter changeLetter = new ChangeLetter(); // Object is declared to utilize its functions
        changeLetter.start();

        // JFRAME PROPERTIES ==============================================================
        this.setTitle("Letter to Digit Decoding Practice");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLayout(new GridLayout(4, 1, 10, 10));
        this.setLocationRelativeTo(null);

        this.setIconImage(new ImageIcon("assets/tingyun.png").getImage());
        // JFRAME PROPERTIES ==============================================================

        this.add(randomCharacterDisplay);
        this.add(randomCharacterPositionDisplay);
        this.add(refreshButton);
        this.add(toggleNumberPositionButton);

        this.setVisible(true);
    }

    public static void toggleRefreshButton(boolean value) { // Mainly for the ChangeLetter class's threading function to be able to change the state of the refresh button
        refreshButton.setEnabled(value);
    }

    public static String getButtonStatus(String value) { // Right now, it's useless to use switch case with only 1 option involved. But can be useful for future updates
        return switch(value) {
            case "toggleNumberPositionButton" -> toggleNumberPositionButton.getText();
            default -> "null";
        };
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CustomButton triggeredButton = (CustomButton) e.getSource();

        if (triggeredButton.getId().equals("ChangeLetter")) { // Refreshes the selected letter if clicked
            final ChangeLetter changeLetter = new ChangeLetter(); // Object is declared to utilize its functions
            changeLetter.start();
        }

        else if (triggeredButton.getId().equals("TogglePosition")) { // Show/Hides the index of the selected letter
            switch (triggeredButton.getText()) {
                case "Display Text Position" -> {
                    triggeredButton.setText("Hide Text Position");
                    randomCharacterPositionDisplay.setVisible(true);
                }
                case "Hide Text Position" -> {
                    triggeredButton.setText("Display Text Position");
                    randomCharacterPositionDisplay.setVisible(false);
                }
            }
        }
    }

}

class CustomButton extends JButton {
    String id;
    public CustomButton(String buttonID) {
        this.id = buttonID;
    }
    public String getId() {
        return this.id;
    }
}
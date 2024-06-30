package org.example.customFunctions;

import org.example.SystemFunctions;

import static org.example.CustomFrame.*;


public class ChangeLetter extends Thread {
    String[] charList = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    /*
    All the happenings must only stay in this run() function or else console will throw an error about "Thread not Owner"
    causing me to write static functions where this class can modify properties of the CustomFrame class.
    */
    public void run() {
        int randomPickedPosition = (int) (Math.floor(Math.random()*26)); // Generates a number between 0 and 25
        toggleRefreshButton(false); // Disable refresh button
        randomCharacterPositionDisplay.setVisible(true); // Display the index of the random character for a moment

        SystemFunctions.delay(200);
        toggleRefreshButton(true);

        if (getButtonStatus("toggleNumberPositionButton").equals("Display Text Position")) { // Checks if the option to always display the button is not enabled
            randomCharacterPositionDisplay.setVisible(false); // Hide the display of the character index if it's normally not hidden
        }

        randomCharacterDisplay.setText(charList[randomPickedPosition]); // Renames the jlabel based on the selected character
        randomCharacterPositionDisplay.setText(String.valueOf(randomPickedPosition+1)); // Renames the jlabel based on the number position of the character
    }
}

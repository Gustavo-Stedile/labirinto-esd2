package labirinto.core;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

class Tile extends JButton {
    public static final String EMPTY = " ";
    public static final String WALL = "#";
    public static final String START = "0";
    public static final String END = "1";
    public static final String TRAVELED = "2";

    private static Map<String, Color> colorMapping = new HashMap<>();
    static {
        colorMapping.put(EMPTY, Color.WHITE);
        colorMapping.put(WALL, Color.BLACK);
        colorMapping.put(START, Color.YELLOW);
        colorMapping.put(END, Color.GREEN);
        colorMapping.put(TRAVELED, Color.GRAY);
    }

    private String currChar = " ";

    public Tile(String value) {
        currChar = value;
        setBackground(colorMapping.get(currChar));
    }

    public String toggle(String ch1, String ch2) {
        if (!currChar.equals(ch1) && !currChar.equals(ch2)) {
            return currChar;
        }

        currChar = currChar.equals(ch1) ? ch2 : ch1;
        setBackground(colorMapping.get(currChar));
        return currChar;
    }

    public String set(String ch) {
        currChar = ch;
        setBackground(colorMapping.get(currChar));
        return currChar;
    }

    public void disable() {
        currChar = " ";
        setBackground(colorMapping.get(currChar));
    }
}
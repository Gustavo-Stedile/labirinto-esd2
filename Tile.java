import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

class Tile extends JButton {
    private static Map<String, Color> colorMapping = new HashMap<>();
    static {
        colorMapping.put(" ", Color.WHITE);
        colorMapping.put("#", Color.BLACK);
        colorMapping.put("0", Color.YELLOW);
        colorMapping.put("1", Color.GREEN);
    }

    public static final String EMPTY = " ";
    public static final String WALL = "#";
    public static final String START = "0";
    public static final String END = "1";

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
        setBackground(colorMapping.get(currChar));
        System.out.println(getBackground());
        return currChar;
    }

    public void disable() {
        currChar = " ";
        setBackground(colorMapping.get(currChar));
    }
}
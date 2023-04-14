package display;

import java.awt.*;

public class ColorModifier {
    public static Color multiply(Color color, double num){
        int r;
        int g;
        int b;
        if (num > 1){
            int rdiff = 255 - color.getRed();
            int gdiff = 255 - color.getGreen();
            int bdiff = 255 - color.getBlue();
            double modifier = 1/num;
            r = (int) (255 - rdiff * modifier);
            g = (int) (255 - gdiff * modifier);
            b = (int) (255 - bdiff * modifier);
        } else {
            r = Math.min((int) (color.getRed() * num), 255);
            g = Math.min((int) (color.getGreen() * num), 255);
            b = Math.min((int) (color.getBlue() * num), 255);
        }
        r = Math.max(r,0);
        g = Math.max(g,0);
        b = Math.max(b,0);
        return new Color(r,g,b);
    }
}

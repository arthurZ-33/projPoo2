package utils;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Util {

    public static Image getIcone() {
        URL caminhoImagem = Util.class.getResource("/images/User_image.png");

        ImageIcon icon = new ImageIcon(caminhoImagem);

        return icon.getImage();
    }
}

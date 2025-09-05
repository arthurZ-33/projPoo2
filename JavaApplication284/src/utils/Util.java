package utils;

import com.sun.org.apache.xml.internal.resolver.Catalog;
import java.awt.Image;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Util {

    public static Image getIcone() {
        URL caminhoImagem = Util.class.getResource("/images/User_image.png");

        ImageIcon icon = new ImageIcon(caminhoImagem);

        return icon.getImage();
    }

    public static Date converterStringToDate(String texto) {
        //constroe o formato que eu quero transformar o texto
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        //erro minha variável data que sera o retorno do método
        Date data = null;

        try {
            //tenta converter a String em Date baseado no formato
            //constuido anteriormente
            data = formato.parse(texto);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao converter a data");
        }
        //retorna a data convertida
        return data;

    }

    public static String calcularHash(String senha) {
        String hashSHA1 = "";

        try {
            // Crie uma instancia de MessasgeDigest
            //com o algoritimo SHA1
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");

            //atualize o digest com os bytes  de texto
            sha1.update(senha.getBytes());

            //calcule o hash SHA1
            byte[] digest = sha1.digest();

            //converta o hash de bytes para
            //uma representação hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                hashSHA1 = hashSHA1 + String.format("%02x", b);
            }
            hashSHA1 = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Algoritimo SHA1 não encontrado");
        }
        return hashSHA1;
    }
    
       public static String converterDateToString(Date data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String texto = "";

        try {
            //Irá formatar a data para o formato dd/MM/yyyy
            texto = formato.format(data);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar a data");
        }
        
       return texto;
    }
}

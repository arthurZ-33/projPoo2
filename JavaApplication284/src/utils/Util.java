package utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

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
            for (byte b : digest) {
                hashSHA1 = hashSHA1 + String.format("%02x", b);
            }
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

    public static File escolherImagem() {
        File arquivo = null;

        //Cria um escolhedor de arquivos
        JFileChooser exploradorArquivos = new JFileChooser();

        //Filtrar os tipos de arquivos que irá buscar
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png");
        exploradorArquivos.setFileFilter(filtro);

        //COnfigurações para permitir a seleção de apenas um arquivo
        exploradorArquivos.setMultiSelectionEnabled(false);

        //chama o explorador de arquivos e guadra o resultado:
        //APPROVE_OPTION (selecinou)
        //CANCEL_OPTION (usuário cancelou)
        int resultado = exploradorArquivos.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            //Pega o arquivo selecionado
            arquivo = exploradorArquivos.getSelectedFile();
        }

        return arquivo;
    }

    public static Icon converterFileToIcon(File arquivo) {
        //passo o caminho da imagem para o construtor de ImagemIcon
        ImageIcon icon = new ImageIcon(arquivo.getAbsolutePath());
        return icon;
    }

    public static ImageIcon redimensionarImagem(Icon icone, int largura, int altura) {

        //converte o icone em imagem
        Image imagemOriginal = ((ImageIcon) icone).getImage();

        //redimensionoa imagem
        Image novaImagem = imagemOriginal.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        //converto a imagem redimensionadssssa em icone de novo
        return new ImageIcon(novaImagem);
    }

    public static byte[] converterIconToBytes(Icon icon) {
        BufferedImage image = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        icon.paintIcon(null, g2d, 0, 0);
        g2d.dispose();

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", byteArray);
        } catch (IOException erro) {
            Logger.getLogger(Util.class.getName()).log(
                    Level.SEVERE, null, erro);
        }
        return byteArray.toByteArray();
    }

}

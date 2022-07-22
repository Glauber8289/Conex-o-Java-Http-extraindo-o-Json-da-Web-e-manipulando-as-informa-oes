import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.plaf.FontUIResource;

public class GeradoradeFigurinhas {

    void cria() throws Exception {

        InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_3.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparencia e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.createGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new FontUIResource(Font.SANS_SERIF, Font.BOLD, 70);
        graphics.setColor(Color.red);
        graphics.setFont(fonte);

        // escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", 0, novaAltura - 100);

        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("Figurinha.png"));

    }

    public static void main(String[] args) throws Exception {
        var geradora = new GeradoradeFigurinhas();
        geradora.cria();
    }

}

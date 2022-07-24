
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_BOLD = "\033[1;37m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";

    public static void main(String[] args) throws Exception {

        // fazer uma conexao HTTP e buscar os tops 250 filmes
        String url = "https://alura-imdb-api.herokuapp.com/movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDefilmes = parser.parse(body);

        // exibir e manipular os dados

        // deixei esse comentario pois posso usar depois para gerar figurinhas para o
        // Json completo.
        // var geradora = new GeradoradeFigurinhas();

        // Exibindo os dados com a orde : title,imdbratin, poster

        for (Map<String, String> filme : listaDefilmes) {

            String stars = "";
            for (int o = 0; o < Math.round(Double.parseDouble(filme.get("imDbRating"))); o++)
                stars = stars.concat("*");

            // deixei esse comentario pois posso usar depois para gerar figurinhas para o
            // Json completo.
            // String urlImagem = filme.get("image");
            // String titulo = filme.get("title");
            // InputStream inputStream = new URL(urlImagem).openStream();
            // String nomeArquivo = titulo + ".png";
            // geradora.cria(inputStream, nomeArquivo);
            // deixei esse comentario
            System.out.printf("%sTítulo: %s%s\n", ANSI_WHITE_BOLD, ANSI_RESET, filme.get("title"));
            System.out.printf("%sPoster: %s%s\n", ANSI_WHITE_BOLD, ANSI_RESET, filme.get("image"));
            System.out.printf("%sNota: %s%s", ANSI_PURPLE_BACKGROUND, filme.get("imDbRating"), ANSI_RESET);
            System.out.printf("\n%s\n\n", stars);

            // System.out.println();

            // System.out.printf("%sTítulo: %s%s\n", ANSI_WHITE_BOLD, ANSI_RESET,
            // filme.get("title"));
            // System.out.printf("%sPoster: %s%s\n", ANSI_WHITE_BOLD, ANSI_RESET,
            // filme.get("image"));
            // System.out.printf("%sNota: %s%s", ANSI_PURPLE_BACKGROUND,
            // System.out.println(filme.get("imDbRating"), ANSI_RESET);
            // System.out.printf("\n%s\n\n", stars);
            // System.out.println(filme.get("imDbRanting"));
        }
    }

    @Override
    public String toString() {
        return "App []";
    }

}

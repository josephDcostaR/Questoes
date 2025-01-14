import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FaturamentoDistribuidora {
    public static void main(String[] args) throws Exception {
        // Carregar o arquivo JSON como recurso
        InputStreamReader reader = new InputStreamReader(
            FaturamentoDistribuidora.class.getResourceAsStream("/dados.json")
        );

        // Parse do JSON para um array
        JsonArray faturamentoDiario = JsonParser.parseReader(reader).getAsJsonArray();

        List<Double> valores = new ArrayList<>();

        // Iterar pelos elementos do array e extrair os valores
        for (JsonElement elemento : faturamentoDiario) {
            JsonObject dia = elemento.getAsJsonObject();
            double valor = dia.get("valor").getAsDouble();
            if (valor > 0) { // Ignorar valores não positivos
                valores.add(valor);
            }
        }

        // Calcular o menor, maior, média e dias acima da média
        double menor = valores.stream().min(Double::compare).orElse(0.0);
        double maior = valores.stream().max(Double::compare).orElse(0.0);
        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        long diasAcimaDaMedia = valores.stream().filter(v -> v > media).count();

        // Exibir os resultados
        System.out.println("Menor faturamento: R$ " + menor);
        System.out.println("Maior faturamento: R$ " + maior);
        System.out.println("Dias acima da média: " + diasAcimaDaMedia);
    }
}

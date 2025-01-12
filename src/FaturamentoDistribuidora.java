import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FaturamentoDistribuidora {
    public static void main(String[] args) throws Exception {
        JsonArray faturamentoDiario = JsonParser.parseReader(new FileReader("faturamento.json")).getAsJsonArray();

        List<Double> valores = new ArrayList<>();
        for (JsonElement dia : faturamentoDiario) {
            double valor = dia.getAsDouble();
            if (valor > 0) {
                valores.add(valor);
            }
        }

        double menor = valores.stream().min(Double::compare).orElse(0.0);
        double maior = valores.stream().max(Double::compare).orElse(0.0);
        double media = valores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        long diasAcimaDaMedia = valores.stream().filter(v -> v > media).count();

        System.out.println("Menor faturamento: R$ " + menor);
        System.out.println("Maior faturamento: R$ " + maior);
        System.out.println("Dias acima da média: " + diasAcimaDaMedia);
    }
}

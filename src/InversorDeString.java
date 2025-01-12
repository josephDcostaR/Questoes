import java.util.Scanner;

public class InversorDeString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe uma string para ser invertida: ");
        String input = scanner.nextLine();
        scanner.close();

        String invertida = inverterString(input);
        System.out.println("String invertida: " + invertida);
    }

    public static String inverterString(String str) {
        char[] caracteres = str.toCharArray();
        String resultado = "";

        for (int i = caracteres.length - 1; i >= 0; i--) {
            resultado += caracteres[i];
        }

        return resultado;
    }
}

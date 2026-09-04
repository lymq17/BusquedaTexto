package algoritmos;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public static long comparaciones = 0;

    // Tabla LPS: lps[i] = longitud del mayor prefijo propio de patron[0..i]
    // que tambien es sufijo de patron[0..i]
    public static int[] construirLPS(String patron) {
        int m = patron.length();
        int[] lps = new int[m];
        int longitud = 0;
        int i = 1;

        while (i < m) {
            if (patron.charAt(i) == patron.charAt(longitud)) {
                longitud++;
                lps[i] = longitud;
                i++;
            } else if (longitud > 0) {
                longitud = lps[longitud - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }
        return lps;
    }

    public static List<Integer> buscar(String texto, String patron) {
        List<Integer> ocurrencias = new ArrayList<>();
        comparaciones = 0;

        int n = texto.length();
        int m = patron.length();
        if (m == 0 || m > n) return ocurrencias;

        int[] lps = construirLPS(patron);
        int i = 0; // indice sobre el texto
        int j = 0; // indice sobre el patron

        while (i < n) {
            comparaciones++;
            if (texto.charAt(i) == patron.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    ocurrencias.add(i - m);
                    j = lps[j - 1];
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return ocurrencias;
    }
}
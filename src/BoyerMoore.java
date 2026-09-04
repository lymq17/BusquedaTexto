package algoritmos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoyerMoore {

    public static long comparaciones = 0;

    // Regla del mal caracter: ultima posicion de cada caracter dentro del patron
    public static Map<Character, Integer> construirMalCaracter(String patron) {
        Map<Character, Integer> ultima = new HashMap<>();
        for (int i = 0; i < patron.length(); i++) {
            ultima.put(patron.charAt(i), i);
        }
        return ultima;
    }

    // Regla del sufijo bueno: shift[j] = cuanto desplazar si fallo en la posicion j-1
    public static int[] construirSufijoBueno(String patron) {
        int m = patron.length();
        int[] shift = new int[m + 1];
        int[] borde = new int[m + 1];

        int i = m;
        int j = m + 1;
        borde[i] = j;

        // Caso 1: El sufijo bueno reaparece completo en otra parte del patron
        while (i > 0) {
            while (j <= m && patron.charAt(i - 1) != patron.charAt(j - 1)) {
                if (shift[j] == 0) shift[j] = j - i;
                j = borde[j];
            }
            i--;
            j--;
            borde[i] = j;
        }

        // Caso 2: Solo una parte del sufijo bueno coincide con un prefijo del patron
        j = borde[0];
        for (i = 0; i <= m; i++) {
            if (shift[i] == 0) shift[i] = j;
            if (i == j) j = borde[j];
        }
        return shift;
    }

    public static List<Integer> buscar(String texto, String patron) {
        List<Integer> ocurrencias = new ArrayList<>();
        comparaciones = 0;

        int n = texto.length();
        int m = patron.length();
        if (m == 0 || m > n) return ocurrencias;

        Map<Character, Integer> ultima = construirMalCaracter(patron);
        int[] shift = construirSufijoBueno(patron);

        int desplazamiento = 0;
        while (desplazamiento <= n - m) {
            int j = m - 1;

            // Se compara de derecha a izquierda
            while (j >= 0) {
                comparaciones++;
                if (patron.charAt(j) != texto.charAt(desplazamiento + j)) break;
                j--;
            }

            if (j < 0) {
                ocurrencias.add(desplazamiento);
                desplazamiento += shift[0];
            } else {
                char c = texto.charAt(desplazamiento + j);
                int malCaracter = j - ultima.getOrDefault(c, -1);
                desplazamiento += Math.max(shift[j + 1], malCaracter);
            }
        }
        return ocurrencias;
    }
}
import algoritmos.BoyerMoore;
import algoritmos.KMP;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String TEXTO_DEMO =
        "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, "
        + "no ha mucho tiempo que vivia un hidalgo de los de lanza en astillero.";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Texto (Enter para usar el de ejemplo): ");
        String texto = sc.nextLine();
        if (texto.isBlank()) {
            texto = TEXTO_DEMO;
            System.out.println("> " + texto);
        }

        System.out.print("Patron a buscar: ");
        String patron = sc.nextLine();

        List<Integer> posKMP = KMP.buscar(texto, patron);
        long compKMP = KMP.comparaciones;

        List<Integer> posBM = BoyerMoore.buscar(texto, patron);
        long compBM = BoyerMoore.comparaciones;

        imprimir("Knuth-Morris-Pratt", posKMP, compKMP);
        imprimir("Boyer-Moore", posBM, compBM);

        sc.close();
    }

    private static void imprimir(String nombre, List<Integer> posiciones, long comparaciones) {
        System.out.println();
        System.out.println("--- " + nombre + " ---");
        if (posiciones.isEmpty()) {
            System.out.println("Sin ocurrencias.");
        } else {
            System.out.println("Ocurrencias: " + posiciones.size());
            System.out.println("Posiciones : " + posiciones);
        }
        System.out.println("Comparaciones de caracteres: " + comparaciones);
    }
}
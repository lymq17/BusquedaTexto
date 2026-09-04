@'
# Búsqueda de patrones en texto

Implementación en Java de dos algoritmos clásicos de búsqueda de cadenas: **Knuth-Morris-Pratt** y **Boyer-Moore**. El programa localiza todas las ocurrencias de un patrón dentro de un texto y cuenta las comparaciones de caracteres que realiza cada uno, para poder contrastar su rendimiento.

## Algoritmos

| Algoritmo | Preprocesamiento | Búsqueda | Idea central |
|---|---|---|---|
| Knuth-Morris-Pratt | O(m) | O(n + m) | Tabla LPS: al fallar reaprovecha el prefijo ya comparado y nunca retrocede sobre el texto |
| Boyer-Moore | O(m + σ) | O(n·m) en el peor caso, sublineal en la práctica | Compara de derecha a izquierda y salta bloques con las reglas de mal carácter y sufijo bueno |

## Estructura

```
src/
├── Main.java          Punto de entrada, ejecuta y compara ambos algoritmos
├── KMP.java
└── BoyerMoore.java
```

## Ejecución

```bash
javac -d bin src/*.java
java -cp bin Main
```

## Ejemplo

Buscando `nombre` sobre el texto de prueba:

```
--- Knuth-Morris-Pratt ---
Ocurrencias: 1
Posiciones : [34]
Comparaciones de caracteres: 139

--- Boyer-Moore ---
Ocurrencias: 1
Posiciones : [34]
Comparaciones de caracteres: 30
```

Boyer-Moore resuelve la misma búsqueda mirando menos de la cuarta parte de los caracteres.

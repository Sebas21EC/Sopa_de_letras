/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sopa_letras;

/**
 *
 * @author sebas
 */

import java.util.*;

public class Sopa_letras {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /* System.out.print("Ingrese el número de filas: ");
        String filas_string = sc.nextLine();
        int numero_filas = Integer.parseInt(filas_string);
        System.out.print("Ingrese el número de columnas: ");
        String columnas_string = sc.nextLine();
        int numero_columnas = Integer.parseInt(columnas_string);*/

        char[][] matriz = Generar_matiz_letras(8, 9);

        Imprimir_matriz(matriz);
         System.out.print("Ingrese palabra a buscar: ");
        String palabra = (sc.nextLine()).toUpperCase();
        //Buscar_palabra(matriz, palabra);
       String resu=buscar_palabra_recursivo(matriz, palabra)? "ENCONTRADO":"NO SE ENCONTRO";
        System.out.println(resu);

        long startTime = System.nanoTime();
        char[][] matriz_2 = ordenar_matriz(matriz);
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("\nTiempo en nanosegundos: " + timeElapsed);

        Imprimir_matriz(matriz_2);

    }

    public static char[][] Generar_matiz_letras(int filas, int columnas) {
        Random numero_random = new Random();
        char[][] matriz = new char[filas][columnas];
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                matriz[j][i] = (char) (numero_random.nextInt(25) + 65);
            }
        }

        return matriz;
    }

    public static void Imprimir_matriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "  ");
            }
            System.out.println("");
        }
    }

    public static void Buscar_palabra(char[][] matriz, String palabra) {
        int numero_filas = matriz.length;
        int numero_columnas = matriz[0].length;
        int tamanio_palabra = palabra.length();
        boolean encontrada = false;
        for (int i = 0; i < numero_filas; i++) {
            for (int j = 0; j < numero_columnas; j++) {
                if (matriz[i][j] == palabra.charAt(0)) {

                    // HORIZONTAL DERECHA
                    if (j + tamanio_palabra <= numero_columnas) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i][j + k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + i + "," + (j + k) + ") ; ");
                            }
                        }
                    }

                    // DIAGONAL, ABAJO, DERECHA
                    if (i + tamanio_palabra <= numero_filas && j + tamanio_palabra <= numero_columnas) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i + k][j + k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i + k) + "," + (j + k) + ") ; ");
                            }
                        }
                    }

                    // DIAGONAL,ARRIBA,DERECHA
                    if (i >= tamanio_palabra - 1 && j + tamanio_palabra <= numero_columnas) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i - k][j + k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i - k) + "," + (j + k) + ") ; ");
                            }
                        }
                    }

                    // Vertical, abajo
                    if (i + tamanio_palabra <= numero_filas) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i + k][j] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i + k) + "," + j + ") ; ");
                            }
                        }
                    }

                    // Vertical, arriba
                    if (i >= tamanio_palabra - 1) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i - k][j] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i - k) + "," + j + ") ; ");
                            }
                        }
                    }

                    // Horizontal, derecha a izquierda
                    if (j >= tamanio_palabra - 1) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i][j - k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + i + "," + (j - k) + ") ; ");
                            }
                        }
                    }

                    // Diagonal, abajo, izquierda
                    if (i + tamanio_palabra <= numero_filas && j >= tamanio_palabra - 1) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i + k][j - k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i + k) + "," + (j - k) + ") ; ");
                            }
                        }
                    }

                    // Diagonal, arriba, izquierda
                    if (i >= tamanio_palabra - 1 && j >= tamanio_palabra - 1) {
                        encontrada = true;
                        for (int k = 1; k < tamanio_palabra; k++) {
                            if (matriz[i - k][j - k] != palabra.charAt(k)) {
                                encontrada = false;
                                break;
                            }
                        }
                        if (encontrada) {
                            System.out.print("La palabra " + palabra + " se encuntra en las posiciones: ");
                            for (int k = 0; k < tamanio_palabra; k++) {
                                System.out.print("(" + (i - k) + "," + (j - k) + ") ; ");
                            }
                        }
                    }

                }
            }
        }
    }

    public static char[][] ordenar_matriz(char[][] matriz) {

        int numero_columna = matriz[0].length;
        int numero_fila = matriz.length;

        char[][] transpuesta = new char[numero_columna][numero_fila];
        for (int i = 0; i < numero_columna; i++) {
            for (int j = 0; j < numero_fila; j++) {
                transpuesta[i][j] = matriz[j][i];
            }
        }

        // Ordenar 
        char temporal;
        for (int i = 0; i < numero_columna; i++) {
            Arrays.sort(transpuesta[i]);
            for (int j = 0, k = numero_fila - 1; j < k; j++, k--) {
                temporal = transpuesta[i][j];
                transpuesta[i][j] = transpuesta[i][k];
                transpuesta[i][k] = temporal;
            }
        }

        // resultado final
        char[][] resultado = new char[numero_fila][numero_columna];
        for (int i = 0; i < numero_columna; i++) {
            for (int j = 0; j < numero_fila; j++) {
                resultado[j][i] = transpuesta[i][j];
            }
        }

        return resultado;
    }

    public static boolean buscar_palabra_recursivo(char[][] matriz, String palabra) {
        int m = matriz.length;
        int n = matriz[0].length;
        boolean[][] visitado = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (buscarPalabraRecursiva(matriz, palabra, i, j, visitado)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean buscarPalabraRecursiva(char[][] matriz, String palabra, int fila, int columna, boolean[][] visitado) {
        // Si la palabra está vacía, entonces la hemos encontrado
        if (palabra.length() == 0) {
            System.out.println(fila + ";" + columna);
            return true;
        }

        int m = matriz.length;
        int n = matriz[0].length;

        // Si estamos fuera de la matriz o la posición ya ha sido visitada, entonces regresamos false
        if (fila < 0 || fila >= m || columna < 0 || columna >= n || visitado[fila][columna]) {
            return false;
        }

        // Si la letra actual no coincide con la letra de la palabra que estamos buscando, entonces regresamos false
        if (matriz[fila][columna] != palabra.charAt(0)) {
            return false;
        }

        // Marcamos la posición actual como visitada
        visitado[fila][columna] = true;

        // Buscamos recursivamente en todas las posibles direcciones
        boolean encontrado = buscarPalabraRecursiva(matriz, palabra.substring(1), fila - 1, columna, visitado)
                || // Arriba
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila + 1, columna, visitado)
                || // Abajo
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila, columna - 1, visitado)
                || // Izquierda
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila, columna + 1, visitado)
                || // Derecha
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila - 1, columna - 1, visitado)
                || // Arriba-izquierda
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila - 1, columna + 1, visitado)
                || // Arriba-derecha
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila + 1, columna - 1, visitado)
                || // Abajo-izquierda
                buscarPalabraRecursiva(matriz, palabra.substring(1), fila + 1, columna + 1, visitado);   // Abajo-derecha

        // Desmarcamos la posición actual como visitada
        visitado[fila][columna] = false;

        return encontrado;
    }

    public static char[][] ordenar_matriz_burbuja(char[][] matriz) {
        int numero_filas = matriz.length;
        int numero_columnas = matriz[0].length;

        // Ordenar por columnas en forma descendente
        for (int j = 0; j < numero_columnas; j++) {
            for (int i = 0; i < numero_filas - 1; i++) {
                for (int k = i + 1; k < numero_filas; k++) {
                    if (matriz[k][j] > matriz[i][j]) {
                        // Intercambiar elementos
                        char temp = matriz[i][j];
                        matriz[i][j] = matriz[k][j];
                        matriz[k][j] = temp;
                    }
                }
            }
        }

        return matriz;
    }

    public static char[][] ordenar_matriz_insercion(char[][] matriz) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Recorrer la matriz por columnas
        for (int j = 0; j < columnas; j++) {
            // Aplicar ordenamiento por inserción a cada columna
            for (int i = 1; i < filas; i++) {
                char clave = matriz[i][j];
                int k = i - 1;
                while (k >= 0 && matriz[k][j] > clave) {
                    matriz[k + 1][j] = matriz[k][j];
                    k--;
                }
                matriz[k + 1][j] = clave;
            }
        }
        return matriz;
    }

    public static char[][] ordenar_matriz_seleccion(char[][] matriz) {
        int n = matriz.length;
        int m = matriz[0].length;
        char[][] resultado = new char[n][m];

        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                resultado[i][j] = matriz[i][j];
            }
            for (int i = 0; i < n - 1; i++) {
                int maxIndex = i;
                for (int k = i + 1; k < n; k++) {
                    if (resultado[k][j] > resultado[maxIndex][j]) {
                        maxIndex = k;
                    }
                }
                char temp = resultado[i][j];
                resultado[i][j] = resultado[maxIndex][j];
                resultado[maxIndex][j] = temp;
            }
        }

        return resultado;
    }

    public static char[][] ordenamiento_mezcla(char[][] matriz) {
        int numero_filas = matriz.length;
        int numero_columnas = matriz[0].length;
        char[][] resultado = new char[numero_filas][numero_columnas];

        if (numero_filas == 1) {
            return matriz;
        } else {
            int mitad = numero_filas / 2;
            char[][] matriz_izquierda = new char[mitad][numero_columnas];
            char[][] matriz_derecha = new char[numero_filas - mitad][numero_columnas];

            for (int i = 0; i < mitad; i++) {
                matriz_izquierda[i] = matriz[i];
            }

            for (int i = mitad; i < numero_filas; i++) {
                matriz_derecha[i - mitad] = matriz[i];
            }

            matriz_izquierda = ordenamiento_mezcla(matriz_izquierda);
            matriz_derecha = ordenamiento_mezcla(matriz_derecha);

            resultado = mezclar(matriz_izquierda, matriz_derecha);
        }
        return resultado;
    }

    public static char[][] mezclar(char[][] matriz_izquierda, char[][] matriz_derecha) {
        int longitud_izquierda = matriz_izquierda.length;
        int longitud_derecha = matriz_derecha.length;
        int total = longitud_izquierda + longitud_derecha;
        int i = 0, j = 0, k = 0;
        char[][] resultado = new char[total][matriz_izquierda[0].length];

        while (i < longitud_izquierda && j < longitud_derecha) {
            if (matriz_izquierda[i][0] < matriz_derecha[j][0]) {
                resultado[k] = matriz_izquierda[i];
                i++;
            } else {
                resultado[k] = matriz_derecha[j];
                j++;
            }
            k++;
        }

        while (i < longitud_izquierda) {
            resultado[k] = matriz_izquierda[i];
            i++;
            k++;
        }

        while (j < longitud_derecha) {
            resultado[k] = matriz_derecha[j];
            j++;
            k++;
        }

        return resultado;
    }

    public static void shellSort(int[] arr) {
        int n = arr.length;

        // Definir el tamaño del intervalo
        int intervalo = 1;
        while (intervalo < n / 3) {
            intervalo = intervalo * 3 + 1;
        }

        // Ordenar el arreglo con intervalos decrecientes
        while (intervalo > 0) {
            for (int i = intervalo; i < n; i++) {
                int tmp = arr[i];
                int j = i;
                while (j >= intervalo && arr[j - intervalo] > tmp) {
                    arr[j] = arr[j - intervalo];
                    j -= intervalo;
                }
                arr[j] = tmp;
            }
            intervalo = (intervalo - 1) / 3;
        }
    }

    public static void quicksort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = particion(arr, left, right);
            quicksort(arr, left, pivotIndex - 1);
            quicksort(arr, pivotIndex + 1, right);
        }
    }

    private static int particion(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                intercambio(arr, i, j);
            }
        }
        intercambio(arr, i + 1, right);
        return i + 1;
    }

    private static void intercambio(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    
    
    
    
}

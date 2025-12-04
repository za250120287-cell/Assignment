public class Main {

    public static void main(String[] args) {

        // Hands 3
        String[] nombres = {"CARRO", "AVION", "TREN", "BARCO"};
        int[] frecuencias = {15, 13, 4, 8};

        // Hands 4–Hands 5
        double[] data = {
                12.3, 11.8, 10.5, 15.2, 22.1, 25.5, 26.8, 27.4,
                28.9, 30.0, 14.2, 13.5, 18.1, 17.8, 19.4, 21.0,
                23.5, 24.7, 29.3, 31.2, 32.1, 33.0, 34.5, 35.8,
                36.3, 37.9, 38.4, 39.0, 40.1, 41.5
        };

        TablaFrecuencias tabla = new TablaFrecuencias(nombres, frecuencias, data);

        tabla.imprimirHO3();
        tabla.imprimirHO4();
        tabla.imprimirHO5();
    }
}
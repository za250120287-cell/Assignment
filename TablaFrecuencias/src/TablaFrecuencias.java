import java.util.ArrayList;
import java.util.Arrays;

public class TablaFrecuencias {

    private ArrayList<Categoria> tablaHO3;
    private ArrayList<Categoria> tablaHO4;

    private double[] data;   // para HO4 y HO5

    public TablaFrecuencias(String[] nombres, int[] frecuencias, double[] dataHO4) {

        construirHO3(nombres, frecuencias);

        this.data = dataHO4;
        construirHO4();
    }


    //  HANDS 3

    private void construirHO3(String[] nombres, int[] frecuencias) {
        tablaHO3 = new ArrayList<>();

        int total = 0;
        for (int f : frecuencias) total += f;

        for (int i = 0; i < nombres.length; i++) {
            tablaHO3.add(new Categoria(nombres[i], frecuencias[i], total));
        }
    }

    public void imprimirHO3() {
        System.out.println("\n===== TABLA DE FRECUENCIAS (HANDS-ON 3) =====");
        System.out.printf("%-10s %-5s %-5s %-5s\n", "MT", "f", "fr", "%");

        int total = 0;

        for (Categoria c : tablaHO3) {
            System.out.printf("%-10s %-5d %-5.2f %-5.0f%%\n",
                    c.getNombre(), c.getFa(), c.getFr(), c.getPorcentaje());
            total += c.getFa();
        }

        System.out.println("---------------------------------------------");
        System.out.printf("%-10s %-5d %-5.2f %-5.0f%%\n",
                "TOTAL", total, 1.00, 100.0);
    }


    //  HANDS 4  (agrupación)

    private void construirHO4() {

        tablaHO4 = new ArrayList<>();

        Arrays.sort(data);
        int n = data.length;

        double min = data[0];
        double max = data[n - 1];
        double rango = max - min;

        int k = (int) Math.ceil(1 + 3.322 * Math.log10(n));
        double amplitud = Math.ceil(rango / k);

        double li = min;

        for (int i = 0; i < k; i++) {
            double ls = li + amplitud;
            tablaHO4.add(new Categoria(li, ls));
            li = ls;
        }

        for (double valor : data) {
            for (Categoria c : tablaHO4) {
                if ((valor >= c.getLi() && valor < c.getLs()) || valor == max) {
                    c.incrementarFA();
                    break;
                }
            }
        }

        int faAc = 0;
        double frAc = 0;

        for (Categoria c : tablaHO4) {
            double fr = (double) c.getFa() / data.length;
            c.setFr(fr);

            faAc += c.getFa();
            frAc += fr;

            c.setFaAc(faAc);
            c.setFrAc(frAc);
        }
    }

    public void imprimirHO4() {
        System.out.println("\n===== TABLA EXTENDIDA (HANDS-ON 4) =====");
        System.out.printf("%-15s %-5s %-5s %-5s %-8s %-8s %-8s\n",
                "Clase", "f", "fr", "%", "FA Ac", "FR Ac", "PM");

        for (Categoria c : tablaHO4) {
            System.out.printf("%6.2f-%-6.2f %-5d %-5.2f %-5.0f%% %-8d %-8.2f %-8.2f\n",
                    c.getLi(), c.getLs(), c.getFa(), c.getFr(), c.getPorcentaje(),
                    c.getFaAc(), c.getFrAc(), c.getPuntoMedio());
        }
    }


    //  HANDS-ON 5

    public void imprimirHO5() {

        System.out.println("\n===== MEDIDAS DE TENDENCIA CENTRAL (HANDS-ON 5) =====");

        double media = calcularMedia();
        double moda = calcularModa();
        double mediana = calcularMediana();

        System.out.printf("Media agrupada: %.2f\n", media);
        System.out.printf("Moda agrupada: %.2f\n", moda);
        System.out.printf("Mediana agrupada: %.2f\n", mediana);
    }

    // MEDIA AGRUPADA
    private double calcularMedia() {
        double suma = 0;
        int total = data.length;

        for (Categoria c : tablaHO4) {
            suma += c.getPuntoMedio() * c.getFa();
        }
        return suma / total;
    }

    // MODA AGRUPADA
    private double calcularModa() {

        Categoria mayorFa = tablaHO4.get(0);
        for (Categoria c : tablaHO4) {
            if (c.getFa() > mayorFa.getFa()) {
                mayorFa = c;
            }
        }

        // Fórmula de moda agrupada
        // Mo = Li + ( (fm - f1) / (2fm - f1 - f2) ) * h

        int idx = tablaHO4.indexOf(mayorFa);

        double Li = mayorFa.getLi();
        double fm = mayorFa.getFa();
        double f1 = (idx == 0) ? 0 : tablaHO4.get(idx - 1).getFa();
        double f2 = (idx == tablaHO4.size() - 1) ? 0 : tablaHO4.get(idx + 1).getFa();

        double h = mayorFa.getLs() - mayorFa.getLi();

        return Li + ((fm - f1) / ((2 * fm) - f1 - f2)) * h;
    }

    // MEDIANA AGRUPADA
    private double calcularMediana() {

        int N = data.length;
        int N2 = N / 2;

        Categoria medianaClase = null;

        for (Categoria c : tablaHO4) {
            if (c.getFaAc() >= N2) {
                medianaClase = c;
                break;
            }
        }

        int idx = tablaHO4.indexOf(medianaClase);

        // Fórmula:
        // Me = Li + ( (N/2 - FAprev) / f ) * h

        double Li = medianaClase.getLi();
        double h = medianaClase.getLs() - medianaClase.getLi();
        int FaPrev = (idx == 0) ? 0 : tablaHO4.get(idx - 1).getFaAc();
        int f = medianaClase.getFa();

        return Li + ((double)(N2 - FaPrev) / f) * h;
    }
}
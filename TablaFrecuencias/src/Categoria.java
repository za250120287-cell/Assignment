public class Categoria {

    // Para Hands 3 (nominal)
    private String nombre;

    // Para Hands 4 (intervalos)
    private double li;
    private double ls;
    private double puntoMedio;

    // Comunes
    private int fa;
    private double fr;
    private double porcentaje;
    private int faAc;
    private double frAc;

    // Constructor para Hands 3 (nominal)
    public Categoria(String nombre, int fa, int total) {
        this.nombre = nombre;
        this.fa = fa;
        this.fr = (double) fa / total;
        this.porcentaje = fr * 100;
    }

    //Constructor para Hands 4 (intervalos)
    public Categoria(double li, double ls) {
        this.li = li;
        this.ls = ls;
        this.puntoMedio = (li + ls) / 2.0;
    }

    public void incrementarFA() { fa++; }

    public String getNombre() { return nombre; }

    public double getLi() { return li; }
    public double getLs() { return ls; }
    public double getPuntoMedio() { return puntoMedio; }

    public int getFa() { return fa; }
    public double getFr() { return fr; }
    public double getPorcentaje() { return porcentaje; }

    public int getFaAc() { return faAc; }
    public void setFaAc(int faAc) { this.faAc = faAc; }

    public double getFrAc() { return frAc; }
    public void setFrAc(double frAc) { this.frAc = frAc; }

    public void setFr(double fr) {
        this.fr = fr;
        this.porcentaje = fr * 100;
    }
}

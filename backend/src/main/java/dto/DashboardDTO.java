package dto;

public class DashboardDTO {

    public long correctos;
    public long incorrectos;
    public double porcentaje;

    public DashboardDTO(
            long c,
            long i,
            double p) {

        this.correctos = c;
        this.incorrectos = i;
        this.porcentaje = p;
    }
}
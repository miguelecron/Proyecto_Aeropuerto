package Tarea12_Aeropuerto.modelo;

public class Charter extends Vuelo {

    private String nifEmpresa;

    public Charter(String destino, String modeloAvion, int plazas, String nifEmpresa, int precio) {
        super(destino, modeloAvion, plazas, precio);
        this.nifEmpresa = nifEmpresa;
    }

    // ------------------------- GETTERS & SETTERS -----------------------------------


    public String getNifEmpresa() {
        return nifEmpresa;
    }

    public void setNifEmpresa(String nifEmpresa) {
        this.nifEmpresa = nifEmpresa;
    }


    // ------------------------- METODOS OVERRIDE -----------------------------------


    @Override
    public double calcularPrecioBillete() {
        double precioTotal = getPrecio() * 1.25;
        if (getPlazas() < 200){
            precioTotal += 50;
        }
        return precioTotal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\nVuelo Charter");
        sb.append("\n-------------");
        sb.append(super.toString());
        sb.append("\nNIF Empresa: ").append(nifEmpresa);

        return sb.toString();
    }
}

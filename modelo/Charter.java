package Tarea12_Aeropuerto.modelo;

public class Charter extends Vuelo {

    private String nifEmpresa;

    public Charter(String destino, String modeloAvion, int plazas, String nifEmpresa) {
        super(destino, modeloAvion, plazas);
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
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n Vuelo Charter");
        sb.append("\n-------------");
        sb.append(super.toString());
        sb.append("\n NIF Empresa: ").append(nifEmpresa);

        return sb.toString();
    }
}

package Tarea12_Aeropuerto.modelo;

import java.util.*;

public class Aeropuerto {

    private Map<String, TreeSet<Vuelo>> vuelos;

    public Aeropuerto() {
        vuelos = new TreeMap<>();
    }

    /**
     * Anade un vuelo a la aerolinea correspondiente solo en el caso de que el vuelo
     * no estuviese ya introducido, si la aerolinea no existiese se anade tanto la
     * aerolinea como el vuelo.
     */
    public void addVuelo(String aerolinea, Vuelo vuelo) {

        if (!this.vuelos.containsKey(aerolinea)) {
            Set<Vuelo> tmpVuelos = new TreeSet<>();
            tmpVuelos.add(vuelo);
            this.vuelos.put(aerolinea, (TreeSet<Vuelo>) tmpVuelos);
        } else {
            this.vuelos.get(aerolinea).add(vuelo);
        }

    }

    /**
     * Imprime los vuelos por cada aerolinea ordenados por destino, tanto aerolineas
     * como vuelos estaran ordenados alfabeticamente (Ver resultados de ejecucion)
     */
    public void ordenAerolineasAlfabetico() {
        for (String aerolinea : vuelos.keySet()) {
            System.out.println();
            System.out.println(aerolinea);
            System.out.println("========");
            for (Vuelo vuelo : this.vuelos.get(aerolinea)) {
                System.out.println(vuelo);
            }
        }
    }

    /**
     * Muestra los vuelos regulares de la aerolinea pasada por parametro, se
     * visualizaran de mayor a menor segun el numero de plazas
     *
     * @param aerolinea Aerolinea de la que se imprimiran los vuelos regulares
     */
    public void regularPorPlazas(String aerolinea) {
        Set<Regular> vuelosRegulares = new TreeSet<>(new Comparator<Regular>() {
            @Override
            public int compare(Regular r1, Regular r2) {
                // Ordenar por plazas libres de mayor a menor
                return r2.getPlazasLibres() - r1.getPlazasLibres();
            }
        });

        for (Vuelo vuelo : this.vuelos.get(aerolinea)) {
            if (vuelo instanceof Regular) {
                vuelosRegulares.add((Regular) vuelo);
            }
        }

        for (Regular regular : vuelosRegulares) {
            System.out.println(regular);
        }
    }


    /**
     * Devuelve una lista con vuelos regulares con plazas libres
     *
     * @return aerolina Aerolina del avion charter con m?s capacidad
     */
    public List<Vuelo> plazasLibres() {
        return null;
    }

    /**
     * Muestra el numero de vuelos de cada aerolinea que llegan al destino pasado
     * por parametro, ver resultados de ejecucion
     *
     * @param destino Destino del que se debe sacar la estadistica
     */
    public void estadisticaDestino(String destino) {

    }

    /**
     * Borra los vuelos reservados por una empresa y devuelve el numero de vuelos
     * borrados, utiliza un conjunto de entradas
     *
     * @param nifEmpresa
     * @return numero de vuelos borrados
     */
    public int borrarVuelosEmpresa(String nifEmpresa) {
        return 0;
    }

    /**
     * Imprime la lista de vuelos pasada por parametro
     *
     * @param listaVuelos
     */
    public void imprimirListaVuelos(List<Vuelo> listaVuelos) {

    }

    /**
     * Represetaci?n textual del mapa tal y como se muestra en el enunciado
     */
    @Override
    public String toString() {
        return null;
    }

    /**
     * Rellena el mapa haciendo uso de un fichero de texto
     */
    public void leerFicheroCursos() {
        Scanner entrada = null;
        try {
            entrada = new Scanner(this.getClass().getResourceAsStream("../aviones.txt"));
            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                int pos = linea.indexOf(":");
                String aerolinea = linea.substring(0, pos);
                String[] vuelo = linea.substring(pos + 1).split(":");
                String destino = vuelo[1];
                String avion = vuelo[2];
                int plazas = Integer.parseInt(vuelo[3].trim());
                if (vuelo[0].equals("R")) {
                    int plazasLibres = Integer.parseInt(vuelo[4].trim());
                    this.addVuelo(aerolinea, new Regular(destino, avion, plazas, plazasLibres));
                } else {
                    String nifEmpresa = vuelo[4];
                    this.addVuelo(aerolinea, new Charter(destino, avion, plazas, nifEmpresa));
                }
            }

        } finally {
            try {
                entrada.close();
            } catch (NullPointerException e) {
                System.out.println("Error en IO , no se ha creado el fichero");
            }
        }

    }

}

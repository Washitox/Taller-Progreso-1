class Paciente {
    private String nombre;
    private int prioridad; // 1 = alta, 2 = media, 3 = baja

    public Paciente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return nombre + " (Prioridad " + prioridad + ")";
    }
}
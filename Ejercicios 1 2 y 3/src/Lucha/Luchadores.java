import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Luchadores extends JFrame {
    private JTabbedPane interfaz;
    private JPanel ventana;
    private JTextField nameLuchador;
    private JTextArea infAñadir;
    private JButton añadir;
    private JTextArea infEliminar;
    private JButton Eliminar;
    private JTextField nameBuscar;
    private JTextArea infBuscar;
    private JButton Buscar;
    private JPanel intAñadir;
    private JPanel intEliminar;
    private JPanel intBuscar;
    private JLabel textobuscar;

    private Cola cola;

    public Luchadores() {
        setContentPane(ventana);
        setTitle("Torneo de Artes Marciales");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cola = new Cola(100);

        // Botón añadir luchador
        añadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nameLuchador.getText().trim();
                if (!nombre.isEmpty()) {
                    Luchador l = new Luchador(nombre);
                    cola.enqueue(l);
                    nameLuchador.setText("");
                    // Mostrar toda la cola actualizada
                    infAñadir.setText("Lista actual de luchadores:\n" + cola.listarCola());
                }
            }
        });

        // Botón eliminar (llamar a pelear)
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!cola.estaVacia()) {
                    Luchador l = cola.dequeue();
                    infEliminar.append(l.getNombre() + " ha sido llamado a pelear.\n");
                    // Actualizar la cola después de eliminar
                    infAñadir.setText("Lista actual de luchadores:\n" + cola.listarCola());
                } else {
                    infEliminar.append("Ya no hay luchadores.\n");
                }
            }
        });

        // Botón buscar
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreBuscado = nameBuscar.getText().trim();
                if (!nombreBuscado.isEmpty()) {
                    boolean encontrado = false;
                    for (int i = 0; i < cola.getTamaño(); i++) {
                        Luchador l = cola.obtenerEn(i);
                        if (l.getNombre().equalsIgnoreCase(nombreBuscado)) {
                            encontrado = true;
                            infBuscar.append(nombreBuscado + " El luchador es el " + (i + 1) + " de la lista de luchadores.\n");
                            break;
                        }
                    }
                    if (!encontrado) {
                        infBuscar.append(nombreBuscado + " Ya ha sido convocado o no fue inscrito.\n");
                    }
                    nameBuscar.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Luchadores().setVisible(true);
            }
        });
    }
}
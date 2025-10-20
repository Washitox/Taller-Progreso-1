import javax.swing.*;
import java.awt.*;
import java.util.Stack;


public class NavesOrden extends JFrame {

    private JPanel Ventana;
    private JTabbedPane todo;
    private JTextField ingresaText;
    private JTextArea invertidos;
    private JPanel intAñadir;
    private JPanel intEliminar;
    private JPanel intBuscar;
    private JButton invertir;

    private JTextArea eliminado;
    private JButton eliminar;

    private JTextField ingText3;
    private JTextArea infText3;
    private JButton buscar;
    private JLabel text3;

    private Pila pila; // Pila compartida

    public NavesOrden() {
        setTitle("Gestión de Naves");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pila = new Pila(); // Inicializamos la pila

        Ventana = new JPanel();
        Ventana.setLayout(new BorderLayout());

        todo = new JTabbedPane();

        // Panel para invertir/añadir
        intAñadir = new JPanel(new BorderLayout());
        JPanel top = new JPanel();
        top.add(new JLabel("Ingrese las naves separadas por espacio:"));
        ingresaText = new JTextField(20);
        top.add(ingresaText);
        intAñadir.add(top, BorderLayout.NORTH);

        invertir = new JButton("Añadir e Invertir");
        intAñadir.add(invertir, BorderLayout.SOUTH);

        invertidos = new JTextArea();
        invertidos.setEditable(false);
        intAñadir.add(new JScrollPane(invertidos), BorderLayout.CENTER);

        todo.addTab("Invertir", intAñadir);

        // Panel eliminar como pop de pila
        intEliminar = new JPanel(new BorderLayout());
        eliminar = new JButton("Eliminar cima de la pila");
        intEliminar.add(eliminar, BorderLayout.NORTH);

        eliminado = new JTextArea();
        eliminado.setEditable(false);
        intEliminar.add(new JScrollPane(eliminado), BorderLayout.CENTER);

        todo.addTab("Eliminar", intEliminar);

        // Panel Buscar por posición
        intBuscar = new JPanel(new BorderLayout());
        JPanel topBuscar = new JPanel();
        text3 = new JLabel("Ingrese ID de nave a buscar:");
        topBuscar.add(text3);

        ingText3 = new JTextField(10);
        topBuscar.add(ingText3);

        buscar = new JButton("Buscar");
        topBuscar.add(buscar);

        intBuscar.add(topBuscar, BorderLayout.NORTH);

        infText3 = new JTextArea();
        infText3.setEditable(false);
        intBuscar.add(new JScrollPane(infText3), BorderLayout.CENTER);

        todo.addTab("Buscar", intBuscar);

        Ventana.add(todo, BorderLayout.CENTER);
        add(Ventana);

        // Acciones de los botones
        invertir.addActionListener(e -> invertirSecuencia());
        eliminar.addActionListener(e -> eliminarCima());
        buscar.addActionListener(e -> buscarNave());
    }

    // Método para añadir y mostrar la pila invertida
    private void invertirSecuencia() {
        String entrada = ingresaText.getText().trim();
        if (entrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese al menos una nave.");
            return;
        }

        String[] partes = entrada.split("\\s+");
        for (String parte : partes) {
            try {
                int id = Integer.parseInt(parte);
                pila.añadir(new Nave(id));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Entrada inválida: " + parte);
                return;
            }
        }

        // Mostrar pila invertida
        StringBuilder resultado = new StringBuilder();
        Stack<Nave> temp = new Stack<>();
        while (!pila.estaVacia()) {
            Nave n = pila.eliminar();
            resultado.append(n).append(" ");
            temp.push(n);
        }
        while (!temp.isEmpty()) {
            pila.añadir(temp.pop());
        }

        invertidos.setText(resultado.toString().trim());
        ingresaText.setText("");
    }

    // Método para eliminar la cima de la pila
    private void eliminarCima() {
        if (pila.estaVacia()) {
            eliminado.setText("La pila está vacía.");
            return;
        }

        Nave n = pila.eliminar();
        eliminado.setText("Se eliminó la nave: " + n + "\nPila actual: " + pila.mostrarTodo());
    }

    // Método para buscar una nave específica y mostrar su posición desde la cima
    private void buscarNave() {
        String texto = ingText3.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID de nave a buscar.");
            return;
        }

        try {
            int id = Integer.parseInt(texto);
            Stack<Nave> temp = new Stack<>();
            int posicion = -1;
            int contador = 1;

            // Buscamos desde la cima
            while (!pila.estaVacia()) {
                Nave n = pila.eliminar();
                temp.push(n);
                if (n.getId() == id && posicion == -1) {
                    posicion = contador; // Guardamos la posición desde la cima
                }
                contador++;
            }

            // Recuperamos la pila
            while (!temp.isEmpty()) {
                pila.añadir(temp.pop());
            }

            if (posicion != -1) {
                infText3.setText("Nave " + id + " encontrada en la posición " + posicion + " desde la cima.");
            } else {
                infText3.setText("Nave " + id + " no encontrada en la pila.");
            }

            ingText3.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NavesOrden().setVisible(true));
    }
}
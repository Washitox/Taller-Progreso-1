import javax.swing.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InterfazEsferasDragon extends JFrame {
    private Cola cola;

    // Componentes
    private JTextField txtNumero;
    private JTextField txtUbicacion;
    private JTextArea txtResultado;
    private JButton btnAgregar;
    private JButton btnMostrar;
    private JButton btnInvocar;
    private JPanel Ventana;

    public InterfazEsferasDragon() {
        setContentPane(Ventana);
        setTitle("✨ Búsqueda de las Esferas del Dragón ✨");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550, 420);
        setLocationRelativeTo(null);

        cola = new Cola();

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEsfera();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEsferas();
            }
        });

        btnInvocar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invocarShenlong();
            }
        });
    }

    private void agregarEsfera() {
        String numTexto = txtNumero.getText().trim();
        String ubicacion = txtUbicacion.getText().trim();

        if (numTexto.isEmpty() || ubicacion.isEmpty()) {
            txtResultado.append("⚠️ Debes ingresar número y ubicación.\n");
            return;
        }

        try {
            int numero = Integer.parseInt(numTexto);

            // Validar rango
            if (numero < 1 || numero > 7) {
                txtResultado.append("⚠️ Solo existen 7 esferas (1 al 7).\n");
                return;
            }

            // Validar duplicado
            boolean repetida = false;
            for (EsferaDragon e : getColaInterna()) {
                if (e.getNumero() == numero) {
                    repetida = true;
                    txtResultado.append("⚠️ La esfera " + numero + " ya fue encontrada en " + e.getUbicacion() + ".\n");
                    break;
                }
            }

            if (!repetida) {
                cola.agregar(new EsferaDragon(numero, ubicacion));
                txtResultado.append("✅ Esfera " + numero + " agregada en " + ubicacion + ".\n");
                txtNumero.setText("");
                txtUbicacion.setText("");
            }

        } catch (NumberFormatException ex) {
            txtResultado.append("⚠️ El número debe ser un entero válido.\n");
        }
    }

    private void mostrarEsferas() {
        txtResultado.append("\n📜 Estado actual de las esferas:\n");
        txtResultado.append(capturarSalida(() -> cola.mostrar()));
    }

    private void invocarShenlong() {
        txtResultado.append("\n🐉 Intentando invocar a Shenlong...\n");
        txtResultado.append(capturarSalida(() -> cola.invocar()));
    }


    private String capturarSalida(Runnable accion) {
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(salida);
        PrintStream viejo = System.out;
        System.setOut(ps);
        accion.run();
        System.out.flush();
        System.setOut(viejo);
        return salida.toString();
    }


    private java.util.Queue<EsferaDragon> getColaInterna() {
        try {
            java.lang.reflect.Field field = Cola.class.getDeclaredField("cola");
            field.setAccessible(true);
            return (java.util.Queue<EsferaDragon>) field.get(cola);
        } catch (Exception e) {
            return new java.util.LinkedList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazEsferasDragon().setVisible(true));
    }
}

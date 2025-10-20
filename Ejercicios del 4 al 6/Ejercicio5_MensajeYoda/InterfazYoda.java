import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazYoda extends JFrame {

    // 🔹 Campos que deben coincidir exactamente con los nombres del .form
    private JPanel Ventana;
    private JTextField txtSecuencia;
    private JButton btnProcesar;
    private JTextPane txtResultado;

    public InterfazYoda() {
        // Inicializa el formulario
        setContentPane(Ventana);
        setTitle("Mensaje de Yoda - Formato RPN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        // Acción del botón
        btnProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesarMensaje();
            }
        });
    }

    private void procesarMensaje() {
        String linea = txtSecuencia.getText().trim();

        if (linea.isEmpty()) {
            txtResultado.setText("⚠️ Ingrese una secuencia RPN válida.");
            return;
        }

        String[] tokens = linea.split(" ");
        java.util.Stack<String> pila = new java.util.Stack<>();

        for (String token : tokens) {
            if (token.equals("+")) {
                if (pila.size() >= 2) {
                    String segundo = pila.pop();
                    String primero = pila.pop();
                    // 🧠
                    pila.push(segundo + " " + primero);
                } else {
                    txtResultado.setText("❌ Error: operador '+' sin suficientes operandos.");
                    return;
                }
            } else {
                token = token.replaceAll("^\"|\"$", "");
                pila.push(token);
            }
        }

        if (pila.size() == 1) {
            txtResultado.setText("🟢 Mensaje de Yoda:\n" + pila.pop());
        } else {
            txtResultado.setText("❌ Error: la expresión RPN no está bien formada.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazYoda ventana = new InterfazYoda();
            ventana.setVisible(true);
        });
    }
}

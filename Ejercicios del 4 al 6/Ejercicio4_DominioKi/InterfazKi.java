import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class InterfazKi {
    private JPanel Ventana;
    private JTextField txtExpresion;
    private JButton btnDefinir;
    private JTextPane txtResultado;

    public InterfazKi() {

        btnDefinir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expresion = txtExpresion.getText().trim();

                if (expresion.isEmpty()) {
                    txtResultado.setForeground(Color.ORANGE);
                    txtResultado.setText("⚠️ Por favor ingrese una expresión.");
                    return;
                }

                boolean balanceada = ValidadorExpresion.esBalanceada(expresion);

                if (balanceada) {
                    txtResultado.setForeground(new Color(0, 150, 0)); // verde
                    txtResultado.setText("✅ La expresión está balanceada.");
                } else {
                    txtResultado.setForeground(Color.RED);
                    txtResultado.setText("❌ La expresión NO está balanceada.");
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Validador de Expresiones KI");
        frame.setContentPane(new InterfazKi().Ventana);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

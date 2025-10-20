import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HospitalSanMungo extends JFrame {
    private JPanel panelPrincipal;
    private JTabbedPane tabbedPane1;
    private JTextField nombre;
    private JComboBox prioridad;
    private JButton agregar;
    private JTextArea infoAgregar;
    private JButton atender;
    private JTextArea infoAtender;
    private JTextField nombreBuscar;
    private JButton buscar;
    private JTextArea infoBuscar;
    private JPanel intAgregar;
    private JPanel intAtender;
    private JPanel intBuscar;
    private JLabel txtAgregar;
    private JLabel txtBuscar;

    private ColaPrioridad cola;

    public HospitalSanMungo() {
        if (panelPrincipal == null) {
            construirUIProgramatica();
        } else {
            setContentPane(panelPrincipal);
        }

        setTitle("Hospital San Mungo - Atención Prioritaria");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cola = new ColaPrioridad(200);

        if (prioridad.getItemCount() == 0) {
            prioridad.addItem("1 - Alta");
            prioridad.addItem("2 - Media");
            prioridad.addItem("3 - Baja");
        }

        // Botón agregar
        agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom = nombre.getText().trim();
                if (nom.isEmpty()) {
                    JOptionPane.showMessageDialog(HospitalSanMungo.this, "Ingresa el nombre del paciente.");
                    return;
                }
                int pr = obtenerPrioridadSeleccionada();
                Paciente p = new Paciente(nom, pr);
                cola.enqueue(p);
                nombre.setText("");
                actualizarInfoAgregar();
            }
        });

        // Botón atender
        atender.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cola.estaVacia()) {
                    JOptionPane.showMessageDialog(HospitalSanMungo.this, "No hay pacientes en la cola.");
                    return;
                }
                Paciente atendido = cola.dequeue(); // ya será el de mayor prioridad
                infoAtender.append(atendido.getNombre() + " ha sido atendido (prioridad " + atendido.getPrioridad() + ").\n");
                actualizarInfoAgregar();
            }
        });

        // Botón buscar
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomBus = nombreBuscar.getText().trim();
                if (nomBus.isEmpty()) {
                    JOptionPane.showMessageDialog(HospitalSanMungo.this, "Ingresa el nombre a buscar.");
                    return;
                }
                int pos = cola.buscarPosicion(nomBus);
                if (pos == -1) {
                    infoBuscar.append(nomBus + " no está en la cola.\n");
                } else {
                    infoBuscar.append(nomBus + " está en la posición " + pos + " de la cola.\n");
                }
                nombreBuscar.setText("");
            }
        });

        // Mostrar cola inicial
        actualizarInfoAgregar();
    }

    // Si la UI no fue creada por un GUI designer, se crea aquí (asegura que el archivo compile)
    private void construirUIProgramatica() {
        panelPrincipal = new JPanel(new BorderLayout());
        tabbedPane1 = new JTabbedPane();

        // Panel Agregar
        intAgregar = new JPanel(new BorderLayout(8,8));
        JPanel formAgregar = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4,4,4,4);
        c.gridx = 0; c.gridy = 0; c.anchor = GridBagConstraints.LINE_END;
        formAgregar.add(new JLabel("Nombre:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START;
        nombre = new JTextField(18);
        formAgregar.add(nombre, c);

        c.gridx = 0; c.gridy = 1; c.anchor = GridBagConstraints.LINE_END;
        formAgregar.add(new JLabel("Prioridad:"), c);
        c.gridx = 1; c.anchor = GridBagConstraints.LINE_START;
        prioridad = new JComboBox();
        prioridad.addItem("1 - Alta");
        prioridad.addItem("2 - Media");
        prioridad.addItem("3 - Baja");
        formAgregar.add(prioridad, c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        agregar = new JButton("Agregar paciente");
        formAgregar.add(agregar, c);

        infoAgregar = new JTextArea(10, 30);
        infoAgregar.setEditable(false);
        JScrollPane spAgregar = new JScrollPane(infoAgregar);

        intAgregar.add(formAgregar, BorderLayout.NORTH);
        intAgregar.add(spAgregar, BorderLayout.CENTER);

        // Panel Atender
        intAtender = new JPanel(new BorderLayout(8,8));
        JPanel topAtender = new JPanel(new FlowLayout(FlowLayout.LEFT));
        atender = new JButton("Atender siguiente");
        topAtender.add(atender);
        intAtender.add(topAtender, BorderLayout.NORTH);
        infoAtender = new JTextArea(10, 30);
        infoAtender.setEditable(false);
        intAtender.add(new JScrollPane(infoAtender), BorderLayout.CENTER);

        // Panel Buscar
        intBuscar = new JPanel(new BorderLayout(8,8));
        JPanel formBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formBuscar.add(new JLabel("Nombre a buscar:"));
        nombreBuscar = new JTextField(14);
        formBuscar.add(nombreBuscar);
        buscar = new JButton("Buscar");
        formBuscar.add(buscar);
        infoBuscar = new JTextArea(8, 30);
        infoBuscar.setEditable(false);
        intBuscar.add(formBuscar, BorderLayout.NORTH);
        intBuscar.add(new JScrollPane(infoBuscar), BorderLayout.CENTER);


        tabbedPane1.addTab("Agregar", intAgregar);
        tabbedPane1.addTab("Atender", intAtender);
        tabbedPane1.addTab("Buscar", intBuscar);

        panelPrincipal.add(tabbedPane1, BorderLayout.CENTER);


        txtAgregar = new JLabel("Agregar paciente");
        txtBuscar = new JLabel("Buscar paciente");
    }

    private int obtenerPrioridadSeleccionada() {
        Object sel = prioridad.getSelectedItem();
        if (sel == null) return 3; // prioridad por defecto si no hay selección
        String s = sel.toString().trim(); // convierte a String

        // Buscar el primer dígito en la cadena
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                int v = Character.getNumericValue(s.charAt(i));
                if (v >= 1 && v <= 3) return v; // solo 1, 2 o 3
            }
        }

        return 3; // si no encuentra ningún número válido, prioridad por defecto
    }

    private void actualizarInfoAgregar() {
        infoAgregar.setText("Cola actual de pacientes:\n" + cola.listarCola());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HospitalSanMungo h = new HospitalSanMungo();
            h.setVisible(true);
        });
    }
}
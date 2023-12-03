package P1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class VentanaCreacionCuenta extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton showPasswordButton;
    private JTextField emailField;
    private JButton crearCuentaButton;
    private JButton sugerirContraseñaButton;
    
    private ManejoUsuarios manejoUsuarios;

    public VentanaCreacionCuenta(ManejoUsuarios manejoUsuarios) {
        setTitle("Crear Nueva Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Nuevo Usuario:");
        panel.add(usernameLabel);

        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Nueva Contraseña:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        panel.add(passwordField);

        showPasswordButton = new JButton("Mostrar Contraseña");
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMostrarContraseña();
            }
        });
        panel.add(showPasswordButton);

        JLabel emailLabel = new JLabel("Correo Electrónico:");
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);

        crearCuentaButton = new JButton("Crear Cuenta");
        crearCuentaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarNuevoUsuario();
            }
        });
        panel.add(crearCuentaButton);
        
        sugerirContraseñaButton = new JButton("Sugerencia de contraseña");
        sugerirContraseñaButton = new JButton("Sugerir Contraseña");
        sugerirContraseñaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sugerirContraseña();
            }
        });
        panel.add(sugerirContraseñaButton);


        add(panel);
        setVisible(true);
        this.manejoUsuarios = manejoUsuarios;
    }

    private void registrarNuevoUsuario() {
        String nuevoUsuario = usernameField.getText();
        String nuevaContraseña = new String(passwordField.getPassword());
        String nuevoCorreo = emailField.getText();

        if (!nuevoUsuario.isEmpty() && !nuevaContraseña.isEmpty() && !nuevoCorreo.isEmpty()) {
            if (manejoUsuarios.agregarUsuario(nuevoUsuario, nuevaContraseña, nuevoCorreo)) {
                JOptionPane.showMessageDialog(null, "Usuario creado exitosamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "El usuario ya existe. Por favor, elige otro nombre de usuario.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
        }
    }
    
    private void toggleMostrarContraseña() {
        if (passwordField.getEchoChar() == 0) {
            passwordField.setEchoChar('*'); // Si está visible, ocultar la contraseña
            showPasswordButton.setText("Mostrar Contraseña");
        } else {
            passwordField.setEchoChar((char) 0); // Si está oculta, mostrar la contraseña
            showPasswordButton.setText("Ocultar Contraseña");
        }
    }
    
    private void sugerirContraseña() {
        String contraseñaAleatoria = generarContrAleatoria(10); 
        passwordField.setText(contraseñaAleatoria);
    }

    private String generarContrAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%^&+=!";
        Random random = new Random();
        StringBuilder contraseña = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            int index = random.nextInt(caracteres.length());
            contraseña.append(caracteres.charAt(index));
        }
        return contraseña.toString();
    }

}



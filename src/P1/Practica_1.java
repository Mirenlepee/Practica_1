package P1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Practica_1 extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton showPasswordButton;
    private JButton loginButton; 
    private JButton forgotPasswordButton;
    private JButton createAccountButton;
    private ManejoUsuarios manejoUsuarios;

    public Practica_1() {
    	manejoUsuarios = new ManejoUsuarios();
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        
        JPanel pnlTexto = new JPanel();
        pnlTexto.setLayout(new GridLayout(3, 2));
        pnlTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        JLabel usernameLabel = new JLabel("Usuario:");
        pnlTexto.add(usernameLabel);

        usernameField = new JTextField();
        pnlTexto.add(usernameField);

        JPanel passwordPanel = new JPanel(new GridLayout(1, 2));
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordPanel.add(passwordLabel);
        pnlTexto.add(passwordPanel);

        showPasswordButton = new JButton("Mostrar");
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                toggleMostrarContraseña();
            }
        });
        passwordPanel.add(showPasswordButton);

        passwordField = new JPasswordField();
        pnlTexto.add(passwordField);
       
        JPanel pnlButtons = new JPanel();
        pnlButtons.setLayout(new FlowLayout());
        
        loginButton = new JButton("Iniciar Sesión"); 

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                if (manejoUsuarios.verificarUsuario(username, password)) {
                    if (isValidLogin(username, password)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso");
                        VentanaPrincipal ventPrincipal = new VentanaPrincipal();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "La contraseña no cumple con los requisitos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Credenciales incorrectas.");
                }
            }
        });
        pnlButtons.add(loginButton);

        forgotPasswordButton = new JButton("Olvidé mi contraseña");
        forgotPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                sendForgotPasswordEmail(username);
                JOptionPane.showMessageDialog(null, "Se ha enviado un correo con instrucciones");
            }
        });
        pnlButtons.add(forgotPasswordButton);

        manejoUsuarios = new ManejoUsuarios();

        createAccountButton = new JButton("Crear Cuenta");
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCreacionCuenta();
            }
        });
        pnlButtons.add(createAccountButton);
        
        add(pnlTexto, BorderLayout.NORTH);
        add(pnlButtons, BorderLayout.CENTER);
        setVisible(true);
    }
    
    private void abrirVentanaCreacionCuenta() {
    	 VentanaCreacionCuenta ventanaCreacionCuenta = new VentanaCreacionCuenta(manejoUsuarios);        
    }

    private boolean isValidLogin(String username, String password) {
    	Pattern pattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private void toggleMostrarContraseña() {
        if (passwordField.getEchoChar() == 0) {
            passwordField.setEchoChar('*');
            showPasswordButton.setText("Mostrar");
        } else {
            passwordField.setEchoChar((char) 0);
            showPasswordButton.setText("Ocultar");
        }
    }
    
    private void sendForgotPasswordEmail(String username) {    	
    	String usernameStr = usernameField.getText();
    	
    	ManejoUsuarios manejoUsuarios = new ManejoUsuarios();
        String correo = manejoUsuarios.obtenerUsuario(usernameStr);

        if (correo != null) {
            String to = correo;
            String from = "miren.lepee@opendeusto.es";
            final String usernameEmail = "tucorreo@gmail.com";
            final String passwordEmail = "tupassword";
            
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", "true");
            propiedades.put("mail.smtp.host", "smtp.gmail.com");
            propiedades.put("mail.smtp.port", "587");

            Session session = Session.getInstance(propiedades, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(usernameEmail, passwordEmail);
                }
            });
            
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Recuperación de Contraseña");
                message.setText("Estimado " + username + ", \n\nTu contraseña es: [aquí va la contraseña]\n\n¡Gracias!");

                Transport.send(message);
                JOptionPane.showMessageDialog(null, "Se ha enviado un correo con instrucciones");
            } catch (MessagingException mex) {
                mex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al enviar el correo");
            }

        } else {
            JOptionPane.showMessageDialog(null, "El usuario no existe");
        }
    }
   
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Practica_1();
            }
        });
    }
}

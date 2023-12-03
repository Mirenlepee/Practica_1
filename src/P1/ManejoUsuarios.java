package P1;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class ManejoUsuarios {
    private HashMap<String, String> usuarios; // Almacena nombre de usuario y contraseña
    private HashMap<String, String> correos; // Almacena nombre de usuario y correo

    public ManejoUsuarios() {
        usuarios = new HashMap<>();
        correos = new HashMap<>();
    }
    
    public boolean agregarUsuario(String nombreUsuario, String contraseña, String correo) {
        if (!usuarios.containsKey(nombreUsuario)) {
            usuarios.put(nombreUsuario, contraseña);
            correos.put(nombreUsuario, correo);
            return true; // Usuario agregado con éxito
        }
        return false; // El usuario ya existe
    }
    
    public boolean verificarUsuario(String nombreUsuario, String contraseña) {
        return usuarios.containsKey(nombreUsuario) && usuarios.get(nombreUsuario).equals(contraseña);
    }

    // Obtener el correo asociado a un usuario (solo para este ejemplo)
    public String obtenerUsuario(String nombreUsuario) {
        return correos.get(nombreUsuario);
    }
}


package P1;

public class Usuario {
    private String correoElectronico;
    private String contraseña;
    private String nombre;

    public Usuario(String correoElectronico, String contraseña, String nombre) {
        this.correoElectronico = correoElectronico;
        this.contraseña = contraseña;
        this.nombre = nombre;
    }

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [correoElectronico=" + correoElectronico + ", contraseña=" + contraseña + ", nombre=" + nombre + "]";
	}
    
}


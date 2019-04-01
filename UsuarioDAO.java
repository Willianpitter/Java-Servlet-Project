package usuarios;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsuarioDAO {
	public void setConnection(Object Connection) throws SQLException;
	public int newUser(String nome, String email, String senha,int tipo) throws SQLException;
	public int deleteUser(int cod_admin) throws SQLException;
	public ArrayList<Usuario> listaUsuarios() throws SQLException;
	public Usuario mostraUsuario(int cod_admin) throws SQLException;
	public int updateUsuario(Usuario usuario) throws SQLException;
}

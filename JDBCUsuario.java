package usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Entidade;

public class JDBCUsuario implements UsuarioDAO{
	private transient Connection connection = null; 

	@Override
	public void setConnection(Object Connection) throws SQLException {
		this.connection = (Connection)Connection;
		
	}

	@Override
	public int newUser(String nome, String email, String senha, int tipo) throws SQLException {
		String sql = "insert into usuario (cod_administrador,nome,email,senha,tipo) values (admin_seq.NEXTVAL,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, nome);
		pst.setString(2, email);
		pst.setString(3, senha);
		pst.setInt(4, tipo);	
		int res = pst.executeUpdate();
		return res;
	}

	public int deleteUser(int cod_admin) throws SQLException{	
		String sql="DELETE FROM usuario WHERE cod_admin=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, cod_admin);
		int res = pst.executeUpdate();
		pst.close();
		return res;
	}
	
	
	public ArrayList<Usuario> listaUsuarios() throws SQLException{
		String sql = "select * from usuario";
		Statement st = connection.createStatement();
		ResultSet rs= st.executeQuery(sql);
		ArrayList <Usuario> usuarios= new ArrayList<Usuario>();
		
		while (rs.next()){
			Usuario u = new Usuario(Integer.parseInt(rs.getString("cod_admin")), rs.getString("nome"), rs.getString("email"), rs.getString("senha"), Integer.parseInt(rs.getString("tipo")));
			usuarios.add(u);
		}
		return usuarios;
		
	}

	public Usuario mostraUsuario(int cod_admin) throws SQLException{
		String sql = "select * from usuario where cod_admin=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, cod_admin);
		ResultSet rs = pst.executeQuery();
		Usuario u = null;
		if (rs.next()){
			u = new Usuario (Integer.parseInt(rs.getString("cod_admin")), rs.getString("nome"), rs.getString("email"), rs.getString("senha"), Integer.parseInt(rs.getString("tipo")));
		}
		
		return u;
		
	}
	
	public int updateUsuario(Usuario usuario) throws SQLException{
		String sql="UPDATE usuario SET cod_admin=?,nome=?, email=?, senha = ?, tipo = ? WHERE cod_admin=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, usuario.cod_admin);
		pst.setString(2, usuario.nome);
		pst.setString(3, usuario.email);
		pst.setString(4, usuario.senha);
		pst.setInt(5, usuario.tipo);
		pst.setInt(6, usuario.cod_admin);
		int res = pst.executeUpdate();
		pst.close();
		return res;
	}
	
}

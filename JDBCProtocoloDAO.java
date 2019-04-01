package protocolos;


import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Entidade;
import usuarios.Usuario;

public class JDBCProtocoloDAO implements ProtocoloDAO{

	private transient Connection connection = null; 

	@Override
	public void setConnection(Object Connection) throws SQLException {
		this.connection = (Connection)Connection;
		
	}

	@Override
	public int newProtocolo(Protocolo protocolo, int[] pEntidade) throws SQLException {
		// QUANDO FOR ACESSAR A TABELA AUXILIAR DO ATRIBUTO MULTIVALORADO cod_entidade, ELE SERÁ UM A MAIS QUE O 
		// DA TABELA PROTOCOLO
		String sqlCod = "select cod_multentidade.nextval from dual";
		Statement st = connection.createStatement();
		ResultSet rs= st.executeQuery(sqlCod);
		int cod = 0;
		while (rs.next()){
			cod = Integer.parseInt(rs.getString("NEXTVAL"));
		}
		String sql = "insert into protocolo (id_protocolo, objetivo, ambito, cod_entidade, dia, tempo, condicao, caminho)"
				+ " values (proto.NEXTVAL,?,?,multentidade.NEXTVAL,?,?,?,?)";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setString(1, protocolo.objetivo);
		pst.setString(2, protocolo.ambito);
		pst.setDate(3, protocolo.data);	
		pst.setInt(4,protocolo.tempo);
		pst.setString(5,protocolo.condicao);
		pst.setString(6,protocolo.caminho);
		int res = pst.executeUpdate();
		
		for(int i = 0; i < pEntidade.length; i++){
			String sql2 = "insert into idDoProtocolo values (?,?)";
			PreparedStatement pst2 = connection.prepareStatement(sql2);
			pst2.setInt(1,cod);
			pst2.setInt(2, pEntidade[i]);
			int f = pst2.executeUpdate();
			pst2.close();
	
		}
		
		return res;
		
	}

	@Override
	public int deleteProtocolo(Protocolo protocolo) throws SQLException {
		String sql="DELETE FROM protocolo WHERE id_protocolo=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, protocolo.id_protocolo);
		int res = pst.executeUpdate();
		pst.close();
		return res;
	}

	@Override
	public ArrayList<Protocolo> listaProtocolos() throws SQLException {
		String sql = "select * from protocolo";
		Statement st = connection.createStatement();
		ResultSet rs= st.executeQuery(sql);
		ArrayList <Protocolo> protocolos= new ArrayList<Protocolo>();
		
		while (rs.next()){

			Protocolo p = new Protocolo(Integer.parseInt(rs.getString("id_protocolo")), rs.getString("objetivo"), rs.getString("ambito"), Integer.parseInt(rs.getString("id_protocolo")), rs.getDate("dia"),Integer.parseInt(rs.getString("tempo")),rs.getString("condicao"),rs.getString("caminho"));
			protocolos.add(p);
		}
		return protocolos;
	}

	@Override
	public Protocolo mostraProtocolo(int protocolo) throws SQLException {
		String sql = "select * from protocolo where id_protocolo=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, protocolo);
		ResultSet rs = pst.executeQuery();
		Protocolo p = null;
		if (rs.next()){
			 p = new Protocolo(Integer.parseInt(rs.getString("id_protocolo")), rs.getString("objetivo"), rs.getString("ambito"), Integer.parseInt(rs.getString("id_protocolo")), rs.getDate("dia"),Integer.parseInt(rs.getString("tempo")),rs.getString("condicao"),rs.getString("caminho"));
		}
		
		return p;
	}

	@Override
	public int updateProtocolo(Protocolo protocolo) throws SQLException {
		String sql="UPDATE protocolo SET id_protocolo=?,objetivo=?, ambito=?, cod_entidade = ?, dia = ?, tempo = ?,condicao = ?,caminho = ? WHERE id_protocolo=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, protocolo.id_protocolo);	
		pst.setString(2, protocolo.objetivo);
		pst.setString(3, protocolo.ambito);
		pst.setInt(4, protocolo.entidade);
		pst.setDate(5, protocolo.data);	
		pst.setInt(6,protocolo.tempo);
		pst.setString(7,protocolo.condicao);
		pst.setString(8,protocolo.caminho);
		pst.setInt(9, protocolo.id_protocolo);	
		int res = pst.executeUpdate();
		pst.close();
		return res;
	}

	@Override
	public int[] listaEntidadesDeUmProtocolo(Protocolo protocolo) throws SQLException {
		int[] entidades = new int[200]; 
		for(int i =0; i < entidades.length; i++){
			entidades[i] = -1;
		}
		int i = 0;
		String sql = "select * from idDoProtocolo where id_na_outra_tabela_protocolo=?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.setInt(1, protocolo.entidade);
		ResultSet rs = pst.executeQuery();
	
		while (rs.next()){
			entidades[i] = Integer.parseInt(rs.getString("cod_protocolo"));
			i++;
		}
		
		return entidades;
	}
}

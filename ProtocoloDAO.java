package protocolos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Entidade;
import usuarios.Usuario;

public interface ProtocoloDAO {

	public void setConnection(Object Connection) throws SQLException;
	public int newProtocolo(Protocolo protocolo, int[] entidade) throws SQLException;
	public int deleteProtocolo(Protocolo protocolo) throws SQLException;
	public ArrayList<Protocolo> listaProtocolos() throws SQLException;
	public Protocolo mostraProtocolo(int protocolo) throws SQLException;
	public int updateProtocolo(Protocolo protocolo) throws SQLException;
	public int[] listaEntidadesDeUmProtocolo(Protocolo protocolo) throws SQLException;

}

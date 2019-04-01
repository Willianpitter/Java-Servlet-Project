package usuarios;

public class Usuario {
	public int cod_admin;


	public Usuario(int cod_admin, String nome, String email, String senha, int tipo) {
		super();
		this.cod_admin = cod_admin;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "Usuario [cod_admin=" + cod_admin + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", tipo=" + tipo + "]";
	}
	public int getCod_admin() {
		return cod_admin;
	}
	public void setCod_admin(int cod_admin) {
		this.cod_admin = cod_admin;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String nome;
	public String email;
	public String senha;
	public int tipo;
}

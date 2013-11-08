package blog;
/**
 * 
 * @author Karina Silva de Medeiros
 *
 */
public class Perfil {

	private String nome;
	private int idade;
	private Sexo sexo;
	private String email;
	private String preferencias;
	
	public enum Sexo {
		
		/**
		 * Define o sexo feminino;
		 */
		FEMININO, 
		/**
		 * Define o sexo masculino;
		 */
		MASCULINO, 
		/**
		 * Nao ha definicao de sexo;
		 */
		INDEFINIDO;
		
	}

	/**
	 * Construtor
	 * @param nome
	 * @param sexo
	 * @param idade
	 * @param email
	 * @param preferencias
	 * @throws Exception
	 */
	public Perfil(String nome, int sexo, int idade, String email, String preferencias) throws Exception {
		if ((nome == null) || (nome.equals(" ")) || (nome.equals(""))){
			throw new Exception("Nome Invalido.");
		}
		this.nome = nome;
		this.sexo = Sexo.INDEFINIDO;
		if (idade <= 0) {
			throw new Exception("Idade Invalida.");
		}
		this.idade = idade;
		if ((email == null) || (email.equals(" ")) || (email.equals(""))){
			throw new Exception("Email Invalido.");
		}
		this.email = email;
		if ((preferencias == null) || (preferencias.equals(" ")) || (preferencias.equals(""))){
			throw new Exception("Preferencias Invalidas.");
		}
		this.preferencias = preferencias;
		
	}
	
	/**
	 * Retorna o nome
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Retorna o sexo
	 * @return sexo
	 */
	public Sexo getSexo() {
		return sexo;
	}

	/**
	 * Retorna a idade
	 * @return idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * Retorna os Interesses
	 * @return preferencias
	 */
	public String getInteresses() {
		return preferencias;
	}

	/**
	 * Retorna o Email
	 * @return email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Seta o nome
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Seta o sexo	
	 * @param sexo
	 */
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	/**
	 * Seta idade
	 * @param idade
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	/**
	 * Seta email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Seta as preferencias
	 * @param preferencias
	 */
	public void setPreferencias(String preferencias) {
		this.preferencias = preferencias;
	}
	
	/**
	 * Retorna uma String com o perfil
	 */
	public String toString(){
		return getNome() + ": " + getIdade() + "anos - " 
				+ getSexo() + " - " + "email: " + getEmail() 
				+ " - " + "Interesses: " + getInteresses();
	}

}

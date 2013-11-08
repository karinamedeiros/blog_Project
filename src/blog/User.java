package blog;

import java.util.ArrayList;

/**
 * @author Karina Silva de Medeiros
 * @version 1.01 25 de Junho de 2009
 */
public class Usuario {

	//Atributos
	private String nome;
	private String senha;
	private Perfil perfil;
	private boolean logado = false;
	private String email;
	private ArrayList<Blog> blogs;

	/**
	 * Construtor 
	 * @param nome
	 * @param senha
	 * @throws Exception nome e senha vazios ou nulos
	 */
	public Usuario(String nome, String senha, String email) throws Exception {
		if (nome.equals("")){
			throw new Exception("Nome não pode ser vazio");
		}
		if (nome.equals(null)){
			throw new Exception("Nome não pode ser nulo");
		}
		if (senha.equals("")){
			throw new Exception("Senha nao pode ser vazia");
		}
		if (senha.equals(null)){
			throw new Exception("Senha nao pode ser nula");
		}
		if (email.equals("")){
			throw new Exception("Email nao pode ser nula");
		}
		if (email.equals(null)){
			throw new Exception("Email nao pode ser nula");
		}
		
		this.nome = nome;
		this.senha = senha;
		this.email = email;
	}

	

	/**
	 * Seta o login do usuario
	 * @param nome
	 * @param senha
	 */
	public void setLogin(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}		
	
	public boolean addBlog(Blog blog) {
		if(!blogs.contains(blog)){
			blog.setAutor(this.getLogin());
			this.blogs.add(blog);
			return true;
		} return false;
	}
	
	public ArrayList<Blog> getBlogs(){
		return this.blogs;
	}
	
	/**
	 * Loga o usuario
	 * @return 
	 */
	public String getLogin(){
		return this.nome + " , " + this.senha;
	}
	
	/**
	 * Ver o perfil do usuario apos logado
	 * @return perfil
	 */
	public Perfil getPerfil() {
		return perfil;
	}

	public String getSenha() {
		return this.senha;
	}

	public void logSwitch() {
		this.logado = false;
		
	}
	
	public boolean isLoged() {
		return this.logado ;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}	
}
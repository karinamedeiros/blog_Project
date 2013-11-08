package blog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Karina Silva de Medeiros
 */
public class Blog {
	
	private String titulo;
	private String autor;
	private ArrayList<Postagem> posts;
	private ArrayList<String> postTitulos;
	
	public Blog(String titulo) throws Exception{
		if(titulo.isEmpty()){
			throw new Exception("Titulo do blog nao pode ser vazio");
		}
		this.titulo = titulo;
		this.posts = new ArrayList<Postagem>();
		this.postTitulos = new ArrayList<String>();
	}
	
	public boolean deletarPost(String titulo) throws Exception{
		if(titulo.isEmpty()){
			throw new Exception("Post nao pode ser nulo");
		}
		if(this.postTitulos.contains(titulo)){
			this.posts.remove(titulo);
			this.postTitulos.remove(titulo);
			return true;
		} return false;
	}
	
	public void setTitulo(String titulo) throws Exception{
		if(titulo.isEmpty()){
			throw new Exception("Titulo nao pode ser vazio");
		}
		this.titulo = titulo;
	}
	
	public void setAutor(String user){
		this.autor = user;
	}
	
	public String getAutor(){
		return this.autor;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	public Postagem getPost(String titulo){
		Iterator<Postagem> iteradorPost = this.posts.iterator();
		while (iteradorPost.hasNext()) {
			Postagem p = iteradorPost.next();
			if(p.getTitulo().equals(titulo))
				return p;
		}
		return null;
	}
	public ArrayList<Postagem> getPostsList(){
		return this.posts;
	}
	public ArrayList<String> getPostsTitles(){
		return this.postTitulos;
	}
	
	public void addPost(Postagem post){
		this.posts.add(post);
		this.postTitulos.add(post.getTitulo());
	}
	/**
	 * Adiciona um novo post
	 * @param nome
	 * @param post
	 * @return Boolean true se foi adicionado, false se não
	 * @throws Exception nome e post vazios
	 */
	public boolean add(String titulo, String post) throws Exception {
		if(post.equals(null)){
			throw new Exception ("Post nao pode ser vazio");
		}
		if(titulo.equals(null)){
			throw new Exception ("Nome nao pode ser vazio");
		}
		this.posts.add(new Postagem(titulo,post));
		return true;
	}

	/**
	 * Ve todas as postagens do blog
	 * @return
	 */
	public Object verPostagens() {
		Iterator<Postagem> iteradorPost = this.posts.iterator();
		while (iteradorPost.hasNext()) {
			Postagem p = iteradorPost.next();
			System.out.println(p.getTitulo());
			System.out.println(p.getConteudo());
		}

		return null;
	}
	

	/**
	 * Remove um post
	 * @param post
	 * @throws Exception Se caso o post nao exista
	 */
	public void remove(String titulo) throws Exception{
		if(!(this.postTitulos.contains(titulo))){
			throw new Exception("Post não está nas postagens, portanto nao pode ser removido");
		}
		this.postTitulos.remove(titulo);
		Iterator<Postagem> it = this.posts.iterator();
		while (it.hasNext()){
			Postagem p = it.next();
			if (titulo.equals(p.getTitulo()))
				this.posts.remove(p);
		}
	}
	
	public void remove(Postagem p){
		this.posts.remove(p);
	}
}
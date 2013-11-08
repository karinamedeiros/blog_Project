package blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Karina Silva de Medeiros
 */

public class Sistema implements Web {

	private IODataBase db;
	private ArrayList<Usuario> users;
	private ArrayList<Blog> blogs;
	private Map<String, Usuario> logs;
	private Map<String, Usuario> emails;
	private Map<String, Usuario> blogsMap;
	private static Sistema instance;
	
	public static Sistema getInstance(){
		if(instance == null){
			instance = new Sistema();
		}
		return instance;
	}

	public Sistema() {
		this.users = new ArrayList<Usuario>();
		this.blogs = new ArrayList<Blog>();
		this.logs = new HashMap<String, Usuario>();
		this.emails = new HashMap<String, Usuario>();
		this.blogsMap = new HashMap<String, Usuario>();
	}
	
	public void loadDB() throws Exception {
		this.setBlogs();
		this.setUsers();
		this.logs = db.getLog();
		this.emails = db.getEmail();
		
	}

	public void setUsers() throws Exception {
		this.users = db.loadUsersList();
	}

	public void setBlogs() throws Exception {
		this.blogs = db.loadBlogsList();
	}

	public boolean criaUsuario(String login, String senha, String email)
			throws Exception {
		if (login.isEmpty() || senha.isEmpty() || email.isEmpty()) {
			throw new Exception("Parametro(s) invalido(s)");
		}
		if (this.logs.containsKey(login) || this.emails.containsKey(email)) {
			return false;
		}
		Usuario newUser = new Usuario(login, senha, email);
		this.users.add(newUser);
		this.logs.put(login, newUser);
		this.emails.put(email, newUser);
		this.salvaUsuario(newUser); 
		return true;
	}

	public boolean login(String login, String senha) throws Exception {
		if (login.isEmpty() || senha.isEmpty()) {
			throw new Exception("Parametro(s) invalido(s)");
		}
		if (!this.logs.containsKey(login)) {
			return false;
		}
		if (senha != this.logs.get(login).getSenha()) {
			return false;
		}
		this.logs.get(login).logSwitch();
		return true;
	}

	public boolean logoff(Usuario user) throws Exception {
		if (user == null) {
			throw new Exception("Usario nao pode ser null");
		}
		if (user.isLoged()) {
			user.logSwitch();
			return true;
		}
		return false;
	}

	public ArrayList<Blog> getBlogs() {
		return this.blogs;
	}

	public ArrayList<Usuario> getUsuarios() {
		return this.users;
	}

	public void fecharDB() throws Exception {
		db.fecharDB();
	}

	public boolean autenticar(String login) throws Exception {
		if (login == null) {
			throw new Exception("Parametro nao pode ser null");
		}
		return logs.containsKey(login);
	}

	public Perfil getPerfil(Usuario user) throws Exception {
		if (!this.autenticar(user.getLogin())) {
			throw new Exception("Usuario nao cadastrado");
		}
		return user.getPerfil();
	}

	public boolean salvaPerfil(Usuario user) throws Exception {
		if (this.autenticar(user.getLogin())) {
			db.salvarPerfil(user);
			return true;
		}
		return false;
	}
	
	public Blog getBlog(String titulo) throws Exception {
		Blog blog = null;
		for (int i = 0; i < blogs.size(); i++) {
			if (blogs.get(i).getTitulo() == titulo) {
				blog = blogs.get(i);
				break;
			}
		}
		if (blog == null) {
			throw new Exception("Blog nao encontrado");
		}
		return blog;
	}

	public boolean salvaBlog(Blog blog){
		if (blogs.contains(blog)) {
			try {
				db.salvarBlog(blog);
			} catch (Exception e) {
				System.out.println("salvo");
			}
			return true;
		}
		return false;
	}

	public Postagem novaPostagem(Blog blog, Postagem post) throws Exception {
		if (!blogs.contains(blog)) {
			throw new Exception("Blog inexistente");
		}
		blog.addPost(post);
		return post;
	}

	public boolean salvaNovoPost(Blog blog, Postagem novaPostagem)
			throws Exception {
		if (blogs.contains(blog) && novaPostagem != null) {

			return true;
		}
		return false;
	}

	public boolean salvaNovoComment(Blog blog, Postagem post,
			Comentario novoComment) {
		if (blogs.contains(blog) && blog.getPostsList().contains(post)) {
			try {
				post.addComentario(novoComment);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				db.salvarComment(blog.getTitulo(), post.getTitulo(), novoComment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public boolean deletaComment(Blog blog, Postagem post, Comentario comentario)
			throws Exception {
		if (blogs.contains(blog) && blog.getPostsList().contains(post)) {
			db.deletaComment(blog.getTitulo(), post.getTitulo(), comentario
					.getAutor());
			return true;
		}
		return false;
	}

	public boolean deletaPost(Blog blog, Postagem post) throws Exception {
		if (blogs.contains(blog)) {
			db.deletaPost(blog.getTitulo(), post.getTitulo());
			return true;
		}
		return false;
	}

	public boolean deletaBlog(Blog blog) {
		if (blogs.contains(blog)) {
			this.blogs.remove(blog);
			return true;
		}
		return false;
	}

	public boolean deletaUser(Usuario user) {
		if (users.contains(user)) {
			this.users.remove(user);
			try {
				db.deletaUsuario(user.getLogin());
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				this.blogs = db.loadBlogsList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public Usuario getUsuario(String login) throws Exception {
		Iterator<Usuario> iteraUsers = users.iterator();
		Usuario user = null;
		while (iteraUsers.hasNext()) {
			user = (Usuario) iteraUsers.next();
			if (user.getLogin() == login) {
				break;
			}
		}
		
		return user;
	}

	public boolean salvaUsuario(Usuario user){
		if (users.contains(user)) {
			try {
				db.salvarPerfil(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} return false;
	}

}

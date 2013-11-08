package blog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import blog.Perfil.Sexo;

/**
 * 
 * @author Karina Silva de Medeiros
 *
 */
public class IODataBase {

	BufferedReader in = null;
	BufferedWriter out = null;
	static final String ls = System.getProperty("line.separator");
	Sistema sis;

	public IODataBase(Sistema sis) {
		this.sis = sis;
		new File("Sistema").mkdir();
	}

	public Perfil getPerfil(Usuario user) throws Exception {
		Perfil perfil = null;
		ArrayList<Object> params;
		Object obj;
		try {
			in = new BufferedReader(new FileReader("Sistema/" + user.getLogin()
					+ "/Perfil.txt"));
			params = new ArrayList<Object>();
			while ((obj = in.readLine()) != null) {
				params.add(obj);
			}
			perfil.setNome((String) params.get(0));
			perfil.setIdade((Integer) params.get(1));
			int sexo = (Integer) params.get(2);
			if (sexo == 1) {
				perfil.setSexo(Sexo.FEMININO);
			} else {
				if (sexo == 2) {
					perfil.setSexo(Sexo.MASCULINO);
				} else {
					perfil.setSexo(Sexo.INDEFINIDO);
				}
			}
			perfil.setPreferencias((String) params.get(3));
			in.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		} 
		return perfil;
	}

	public boolean deletaUsuario(String login) throws Exception {
		ArrayList<String> users = new ArrayList<String>(), blogs = new ArrayList<String>();
		String user, blog;
		if (new File("Sistema/" + login).exists()) {
			in = new BufferedReader(new FileReader("Sistema/" + login + "/blogs.txt"));
			while ((blog = in.readLine())!= null) {
				blogs.add(blog);
			}
			in.close();
			
			for(int i = 0; i < blogs.size(); i++){
				if( new File("Sistema/" + blogs.get(i)).exists()){
					new File("Sistema/" + blogs.get(i)).delete();
				}
			}
			
			new File("Sistema/" + login).delete();
			in = new BufferedReader(new FileReader("Sistema/Usuarios.txt"));
			while ((user = in.readLine()) != null) {
				if (user != login) {
					users.add(user);
				}
			}
			in.close();
			out = new BufferedWriter(new FileWriter("Sistema/Usuarios.txt"));
			for (int i = 0; i < users.size(); i++) {
				out.write(users.get(i));
				out.write(ls);
			}
			out.close();
			return true;
		}
		return false;
	}

	public void salvarPerfil(Usuario user) throws Exception {
		File usuario = new File("Sistema/" + user.getLogin());
		Perfil perfil = user.getPerfil();

		if (usuario.exists()) {
			out = new BufferedWriter(new FileWriter("Sistema/" + user.getLogin()
					+ "/Perfil.txt"));
			out.write(perfil.getNome());
			out.write(ls);

			Sexo sexo = perfil.getSexo();
			if (sexo == Sexo.FEMININO) {
				out.write(1);
			} else {
				if (sexo == Sexo.MASCULINO) {
					out.write(2);
				} else {
					out.write(0);
				}
			}

			out.write(ls);
			out.write(perfil.getIdade());
			out.write(ls);
			out.write(perfil.getInteresses());
			out.close();

			out = new BufferedWriter(new FileWriter("Sistema/" + user.getLogin()
					+ "/Log.txt"));

			out.write(user.getLogin());
			out.write(ls);
			out.write(user.getSenha());
			out.write(ls);
			out.write(perfil.getEmail());
			out.close();
			salvarBlogsSis(user);
		} else {
			throw new Exception("userserNotFouserndInDB");
		}
	}
	
	public void salvarBlogsSis(Usuario user){
		try{
			out = new BufferedWriter(new FileWriter("Sistema/" + user.getLogin() +"/blogs.txt"));
			ArrayList<Blog> blogs = user.getBlogs();
			for(int i = 0; i < blogs.size(); i++){
				out.write(blogs.get(i).getTitulo());
				out.write(ls);
			}
		} catch (IOException e){
			System.out.println(e.getMessage());
		}
	}
	


	public ArrayList<Usuario> loadUsersList() throws Exception {

		ArrayList<Usuario> usersList = new ArrayList<Usuario>();
		ArrayList<String> usersNames = new ArrayList<String>();
		ArrayList<String> userParam;
		BufferedReader in;
		String userser;
		try {
			in = new BufferedReader(new FileReader("Sistema/Usuarios.txt"));
			while ((userser = in.readLine()) != null) {
				usersNames.add(userser);
			}
			for (int i = 0; i < usersNames.size(); i++) {
				in = new BufferedReader(new FileReader("Sistema/"
						+ usersNames.get(i) + "/Log.txt"));
				userParam = new ArrayList<String>();
				while ((userser = in.readLine()) != null) {
					userParam.add(userser);
				}
				usersList.add(new Usuario(userParam.get(0), userParam.get(1),
						userParam.get(2)));
				in.close();
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
		return usersList;
	}

	public void salvarBlog(Blog blog) throws Exception {
		if (sis.getBlogs().contains(blog)) {
			try {
				if (!new File("Sistema/" + blog.getTitulo()).exists()) {
					new File("Sistema/" + blog.getTitulo()).mkdirs();
				}
				out = new BufferedWriter(new FileWriter("Sistema/" + blog.getTitulo() + "/autor.txt"));
				out.write(blog.getAutor());
				for (int i = 0; i < blog.getPostsList().size(); i++) {
					this.salvarPost(blog.getTitulo(), blog.getPostsList()
							.get(i));
					
				}
			} catch (IOException e) {
				throw new Exception(e.getMessage());
			}
		}
	}

	public ArrayList<Blog> loadBlogsList() throws Exception {
		ArrayList<Blog> blogList = new ArrayList<Blog>();
		ArrayList<String> titulos = new ArrayList<String>();
		String titulo, autor;
		try {
			in = new BufferedReader(new FileReader("Sistema/Blogs.txt"));
			while ((titulo = in.readLine()) != null) {
				titulos.add(titulo);
			}
			in.close();
			for (int i = 0; i < titulos.size(); i++) {
				Blog blog = new Blog(titulos.get(i));
				in = new BufferedReader(new FileReader("Sistema/" + titulos.get(i) + "/autor.txt"));
				while((autor = in.readLine())!=null){
					blog.setAutor(autor);
					break;
				}				
				Iterator iteraPosts = this.loadPosts(titulos.get(i)).iterator();
				while (iteraPosts.hasNext()) {
					Postagem post = (Postagem) iteraPosts.next();
					blog.addPost(post);
				}
			}
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}
		return blogList;
	}

	public ArrayList<Postagem> loadPosts(String blog) throws Exception {
		ArrayList<Postagem> posts = new ArrayList<Postagem>();
		ArrayList<String> titulos = new ArrayList<String>();
		if (new File("Sistema/" + blog + "/").exists()) {
			in = new BufferedReader(new FileReader("Sistema/" + blog
					+ "/posts.txt"));
			String titulo;
			while ((titulo = in.readLine()) != null) {
				titulos.add(titulo);
			}
			in.close();

			for (int i = 0; i < titulos.size(); i++) {
				in = new BufferedReader(new FileReader("Sistema/" + blog + "/"
						+ titulos.get(i) + ".txt"));
				String partePost, todoPost = null;
				while ((partePost = in.readLine()) != null) {
					todoPost += partePost + ls;
				}
				Postagem novoPost = new Postagem(titulos.get(i), todoPost);
				Iterator iteraComents = loadComments(blog, novoPost.getTitulo())
						.iterator();
				while (iteraComents.hasNext()) {
					Comentario newComent = (Comentario) iteraComents.next();
					novoPost.addComentario(newComent);
				}
				in.close();
				posts.add(novoPost);
			}
		}
		return posts;
	}

	public ArrayList<Comentario> loadComments(String blog, String post)
			throws Exception {
		String coments;
		ArrayList<String> autores = new ArrayList<String>();
		ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
		if (new File("Sistema/" + blog + "/" + post).exists()) {
			in = new BufferedReader(new FileReader("Sistema/" + blog + "/"
					+ post + "/comments.txt"));
			while ((coments = in.readLine()) != null) {
				autores.add(coments);
			}
			in.close();
			for (int i = 0; i < autores.size(); i++) {
				String allComent = null;
				in = new BufferedReader(new FileReader("Sistema/" + blog + "/"
						+ post + "/" + autores.get(i) + ".txt"));
				while ((coments = in.readLine()) != null) {
					allComent += coments + ls;
				}
				comentarios.add(new Comentario(autores.get(i), allComent));
				in.close();
			}
		}
		return comentarios;
	}

	public Map<String, Usuario> getLog() throws Exception {
		Map<String, Usuario> logsMap = new HashMap<String, Usuario>();
		ArrayList<Usuario> users = this.loadUsersList();
		for (int i = 0; i < users.size(); i++) {
			logsMap.put(users.get(i).getLogin(), users.get(i));
		}
		return logsMap;
	}

	public Map<String, Usuario> getEmail() {
		Map<String, Usuario> emailMap = new HashMap<String, Usuario>();
		return emailMap;

	}

	public void fecharDB() throws Exception {
		ArrayList<Usuario> usuarios = this.sis.getUsuarios();
		ArrayList<Blog> blogs = this.sis.getBlogs();
		try {
			out = new BufferedWriter(new FileWriter("Sistema/Usuarios.txt"));
			for (int i = 0; i < usuarios.size(); i++) {
				out.write(usuarios.get(i).getLogin());
			}
			out.close();
			out = new BufferedWriter(new FileWriter("Sistema/Blogs.txt"));
			for (int i = 0; i < blogs.size(); i++) {
				out.write(blogs.get(i).getTitulo());
			}
			out.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}

	}

	public void salvarPost(String tituloBlog, Postagem post) throws Exception {
		if (new File("Sistema/" + tituloBlog).exists()) {
			out = new BufferedWriter(new FileWriter("Sietema/" + tituloBlog + "/"
					+ post.getTitulo() + ".txt"));
			out.close();
			in = new BufferedReader(new FileReader("Sistema/" + tituloBlog
					+ "/posts.txt"));
			out = new BufferedWriter(new FileWriter("Sistema/" + tituloBlog + "/posts.txt"));
			String line;
			while ((line = in.readLine()) != null) {
				out.write(line);
			}
			out.write(post.getTitulo());
			out.close();
			in.close();
		}
	}

	public void salvarComment(String blog, String post, Comentario comment)
			throws Exception {
		if (!new File("Sistema/" + blog + "/" + post + ".txt").exists()) {
			new File("Sistema/" + blog + "/" + post + ".txt").mkdirs();
		}
			out = new BufferedWriter(new FileWriter("Sistema/" + blog + "/" + post + "/"
					+ comment.getAutor()));
			out.write(comment.getComent());
			out.close();
			out = new BufferedWriter(new FileWriter("Sistema/" + blog + "/" + post
					+ "/comments.txt"));
			out.write(comment.getAutor());
			out.write(ls);
			out.close();
		
	}

	public void deletaPost(String tituloBlog, String post) throws Exception {
		if (new File("Sistema/" + tituloBlog + "/" + post + ".txt").exists()) {
			new File("Sistema/" + tituloBlog + "/" + post + ".txt").delete();
			if (new File("Sistema/" + tituloBlog + "/" + post + "/").exists()) {
				new File("Sistema/" + tituloBlog + "/" + post + "/").delete();
			}
		}
	}

	public void deletaComment(String blog, String post, String comment)
			throws Exception {
		ArrayList<String> comments = new ArrayList<String>();
		String comentario;
		if (new File("Sistema/" + blog + "/" + post + "/" + comment + ".txt")
				.exists()) {
			new File("Sistema/" + blog + "/" + post + "/" + comment + ".txt")
					.delete();
			in = new BufferedReader(new FileReader("Sistema/" + blog + "/"
					+ post + "/comments.txt"));
			while ((comentario = in.readLine()) != null) {
				if (comentario != comment) {
					comments.add(comentario);
				}
			}
			in.close();
			out = new BufferedWriter(new FileWriter("Sistema/" + blog + "/" + post
					+ "/comments.txt"));
			for (int i = 0; i < comments.size(); i++) {
				out.write(comments.get(i));
				out.write(ls);
			}
			out.close();
		}
	}
}
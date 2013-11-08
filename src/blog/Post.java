package blog;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Karina Silva de Medeiros
 */

public class Postagem {

	// Atributos
	private ArrayList<Comentario> comments;
	private String titulo;
	private String post;

	/**
	 * Construtor
	 * 
	 * @throws Exception
	 */
	public Postagem(String t, String p) throws Exception {
		if (t == null) {
			throw new Exception("titulo null");
		}

		if (p == null) {
			throw new Exception("post null");
		}

		this.titulo = t;
		this.post = p;
		this.comments = new ArrayList<Comentario>();
	}

	public String getTitulo() {
		return this.titulo;
	}

	public String getConteudo() {
		return this.post;
	}

	public boolean addComentario(Comentario c) throws Exception {
		if (c == null) {
			throw new Exception("Comentario null");
		}
		return this.comments.add(c);
	}

	public <Comentario> ArrayList getComentarios() {
		return this.comments;
	}

	public String verComentarios() {
		String s = "";

		if (this.comments != null) {
			Iterator<Comentario> it = this.comments.iterator();

			while (it.hasNext()) {
				s += it.toString();
			}

		}
		return s;

	}

}
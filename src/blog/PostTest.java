package blog;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Karina Silva de Medeiros   
 *
 */
public class PostagemTest {

	private Comentario comentarios;
	private String p;
	private String t;
	private Postagem postagem;

	@Before
	public void setUp() throws Exception {
		this.p = "oi";
		this.t = "ola";
		this.comentarios = new Comentario(t, p);
	}

	@After
	public void tearDown() throws Exception {
		comentarios = null;
	}

	@Test
	public void testAddNovoPost() throws Exception {
		postagem = new Postagem(t, p);
	}

	@Test(expected = Exception.class)
	public void testAddPostNull() throws Exception {
		postagem = new Postagem(null, null);
	}

	@Test(expected = Exception.class)
	public void testAddPostSemAutor() throws Exception {
		postagem = new Postagem(null, "Legal, mas tem que colocar seu nome!");

	}

	@Test(expected = Exception.class)
	public void testAddPostComAutorSemPost() throws Exception {
		postagem = new Postagem(
				"Moco, coloca seu nome...Teu post tem que ter um autor", null);

	}

}

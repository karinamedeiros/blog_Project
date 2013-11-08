package blog;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Karina Silva de Medeiros
 *
 */
public class ComentariosTest {
	
	private Comentario comentarios;

	@Before
	public void setUp() throws Exception {
		comentarios = new Comentario("comentario","autor");
	}

	@After
	public void tearDown() throws Exception {
		comentarios = null;
	}

	@Test
	public void testAdd() throws Exception {
		comentarios = new Comentario("Juliano","Excelente post!");
	}
	
	@Test(expected=Exception.class)
	public void testAddNull() throws Exception {
		comentarios = new Comentario(null,null);
	}

	@Test(expected=Exception.class)
	public void testAddComentarioSemAutor() throws Exception {
		comentarios = new Comentario(null,"Gostei!");

	}
	
	@Test(expected=Exception.class)
	public void testAddComentarioComAutorSemComentario() throws Exception {
		comentarios = new Comentario("Jose Noob",null);
		
	}
	

}
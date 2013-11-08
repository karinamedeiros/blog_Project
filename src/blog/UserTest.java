package blog;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Karina Silva de Medeiros
 * @version 1.01 25 de Junho de 2009
 *
 */
public class UsuarioTest {

	Usuario usuario1, usuario2, usuario3, usuario4;
	Perfil perfil;
	
	@Before
	public void setUp() throws Exception {

		usuario1 = new Usuario("Karina","123a45","karina");
		usuario2 = new Usuario("Raquel", "789b","raquel");
		usuario3 = new Usuario("David", "12345z","david");
		usuario4 = new Usuario("Daniel","12345x","daniel");

	}

	
	@Test(expected=Exception.class)
	public void testLoginNull() throws Exception {
		usuario1 = new Usuario(null, "123a45",null);
	}

	@Test(expected=Exception.class)
	public void testLoginVazio() throws Exception {
		usuario1 = new Usuario("", "123a45","");
	}

	@Test(expected=Exception.class)
	public void testSenhaNull() throws Exception {
		usuario1 = new Usuario("Karina", null,"karina");
	}

	@Test(expected=Exception.class)
	public void testSenhaVazia() throws Exception {
		usuario1 = new Usuario("Karina", "","karina");
	}


	public void testPerfilValido(){
		Assert.assertEquals("errado", usuario2.getPerfil(),perfil);
	}
}

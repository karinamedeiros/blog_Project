package blog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import blog.Perfil.Sexo;

/**
 * 
 * @author Karina Silva de Medeiros
 *
 */

public class PerfilTest {
	private Perfil perfil1;
	
	@Before
	public void setUp() throws Exception {
		perfil1 = new Perfil("Andhora", 1, 20,"andhora", "inform�tica, ficcao cientifica, animes, jogos");
	}
	
	@Test
	public void testConstrutor() {
		Assert.assertNotNull("Objeto nao deveria ser nulo.", perfil1);
		Assert.assertEquals("Construtor com problema.", "Andhora", perfil1.getNome());
		Assert.assertEquals("Construtor com problema.", 1, perfil1.getSexo());
		Assert.assertEquals("Construtor com problema.", 20, perfil1.getIdade());
		Assert.assertEquals("Construtor com problema.", "inform�tica, ficcao cientifica, animes, jogos", perfil1.getInteresses());
		
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionFuncao() throws Exception {
		Perfil perfil1 = new Perfil("Jose", 0, 25, "jose", "politica, esportes, economia");
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionNomeNull() throws Exception {
		Perfil perfil1 = new Perfil(null, 0, 25,null, "politica, esportes, economia");
	}
	
		
	@Test(expected=Exception.class)
	public void testConstrutorExceptionNomeVazio() throws Exception {
		Perfil perfil1 = new Perfil("", 0, 25,"", "politica, esportes, economia");
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionInteressesNull() throws Exception {
		Perfil perfil1 = new Perfil("Jose", 0, 25,"jose", null);
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionInteressesVazio() throws Exception {
		Perfil perfil1 = new Perfil("Jose", 0, 25,"jose", "");
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionIdadeNegativa() throws Exception {
		Perfil perfil1 = new Perfil("Jose", 0, -25, "jose","politica, esportes, economia");
	}
	
	@Test(expected=Exception.class)
	public void testConstrutorExceptionIdadeZero() throws Exception {
		Perfil perfil1 = new Perfil("Jose", 0, 0, "jose","politica, esportes, economia");
	}

	@Test
	public void testSetNome() {
		perfil1.setNome("");
		Assert.assertEquals("Nome errado 1.", "Andhora", perfil1.getNome());
		perfil1.setNome(null);
		Assert.assertEquals("Nome errado 2.", "Andhora", perfil1.getNome());
		perfil1.setNome("Joana");
		Assert.assertEquals("Nome errado 3.", "Joana", perfil1.getNome());
	}
	
	@Test
	public void testEquals() throws Exception {
		Perfil perfil2 = new Perfil("Andhora", 1, 20,"andhora", "inform�tica, ficcao cientifica, animes, jogos");
		Assert.assertTrue("Objetos deveriam ser iguais.", perfil1.equals(perfil2));
		perfil1.setSexo(Sexo.FEMININO);
		Assert.assertFalse("Objetos deveriam ser diferentes (sexo).", perfil1.equals(perfil2));
		perfil1.setSexo(Sexo.MASCULINO);
		Assert.assertTrue("Objetos deveriam ser iguais.", perfil1.equals(perfil2));
		perfil1.setNome("Joana");
		Assert.assertFalse("Objetos deveriam ser diferentes (nome).", perfil1.equals(perfil2));
		perfil1.setNome("Andhora");
		Assert.assertTrue("Objetos deveriam ser iguais.", perfil1.equals(perfil2));
		perfil1.setIdade(35);
		Assert.assertFalse("Objetos deveriam ser diferentes (idade).", perfil1.equals(perfil2));
		perfil1.setIdade(20);
		Assert.assertTrue("Objetos deveriam ser iguais.", perfil1.equals(perfil2));
		perfil1.setPreferencias("carros, musicas, viagens");
		Assert.assertFalse("Objetos deveriam ser diferentes (preferencias).", perfil1.equals(perfil2));
		perfil1.setPreferencias("inform�tica, ficcao cientifica, animes, jogoss");
		Assert.assertTrue("Objetos deveriam ser iguais.", perfil1.equals(perfil2));
		Assert.assertEquals("String errada.", "Andhora - feminino - 20 anos - inform�tica, ficcao cientifica, animes, jogos", perfil1.toString());
		
	}
}

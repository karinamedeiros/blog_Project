package blog;

/**
 * 
 * @author Karina Silva de Medeiros
 * @version 1.01 12 de julho de 2009
 *	Interface para ajudar na junção da lógica com a web. 
 *	Sistema implementa a interface.
 */
public interface Web {

	public boolean autenticar(String login) throws Exception;
	
	public boolean salvaUsuario(Usuario user);
	
	public Perfil getPerfil(Usuario user) throws Exception;
	
	public boolean salvaPerfil(Usuario user) throws Exception;
	
	public Blog getBlog(String titulo) throws Exception;
	
	public boolean salvaBlog(Blog blog);
	
	public Postagem novaPostagem(Blog blog, Postagem post) throws Exception;
	
	public boolean salvaNovoPost(Blog blog, Postagem novaPostagem) throws Exception;
	
	public boolean salvaNovoComment(Blog blog, Postagem post, Comentario novoComment);
	
	public boolean deletaComment(Blog blog, Postagem post, Comentario comentario) throws Exception;
	
	public boolean deletaPost(Blog blog, Postagem post) throws Exception;
	
	public boolean deletaBlog(Blog blog);
	
	public boolean deletaUser(Usuario user);
	
	public Usuario getUsuario(String login) throws Exception;
}

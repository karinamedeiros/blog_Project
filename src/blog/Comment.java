package blog;

/**
 * 
 * @author Karina Silva de Medeiros
 *
 */
public class Comentario {

	private String conteudo, autor;
	
	public Comentario(String c, String a) throws Exception {
		if (c == null){
			throw new Exception("conteudo null");
		}
		
		if (a == null){
			throw new Exception("autor null");
		}
		
		this.conteudo = c;
		this.autor = a;
	}

	public String getAutor(){
		return this.autor;
	}
	
	public String getConteudo(){
		return this.conteudo;
	}

	public String getComent() {
		return this.conteudo;
	}


}

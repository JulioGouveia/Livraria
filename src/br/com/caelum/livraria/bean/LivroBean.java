package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;

	public Livro getLivro() {
		return livro;
	}

	public List<Livro> getTodosLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public List<Autor> getcu() {

		return new DAO<Autor>(Autor.class).listaTodos();

	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public void gravaAutor() {

		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(this.getAutorId());

		this.livro.adicionaAutor(autor);

		System.out.println(autor.getId());

	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// throw new RuntimeException("Livro deve ter pelo menos um
			// Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
		}

		if (this.livro.getId() == null) {
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		} else {
			new DAO<Livro>(Livro.class).atualiza(this.livro);
		}
		this.livro = new Livro();

	}

	public void removerLivro(Livro livro) {
		System.out.println("Removendo Livro");

		new DAO<Livro>(Livro.class).remove(livro);

	}

	public void carregar(Livro livro) {

		this.livro = livro;

		System.out.println("carregando livro" + livro.getTitulo());
	}
	
	public void removeAutorDoLivro(Autor autor){
		
		System.out.println("removendo Autor");
		
		this.livro.removeAutor(autor);
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent uc, Object obj) throws ValidatorException {

		String value = obj.toString();
		if (!value.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Valor de ISBN invalido"));
		}

	}

	public RedirectView navega() {

		return new RedirectView("autor");
	}
	
	public void carregaPelaId(){
		Integer id = this.livro.getId();
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(id);
		if(livro.getId() == null)
			this.livro = new Livro();
	}

}

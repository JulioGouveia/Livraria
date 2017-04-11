package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	// metodo para gravar no banco
	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			// CREATE
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			// UPDATE
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		// intancia um novo obj para limpar o formulario
		this.autor = new Autor();

		return new RedirectView("livro");
	}

	// metodo para alterar um objeto no banco UPDATE
	public void alterarAutor(Autor autor) {

		this.autor = autor;

	}

	// DELETE deleta do banco
	public void removeAutor(Autor autor) {
		System.out.println("removendo autor " + autor.getNome());
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		} catch (Exception e) {
			
		}
		
	}

	public List<Autor> getTodosAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void carregaPelaId(){
		
		Integer id = autor.getId();
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(id);
		if(autor.getId() == null)
			this.autor = new Autor();		
	}
}

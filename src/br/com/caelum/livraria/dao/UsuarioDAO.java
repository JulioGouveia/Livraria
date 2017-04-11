package br.com.caelum.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.caelum.livraria.modelo.Usuario;

public class UsuarioDAO {

	public boolean existe(Usuario usuario) {

		EntityManager em = new JPAUtil().getEntityManager();

		TypedQuery<Usuario> Query = em.createQuery(
				"Select u from Usuario u where email = :pEmail and senha = :pSenha", Usuario.class);

		Query.setParameter("pEmail", usuario.getemail());
		Query.setParameter("pSenha", usuario.getSenha());

		try {
			
			 @SuppressWarnings("unused")
			Usuario result = Query.getSingleResult();

		} catch (NoResultException e) {
			return false;
		}

		em.close();
		
		return true;
	}

	
		
}

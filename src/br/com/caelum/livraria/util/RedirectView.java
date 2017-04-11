package br.com.caelum.livraria.util;

public class RedirectView {

	private String redirectView;

	public RedirectView(String redirectView) {
		this.redirectView = redirectView;
	}
	
	@Override
	public String toString() {
		return redirectView + "?faces-redirect=true";
	}
	
	
	
}

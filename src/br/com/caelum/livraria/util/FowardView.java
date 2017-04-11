package br.com.caelum.livraria.util;

public class FowardView {
	
	private String fowardView;
	
	
	public FowardView(String viewName) {
		this.fowardView = viewName;
	}
	
	@Override
	public String toString() {
		return fowardView;
	}

}

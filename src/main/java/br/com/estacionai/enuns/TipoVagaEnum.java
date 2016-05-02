package br.com.estacionai.enuns;

public enum TipoVagaEnum {
	
	CARRO_PEQUENO("Carro Pequeno"),
	CARRO_GRANDE ("Carro Grande"),
	MOTO("Moto");
	
	private String descricao;

	private TipoVagaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

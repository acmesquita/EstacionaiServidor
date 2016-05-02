package br.com.estacionai.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Estacionamento {

	@Id
	@GeneratedValue
	private Long id;
	@Embedded
	private PessoaJuridica pessoaJuridica;
	@Embedded
	private Geolocalizacao geolocalizacao;
	@OneToMany
	private List<Vaga> vagas;
	@OneToOne(cascade = CascadeType.ALL)
	private Responsavel responsavel;
	
	public Estacionamento() {
		// TODO Auto-generated constructor stub
	}

	public Estacionamento(PessoaJuridica pessoaJuridica,
			Geolocalizacao geolocalizacao, List<Vaga> vagas,
			Responsavel responsavel) {
		super();
		this.pessoaJuridica = pessoaJuridica;
		this.geolocalizacao = geolocalizacao;
		this.vagas = vagas;
		this.responsavel = responsavel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	public Geolocalizacao getGeolocalizacao() {
		return geolocalizacao;
	}

	public void setGeolocalizacao(Geolocalizacao geolocalizacao) {
		this.geolocalizacao = geolocalizacao;
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((geolocalizacao == null) ? 0 : geolocalizacao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((pessoaJuridica == null) ? 0 : pessoaJuridica.hashCode());
		result = prime * result
				+ ((responsavel == null) ? 0 : responsavel.hashCode());
		result = prime * result + ((vagas == null) ? 0 : vagas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estacionamento other = (Estacionamento) obj;
		if (geolocalizacao == null) {
			if (other.geolocalizacao != null)
				return false;
		} else if (!geolocalizacao.equals(other.geolocalizacao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoaJuridica == null) {
			if (other.pessoaJuridica != null)
				return false;
		} else if (!pessoaJuridica.equals(other.pessoaJuridica))
			return false;
		if (responsavel == null) {
			if (other.responsavel != null)
				return false;
		} else if (!responsavel.equals(other.responsavel))
			return false;
		if (vagas == null) {
			if (other.vagas != null)
				return false;
		} else if (!vagas.equals(other.vagas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estacionamento [id=" + id + ", pessoaJuridica="
				+ pessoaJuridica + ", geolocalizacao=" + geolocalizacao
				+ ", vagas=" + vagas + ", responsavel=" + responsavel + "]";
	}
	
	
}

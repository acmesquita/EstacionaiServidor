package br.com.estacionai.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Responsavel {

	@Id
	@GeneratedValue
	private Long id;
	@Embedded
	private PessoaFisica pessoaFisica;
	@Embedded
	private Usuario usuario;
	@OneToOne
	private Estacionamento estacionamento;
	private String email;
	private String telefone;
	
	public Responsavel() {
		// TODO Auto-generated constructor stub
	}

	public Responsavel(PessoaFisica pessoaFisica, Usuario usuario,
			Estacionamento estacionamento, String email, String telefone) {
		super();
		this.pessoaFisica = pessoaFisica;
		this.usuario = usuario;
		this.estacionamento = estacionamento;
		this.email = email;
		this.telefone = telefone;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estacionamento getEstacionamento() {
		return estacionamento;
	}

	public void setEstacionamento(Estacionamento estacionamento) {
		this.estacionamento = estacionamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((estacionamento == null) ? 0 : estacionamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((pessoaFisica == null) ? 0 : pessoaFisica.hashCode());
		result = prime * result
				+ ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Responsavel other = (Responsavel) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (estacionamento == null) {
			if (other.estacionamento != null)
				return false;
		} else if (!estacionamento.equals(other.estacionamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pessoaFisica == null) {
			if (other.pessoaFisica != null)
				return false;
		} else if (!pessoaFisica.equals(other.pessoaFisica))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Responsavel [id=" + id + ", pessoaFisica=" + pessoaFisica
				+ ", usuario=" + usuario + ", estacionamento=" + estacionamento
				+ ", email=" + email + ", telefone=" + telefone + "]";
	}

}

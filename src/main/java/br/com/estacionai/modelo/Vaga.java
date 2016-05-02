package br.com.estacionai.modelo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.estacionai.enuns.TipoVagaEnum;
import br.com.estacionai.utils.Utils;

@Entity
public class Vaga {

	@Id
	@GeneratedValue
	private Long id;
	private TipoVagaEnum tipoVaga;
	private BigDecimal valor;
	private String descricao;
	@OneToOne
	private Veiculo veiculo;
	private boolean ocupada;
	
	public Vaga() {
	}

	public Vaga(TipoVagaEnum tipoVaga, BigDecimal valor, String descricao,
			Veiculo veiculo, boolean ocupada) {
		super();
		this.tipoVaga = tipoVaga;
		this.valor = valor;
		this.descricao = descricao;
		this.veiculo = veiculo;
		this.ocupada = ocupada;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public TipoVagaEnum getTipoVaga() {
		return tipoVaga;
	}


	public void setTipoVaga(TipoVagaEnum tipoVaga) {
		this.tipoVaga = tipoVaga;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Veiculo getVeiculo() {
		return veiculo;
	}


	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}


	public boolean isOcupada() {
		return ocupada;
	}


	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (ocupada ? 1231 : 1237);
		result = prime * result
				+ ((tipoVaga == null) ? 0 : tipoVaga.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
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
		Vaga other = (Vaga) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ocupada != other.ocupada)
			return false;
		if (tipoVaga != other.tipoVaga)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (veiculo == null) {
			if (other.veiculo != null)
				return false;
		} else if (!veiculo.equals(other.veiculo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vaga [id=" + id + ", tipoVaga=" + tipoVaga + ", valor=" + valor
				+ ", descricao=" + descricao + ", veiculo=" + veiculo
				+ ", ocupada=" + ocupada + "]";
	}

	public void calcularValor() {
		Date entrada = this.veiculo.getEntrada();
		Date saida = this.veiculo.getSaida();
		
		double dif = Utils.diferencaEmHoras(saida, entrada);
		
		this.valor = this.valor.multiply(new BigDecimal(dif).setScale(2, RoundingMode.HALF_DOWN));		
	}
	public static void main(String[] args) {
		Veiculo veiculo = new Veiculo("AAA-9999", Utils.incrementaDias(new Date(), 1), new Date());
		Vaga vaga = new Vaga(TipoVagaEnum.CARRO_PEQUENO, new BigDecimal(10), "",veiculo,true);
		
		vaga.calcularValor();
		
		System.out.println(vaga);
	}

}

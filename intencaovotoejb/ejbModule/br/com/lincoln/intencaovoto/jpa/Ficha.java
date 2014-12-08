package br.com.lincoln.intencaovoto.jpa;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedQueries({
	@NamedQuery(name = "Ficha.obterTodos", query = "SELECT f FROM Ficha f"),
	@NamedQuery(name = "Ficha.obterQuantidade", query = "SELECT COUNT(f) FROM Ficha f") 
})
public class Ficha implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name = "SQ_ID_FICHA", sequenceName = "SQ_ID_FICHA")
	private int id;

	@Column(name = "DS_GRAU_ESCOLARIDADE")
	private String grauEscolaridade;

	@Column(name = "DS_NOME")
	private String nome;

	@Temporal(TemporalType.DATE)
	@Column(name = "DT_NASCIMENTO")
	private Date nascimento;

	@Column(name = "FL_EMPREGADO")
	private int empregado;

	@Column(name = "FL_SEXO")
	private String sexo;

	@Column(name = "NR_CANDIDATO")
	private int candidato;

	@Column(name = "VL_RENDA")
	private BigDecimal renda;

	public Ficha() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ficha) {
			Ficha ficha = (Ficha) obj;
			return this.id == ficha.id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public int getEmpregado() {
		return empregado;
	}

	public void setEmpregado(int empregado) {
		this.empregado = empregado;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getCandidato() {
		return candidato;
	}

	public void setCandidato(int candidato) {
		this.candidato = candidato;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

}
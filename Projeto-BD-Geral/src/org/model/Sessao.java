package org.model;

import java.sql.Timestamp;

public class Sessao {
	
	private int idSessao;
	private Timestamp tempoInicial;
	private Timestamp tempoFinal;
	private byte[] imagem;
	private Paciente paciente;
	
	public Sessao(int idSessao, Timestamp tempoInicial, Timestamp tempoFinal, 
			byte[] image, Paciente paciente) {
		this.setIdSessao(idSessao);
		this.setTempoInicial(tempoInicial);
		this.setTempoFinal(tempoFinal);
		this.setImagem(image);
		this.setPaciente(paciente);
	}

	public int getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}

	public Timestamp getTempoInicial() {
		return tempoInicial;
	}

	public void setTempoInicial(Timestamp tempoInicial) {
		this.tempoInicial = tempoInicial;
	}

	public Timestamp getTempoFinal() {
		return tempoFinal;
	}

	public void setTempoFinal(Timestamp tempoFinal) {
		this.tempoFinal = tempoFinal;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	

}

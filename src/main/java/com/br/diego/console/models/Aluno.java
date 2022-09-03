package com.br.diego.console.models;

import java.util.List;

//Atributos Nome do Aluno com getters and setters
public class Aluno {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Lista de atributos das notas do Aluno com getters and setters
    private List<Float> notas;

    public List<Float> getNotas() {
        return notas;
    }

    // Metodo para calcular a situacao

    public String situacao() {
        var media = this.media();
        if (media < 5)
            return "Reprovado";
        else if (media >= 5 && media < 7)
            return "Recuperacao";
        else
            return "Aprovado";
    }

    public void setNotas(List<Float> notas) {
        this.notas = notas;
    }

    // Metodo para calcular a media
    public Float media() {
        Float mediaCalculada = (float) 0.0;
        if (this.notas != null) {
            for (Float nota : this.notas) {
                mediaCalculada += nota;
            }

            return mediaCalculada / this.notas.size();
        }
        return mediaCalculada;
    }

}

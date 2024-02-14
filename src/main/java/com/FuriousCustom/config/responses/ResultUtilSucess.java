package com.FuriousCustom.config.responses;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResultUtilSucess {

    private String mensagem;
    private LocalDateTime data;
    private Object dados;

    public ResultUtilSucess(Object dados) {
        this.mensagem = "Requisição feita com suceso!";
        this.data = LocalDateTime.now();
        this.dados = dados;
    }

}

package br.unitins.topicos1.service;

import java.io.File;
import java.io.IOException;

public interface FileService {

    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException;

    public File obter(String nomeArquivo);

    public void excluir(String nomeArquivo);

}
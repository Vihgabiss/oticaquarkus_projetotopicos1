package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OculosFileService implements FileService {
    // /Users/janio/quarkus/images/usuario/
    private final String PATH_OCULOS = System.getProperty("user.home") +

            File.separator + "Documents"+
            File.separator + "Quarkus"+ File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10mb

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        verificarTamanhoImagem(arquivo);
        verificarTipoImagem(nomeArquivo);

        // criar diretório caso não exista
        Path diretorio = Paths.get(PATH_OCULOS);
        Files.createDirectories(diretorio);

        // criando o nome do arquivo randomico
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        String extensao = mimeType.substring(mimeType.lastIndexOf('/') + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        // definindo o caminho completo do arquivo
        Path filePath = diretorio.resolve(novoNomeArquivo);

        if (filePath.toFile().exists())
            throw new IOException("Nome de arquivo já existe.");

        // salvar arquivo
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        File file = new File(PATH_OCULOS + nomeArquivo);
        return file;
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE)
            throw new IOException("Arquivo maior que 10mb.");
    }

    private void verificarTipoImagem(String nomeArquivo) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (!SUPPORTED_MIME_TYPES.contains(mimeType))
            throw new IOException("Tipo de imagem não suportado.");
    }

    @Override
    public void excluir(String nomeArquivo) {
        File file = new File(PATH_OCULOS + nomeArquivo);
        if (file.exists() && file.isFile()) {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

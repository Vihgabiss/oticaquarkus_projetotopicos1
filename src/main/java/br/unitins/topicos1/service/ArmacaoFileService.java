package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoFileService implements FileService {

    private final String PATH_ARMACAO = System.getProperty("user.home")
            + File.separator + "Documents"
            + File.separator + "Quarkus"
            + File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10MB

    @Override
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        verificarTamanhoImagem(arquivo);
        verificarTipoImagem(nomeArquivo);

        Path diretorio = Paths.get(PATH_ARMACAO);
        Files.createDirectories(diretorio);

        Path filePath = diretorio.resolve(nomeArquivo);

        // Sobrescrever o arquivo existente, SEMPRE
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        return new File(PATH_ARMACAO + nomeArquivo);
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("Arquivo maior que 10MB.");
        }
    }

    private void verificarTipoImagem(String nomeArquivo) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (!SUPPORTED_MIME_TYPES.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportado.");
        }
    }

    @Override
    public void excluir(String nomeArquivo) {
        File file = new File(PATH_ARMACAO + nomeArquivo);
        if (file.exists() && file.isFile()) {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

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

import org.apache.commons.io.FilenameUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArmacaoFileService implements FileService {

    @ConfigProperty(name = "armacao.image.upload.dir")
    String uploadDir;
    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList("image/jpeg", "image/jpg", "image/png",
            "image/gif");
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10MB

    @Override
    public String salvar(String originalFileName, byte[] fileData) throws IOException {
        verificarTamanhoImagem(fileData);

        // Determine the file extension from the original filename
        String extension = FilenameUtils.getExtension(originalFileName);

        // Generate a unique filename to prevent overwriting
        String fileName = UUID.randomUUID() + "." + extension;

        // Ensure the upload directory exists
        Path diretorio = Paths.get(uploadDir);
        Files.createDirectories(diretorio);

        Path filePath = diretorio.resolve(fileName);

        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(fileData);
        }
        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        return new File(uploadDir + nomeArquivo);
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE) {
            throw new IOException("Arquivo maior que 10MB.");
        }
    }

    @Override
    public void excluir(String nomeArquivo) {
        File file = new File(uploadDir + nomeArquivo);
        if (file.exists() && file.isFile()) {
            try {
                Files.deleteIfExists(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

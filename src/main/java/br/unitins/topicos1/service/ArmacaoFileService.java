package br.unitins.topicos1.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import br.unitins.topicos1.model.Armacao;
import br.unitins.topicos1.repository.ArmacaoRepository;
import br.unitins.topicos1.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ArmacaoFileService implements FileService {

    private final String PATH_USER = System.getProperty("user.home")
            + File.separator + "Documents"
            + File.separator + "Quarkus"
            + File.separator;

    @Inject
    ArmacaoRepository armacaoRepository;

    @Override
    @Transactional
    public void salvar(Long id, String nomeImagem, byte[] imagem) {
        Armacao armacao = armacaoRepository.findById(id);

        try {
            // Store the current image name for deletion
            String nomeImagemAntiga = armacao.getNomeImagem();

            String novoNomeImagem = salvarImagem(imagem, nomeImagem);
            armacao.setNomeImagem(novoNomeImagem);

            // Delete the old image only if it exists and is not the same as the new one
            if (nomeImagemAntiga != null && !nomeImagemAntiga.equals(novoNomeImagem)) {
                excluirImagem(nomeImagemAntiga);
            }
        } catch (IOException e) {
            throw new ValidationException("imagem", e.toString());
        }
    }

    private void excluirImagem(String nomeArquivo) throws IOException {
        File file = new File(PATH_USER + nomeArquivo);
        if (file.exists() && !file.delete()) {
            throw new IOException("Erro ao excluir a imagem antiga: " + nomeArquivo);
        }
    }

    private String salvarImagem(byte[] imagem, String nomeImagem) throws IOException {

        // verificando o tipo da imagem
        String mimeType = Files.probeContentType(new File(nomeImagem).toPath());
        List<String> listMimeType = Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif");
        if (!listMimeType.contains(mimeType)) {
            throw new IOException("Tipo de imagem não suportada.");
        }

        // verificando o tamanho do arquivo - nao permitir maior que 10 megas
        if (imagem.length > (1024 * 1024 * 10))
            throw new IOException("Arquivo muito grande.");

        // criando as pastas quando não existir
        File diretorio = new File(PATH_USER);
        if (!diretorio.exists())
            diretorio.mkdirs();

        // gerando o nome do arquivo
        String nomeArquivo = UUID.randomUUID()
                + "." + mimeType.substring(mimeType.lastIndexOf("/") + 1);

        String path = PATH_USER + nomeArquivo;

        // salvando o arquivo
        File file = new File(path);
        // alunos (melhorar :)
        if (file.exists())
            throw new IOException("O nome gerado da imagem está repedido.");

        // criando um arquivo no S.O.
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagem);
        // garantindo o envio do ultimo lote de bytes
        fos.flush();
        fos.close();

        return nomeArquivo;

    }

    @Override
    public File download(String nomeArquivo) {
        System.out.println(PATH_USER + nomeArquivo);
        File file = new File(PATH_USER + nomeArquivo);
        return file;
    }
}
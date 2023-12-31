package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.EnderecoResponseDTO;
import jakarta.validation.Valid;

public interface EnderecoService {
  
    public EnderecoResponseDTO insert(Long idUsuario, @Valid EnderecoDTO dto);

    public EnderecoResponseDTO update(Long idUsuario, Long idEndereco, @Valid EnderecoDTO dto);

    public void delete(Long idUsuario, Long idEndereco);

    public EnderecoResponseDTO findById(Long id);

    public List<EnderecoResponseDTO> findByCep(String cep);

    public List<EnderecoResponseDTO> findByAll();

}

package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TelefoneDTO;
import br.unitins.topicos1.dto.TelefoneResponseDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {
    
    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    public UsuarioResponseDTO insertTelefone(@Valid Long idUsuario, TelefoneDTO dto);

    public UsuarioResponseDTO update(@Valid UsuarioDTO dto, Long id);

    public UsuarioResponseDTO updateTelefone(@Valid Long id, Long idTelefone, TelefoneDTO dto);

    public void delete(Long id);

    public void deleteTelefone(Long id, Long idTelefone);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public List<UsuarioResponseDTO> findByAll();

    public UsuarioResponseDTO findByEmailAndSenha(String email, String senha);

    public UsuarioResponseDTO findByEmail(String email);

    public TelefoneResponseDTO findTelById(Long id);

    public List<TelefoneResponseDTO> findTelByCodigoArea(String codigoArea);


}
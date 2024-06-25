package com.agencia.services;

import com.agencia.domain.Usuario;
import com.agencia.domain.dto.UsuarioDTO;
import com.agencia.repository.UsuarioRepository;
import com.agencia.services.exceptions.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id){
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario create(UsuarioDTO obj) {
        return usuarioRepository.save(newUsuario(obj));
    }

    private Usuario newUsuario(UsuarioDTO obj){
        Usuario usuario = new Usuario();

        String nome = obj.getNome();
        String email = obj.getEmail();
        LocalDateTime dataCadastro = obj.getDataCadastro();
        LocalDateTime ultimaAtualização = obj.getUltimaAtualizazao();

        if (obj.getId() != null){
            usuario.setId(obj.getId());
        }

        usuario.setNome(obj.getNome());
        usuario.setEmail(obj.getEmail());
        usuario.setDataCadastro(obj.getDataCadastro());
        usuario.setUltimaAtualizazao(obj.getUltimaAtualizazao());

        return usuario;
    }

    public Usuario update(Long id, UsuarioDTO objDTO){
        Usuario existeUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(id));

        existeUsuario.setNome(objDTO.getNome());
        existeUsuario.setEmail(objDTO.getEmail());
        existeUsuario.setDataCadastro(objDTO.getDataCadastro());
        existeUsuario.setUltimaAtualizazao(objDTO.getUltimaAtualizazao());

        return usuarioRepository.save(existeUsuario);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);

        obj.setDataFim(new Date());
        obj.setExclusaoLogica(true);
        usuarioRepository.save(obj);
    }
}

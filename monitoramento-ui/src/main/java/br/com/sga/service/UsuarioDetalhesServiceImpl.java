package br.com.sga.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sga.model.Funcao;
import br.com.sga.model.Usuario;
import br.com.sga.repository.UsuarioRepository;

@Service
public class UsuarioDetalhesServiceImpl implements UserDetailsService{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nome) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        if (usuario == null) throw new UsernameNotFoundException(nome);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Funcao funcao : usuario.getFuncoes()){
            grantedAuthorities.add(new SimpleGrantedAuthority(funcao.getNome()));
        }

        return new org.springframework.security.core.userdetails.User(usuario.getNome(), usuario.getSenha(), grantedAuthorities);
    }
}
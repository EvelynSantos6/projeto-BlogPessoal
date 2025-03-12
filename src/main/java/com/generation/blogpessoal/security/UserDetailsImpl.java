package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails {
	
	//controle de versão para a classe - "L" de long
	private static final long serialVersionUID = 1L;

	private String userName; //e-mail do usuario
	private String password; //senha do usuario
	private List<GrantedAuthority> authorities;
	//lista de autorizações que o usuário tem

	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario(); //pega o usuario e joga no atributo username
		this.password = user.getSenha();//pega o usuario e joga no atributo password
	}

	public UserDetailsImpl() {	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}
	//verifica se o usuario expirou
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	//verifica se o usuario está bloquado
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	//valida se a credencial expira
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	//valida se o user está ativo
	@Override
	public boolean isEnabled() {
		return true;
	}

}

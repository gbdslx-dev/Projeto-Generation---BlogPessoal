package com.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.generation.blogpessoal.model.UserLogin;
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private com.generation.blogPessoal.repository.UsuarioRepository repository;

	public Optional<Usuario> CadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> retorno;
		Optional<Usuario> user = repository.findByUsuario(usuario.getUsuario());

		if (user.isEmpty()) {
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			repository.save(usuario);
			retorno = Optional.of(usuario);

		} else {
			retorno = Optional.empty();
		}

		return retorno;

	}

	public Optional<UserLogin> Logar(Optional<UserLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));

				String authHeader = "Basic " + new String(encodedAuth);

				user.get().setToken(authHeader);

				user.get().setId(usuario.get().getId());

				user.get().setNome(usuario.get().getNome());

				user.get().setFoto(usuario.get().getFoto());

				user.get().setSenha(usuario.get().getSenha());

				return user;
			}
		}

		return null;
	}

}

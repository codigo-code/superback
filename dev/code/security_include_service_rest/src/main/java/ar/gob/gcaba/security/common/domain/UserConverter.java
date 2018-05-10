package ar.gob.gcaba.security.common.domain;

import javax.annotation.Resource;

import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import ar.gob.gcaba.cas.client.domain.UsuarioParcial;



@Component("userConverter")
public class UserConverter {
	
	@Resource (name = "userConverterMapper")
	private Mapper mapper;
	
	/* (non-Javadoc)
	 * @see ar.gob.gcaba.security.common.domain.IUserConverter#cargarDTO(ar.gob.gcaba.cas.client.domain.UsuarioParcial)
	 */
	public UsuarioSolr cargarDTO(UsuarioParcial usuario) {
		UsuarioSolr usuarioSolr = new UsuarioSolr();
		try {
			if (usuario != null && usuario.getUid()!=null) {
				usuarioSolr.setUsername(usuario.getUid());
				usuarioSolr.setNombreApellido(usuario.getNombre());
				usuarioSolr.setEmail(usuario.getMail());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioSolr;
	}
	
	/* (non-Javadoc)
	 * @see ar.gob.gcaba.security.common.domain.IUserConverter#cargarDTO(ar.gob.gcaba.cas.client.domain.Usuario)
	 */
	public UsuarioSolr cargarDTO(Usuario usuario) {
		UsuarioSolr usuarioSolr = null;
		try {
			if (usuario != null) {
				usuarioSolr = mapper.map(usuario, UsuarioSolr.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioSolr;
	}

	/* (non-Javadoc)
	 * @see ar.gob.gcaba.security.common.domain.IUserConverter#cargarUsuario(ar.gob.gcaba.security.common.domain.UsuarioSolr)
	 */
	public Usuario cargarUsuario(UsuarioSolr usuarioSolr) {		
		try {
			Usuario usuario = mapper.map(usuarioSolr, Usuario.class);
	
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

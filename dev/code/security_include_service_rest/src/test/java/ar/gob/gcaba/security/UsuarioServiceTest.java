//package ar.gob.gcaba.security;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import ar.gob.gcaba.security.common.domain.Usuario;
//import ar.gob.gcaba.security.exceptions.SecurityNegocioException;
//import ar.gob.gcaba.security.il.dao.IUsuarioDao;
//import ar.gob.gcaba.security.services.IUsuarioService;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/config-test.xml"})
//public class UsuarioServiceTest
//{
//
//	@Autowired
//	private IUsuarioService usuarioService;													
//
//	@Autowired
//	private IUsuarioDao usuarioDAO;
//
//	@Before
//	public void cargarDependencias() {
//	}
//	
//	
//	@Test
//	public void obtenerUsuariosPorReparticionTest() throws SecurityNegocioException {	
//		List<Usuario> usuarios = new ArrayList<Usuario>();
//		usuarios = usuarioService.obtenerUsuariosPorGrupo("DGSOCAI", "SEC01");
//
//		Assert.assertTrue("Vacio", usuarios.isEmpty());
//	}
//
//	@Test
//	public void cargarCacheUsuarios() throws SecurityNegocioException {
//		ApplicationContext context = new ClassPathXmlApplicationContext("/config-test.xml");
//		
//		Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
//		usuarios = usuarioService.obtenerMapaUsuarios();
//
//		Assert.assertTrue("Vacio", usuarios.isEmpty());
//	}
//
//	@Test
//	public void testearDependenciaService() {
//		Assert.assertNull(usuarioService);
//	}
//
//	@Test
//	public void testearDependenciaDao() {
//		Assert.assertNull(usuarioDAO);
//	}
//
//	@Test
//	public void testFullImportSOLR() {
//		try {
//			usuarioService.fullImportUsuariosActivos();
//		} catch (Exception e) {
//		}
//	}
//
//	@Test
//	public void testBuscarUsuarioNoExiste() {
//
//		Usuario usu = null;
//		try {
//			usu = usuarioService.obtenerUsuario("DOMIGEZRE");
//		} catch (Exception e) {
//			// fail("Ha ocurrido un error");
//		}
//		Assert.assertNull(usu);
//	}
//
//	@Test
//	public void testDummyFail() {
//		Assert.assertEquals(1, 2);
//	}
//
//	@Test
//	public void testDummySuccess() {
//		Assert.assertEquals(1, 1);
//	}
//
//	@Test
//	public void testBuscarUsuarioNoMRNoExisteEnCache() {
//		Usuario usu = null;
//		try {
//			usu = usuarioService.obtenerUsuario("DEKKERM");
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//
//	@Test
//	public void testBuscarUsuarioNoMR() {
//		Usuario usu = null;
//		try {
//			usu = usuarioService.obtenerUsuario("PUBLICACIONES");
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//
//	@Test
//	public void testBuscarUsuarioMR() {
//		Usuario usu = null;
//		try {
//			usu = usuarioService.obtenerUsuario("DEKKERM");
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//
//	@Test
//	public void test10MilLlamadasMR() {
//		Usuario usu = null;
//		try {
//			for (int i = 0; i < 10000; i++) {
//				usu = usuarioService.obtenerUsuario("DEKKERM");
//			}
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//
//	@Test
//	public void test10MilLlamadasNoMR() {
//		Usuario usu = null;
//		try {
//			for (int i = 0; i < 10000; i++) {
//				usu = usuarioService.obtenerUsuario("PUBLICACIONES");
//			}
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//	
//	@Test
//	public void testEliminarByID() {
//		Usuario usu = null;
//		try {
//			usuarioService.eliminarUsuario("GAMBARTEMO");
//		} catch (Exception e) {
//			Assert.assertNotNull(usu);
//		}
//
//		Assert.assertNotNull(usu);
//	}
//	
//	
//	@Test
//	public void testTraerUsuarios() {		
//		
//		try {
//			usuarioService.obtenerUsuarios();
//		} catch (Exception e) {
//			Assert.assertNotNull(null);
//		}
//		
//	}
//}

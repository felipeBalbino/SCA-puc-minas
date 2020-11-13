package br.com.sga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sga.model.Funcao;
import br.com.sga.model.Usuario;
import br.com.sga.service.UsuarioService;
import br.com.sga.validator.UsuarioValidator;

/**
 * @author sga
 *
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioValidator usuarioValidator;

	/**
	 * @return
	 */
	@GetMapping("/new")
	public ModelAndView usuario() {
		ModelAndView mv = new ModelAndView("/usuario/usuario");
		mv.addObject(new Usuario());
		return mv;
	}

	/**
	 * @param usuario
	 * @return
	 */
	@RequestMapping("{codigo}")
	public ModelAndView update(@PathVariable("codigo") Usuario usuario) {
		ModelAndView mv = new ModelAndView("/usuario/usuario");
		mv.addObject(usuario);
		return mv;
	}

	/**
	 * @param usuario
	 * @param bindingResult
	 * @param attr
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String usuario(Usuario usuario, BindingResult bindingResult, RedirectAttributes attr) {
		usuarioValidator.validate(usuario, bindingResult);

		if (bindingResult.hasErrors()) {
			return "/usuario/usuario";
		}
		if(usuario.getCodigo() != null) {
			usuarioService.update(usuario);
			attr.addFlashAttribute("mensagem", "Usuario foi alterado com sucesso");
		}else{
			usuarioService.save(usuario);
			attr.addFlashAttribute("mensagem", "Usuario foi salvo com sucesso");
		}
		
		
		return "redirect:/usuario/";
	}

	/**
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		List<Usuario> list = usuarioService.findAll();
		ModelAndView mv = new ModelAndView("/usuario/list");
		mv.addObject("usuarios", list);
		return mv;
	}

	/**
	 * @param codigo
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long codigo, RedirectAttributes attr) {
		usuarioService.delete(codigo);
		attr.addFlashAttribute("mensagem", "Usuario foi excluído com sucesso");
		return "redirect:/usuario/";
	}

	/**
	 * @return
	 */
	@ModelAttribute("listFuncoes")
	public List<Funcao> listFuncoes() {
		return usuarioService.listFuncoes();
	}

	
	/**	
	 * @return
	 */
	@ModelAttribute("currentPage")
	public String currentPage() {
		return "usuario";
	}
}
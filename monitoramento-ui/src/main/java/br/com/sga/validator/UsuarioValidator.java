package br.com.sga.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.sga.model.Usuario;
import br.com.sga.service.UsuarioService;

@Component
public class UsuarioValidator implements Validator {
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    	Usuario usuario = (Usuario) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataInclusao", "NotEmpty");
        if (usuario.getNome().length() < 3) {
            errors.rejectValue("nome", "Size.userForm.username");
        }
        if (usuario.getCodigo() == null && usuarioService.findByNome(usuario.getNome()) != null) {
            errors.rejectValue("nome", "Duplicate.userForm.username");
        }

        if (!usuario.getSenhaConfirmacao().equals(usuario.getSenha())) {
            errors.rejectValue("senhaConfirmacao", "Diff.userForm.passwordConfirm");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "senha", "NotEmpty");
        if (usuario.getSenha().length() < 6) {
            errors.rejectValue("senha", "Size.userForm.password");
        }

    }
}
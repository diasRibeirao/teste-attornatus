//package com.teste.attornatus.service.validation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.teste.attornatus.controller.exceptions.FieldMessage;
//import com.teste.attornatus.dto.PessoaAdicionarDTO;
//import com.teste.attornatus.repository.PessoaRepository;
//import com.teste.santander.domain.Cliente;
//import com.teste.santander.domain.dto.ClienteAdicionarDTO;
//import com.teste.santander.service.validation.ClienteAdicionar;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class PessoaAdicionarValidator implements ConstraintValidator<PessoaAdicionar, PessoaAdicionarDTO> {
//
//	@Autowired
//	private PessoaRepository pessoaRepository;
//
//	@Override
//	public void initialize(PessoaAdicionar ann) {
//	}
//
//	@Override
//	public boolean isValid(PessoaAdicionarDTO clienteoAdicionarDTO, ConstraintValidatorContext context) {
//		List<FieldMessage> list = new ArrayList<>();
//
//		Cliente aux = repository.findByNumeroDaConta(clienteoAdicionarDTO.getNumeroDaConta());
//		if (aux != null) {
//			list.add(new FieldMessage("numeroDaConta", "Número da conta já cadastrado"));
//		}
//
//		for (FieldMessage e : list) {
//			context.disableDefaultConstraintViolation();
//			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
//					.addConstraintViolation();
//		}
//
//		return list.isEmpty();
//	}
//
//}

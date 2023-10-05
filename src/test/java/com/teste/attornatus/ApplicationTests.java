package com.teste.attornatus;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	PessoaControllerIntegrationTest.class, 
	EnderecoControllerIntegrationTest.class 
})
public class ApplicationTests {

}

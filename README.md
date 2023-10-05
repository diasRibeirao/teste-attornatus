# teste-attornatus
Teste Técnico - Back End - Attornatus


## Requisitos
Para execução do projeto, é necessário instalação do JDK 17.
O passo-a-passo abaixo foi feito com base no Eclipse.

## Configurações
- Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. As propriedades do datasource são exibidas como abaixo:

    # BD
    spring.datasource.url=jdbc:h2:mem:testdb
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
	
	Acesso ao banco de dados:
	http://localhost:8080/h2-console/login.jsp 
	
## Lombok
Verificar se tem configurado o lombok na IDE.
1. Baixar o jar do lombok;
2. Executar o jar do lombok (java -jar lombok.jar); 
3. Selecionar o local onde está o eclipse;  
4. Instalar o lombok no eclipse.

## Para executar o projeto
Ao ser iniciada, a aplicação cria o banco de dados.
1. Clone o repositório ou faça o download;
2. Se está usando uma ferramenta externa a IDE, importe o projeto como projeto Maven existente;
3. Com o botão direito do mouse, clique sobre o projeto, Maven -> Update Project.
4. Para iniciar a aplicação clique no projeto com o botão direito do mouse, vá até a opção *Run As* e selecione Spring Boot App.

## EndPoints
Para ver a lista de chamadas REST disponíveis, seus parametros, e tipo de retorno, inicie a aplicação e acesse: 
http://localhost:8080/swagger-ui/index.html

## Testes
Para executar os testes abra a classe ApplicationTests.java, clique em Run -> Run As -> JUnit Test. Isso fará com que todos os testes de integração implementados sejam executados.

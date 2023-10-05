<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="testeattornatus_0"></a>teste-attornatus</h1>
<p class="has-line-data" data-line-start="1" data-line-end="2">Teste Técnico - Back End - Attornatus</p>
<h2 class="code-line" data-line-start=4 data-line-end=5 ><a id="Requisitos_4"></a>Requisitos</h2>
<p class="has-line-data" data-line-start="5" data-line-end="7">Para execução do projeto, é necessário instalação do JDK 17.<br>
O passo-a-passo abaixo foi feito com base no Eclipse.</p>
<h2 class="code-line" data-line-start=8 data-line-end=9 ><a id="Configuraes_8"></a>Configurações</h2>
<ul>
<li class="has-line-data" data-line-start="9" data-line-end="21">
<p class="has-line-data" data-line-start="9" data-line-end="10">Para alterar as configurações do banco de dados com URL, usuário, senha e url do console, e ativar/desativar o console, abra o arquivo application.properties. As propriedades do datasource são exibidas como abaixo:</p>
<h1 class="code-line" data-line-start=11 data-line-end=12 ><a id="BD_11"></a>BD</h1>
<p class="has-line-data" data-line-start="12" data-line-end="17">spring.datasource.url=jdbc:h2:mem:testdb<br>
spring.datasource.driverClassName=org.h2.Driver<br>
spring.datasource.username=sa<br>
spring.datasource.password=<br>
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect</p>
<p class="has-line-data" data-line-start="18" data-line-end="20">Acesso ao banco de dados:<br>
<a href="http://localhost:8080/h2-console/login.jsp">http://localhost:8080/h2-console/login.jsp</a></p>
</li>
</ul>
<h2 class="code-line" data-line-start=21 data-line-end=22 ><a id="Lombok_21"></a>Lombok</h2>
<p class="has-line-data" data-line-start="22" data-line-end="23">Verificar se tem configurado o lombok na IDE.</p>
<ol>
<li class="has-line-data" data-line-start="23" data-line-end="24">Baixar o jar do lombok;</li>
<li class="has-line-data" data-line-start="24" data-line-end="25">Executar o jar do lombok (java -jar lombok.jar);</li>
<li class="has-line-data" data-line-start="25" data-line-end="26">Selecionar o local onde está o eclipse;</li>
<li class="has-line-data" data-line-start="26" data-line-end="28">Instalar o lombok no eclipse.</li>
</ol>
<h2 class="code-line" data-line-start=28 data-line-end=29 ><a id="Para_executar_o_projeto_28"></a>Para executar o projeto</h2>
<p class="has-line-data" data-line-start="29" data-line-end="30">Ao ser iniciada, a aplicação cria o banco de dados.</p>
<ol>
<li class="has-line-data" data-line-start="30" data-line-end="31">Clone o repositório ou faça o download;</li>
<li class="has-line-data" data-line-start="31" data-line-end="32">Se está usando uma ferramenta externa a IDE, importe o projeto como projeto Maven existente;</li>
<li class="has-line-data" data-line-start="32" data-line-end="33">Com o botão direito do mouse, clique sobre o projeto, Maven -&gt; Update Project.</li>
<li class="has-line-data" data-line-start="33" data-line-end="35">Para iniciar a aplicação clique no projeto com o botão direito do mouse, vá até a opção <em>Run As</em> e selecione Spring Boot App.</li>
</ol>
<h2 class="code-line" data-line-start=35 data-line-end=36 ><a id="EndPoints_35"></a>EndPoints</h2>
<p class="has-line-data" data-line-start="36" data-line-end="38">Para ver a lista de chamadas REST disponíveis, seus parametros, e tipo de retorno, inicie a aplicação e acesse:<br>
<a href="http://localhost:8080/swagger-ui/index.html">http://localhost:8080/swagger-ui/index.html</a></p>
<h2 class="code-line" data-line-start=39 data-line-end=40 ><a id="Testes_39"></a>Testes</h2>
<p class="has-line-data" data-line-start="40" data-line-end="41">Para executar os testes abra a classe ApplicationTests.java, clique em Run -&gt; Run As -&gt; JUnit Test. Isso fará com que todos os testes de integração implementados sejam executados.</p>

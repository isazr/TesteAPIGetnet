Testes de API realizado para empresa Getnet

T�cnica de teste por parti��o de equival�ncia, separando as possibilidades de testes positivos e negativos, procurando realizar cobertura de testes de modo assertivo.

Observa��es: 
- No m�todo deletarUsuario (class : UsuariosRequisicoes) � observado que a requisi��o � completada com sucesso, por�m deveria retornar erro pois n�o deve ser poss�vel detelar um ID inexistente.
- No m�todo sucessoRegistroUsuario (class : RegisterRequisicoes) � poss�vel observar que a requisi��o do registro do usu�rio � completada com sucesso, mesmo que a senha passada seja diferente da orientada no requestBody.
- No m�todo registroUsuarioEmailIncorreto (class: RegisterRequisicoes) � poss�vel validar, atrav�s do responseBody, que s� � permitido o registro do usu�rio com um email pr�-configurado.
- No m�todo loginSenhaIncorreta (class: RegisterRequisicoes) � poss�vel observar uma falsa valida��o para o login, pois a senha est� diferente da senha apresentada no requestBody.
Testes de API realizado para empresa Getnet

Técnica de teste por partição de equivalência, separando as possibilidades de testes positivos e negativos, procurando realizar cobertura de testes de modo assertivo.

Observações: 
- No método deletarUsuario (class : UsuariosRequisicoes) é observado que a requisição é completada com sucesso, porém deveria retornar erro pois não deve ser possível detelar um ID inexistente.
- No método sucessoRegistroUsuario (class : RegisterRequisicoes) é possível observar que a requisição do registro do usuário é completada com sucesso, mesmo que a senha passada seja diferente da orientada no requestBody.
- No método registroUsuarioEmailIncorreto (class: RegisterRequisicoes) é possível validar, através do responseBody, que só é permitido o registro do usuário com um email pré-configurado.
- No método loginSenhaIncorreta (class: RegisterRequisicoes) é possível observar uma falsa validação para o login, pois a senha está diferente da senha apresentada no requestBody.
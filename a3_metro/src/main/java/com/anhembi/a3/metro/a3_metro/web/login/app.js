document.getElementById('collaborator-btn').addEventListener('click', function() {
    const collaboratorForm = document.getElementById('collaborator-form');
    collaboratorForm.classList.toggle('hidden');
});



// login validação

document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtendo os valores de email e senha
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    // Definindo as credenciais para validação (estes valores seriam normalmente armazenados em um servidor)
    const validEmail = "usuario@example.com";  // Email correto
    const validPassword = "senha123";  // Senha correta

    // Verificando se o email e a senha são válidos
    if (email === validEmail && password === validPassword) {
        // Redireciona para a página "pagina-principal.html" após o login bem-sucedido
        window.location.href = "pagina-principal.html";
    } else {
        // Exibe uma mensagem de erro caso o login falhe
        document.getElementById("error-message").textContent = "Email ou senha incorretos!";
    }
});

const formulario = document.querySelector("form");
const botao = document.querySelector("button");
const nome = document.querySelector(".nome");
const email = document.querySelector(".email");
const senha = document.querySelector(".senha");
const cadastrar = document.querySelector("btn");


function cadastrar() {
    fetch("http://localhost:3306/login", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST",
        body: JSON.stringify({
            nome: nome.value,
            email: email.value,
            senha: senha.value,
            
        })
    })
    .then(function (res) { console.log(res) })
    .catch(function (res) { console.log(res) });
}

function limpar() {
    inome.value = "";
    iemail.value = "";
    isenha.value = "";
    itelefone.value = "";
}

formulario.addEventListener("submit", function (event) {
    event.preventDefault();
    cadastrar();
    limpar();
});





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

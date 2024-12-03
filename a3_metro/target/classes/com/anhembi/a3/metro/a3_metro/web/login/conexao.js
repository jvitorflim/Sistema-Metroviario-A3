const txtEmailLogin = document.getElementById("emailUsuarioLogin");
const txtSenhaLogin = document.getElementById("senhaUsuarioLogin");
const txtEmailTecnico = document.getElementById("emailUsuarioTecnico");
const txtSenhaTecnico = document.getElementById("senhaUsuarioTecnico");
const txtEmailCadastro = document.getElementById("emailUsuarioCadastro");
const txtSenhaCadastro = document.getElementById("senhaUsuarioCadastro");
const txtResposta = document.getElementById("resposta");
const btnLogin = document.getElementById("btn_entrar");
const btnLoginTecnico = document.getElementById("btn_entrarTecnico");
const btnLoginCadastrar = document.getElementById("btn_cadastrar");

const urlLogin = "http://localhost:8080/login";
const urlLoginTecnico = "http://localhost:8080/login/tecnico";
const urlCadastro = "http://localhost:8080/cadastro";

async function login() {
    try {
        let resposta = await fetch(urlLogin, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": txtEmailLogin.value,
                "senha": txtSenhaLogin.value
            })
        });

        if (resposta.status == 200) {
            let dados = await resposta.json();
            txtResposta.innerHTML = `Login realizado! Bem-vindo, ${dados.email}`;
        } else if (resposta.status == 404) {
            txtResposta.innerHTML = "Usuário não encontrado!";
        } else if (resposta.status == 400) {
            txtResposta.innerHTML = "Dados inválidos. Verifique seu email e senha.";
        } else {
            txtResposta.innerHTML = "Erro ao realizar login.";
        }
    } catch (error) {
        txtResposta.innerHTML = "Erro de conexão. Tente novamente.";
        console.error(error);
    }
}

async function loginTecnico() {
    try {
        let resposta = await fetch(urlLoginTecnico, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": txtEmailTecnico.value,
                "senha": txtSenhaTecnico.value
            })
        });

        if (resposta.status === 200) {
            let dados = await resposta.json();
            txtResposta.innerHTML = `Login técnico realizado! Bem-vindo, ${dados.email}`;
        } else if (resposta.status === 403) {
            txtResposta.innerHTML = "Acesso negado. Este usuário não é técnico.";
        } else if (resposta.status === 404) {
            txtResposta.innerHTML = "Usuário não encontrado!";
        } else if (resposta.status === 400) {
            txtResposta.innerHTML = "Dados inválidos. Verifique seu email e senha.";
        } else {
            txtResposta.innerHTML = "Erro ao realizar login técnico.";
        }
    } catch (error) {
        txtResposta.innerHTML = "Erro de conexão. Tente novamente.";
        console.error(error);
    }
}

async function cadastrar() {
    try {
        let resposta = await fetch(urlCadastro, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "email": txtEmailCadastro.value,
                "senha": txtSenhaCadastro.value
            })
        });

        if (resposta.status == 201) {
            let dados = await resposta.json();
            txtResposta.innerHTML = `Usuário cadastrado com sucesso: ${dados.email}`;
        } else {
            txtResposta.innerHTML = "Erro ao cadastrar! Verifique os dados.";
        }
    } catch (error) {
        txtResposta.innerHTML = "Erro de conexão. Tente novamente.";
        console.error(error);
    }
}


btnLogin.addEventListener('click', login);
btnLoginTecnico.addEventListener('click', loginTecnico);
btnLoginCadastrar.addEventListener('click', cadastrar);
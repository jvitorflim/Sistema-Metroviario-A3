const btnDevagar = document.getElementById("btn_devagar");
const btnLotado = document.getElementById("btn_lotado");
const btnVazio = document.getElementById("btn_vazio");
const btnQuebrado = document.getElementById("btn_quebrado");
const txtResposta = document.getElementById("resposta");

const url = "http://localhost:8080/linha";

async function enviarAviso(tipoAviso) {
    try {
        let resposta = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ "tipoAviso": tipoAviso })
        });

        if (resposta.status === 201) {
            txtResposta.innerHTML = "Aviso cadastrado com sucesso!";
        } else if (resposta.status === 400) {
            txtResposta.innerHTML = "Erro: Dados inválidos.";
        } else {
            txtResposta.innerHTML = "Erro ao cadastrar o aviso.";
        }
    } catch (error) {
        txtResposta.innerHTML = "Erro de conexão. Tente novamente.";
        console.error(error);
    }
}

btnDevagar.addEventListener('click', () => enviarAviso("ATRASO"));
btnLotado.addEventListener('click', () => enviarAviso("SUPERLOTACAO"));
btnVazio.addEventListener('click', () => enviarAviso("VAZIO"));
btnQuebrado.addEventListener('click', () => enviarAviso("QUEBRADO"));

//relogio
function atualizarRelogio() {
    const now = new Date();
    const options = { hour: '2-digit', minute: '2-digit', second: '2-digit' };
    document.getElementById('clock').innerText = now.toLocaleTimeString('pt-BR', options);
}
setInterval(atualizarRelogio, 1000);
atualizarRelogio();

// OCORRENCIAS / NOTICIAS

function carregarOcorrencias() {
    // Obtém as ocorrências do localStorage
    const ocorrencias = JSON.parse(localStorage.getItem("ocorrencias")) || [];

    // Atualiza o conteúdo do container
    const letreiro = document.getElementById("ocorrencias-letreiro");
    if (ocorrencias.length > 0) {
        letreiro.textContent = ocorrencias.join(" • ");
    } else {
        letreiro.textContent = "Nenhuma ocorrência no momento.";
    }
}



// Chama a função ao carregar a página
window.onload = carregarOcorrencias;




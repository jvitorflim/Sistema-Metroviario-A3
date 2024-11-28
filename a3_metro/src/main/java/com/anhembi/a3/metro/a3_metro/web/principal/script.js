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
    const url = 'https://seu-backend.com/api/ocorrencias';

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data.ocorrencias && data.ocorrencias.length > 0) {
                const ocorrenciasLetreiro = data.ocorrencias.join(" • ");
                document.getElementById("ocorrencias-letreiro").textContent = ocorrenciasLetreiro;
            } else {
                document.getElementById("ocorrencias-letreiro").textContent = "Nenhuma ocorrência no momento.";
            }
        })
        .catch(error => {
            console.error('Erro ao carregar ocorrências:', error);
            document.getElementById("ocorrencias-letreiro").textContent = "Erro ao carregar ocorrências.";
        });
}

// Chama a função ao carregar a página
window.onload = carregarOcorrencias;




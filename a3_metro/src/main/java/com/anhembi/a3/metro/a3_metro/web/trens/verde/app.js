class Trem {
    constructor(numero, velocidade, posicaoInicial) {
        this.numero = numero;
        this.velocidade = velocidade; 
        this.posicao = posicaoInicial; 
        this.pausado = false;  
        this.tempoPausa = 5; 
        this.tempoRestante = 0;
        this.direcao = 1; 
    }

    calcularTempoChegadaPorDistancia(distancia) {
       
        return (distancia / (this.velocidade * 1000 / 60)).toFixed(2); 
    }

    mover() {
        if (!this.pausado) {
            const movimento = (this.velocidade / 3600) * this.direcao; // km/s
            this.posicao += movimento;
            this.verificarEstacao();
        } else {
            this.tempoRestante--;
            if (this.tempoRestante <= 0) {
                this.pausado = false;
            }
        }
    }

    verificarEstacao() {
        const estacoes = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22];
        const estacaoAtual = Math.floor(this.posicao);

        if (estacoes.includes(estacaoAtual) && !this.pausado) {
            this.pausar();
        }

        if (estacaoAtual >= estacoes.length - 1 && this.direcao === 1) {
            this.pausado = true;
            this.posicao = 0; 
        }
    }

    pausar() {
        this.pausado = true;
        this.tempoRestante = this.tempoPausa;
    }
}

class ControleTrens {
    constructor() {
        this.listaTrens = [];
        this.estacoes = [
            { nome: "Vila-Madalena", posicao: 0, distancia: 1200 },
            { nome: "Sumaré", posicao: 1, distancia: 1300 },
            { nome: "Clínicas", posicao: 2, distancia: 1000 },
            { nome: "Consolação", posicao: 3, distancia: 750 },
            { nome: "Trianon-Masp", posicao: 4, distancia: 800 },
            { nome: "Brigadeiro", posicao: 5, distancia: 1300 },
            { nome: "Paraíso", posicao: 6, distancia: 1200 },
            { nome: "Ana-Rosa", posicao: 7, distancia: 1500 },
            { nome: "Chácara-Klabin", posicao: 8, distancia: 2000 },
            { nome: "Santos-Imigrantes", posicao: 9, distancia: 1200 },
            { nome: "Alto-do-Ipiranga", posicao: 10, distancia: 1300 },
            { nome: "Sacomã", posicao: 11, distancia: 1800 },
            { nome: "Tamanduateí", posicao: 12, distancia: 1200 },
            { nome: "Vila-Prudente", posicao: 13, distancia: 1100 }
        ];
    


       
        this.distanciaTotal = 1500 * 13;  
        this.tempoTotal = 30; 
        
        this.velocidadeMedia = this.distanciaTotal / (this.tempoTotal * 60); 
    }

    adicionarTrem(numero, velocidade, posicaoInicial) {
        const novoTrem = new Trem(numero, velocidade, posicaoInicial);
        this.listaTrens.push(novoTrem);
        this.atualizarTempoChegada();
    }

    atualizarTrens() {
        this.listaTrens.forEach(trem => {
            trem.mover();
        });
        this.atualizarTempoChegada();
    }

    calcularTempoChegadaPorDistancia(distancia) {
        const tempoTotal = 30;  
        const distanciaTotal = 14000;  
        const tempoProporcional = (distancia / distanciaTotal) * tempoTotal;
        return tempoProporcional.toFixed(2);  
    }

    atualizarTempoChegada() {
        const tempoTotalReal = 30; 
        let tempoTotalBruto = 0;
        
        this.listaTrens.forEach(trem => {
            let ultimoTempoChegada = 0;
        
            this.estacoes.forEach((estacao, index) => {
                let distancia = Math.abs(estacao.distancia - (trem.posicao * 1000)); // Distância até a estação
                const tempoPorDistancia = parseFloat(trem.calcularTempoChegadaPorDistancia(distancia));
                tempoTotalBruto += tempoPorDistancia;
            });
        });
        
        const fatorAjuste = tempoTotalReal / tempoTotalBruto;
        
        
        this.listaTrens.forEach(trem => {
            let ultimoTempoChegada = 0;
        
            this.estacoes.forEach((estacao, index) => {
                let distancia = Math.abs(estacao.distancia - (trem.posicao * 1000));
                let tempoPorDistancia = parseFloat(trem.calcularTempoChegadaPorDistancia(distancia));
        
                
                tempoPorDistancia *= fatorAjuste;
        
                
                trem.tempoChegada = trem.tempoChegada || {};
                trem.tempoChegada[estacao.nome] = parseFloat(ultimoTempoChegada) + tempoPorDistancia;
                ultimoTempoChegada = trem.tempoChegada[estacao.nome];
            });
        });
    }
    

    atualizarDisplay() {
        const mapa = document.getElementById('mapa-linha');
        this.listaTrens.forEach(trem => {
            const existingIcon = document.getElementById(`trem-${trem.numero}`);
            if (existingIcon) {
                mapa.removeChild(existingIcon);
            }

            const iconTrem = document.createElement('div');
            iconTrem.id = `trem-${trem.numero}`;
            iconTrem.classList.add('trem');

            const posX = (trem.posicao / this.estacoes.length) * 100;
            iconTrem.style.left = `${posX}%`;
            mapa.appendChild(iconTrem);
        });
    }

    
mostrarTempoProximaEstacao(estacaoNome) {
    const infoDiv = document.getElementById('informacoes');
    infoDiv.innerHTML = "";
    let trensExibidos = [];

    this.listaTrens.forEach(trem => {
        const tempoChegada = trem.tempoChegada[estacaoNome];

        if (tempoChegada !== undefined && tempoChegada >= 0 && !trensExibidos.includes(trem.numero)) {
            
            const estacao = this.estacoes.find(estacao => estacao.nome === estacaoNome);

            
            if (trem.posicao < estacao.posicao) {
                
                const distanciaRestante = estacao.posicao - trem.posicao; 

                
                const tempoPorEstacao = 30 / 13; 

                
                const tempoRestanteEmMinutos = distanciaRestante * tempoPorEstacao;

                
                const horaAtual = new Date();
                const horaChegada = new Date(horaAtual.getTime() + tempoRestanteEmMinutos * 60000);
                const horaFormatada = horaChegada.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });

                
                infoDiv.innerHTML += `Frota G ${trem.numero}: Chegará em ${tempoRestanteEmMinutos.toFixed(2)} minuto(s) (às ${horaFormatada})<br>`;
                trensExibidos.push(trem.numero);
            }
        }
    });
}


    
}

const controleTrens = new ControleTrens();


function inicializarTrens() {
    controleTrens.adicionarTrem(1, 60, 0); 
    controleTrens.adicionarTrem(2, 60, 2); 
    controleTrens.adicionarTrem(3, 60, 4); 
    controleTrens.adicionarTrem(4, 60, 6); 
    controleTrens.adicionarTrem(5, 60, 8); 
    controleTrens.adicionarTrem(6, 60, 10); 
    controleTrens.adicionarTrem(7, 60, 12); 
    atualizarInterface();
}


function atualizarInterface() {
    controleTrens.atualizarTrens();
    controleTrens.atualizarDisplay();
}


inicializarTrens();
setInterval(atualizarInterface, 1000); 

document.querySelectorAll('.estacao').forEach(estacao => {
    estacao.addEventListener('click', function() {
        const estacaoNome = this.textContent.trim();
        controleTrens.mostrarTempoProximaEstacao(estacaoNome);
    });
});

// Adiciona evento de clique aos botões
document.querySelectorAll('.feedback-btn').forEach(botao => {
    botao.addEventListener('click', () => {
        const feedback = botao.dataset.feedback; 
        enviarFeedback(feedback); 
    });
});
async function enviarFeedback(feedback) {
    
    if (resposta.ok) {
        alert('Feedback enviado com sucesso!');
    } else {
        alert('Feedback enviado com sucesso!');
    }
}  
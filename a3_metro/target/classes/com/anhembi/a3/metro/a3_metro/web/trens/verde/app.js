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
        const estacoes = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17];
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
            { nome: "Palmeiras-Barra-Funda", posicao: 0, distancia: 1500 },
            { nome: "Marechal-Deodoro", posicao: 1, distancia: 1300 },
            { nome: "Santa-Cecília", posicao: 2, distancia: 1000 },
            { nome: "República", posicao: 3, distancia: 800 },
            { nome: "Anhangabaú", posicao: 4, distancia: 900 },
            { nome: "Sé", posicao: 5, distancia: 1400 },
            { nome: "Pedro-II", posicao: 6, distancia: 900 },
            { nome: "Brás", posicao: 7, distancia: 1300 },
            { nome: "Bresser-Mooca", posicao: 8, distancia: 1500 },
            { nome: "Belém", posicao: 9, distancia: 1100 },
            { nome: "Tatuapé", posicao: 10, distancia: 1200 },
            { nome: "Carrão", posicao: 11, distancia: 1250 },
            { nome: "Penha", posicao: 12, distancia: 1300 },
            { nome: "Vila-Matilde", posicao: 13, distancia: 1400 },
            { nome: "Guilhermina-Esperança", posicao: 14, distancia: 1100 },
            { nome: "Patriarca", posicao: 15, distancia: 1300 },
            { nome: "Artur-Alvim", posicao: 16, distancia: 1400 },
            { nome: "Corinthians-Itaquera", posicao: 17, distancia: 1500 }
        ];

         
          this.distanciaTotal = 1500 * 17;  
          this.tempoTotal = 51; 
  
         
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
         
          const tempoTotal = 51;  
          const distanciaTotal = 22000;  
      
          
          const tempoProporcional = (distancia / distanciaTotal) * tempoTotal;
          return tempoProporcional.toFixed(2);  
      }
  
      atualizarTempoChegada() {
          const tempoTotalReal = 51; 
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
  
                  
                  const tempoPorEstacao = 51 / 17; 
  
                  
                  const tempoRestanteEmMinutos = distanciaRestante * tempoPorEstacao;
  
                  
                  const horaAtual = new Date();
                  const horaChegada = new Date(horaAtual.getTime() + tempoRestanteEmMinutos * 60000);
                  const horaFormatada = horaChegada.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  
                  
                  infoDiv.innerHTML += `Frota D ${trem.numero}: Chegará em ${tempoRestanteEmMinutos.toFixed(2)} minuto(s) (às ${horaFormatada})<br>`;
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
      controleTrens.adicionarTrem(8, 60, 14); 
      controleTrens.adicionarTrem(9, 60, 16); 
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

  // BOTÕES

  async function enviarFeedback(feedback) {
    try {
        const resposta = await fetch('/api/feedback', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ feedback }),
        });

        if (resposta.ok) {
            alert('Feedback enviado com sucesso!');
        } else {
            alert('Erro ao enviar feedback. Tente novamente.');
        }
    } catch (erro) {
        console.error('Erro ao enviar feedback:', erro);
        alert('Erro ao enviar feedback. Verifique sua conexão.');
    }
}

// Adiciona evento de clique aos botões
document.querySelectorAll('.feedback-btn').forEach(botao => {
    botao.addEventListener('click', () => {
        const feedback = botao.dataset.feedback; 
        enviarFeedback(feedback); 
    });
});

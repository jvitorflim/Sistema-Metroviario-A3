
// Dados simulados de notificações (você pode integrar isso com um backend futuramente)
const notifications = [
    { id: 1, message: "Usuário pediu ajuda na Simulação do Metrô.", screen: "Simulação do Metrô" },
    { id: 2, message: "Erro reportado na tela Gerenciamento de Trens.", screen: "Gerenciamento de Trens" },
];

// Selecionar o contêiner de notificações
const notificationsContainer = document.getElementById("notifications");

// Renderizar as notificações na tela
function renderNotifications() {
    notificationsContainer.innerHTML = ""; // Limpar notificações anteriores
    notifications.forEach(notification => {
        const notificationElement = document.createElement("div");
        notificationElement.className = "notification";
        notificationElement.innerHTML = `
            <div class="content">
                <p>${notification.message}</p>
                <small>Redirecionar para: ${notification.screen}</small>
            </div>
            <button id="btn_devagar" onclick="sendNotification(${notification.id})">Enviar</button>
        `;
        notificationsContainer.appendChild(notificationElement);
    });
}



renderNotifications();


// Dados simulados de notificações (você pode integrar isso com um backend futuramente)
const notifications = [
    { id: 1, message: "Metro Devagar na Linha Azul " },
    { id: 2, message: "Metro Lotado na Linha Verde" },
    { id: 3, message: "Metro Vazio na Linha Vermelha" },
    { id: 4, message: "Metro Quebrado na Linha Amarela" }
];

// Renderizar as notificações na tela
const notificationsContainer = document.getElementById("notifications");

        function renderNotifications() {
            notificationsContainer.innerHTML = ""; // Limpar notificações anteriores
            notifications.forEach(notification => {
                const notificationElement = document.createElement("div");
                notificationElement.className = "notification";
                notificationElement.innerHTML = `
                    <div class="content">
                        <p>${notification.message}</p>
                    </div>
                    <button type="button" onclick="sendNotification(${notification.id})">Enviar</button>
                `;
                notificationsContainer.appendChild(notificationElement);
            });
        }

        function sendNotification(notificationId) {
            const notification = notifications.find(n => n.id === notificationId);
            if (notification) {
                const ocorrencias = JSON.parse(localStorage.getItem("ocorrencias")) || [];
                ocorrencias.push(notification.message);
                localStorage.setItem("ocorrencias", JSON.stringify(ocorrencias));
                alert("Notificação enviada!");
                // Redirecionar para a página principal
                window.location.href = "../principal/telaprincipal.html";
            }
        }
        




renderNotifications();

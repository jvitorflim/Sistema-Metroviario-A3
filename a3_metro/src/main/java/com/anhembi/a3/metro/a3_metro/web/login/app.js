document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('login-form');
    const collaboratorForm = document.getElementById('collaborator-form');
    const registerForm = document.getElementById('register-form');

    const collaboratorLink = document.getElementById('collaborator-link');
    const registerLink = document.getElementById('register-link');
    const loginLink = document.getElementById('login-link');
    const backLoginLink = document.getElementById('back-login-link');

    const showError = (message) => {
        const resposta = document.getElementById('resposta');
        resposta.textContent = message;
        resposta.style.color = 'red';
    };

    const showSuccess = (message) => {
        const resposta = document.getElementById('resposta');
        resposta.textContent = message;
        resposta.style.color = 'green';
    };

    const clearMessage = () => {
        const resposta = document.getElementById('resposta');
        resposta.textContent = '';
    };

    const isValidEmail = (email) => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

    const validateFields = (email, password) => {
        if (!email) {
            showError('Por favor, insira seu email.');
            return false;
        }
        if (!isValidEmail(email)) {
            showError('Por favor, insira um email válido.');
            return false;
        }
        if (!password) {
            showError('Por favor, insira sua senha.');
            return false;
        }
        if (password.length < 8) {
            showError('A senha deve ter pelo menos 8 caracteres.');
            return false;
        }
        clearMessage();
        return true;
    };

    document.getElementById('btn_entrar').addEventListener('click', (e) => {
        e.preventDefault();
        const email = document.getElementById('emailUsuarioLogin').value.trim();
        const password = document.getElementById('senhaUsuarioLogin').value.trim();
        if (validateFields(email, password)) {
            showSuccess('Login realizado com sucesso!');
            setTimeout(() => (window.location.href = '../principal/telaprincipal.html'), 1000);
        }
    });

    document.getElementById('btn_entrarTecnico').addEventListener('click', (e) => {
        e.preventDefault();
        const email = document.getElementById('emailUsuarioTecnico').value.trim();
        const password = document.getElementById('senhaUsuarioTecnico').value.trim();
        if (validateFields(email, password)) {
            showSuccess('Login de colaborador realizado com sucesso!');
            setTimeout(() => (window.location.href = '../telaColaborador/index.html'), 1000);
        }
    });

    document.getElementById('btn_cadastrar').addEventListener('click', (e) => {
        e.preventDefault();
        const email = document.getElementById('emailUsuarioCadastro').value.trim();
        const password = document.getElementById('senhaUsuarioCadastro').value.trim();
        if (validateFields(email, password)) {
            showSuccess('Cadastro realizado com sucesso!');
        }
    });

    collaboratorLink.addEventListener('click', (e) => {
        e.preventDefault();
        loginForm.classList.add('hidden');
        collaboratorForm.classList.remove('hidden');
    });

    registerLink.addEventListener('click', (e) => {
        e.preventDefault();
        loginForm.classList.add('hidden');
        registerForm.classList.remove('hidden');
    });

    loginLink.addEventListener('click', (e) => {
        e.preventDefault();
        collaboratorForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    });

    backLoginLink.addEventListener('click', (e) => {
        e.preventDefault();
        registerForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    });
});

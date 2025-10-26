function validateEmail() {
    const emailInput = document.getElementById('email-input').value;

    const feedbackElementEmail = document.getElementById('errorFeedbackEmail');

    const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    let ehFormValido = true;

    feedbackElementEmail.textContent = '';

    if (!emailRegex.test(emailInput)) {
        feedbackElementEmail.textContent = 'Formato de e-mail inválido.';
        ehFormValido = false;
    }

    return ehFormValido;
}

function validatePhone() {
    const telefoneInput = document.getElementById('phone-input').value;

    const feedbackElementTelefone = document.getElementById('errorFeedbackPhone');

    const telefoneRegex = /^\(?[0-9]{2}\)? ?[0-9]{5}-?[0-9]{4}$/;

    let ehFormValido = true;

    feedbackElementTelefone.textContent = '';

    if (!telefoneRegex.test(telefoneInput)) {
        feedbackElementTelefone.textContent = 'Formato de telefone inválido.';
        ehFormValido = false;
    }

    return ehFormValido;
}
function validateForm() {
    const emailInput = document.getElementById('email-input').value;
    const feedbackElement = document.getElementById('errorFeedback');
    const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if (emailRegex.test(emailInput)) {
        feedbackElement.textContent = '';
    } else {
        feedbackElement.textContent = 'Formato de email inválido.';
    }
}
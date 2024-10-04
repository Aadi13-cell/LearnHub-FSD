document.getElementById('signup-form').addEventListener('submit', function(event) {
    event.preventDefault();

    // Clear previous error messages
    const errorMessage = document.getElementById('error-message');
    errorMessage.textContent = '';

    // Get form values
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const address = document.getElementById('address').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirm-password').value;

    // Validate form values
    if (!validateEmail(email)) {
        errorMessage.textContent = 'Please enter a valid email address.';
        return;
    }

    if (password !== confirmPassword) {
        errorMessage.textContent = 'Passwords do not match.';
        return;
    }

    // Submit the form (simulated here as an alert for demonstration)
    alert('Form submitted successfully!');
    document.getElementById('signup-form').submit();
});

function validateEmail(email) {
    const re = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return re.test(String(email).toLowerCase());
}

document.addEventListener('DOMContentLoaded', function() {
    // Validation côté client
    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });

    // Auto-focus sur le premier champ invalide
    const invalidFields = document.querySelectorAll('.is-invalid');
    if (invalidFields.length > 0) {
        invalidFields[0].focus();
    }
});
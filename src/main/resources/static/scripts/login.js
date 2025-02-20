// Mantener la modal abierta si hay error
document.addEventListener("DOMContentLoaded", function () {
    const errorMessage = document.querySelector(".error-message");
    if (errorMessage != null) {
        document.getElementById("registerModal").style.display = "flex";
        if(
            errorMessage.textContent.trim() === "*El nombre de usuario ya está en uso" ||
            errorMessage.textContent.trim() === "*El nombre de usuario no puede contener espacios en blanco" ||
            errorMessage.textContent.trim() === "**El nombre de usuario debe tener un mínimo de 4 caracteres y un máximo de 15"
        ) {
            document.getElementById("username-register").classList.add("input-error");
        }
        else if(
            errorMessage.textContent.trim() === "*El correo electrónico ya está en uso" ||
            errorMessage.textContent.trim() === "*El email no puede contener espacios en blanco"
        ) {
            document.getElementById("email-register").classList.add("input-error");
        }
        else if(
            errorMessage.textContent.trim() === "*No has repetido la misma contraseña"
        ) {
            document.querySelectorAll(".password").forEach(function(element) {
                element.classList.add("input-error");
            });
        }
    }
});

// Obtener elementos
const modal = document.getElementById('registerModal');
const openModalBtn = document.getElementById('openModalBtn');
const closeModalBtn = document.getElementById('closeModalBtn');
const registerForm = document.getElementById('registerForm');
const successMessage = document.getElementById('successMessage');
const errorMessage = document.getElementById('errorMessage');

// Mostrar la modal
openModalBtn.addEventListener('click', () => {
    modal.style.display = 'flex';
});

// Cerrar la modal
closeModalBtn.addEventListener('click', () => {
    modal.style.display = 'none';
});

/*
// Validar el formulario y manejar la acción de registro
registerForm.addEventListener('submit',  () => {
    e.preventDefault(); // Evitar que la página se recargue

    // Obtener los valores de los campos
    const username = document.getElementById('username-register').value;
    const email = document.getElementById('email-register').value;
    const password = document.getElementById('password-register').value;
  //  const confirmPassword = document.getElementById('confirmPassword').value;



});

*/
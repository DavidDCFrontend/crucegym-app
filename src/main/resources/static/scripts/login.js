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
document.addEventListener("DOMContentLoaded", () => {
    const menuToggle = document.querySelector(".site-header__menu-toggle");
    const navMenu = document.querySelector(".site-header__nav");
    const overlay = document.querySelector(".overlay");
    const exerciseCards = document.querySelectorAll('.exercise-card');


    // Abrir el menú y mostrar el overlay
    menuToggle.addEventListener("click", () => {
        navMenu.classList.toggle("site-header__nav--open");
        overlay.classList.toggle("overlay--active");
    });

    // Cerrar el menú al hacer clic fuera del menú (en el overlay)
    overlay.addEventListener("click", () => {
        navMenu.classList.remove("site-header__nav--open");
        overlay.classList.remove("overlay--active");
    });

    // Envío de datos a 'Exercise'
    exerciseCards.forEach(card => {
        card.addEventListener('click', function () {
            //Obtener datos ejercicio
            const exerciseId = card.getAttribute('data-id');
            const exerciseName = card.getAttribute('data-name');

            // Asignar los valores al formulario oculto
             document.getElementById('idExercise').value = exerciseId;
             document.getElementById('exerciseName').value = exerciseName;

             // Enviar el formulario
             document.getElementById('exerciseForm').submit();
        })
    })
});


// Cerrar el menú al pulsar la tecla Esc para mejorar la accesibilidad
document.addEventListener("keydown", (event) => {
    if (event.key === "Escape" && navMenu.classList.contains("site-header__nav--open")) {
        navMenu.classList.remove("site-header__nav--open");
        overlay.classList.remove("overlay--active");
    }
});



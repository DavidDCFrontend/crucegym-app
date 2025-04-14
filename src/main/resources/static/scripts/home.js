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


const isMobile = 'ontouchstart' in window || navigator.maxTouchPoints > 0;

document.addEventListener("DOMContentLoaded", function () {
    const videos = document.querySelectorAll(".hover-video");

    videos.forEach(function (video) {
        // Evento para dispositivos de escritorio (hover)
        video.addEventListener("mouseenter", function () {
            video.play(); // Reproduce el video al hacer hover
        });

        video.addEventListener("mouseleave", function () {
            video.pause(); // Pausa el video al salir del hover
            video.currentTime = 0; // Reinicia el video al principio
        });

        // Evento para dispositivos móviles (touch)
        if ('ontouchstart' in window) { // Verifica si el dispositivo es táctil
            video.addEventListener("touchstart", function () {
                if (video.paused) {
                    video.play(); // Reproduce el video al tocar
                } else {
                    video.pause(); // Pausa el video si ya está reproduciéndose
                    video.currentTime = 0; // Reinicia el video al principio
                }
            });
        }
    });
});

/*
document.addEventListener("DOMContentLoaded", function () {
    const videos = document.querySelectorAll(".hover-video");

    videos.forEach(function (video) {
        video.addEventListener("mouseenter", function () {
            video.play(); // Reproduce el video al hacer hover
        });

        video.addEventListener("mouseleave", function () {
            video.pause(); // Pausa el video al salir del hover
            video.currentTime = 0; // Opcional: Reinicia el video al principio
        });
    });
});

*/


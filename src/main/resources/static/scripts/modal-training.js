/* CURRENT SESSION MODAL */
function openTrainingModal() {
    // Mostrar el modal
    document.getElementById("trainingModal").style.display = "block";

    // Cargar el contenido del modal desde el servidor
    fetch('/current-training')
      .then(response => {
          if (!response.ok) {
              throw new Error(`Error ${response.status}: ${response.statusText}`);
          }
          return response.text();
      })
      .then(html => {
          // Reemplazar solo el contenido dentro del modal (no el modal completo)
          document.getElementById("training-content").innerHTML = html;
      })
      .catch(error => {
          console.error('Error al cargar entrenamiento:', error);
          document.getElementById("training-content").innerHTML = "<p>Error al cargar entrenamiento.</p>";
      });
}

function closeTrainingModal() {
    // Ocultar el modal
    document.getElementById("trainingModal").style.display = "none";
}


/* HISTORY TRAINING TRAINING FORM */
function openHistoryTrainingForm() {
    document.getElementById("historyModal").style.display = "block";
}

const historyTrainingForm = document.getElementById("history-training-form");

historyTrainingForm.addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(historyTrainingForm);

    fetch('/get-training', {
        method: 'POST',
        body: formData,
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        return response.text();
    })
    .then(html => {
        document.getElementById("history-content").innerHTML = html;
    })
    .catch(error => {
        console.error('Error al cargar entrenamiento:', error);
        document.getElementById("history-content").innerHTML = "<p>Error al cargar entrenamiento.</p>";
    });
})

function closeHistoryModal() {
    document.getElementById("historyModal").style.display = "none";
}
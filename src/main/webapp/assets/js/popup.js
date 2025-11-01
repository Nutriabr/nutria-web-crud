document.addEventListener("DOMContentLoaded", function () {
    deletarPorId();
    deletarPorEmpresa();
    encerrarSessao();
});

function deletarPorId() {
    const deletePopupOverlay = document.getElementById("delete-popup-overlay");
    const cancelBtn = document.getElementById("cancel-delete-btn");
    const inputId = document.getElementById("input-id")
    const idForm = document.getElementById("delete-id");
    const deleteForm = document.getElementById("delete-form");

    const deleteButtons = document.querySelectorAll(".btn-delete");

    if (!deletePopupOverlay) {
        return;
    }

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const dataId = this.getAttribute("data-id");

            idForm.textContent = dataId;
            inputId.value = dataId;

            deletePopupOverlay.style.display = "flex";
        });
    });

    cancelBtn.addEventListener("click", function () {
        deletePopupOverlay.style.display = "none";
        deleteForm.reset();
    });

    deletePopupOverlay.addEventListener("click", function (event) {
        if (event.target === deletePopupOverlay) {
            deletePopupOverlay.style.display = "none";
            deleteForm.reset();
        }
    });
}

function deletarPorEmpresa() {
    const deletePopupOverlay = document.getElementById("delete-all-popup-overlay");
    const deleteAllform = document.getElementById("delete-all-form");
    const empresaNome = document.getElementById("empresa-nome");
    const inputEmpresa = document.getElementById("input-empresa");
    const cancelBtn = document.getElementById("cancel-delete-all-btn");
    const deleteButton = document.querySelectorAll(".deletar-por-empresa");

    if (!deletePopupOverlay) {
        return;
    }

    deleteButton.forEach(button => {
        button.addEventListener("click", function () {
            const opcao = document.getElementById("selectEmpresa").value;

            empresaNome.value = opcao;

            inputEmpresa.value = opcao;

            deletePopupOverlay.style.display = "flex";
        });
    });

    cancelBtn.addEventListener("click", function () {
        deletePopupOverlay.style.display = "none";
        deleteForm.reset();
    });

    deletePopupOverlay.addEventListener("click", function (event) {
        if (event.target === deletePopupOverlay) {
            deletePopupOverlay.style.display = "none";
            deleteAllform.reset();
        }
    });
}

function encerrarSessao() {
    const endSessionPopupOverlay = document.getElementById("end-session-popup-overlay");
    const endSessionForm = document.getElementById("end-session-form");
    const cancelBtn = document.getElementById("cancel-btn");
    const endSessionButton = document.querySelectorAll(".btn-end-sesion");

    if (!endSessionPopupOverlay) {
        return;
    }

    endSessionButton.forEach(button => {
        button.addEventListener("click", function () {
            console.log("Botão de logout clicado!");
            endSessionPopupOverlay.style.display = "flex";
        });
    });

    cancelBtn.addEventListener("click", function () {
        endSessionPopupOverlay.style.display = "none";
        endSessionForm.reset();
    });

    endSessionPopupOverlay.addEventListener("click", function (event) {
        if (event.target === endSessionPopupOverlay) {
            endSessionPopupOverlay.style.display = "none";
            endSessionForm.reset();
        }
    });
}
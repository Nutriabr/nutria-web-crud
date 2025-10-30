document.addEventListener("DOMContentLoaded", function () {
    deletarPorId();
    deletarPorEmpresa();
});

function deletarPorId() {
    const deletePopupOverlay = document.getElementById("delete-popup-overlay");
    const cancelBtn = document.getElementById("cancel-delete-btn");
    const inputId = document.getElementById("input-id")
    const idForm = document.getElementById("delete-id");
    const deleteForm = document.getElementById("delete-form");

    const deleteButtons = document.querySelectorAll(".btn-delete");

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
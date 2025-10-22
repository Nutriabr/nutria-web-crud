document.addEventListener("DOMContentLoaded", function () {
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
});
document.addEventListener('DOMContentLoaded', function () {
    const deletePopupOverlay = document.getElementById('delete-popup-overlay');
    const cancelBtn = document.getElementById('cancel-delete-btn');
    const adminNameElement = document.getElementById('delete-admin-name');
    const adminIdInput = document.getElementById('delete-admin-id');
    const deleteForm = document.getElementById('delete-form');

    const deleteButtons = document.querySelectorAll(".btn-delete");

    deleteButtons.forEach(button => {
        button.addEventListener("click", function () {
            const adminId = this.getAttribute("data-id");
            const adminName = this.getAttribute("data-name");

            adminIdInput.value = adminId;
            adminNameElement.textContent = adminName;

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
document.addEventListener("DOMContentLoaded", function () {
    deletarPorId();
    deletarPorFiltro();
    encerrarSessao();
    toggleFiltro();
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

function toggleFiltro() {
    const btnFilter = document.getElementById("btn-filter");
    const deletarPopup = document.querySelector(".delete-popup");

    if (!btnFilter || !deletarPopup) {
        return;
    }

    deletarPopup.style.display = "none";

    const newBtnFilter = btnFilter.cloneNode(true);
    btnFilter.parentNode.replaceChild(newBtnFilter, btnFilter);

    newBtnFilter.addEventListener("click", function (event) {
        event.preventDefault();
        event.stopPropagation();

        console.log("Clicou no filtro");
        console.log("Display:", deletarPopup.style.display);

        if (deletarPopup.style.display === "flex") {
            deletarPopup.style.display = "none";
        } else {
            deletarPopup.style.display = "flex";
        }

    }, { once: false });

    document.addEventListener("click", function (event) {
        const isClickInsidePopup = deletarPopup.contains(event.target);
        const isClickOnButton = newBtnFilter.contains(event.target);

        if (!isClickInsidePopup && !isClickOnButton && deletarPopup.style.display === "flex") {
            deletarPopup.style.display = "none";
        }
    });
}
function deletarPorFiltro() {
    const deletePopupOverlay = document.getElementById("delete-all-popup-overlay");
    const deleteAllform = document.getElementById("delete-all-form");
    const opcaoNome = document.getElementById("opcao-nome");
    const inputOpcao = document.getElementById("input-opcao");
    const cancelBtn = document.getElementById("cancel-delete-all-btn");
    const deleteButton = document.querySelectorAll(".deletar-por-opcao");

    if (!deletePopupOverlay) {
        return;
    }

    deleteButton.forEach(button => {
        button.addEventListener("click", function () {
            const opcao = document.getElementById("selectOpcao").value;

            opcaoNome.textContent = opcao;
            inputOpcao.value = opcao;

            deletePopupOverlay.style.display = "flex";
        });
    });

    cancelBtn.addEventListener("click", function () {
        deletePopupOverlay.style.display = "none";
        deleteAllform.reset();
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
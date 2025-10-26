const formBusca = document.getElementById('form-busca');
const busca = document.getElementById("input-busca");

busca.addEventListener("input", () => {
    if (busca.value.trim() === "") {
        formBusca.submit();
    }
});
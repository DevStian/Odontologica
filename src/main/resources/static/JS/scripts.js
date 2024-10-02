function showBadCredentialsMessage() {
  const form = document.getElementById("Debajo");
  const alertHTML = `<div class="alert alert-danger" role="alert">Bad credentials</div>`;
  form.insertAdjacentHTML("afterend", alertHTML);
}

const ShowSign = (cual) => {
  const Tarjeta = document.getElementById(cual);
  if (Tarjeta.classList.contains("d-none")) {
    Tarjeta.classList.remove("d-none");
    Tarjeta.classList.add("d-flex");
  } else {
    Tarjeta.classList.add("d-none");
    Tarjeta.classList.remove("d-flex");
  }
};

function cargar(queSePide, DondeSePone) {
  let ruta = "/index/" + queSePide;
  let aca = document.getElementById(DondeSePone);
  console.log(ruta + "__" + DondeSePone);
  if (aca.innerHTML.trim() === "") {
    fetch(ruta)
      .then((response) => response.text())
      .then((html) => {
        aca.innerHTML = html;
        ShowSign(DondeSePone);
      })
      .catch((e) => console.error("Error al cargar el HTML: ", e));
  } else {
    ShowSign(DondeSePone);
  }
}

function GetValuesOfInput(DeCual) {
  const Form = document.getElementById(DeCual);
  let name = "";
  let surname = "";
  if (DeCual == "Tarjeta2") {
    name = Form.querySelector("#Name").value;
    surname = Form.querySelector("#SurName").value;
  }
  const email = Form.querySelector("#email").value;
  const password = Form.querySelector("#password").value;
  return {
    name: name,
    surname: surname,
    email: email,
    password: password,
  };
}

function enviarDatos(Tipo, DeCual) {
  const datos = GetValuesOfInput(DeCual);
  fetch(Tipo, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(datos), // Convertir los datos en JSON
  })
    .then((response) => response.text()) // Espera la respuesta en formato texto
    .then((data) => {
      console.log("Respuesta del servidor: ", data);
      // window.location.href = "/index";
    })
    .catch((e) => {
      console.error("Error: ", e); // Maneja errores en la consola del navegador
    });
}

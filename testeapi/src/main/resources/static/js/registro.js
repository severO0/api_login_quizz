document
  .getElementById("registro-form")
  .addEventListener("submit", function (event) {
    event.preventDefault(); 

    const nome = document.getElementById("nome").value.trim();
    const email = document.getElementById("email").value.trim();
    const contato = document.getElementById("contato").value.trim();
    const senha = document.getElementById("senha").value.trim();
    const senha2 = document.getElementById("senha2").value.trim();
    const ra = document.getElementById("ra").value.trim();
    const avisoSenha = document.getElementById("senha-aviso");

    if (!nome || !email || !contato || !senha || !senha2 || !ra) {
      alert("Por favor, preencha todos os campos!");
      return;
    }

    if (senha.length < 5) {
      alert("A senha deve ter no mínimo 5 caracteres!");
      return;
    }

    if (senha !== senha2) {
      avisoSenha.style.display = "block";
      return;
    } else {
      avisoSenha.style.display = "none";
    }

    fetch('http://localhost:8082/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        nome: nome,
        email: email,
        telefone: contato,
        registroAcademico: ra,
        senha: senha
      })
    })
    .then(response => {
      if (response.status === 201) {
        location.href = '/html/login.html'; 
      } else {
        return response.json().then(err => {
          throw new Error(err.message || 'Erro no registro.');
        });
      }
    })
    .catch(error => {
      console.error('Erro ao registrar:', error);
      alert('Erro ao tentar registrar: ' + error.message);
    });
  });

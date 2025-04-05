document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector(".btn-login");
  
    loginForm.addEventListener("click", function (event) {
      event.preventDefault(); 
  
      const registroAcademico = document.getElementById("registroAcademico").value.trim();
      const senha = document.getElementById("senha").value.trim();
  
      if (!registroAcademico || !senha) {
        alert("Por favor, preencha todos os campos.");
        return;
      }
  
      fetch('http://localhost:8082/api/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          registroAcademico: registroAcademico,
          password: senha
        })
      })
        .then(response => {
          if (response.status === 200) {
            location.href = '/home/home.html';

          } else {
            return response.json().then(err => {
              throw new Error(err.message || 'Login falhou.');
            });
          }
        })
        .catch(error => {
          console.error('Erro ao fazer login:', error);
          alert('Erro ao tentar fazer login: ' + error.message);
        });
    });
  });
  
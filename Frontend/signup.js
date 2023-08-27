document.getElementById("register-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const email = document.getElementById("email").value;
    const contact = document.getElementById("contact").value;

    const data = {
        username,
        password,
        email,
        contact
    };

    const apiUrlRegister = "http://localhost:8081/api/auth/register";

    fetch(apiUrlRegister , {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
    .then(response => response)
    .then(responseData => {
        if(responseData.status === 200){
            alert(`User Registered Successfully`);
            window.location.href ='./dashboard.html';
        }
        else{
            alert(`User already exists`);
        }
        
    })
    .catch(error => {
        console.error("Error:", error);
    });
});
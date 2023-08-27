document.getElementById("login-form").addEventListener("submit", function(event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    console.log("User name", username);
    console.log("passwword", password);

    const data = {
        username: username,
        password: password
    };

    // const corsProxyUrl = "https://cors-anywhere.herokuapp.com/"; // Example cors-anywhere URL
    const apiUrl = "http://localhost:8081/api/auth/login";

    fetch(apiUrl , {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(data)
    })
    .then(response => response)
    .then(responseData => {
        console.log("response", responseData);
        // Handle the API response here
        if(responseData.status === 200){
            alert(`User Login Successful`);
            window.location.href ='./dashboard.html';
        }
        else{
            alert(`Invalid login credentials`);
        }
       

        // You can perform actions based on the response, such as showing success or error messages.
    })
    .catch(error => {
        console.error("Error:", error);
    });
});






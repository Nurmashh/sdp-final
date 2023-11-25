const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
    e.preventDefault();
    const username = loginForm.username.value;
    const password = loginForm.password.value;

    // Check that the username and password have been entered
    if (username.trim() === "" || password.trim() === "") {
        loginErrorMsg.innerText = "Please enter your username and password.";
        loginErrorMsg.style.opacity = 1;
    } else {
       // We always display a successful login message
        alert("You have successfully logged in.");
        location.reload();
    }
});

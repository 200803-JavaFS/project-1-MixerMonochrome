const url = "http://localhost:8080/project0/";

document.getElementById("loginbtn").addEventListener("click", loginFunc);

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username : usern,
        password : userp
    }

    let resp = await fetch(url+"login", {
        method: 'POST',
        body: JSON.stringify(user)
    })

    if(resp.status===200){
        console.log(resp);
        document.getElementById("login-row").innerText = "Logged in!";
		
    } else {
        document.getElementById("login-row").innerText = "Login failed!";
    }
}

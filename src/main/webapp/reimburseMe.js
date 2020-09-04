const url = "http://localhost:8080/project0/";

document.getElementById("loginbtn").addEventListener("click", loginFunc);
document.getElementById("tester").addEventListener("click",TestFunc);

async function TestFunc(){
    let resp = await fetch(url + "test",{
        method:'POST',
        credentials: 'include'
    })

    if(resp.status === 200){
        console.log(resp);
    }
}

async function loginFunc() {
    let usern = document.getElementById("username").value;
    let userp = document.getElementById("password").value;

    let user = {
        username : usern,
        password : userp
    }
    

    let resp = await fetch(url+"login", {
        method: 'POST',
        credentials: 'include',
        body: JSON.stringify(user)
    })

    if(resp.status===200){
        console.log(resp);
        let data = await resp.json();
        if (data == 1){
        	window.location = "viewPastTickets.html";
        }
        else{
        	window.location = "viewReimb.html";
        }
		
    } else {
    	console.log(resp);
        document.getElementById("login-row").innerText = "Login failed!";
    }
}

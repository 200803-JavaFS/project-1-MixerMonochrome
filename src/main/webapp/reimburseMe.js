const url = "http://localhost:8080/project0/";

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
        sessionStorage.setItem("userRole",data);
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

async function getReims(call){
    console.log("START");
	let resp = await fetch(url + call, {
		method: 'GET',
		credentials: 'include'
	})
    
    //ADD REDIRECTS IN PAGES
	if (resp.status === 200){
        let table = document.createElement("table");
        document.getElementById("allReimHere").innerHTML = "";
        document.getElementById("allReimHere").appendChild(table);
        let headers = document.createElement("thead");
        table.appendChild(headers);
        let tId = document.createElement("td");
        let tAmn = document.createElement("td");
        let tDes = document.createElement("td");
        let tRecp = document.createElement("td");
        let tStat = document.createElement("td");
        let tType = document.createElement("td");
        let tAuth = document.createElement("td");
        let tSubm = document.createElement("td");
        let tResl = document.createElement("td");
        let tResd = document.createElement("td");
        tId.innerText = "Ticket ID";
        tAmn.innerText = "Amount";
        tDes.innerText = "Description";
        tRecp.innerText = "Receipt";
        tStat.innerText = "Status";
        tType.innerText = "Type";
        tAuth.innerText = "Author";
        tSubm.innerText = "Submitted";
        tResl.innerText = "Resolver";
        tResd.innerText = "Resolved";
        headers.appendChild(tId);
        headers.appendChild(tAmn);
        headers.appendChild(tDes);
        headers.appendChild(tRecp);
        headers.appendChild(tStat);
        headers.appendChild(tType);
        headers.appendChild(tAuth);
        headers.appendChild(tSubm);
        headers.appendChild(tResl);
        headers.appendChild(tResd);
		let data = await resp.json();
		console.log(data);
		for (let reim of data){
            console.log(reim.reimbId);
            let row = document.createElement("tr");
            let id = document.createElement("td");
            let amnt = document.createElement("td");
            let desc = document.createElement("td");
            let recp = document.createElement("td");
            let stat = document.createElement("td");
            let type = document.createElement("td");
            let auth = document.createElement("td");
            let subm = document.createElement("td");
            let reslvr = document.createElement("td");
            let reslvd = document.createElement("td");
            id.innerText = reim.reimbId;
            amnt.innerText =  reim.reimbAmnt;
            subm.innerText =  reim.reimbSubbed;
            reslvd.innerText = reim.reimbResolved;
            desc.innerText = reim.reimbDesc;
            if(reim.reimbRecpt != null){
                console.log(typeof reim.reimbRecpt);
                let blob = document.createElement("img");
                blob.setAttribute("href",URL.createObjectURL(new Blob([''],{type: 'image/png'})));
                recp.appendChild(blob);
            }
            type.innerText = reim.type;
            auth.innerText = reim.author;
            reslvr.innerText = reim.resolver;
            stat.innerText = reim.status;
            if(reim.status == "PENDING"){
                row.setAttribute("class","pending");
            }else if(reim.status == "APPROVED"){
                row.setAttribute("class","approved");
            }else if(reim.status == "DENIED"){
                row.setAttribute("class","denied");
            }else{
                stat.innerText == "WHAT HAPPENED HOW DID I GET HERE?????";
                row.setAttribute("class","allpowerful")
            }
            row.appendChild(id);
            row.appendChild(amnt);
            row.appendChild(desc);
            row.appendChild(recp);
            row.appendChild(stat);
            row.appendChild(type);
            row.appendChild(auth);
            row.appendChild(subm);
            row.appendChild(reslvr);
            row.appendChild(reslvd);
            table.appendChild(row);
        }
	}else if(resp.status === 401){
        if(sessionStorage.getItem("userRole") == 1){
            window.location = "viewPastTickets.html";
        }
        else if(sessionStorage.getItem("userRole") == 2){
            window.location = "viewReimb.html";
        }
        else{
             window.location = "login.html";
        }
    }
}

function filterReims(){
    let stats = document.forms[0];
    for(i=0;i<stats.length;i++){
        let ticks = document.getElementsByClassName(stats[i].value);
        console.log(ticks);
        if(stats[i].checked == false){
            console.log(stats[i].value + " FALSE");
            for(j=0;j<ticks.length;j++){
                ticks[j].hidden = true;
            }
        }
        else{
            console.log(stats[i].value + " TRUE");
            for(j=0;j<ticks.length;j++){
                ticks[j].hidden = false;
            }
        }
    }
}

//ADDREIM FUNC!
async function submitNew(){
    let resp = await fetch (url + "request",{
        method : 'POST',
        body : JSON.stringify({
            reimbAmnt : document.getElementById("reimbAmnt").value,
            reimbDesc : document.getElementById("reimbDesc").value,
            reimbRecpt : document.getElementById("reimbRecpt").value,
            typeId : document.getElementById("typeId").value
        }),
        credentials : 'include'
    })
    if(resp.status === 200){
        getReims('viewPast');
        document.getElementById("appStatus").innerText = "Account Submitted";
    }
    else{
        document.getElementById("appStatus").innerText = "Submission Failed";
    }
}

//STRETCH - SUBMIT PHOTO FUNC (HELPER)

//APPDEN REIM FUNC!
async function appDen(){
    let resp = await fetch(url + "appden", {
        method : 'POST',
        body : JSON.stringify({
            aId : document.getElementById("aId").value,
            sId : document.getElementById("sId").value
        }),
        credentials: 'include'
    })
    if (resp.status === 200){
        getReims('viewAll');
        document.getElementById("updateStatus").innerText = "Accounts Updated";
    }
    else if (resp.status == 401){
        document.getElementById("updateStatus").innerText = "Non-Pending Account Cannot Be Altered";
    }
    else{
        document.getElementById("updateStatus").innerText = "Update Failed";
    }
}

//LOGOUT!
async function logout(){
    let resp = await fetch(url + "logout",{
        method: 'POST',
        credentials: 'include'
    })

    if (resp.status === 200){
        window.location = "login.html";
        sessionStorage.removeItem("userRole");
    }
}


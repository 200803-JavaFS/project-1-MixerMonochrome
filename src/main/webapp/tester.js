const url = "http://localhost:8080/project0/";
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
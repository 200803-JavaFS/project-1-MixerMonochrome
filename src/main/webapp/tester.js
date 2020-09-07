const url = "http://localhost:8080/project0/";

async function TestFunc(){

	console.log(window.sessionStorage);
	console.log(document.cookie);
    let resp = await fetch(url + "test",{
        method:'POST',
        credentials: 'include'
    })

    if(resp.status === 200){
        console.log(resp);
    }

	function filterReims(){

	}
}
function sendJSON() {

    let result = document.querySelector('.result'); 
    let name = document.querySelector('#doc_text');

    // Creating a XHR object 
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/";

    // open a connection 
    xhr.open("POST", url, true);

    // Set the request header i.e. which type of content you are sending 
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader('Access-Control-Allow-Origin', "http://localhost:8080");
    xhr.setRequestHeader('Access-Control-Allow-Credentials', "true");


    // window.alert(name.value)
    // Create a state change callback 
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            window.alert(json);
            }
        };

    // Converting JSON data to string 
    var data = JSON.stringify({
        "document": name.value,
        "streamName": "covid", "metadata": "",
        "serializers": [
            "medtagger"
        ]
    });

    // window.alert(data);

    // Sending data with the request 
    xhr.send(data);
} 

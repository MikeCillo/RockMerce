function GuitarValidation(){
   return valName(),valProducer(),valPrice(),valColor(),valDescription(),valDisp(),
       videoLoad();
}



function valName() {
    let name = document.getElementById("name").value;
    if (name === ""){
        document.getElementById("errorName").textContent = "UNVALID NAME "
        document.getElementById("cardName").innerText = "NAME";
        return false;
    }
    else{
        document.getElementById("errorName").textContent="√"
        document.getElementById("name").innerText=name;
    }
}

function valProducer(){
    let prod=document.getElementById("producer").value;
    if(prod===""){
        document.getElementById("errorProd").textContent="UNVALID PRODUCER"
        document.getElementById("cardProducer").innerText="PRODUCER";
        return false;
    }
    else{
        document.getElementById("errorProd").textContent="√"
        document.getElementById("cardProducer").innerText=prod;
    }
}



function valPrice() {
    let price=document.getElementById("price").value;


    if (isNaN(price)) {
        alert("PRICE CANNOT CONTAIN CHAR");
        document.getElementById("price").value = "";
        document.getElementById("errorPrice").textContent = "UNVALID PRICE";
        return false;
    }
    else if (price === ""){
        document.getElementById("errorPrice").textContent = "UNVALID PRICE";
        document.getElementById("cardPrice").innerText="PRICE";
        return false;

    }
    else if( price<=49.99){
        document.getElementById("errorPrice").textContent="MIN PRICE 50.00";
        return false;
    }

    else if( price>=50.00) {
        document.getElementById("errorPrice").textContent="√"
        document.getElementById("cardPrice").innerText="€ "+price
    }

}

function valDescription() {
    let desc = document.getElementById("description").textContent;
    if (desc.length === 0 || desc.length > 2000){
        document.getElementById("errorDescription").textContent = "INVALID DESCRIPTION"
    return false;
    }
    else{
        document.getElementById("errorDescription").textContent="√"
    }
}


function checkGuitar() {
    if(type==="electric") {
        document.getElementById("category").value = "electric";
    }
    else if(type==="classic") {
        document.getElementById("category").value = "classic";
    }
    else if(type==="semiAcoustic") {
        document.getElementById("category").value = "semiAcoustic";
    }

}


function valColor(){


    let color=document.getElementById("color").value;
    let len=document.getElementById("color").value.length;

    if(color===""|| len>20 ||len <=2) {
        document.getElementById("errorColor").textContent = "UNVALID COLOR "
        return false;
    }

    if (/\d/.test(color))
    {
        alert("COLOR CANNOT CONTAIN DIGITS")
        document.getElementById("color").value = ""
        document.getElementById("errorColor").textContent = "UNVALID COLOR "
        return false;
    }
    else if(len>=3){
        document.getElementById("errorColor").textContent="√"
    }
}



function valDisp() {
    let disp = document.getElementById("dispo").value;

    if (isNaN(disp)) {
        alert("DISPONIBILITY CANNOT CONTAIN CHAR");
        document.getElementById("disp").value = "";
        document.getElementById("errorDisp").textContent = "INVALID DISPONIBILITY";
        return false
    }
    else if (disp === ""){
        document.getElementById("errorDisp").textContent = "INVALID DISPONIBILITY";
        return false;
    }
    else if( disp<=-1){
        document.getElementById("errorDisp").textContent="MIN DISPONOBILITY IS: 0";
        return false;
    }

    else if( disp>=0) {
        document.getElementById("errorDisp").textContent="√"
    }

}

function checkGuitarVisibility() {
    if(visibility==="yes") {
        document.getElementById("visibility").value= "yes";
    }
    else if(visibility==="no") {
        document.getElementById("visibility").value = "no";
    }
}


function videoLoad(){
    let vid = document.getElementById("YTsrc");
    var x = document.getElementById("sound").value;
    var y = x.substring(x.lastIndexOf("/") + 1);
    document.getElementById("sound").value="https://www.youtube.com/embed/"+y;
    vid.src ="https://www.youtube.com/embed/"+y;
    vid.load();
}



function loadGuitarPic(){
    let imgName = document.getElementById("image").value;
    document.getElementById("image").value="GuitarsPic/"+imgName;
    document.getElementById("cardImage").src="GuitarsPic/"+imgName;

}







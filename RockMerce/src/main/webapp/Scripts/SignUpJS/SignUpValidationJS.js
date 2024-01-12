function SignUpJsValidation(){
 return valName(),valSurname(),valUsername(),valEmail(),valPassword(),
     valCountry(),valCity(),valPhone(),valAddress(),valCardNumber(),valOwner(), valCvv();
}

var ncount=0;
var sncount=0;
var usercount=0;
var emcount=0;
var pcount=0;
var couCount=0;
var cityCount=0;
var phoneCount=0;
var addressCount=0;
var cardNumberCount=0;
var ownerCount=0;
var cvvCount=0;

function pass(){
    if( ncount===1 && sncount===1 && usercount===1 && emcount===1 && pcount===1
       && couCount===1 && cityCount===1 && phoneCount===1 && addressCount===1
       && cardNumberCount===1 && ownerCount===1 && cvvCount===1){

        document.getElementById("invia").style.visibility="visible"
    }
    else {
        document.getElementById("invia").style.visibility="hidden"
    }
}


function valName() {
    let name = document.getElementById("name").value;
    var regName =  /^[a-zA-Zòàèùì ]*$/;

    if (!regName.test(name)||name.length<=2||name.length>20){
        alert("NAME MUST CONTAIN ONLY ALPHABETICAL CHARACTERS"+"\n"+"NAME LENGTH: MIN 2 - MAX 20")
        document.getElementById("errName").textContent = "INVALID NAME "
        ncount=0;
        pass()
        return false;
    }

    else{
        ncount=1;
        pass()
        document.getElementById("errName").textContent="√"
    }
}



function valSurname(){
    let sname = document.getElementById("surname").value;
    var regName =  /^[a-zA-Zòàèùì ]*$/;

    if (!regName.test(sname)||sname.length<=2||sname.length>20){
        alert("SURNAME MUST CONTAIN ONLY ALPHABETICAL CHARACTERS"+"\n"+"SURNAME LENGTH: MIN 2 - MAX 20")
        document.getElementById("errSurname").textContent = "INVALID SURNAME"
        sncount=0;
        pass()
        return false;
    }

    else{
        sncount=1;
        document.getElementById("errSurname").textContent="√"
        pass()
    }

}


function valUsername(){
    let username = document.getElementById("username").value;
    var regName =  /^[a-zA-Zòàèùì0-9 ]*$/;

    if (!regName.test(username)||username.length>20||username.length<=2){
        alert("USERNAME CANNOT CONTAIN SPECIAL CHARACTERS"+"\n"+"USERNAME LENGTH: MIN 2 - MAX 20")
        document.getElementById("errUsername").textContent = "INVALID USERNAME"
        usercount=0;
        pass()
        return false;
    }

    else{
        usercount=1;
        document.getElementById("errUsername").textContent="√"
        pass()
    }
}


function valEmail(){
    let email=document.getElementById("email").value;

    if(email.match(/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/)){
        document.getElementById("errEmail").textContent="√"
        emcount=1;
        pass()
    }
    else {
        alert("EMAIL NOT FOUNDED: PLEASE RETRY")
        document.getElementById("errEmail").textContent="INVALID EMAIL"
        emcount=0;
        pass()
        return false;
    }
}

function valPassword(){
    var pword=document.getElementById("password").value;

    if(pword.match(/^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[?!@#$%^&+=.,])(?=\S+$).{8,}/)){
        document.getElementById("errPassword").textContent="√"
        document.getElementById("pw").style.visibility="hidden"
        pcount=1;
        pass()
    }
    else {
        alert("PASSWORD DO NOT MATCH")
        document.getElementById("errPassword").textContent="INVALID PASSWORD"
        document.getElementById("pw").style.visibility="visible"
        pcount=0;
        pass()
        return false;

    }
}


function valCountry() {
    let country= document.getElementById("country").value;
    var regName =  /^[a-zA-Zòàèùì ]*$/;

    if (!regName.test(country)||country.length<=2||country.length>20){
        alert("COUNTRY MUST CONTAIN ONLY ALPHABETICAL CHARACTERS"+"\n"+"COUNTRY LENGTH: MIN 2 - MAX 20")
        document.getElementById("errCountry").textContent = "INVALID COUNTRY "
        couCount=0;
        pass()
        return false;
    }

    else{
        document.getElementById("errCountry").textContent="√"
        couCount=1;
        pass()
    }
}

function valCity(){
    let city = document.getElementById("city").value;
    var regName =  /^[a-zA-Zòàèùì ]*$/;
    if (!regName.test(city)|| city.length>=20 || city.length<=2){
        alert("CITY MUST CONTAIN ONLY ALPHABETICAL CHARACTERS"+"\n"+"CITY LENGTH: MIN 2 - MAX 20")
        document.getElementById("errCity").textContent = "INVALID CITY "
        cityCount=0;
        pass()
        return false;
    }

    else{
        document.getElementById("errCity").textContent="√"
        cityCount=1;
        pass()
    }

}


function valPhone(){
    let phone = document.getElementById("phone").value;
    if (phone.length===10 && /\d/.test(phone)){
        document.getElementById("errPhone").textContent="√"
        phoneCount=1;
        pass()
    }

    else{
        alert("PHONE MUST CONTAIN ONLY DIGITS"+"\n"+"PHONE LENGTH: 10")
        document.getElementById("errPhone").textContent = "INVALID PHONE"
        phoneCount=0;
        pass()
        return false;
    }
}



function valAddress(){
    let address = document.getElementById("address").value;
    if ( address.length<=30 && address.length>=5)  {
        document.getElementById("errAddress").textContent="√"
        addressCount=1;
        pass()
    }

    else{
        alert("ADDRESS NOT FOUNDED: PLEASE RETRY"+"\n"+"ADDRESS LENGTH: MIN 5 MAX 30")
        document.getElementById("errAddress").textContent = "INVALID ADDRESS "
        addressCount=0;
        pass()
        return false;
    }

}

function valCardNumber() {
    let num= document.getElementById("card").value;
    if (/\d/.test(num) && num.length===16){
        document.getElementById("errCardNum").textContent="√"
        cardNumberCount=1;
        pass()
    }

    else{
        alert("CARD NUMBER MUST CONTAIN ONLY DIGITS"+"\n"+"CARD NUMBER LENGTH: 16")
        document.getElementById("errCardNum").textContent = "INVALID CARD NUMBER"
        cardNumberCount=0;
        pass()
        return false;
    }
}

function valOwner(){
    let owner = document.getElementById("owner").value;
    var regName =  /^[a-zA-Zòàèùì ]*$/;
    if (!regName.test(owner) || owner.length>=30 || owner.length<=5){
        alert("OWNER CONTAIN ONLY ALPHABETICAL CHARACTERS"+"\n"+"0WNER LENGTH: MIN 5 MAX 30")
        document.getElementById("errCardOwner").textContent = "INVALID OWNER "
        ownerCount=0;
        pass()
        return false;
    }

    else{
        document.getElementById("errCardOwner").textContent="√"
        ownerCount=1;
        pass()
    }

}


function valCvv(){
    let cvv = document.getElementById("cvv").value;
    if (cvv.length===3 && /\d/.test(cvv)){
        document.getElementById("errCvv").textContent="√"
        cvvCount=1;
        pass()
    }

    else{
        alert("CVV MUST CONTAIN ONLY DIGITS"+"\n"+"DIGITS LENGTH: 3")
        document.getElementById("errCvv").textContent = "INVALID CVV "
        cvvCount=0;
        pass()
        return false;
    }

}


function DateGenerator(){
    const month = ["01","02","03","04","05","06","07","08","09","10","11","12"];

    for(i=0;i<12;i++){
        x = document.getElementById("expMonth");
        option = document.createElement("option");
        option.text = month[i];
        x.add(option);
    }
    const d = new Date();
    let year = d.getFullYear()+1;

    for( i=year;i<=(year+11);i++){
        x = document.getElementById("expYear");
        option = document.createElement("option");
        option.text = String(i);
        x.add(option);
    }

}
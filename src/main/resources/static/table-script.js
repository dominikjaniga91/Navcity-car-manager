window.addEventListener('load', function(){

    let listOfElements = document.getElementById("listOfElements");
    let mytable = listOfElements.getElementsByTagName("table")[0];
    let mytablebody = mytable.getElementsByTagName("tbody")[0];

    if(mytablebody.rows.length !== 0 ){
        document.getElementById("listOfElements").style.display ="block";
    }else{
        document.getElementById("listOfElements").style.display ="none";
    }

    let listOfElements1 = document.getElementById("listOfElements1");
    let mytable1 = listOfElements1.getElementsByTagName("table")[0];
    let mytablebody1 = mytable1.getElementsByTagName("tbody")[0];

    if(mytablebody1.rows.length !== 0 ){
        document.getElementById("listOfElements1").style.display ="block";
    }else{
        document.getElementById("listOfElements1").style.display ="none";
    }

    let listOfElements2 = document.getElementById("listOfElements2");
    let mytable2 = listOfElements2.getElementsByTagName("table")[0];
    let mytablebody2= mytable2.getElementsByTagName("tbody")[0];

    if(mytablebody2.rows.length !== 0 ){
        document.getElementById("listOfElements2").style.display ="block";
    }else{
        document.getElementById("listOfElements2").style.display ="none";
    }
});
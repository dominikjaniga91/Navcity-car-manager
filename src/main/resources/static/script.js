const sideBar = document.getElementById("sideBar");

sideBar.addEventListener('mouseover', function(){

    sideBar.style.right ="-10px";
    sideBar.style.transition = "1s"

});

sideBar.addEventListener('mouseout', function(){

    sideBar.style.right ="-150px";
    sideBar.style.transition = "1s"
});


window.addEventListener('load', function(){

    let mybody = document.getElementsByTagName("body")[0];
    let mytable = mybody.getElementsByTagName("table")[0];
    let mytablebody = mytable.getElementsByTagName("tbody")[0];

    if(mytablebody.rows.length !== 0 ){
        document.getElementById("addSomeRoutes").style.display ="none";
        document.getElementById("listOfElements").style.display ="block";

    }else{
        document.getElementById("addSomeRoutes").style.display ="block";
        document.getElementById("listOfElements").style.display ="none";
    }
});



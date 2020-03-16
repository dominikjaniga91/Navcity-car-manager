
var carReportsNav = document.getElementById("carReportsNav");
var carReports = document.getElementById("carReports");
var driversReports = document.getElementById("driversReports");
var routeReports = document.getElementById("routeReports");

carReportsNav.addEventListener('click', function(){


    reportReportsNav.className = "reportTitle";
    driverReportsNav.className = "reportTitle";
    carReportsNav.className = "onClickReport";
   
    carReports.style.display="block";
    driversReports.style.display="none";
    routeReports.style.display="none";
});


var driverReportsNav = document.getElementById("driverReportsNav");

driverReportsNav.addEventListener('click', function(){


    reportReportsNav.className = "reportTitle";
    driverReportsNav.className = "onClickReport";
    carReportsNav.className = "reportTitle";
   
    carReports.style.display="none";
    driversReports.style.display="block";
    routeReports.style.display="none";

});


var reportReportsNav = document.getElementById("reportReportsNav");

reportReportsNav.addEventListener('click', function(){

    reportReportsNav.className = "onClickReport";
    driverReportsNav.className = "reportTitle";
    carReportsNav.className = "reportTitle";

    carReports.style.display="none";
    driversReports.style.display="none";
    routeReports.style.display="block";
   
});




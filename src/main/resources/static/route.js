

window.onload = function initMap() {
    var directionsService = new google.maps.DirectionsService;
    var directionsRenderer = new google.maps.DirectionsRenderer;
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 6,
      center: {lat: 51.55, lng: 19.08}
    });
    directionsRenderer.setMap(map);

    document.getElementById('submit').addEventListener('click', function() {
      calculateAndDisplayRoute(directionsService, directionsRenderer);
    });
  };

  function calculateAndDisplayRoute(directionsService, directionsRenderer) {

    directionsService.route({
      origin: document.getElementById('start').value,
      destination: document.getElementById('end').value,
      // waypoints: waypts,
      optimizeWaypoints: true,
      travelMode: 'DRIVING'
    }, function(response, status) {
      if (status === 'OK') {
        directionsRenderer.setDirections(response);
        var route = response.routes[0];
        var summaryPanel = document.getElementById('directions-panel');
        summaryPanel.innerHTML = '';
        // For each route, display summary information.
        for (var i = 0; i < route.legs.length; i++) {
          var routeSegment = i + 1;

          summaryPanel.innerHTML +='<p><b>Departure from: </b>' + route.legs[i].start_address + '</p>';
          summaryPanel.innerHTML +='<p><b>Destination: </b>' + route.legs[i].end_address + '</p>';
          summaryPanel.innerHTML +='<p><b>Distance: </b>' + route.legs[i].distance.text + '</p>';
          summaryPanel.innerHTML +='<p><b>Estimation time: </b>' + route.legs[i].duration.text + '</p>';

          document.getElementById("distance").value = route.legs[i].distance.value;
          document.getElementById("duration").value = route.legs[i].duration.value;
        }
      } else {
        window.alert('Directions request failed due to ' + status);
      }
    });
  }

function showRouteDetails(){

    document.getElementById("directions-panel").style.display = "block";
    document.getElementById("submit").style.display = "none";
    document.getElementById("backToMenu").style.display = "none";
    document.getElementById("confirmRoute").style.display = "block";
    document.getElementById("cancelRoute").style.display = "block";
}

function showAddingRoute() {

    document.getElementById("directions-panel").style.display = "none";
    document.getElementById("submit").style.display = "block";
    document.getElementById("backToMenu").style.display = "block";
    document.getElementById("confirmRoute").style.display = "none";
    document.getElementById("cancelRoute").style.display = "none";
}


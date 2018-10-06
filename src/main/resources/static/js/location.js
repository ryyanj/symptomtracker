// A $( document ).ready() block.
    $( document ).ready(function() {
        getLocationConstant();
    });
    function getLocationConstant() {
        $.getJSON('https://ipinfo.io/geo', function(response) {
        var loc = response.loc.split(',');
        var coords = {
            latitude: loc[0],
            longitude: loc[1]
        };
        do_something(coords);
        });
    }

    // If we have a successful location update
    function do_something(coords) {
        document.getElementById("Latitude").value = coords.latitude;
        document.getElementById("Longitude").value = coords.longitude;
    }
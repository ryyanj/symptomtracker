// A $( document ).ready() block.
$(document).ready(function() {
    getChartData();
});

//var GetChartData =
function getChartData() {
    $.ajax({
        url: '/chartData',
        type: 'GET',
        dataType: 'json',
        success: function(d) {
            console.log(d.dataSets)
            new Chart(document.getElementById("line-chart"), {
              type: 'line',
              data: {
                labels: d.labels,
                datasets: d.dataSets
              },
              options: {
                title: {
                  display: true,
                  text: 'World population per region (in millions)'
                }
              }
            });
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
};
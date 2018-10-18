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
        success: function(chartData) {
            var config = {
                                       type: 'line',
                                       data: {
                                         labels: chartData.labels,
                                         datasets: chartData.dataSets
                                       },
                                       options: {
                                         title: {
                                           display: true,
                                           text: 'symptoms'
                                         }
                                       }
                                     }
            new Chart(document.getElementById("line-chart"), config);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
};


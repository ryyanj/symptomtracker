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
            var config = getChartConfig(chartData);
            new Chart(document.getElementById("line-chart"), config);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
};

function getChartConfig(chartData) {

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
            },
            scales: {
                xAxes: [{
                    type: 'time',
                    time: {
                        displayFormats: {
                            'day': 'MMM DD'
                        }
                    }
                }],
                yAxes: [{
                    display: true,
                    ticks: {
                        suggestedMin: 0, // minimum will be 0, unless there is a lower value.
                        suggestedMax: 10,
                        beginAtZero: true
                    }
                }]
            },
            responsive: true
        }
    }
    return config;

}
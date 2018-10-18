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
            chartData = {
                labels: d.labels,
                datasets: d.dataSets,
                options: {
                    scales: {
                        xAxes: [{
                            type: 'time',
                            time: {
                                displayFormats: {
                                    'millisecond': 'MMM DD',
                                    'second': 'MMM DD',
                                    'minute': 'MMM DD',
                                    'hour': 'MMM DD',
                                    'day': 'MMM DD',
                                    'week': 'MMM DD',
                                    'month': 'MMM DD',
                                    'quarter': 'MMM DD',
                                    'year': 'MMM DD',
                                }
                            }
                        }],
                    },
                }

            };
            var chart = new Chart(document.getElementById("line-chart"), chartData);
        },
        error: function(request, error) {
            alert("Request: " + JSON.stringify(request));
        }
    });
};
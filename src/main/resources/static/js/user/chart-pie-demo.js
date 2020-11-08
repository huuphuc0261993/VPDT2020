// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

var chart = chart || {}
var myPieChart
// Pie Chart Example
var ctx = document.getElementById("myPieChart");
// var myPieChart = new Chart(ctx, {
//   type: 'pie',
//   data: {
//     labels: ["Đang thực hiện", "Đúng hạn",  "Quá hạn"],
//     datasets: [{
//       data: [12.21, 15.58, 11.25],
//       backgroundColor: ['#007bff', '#28a745', '#dc3545'],
//     }],
//   },
// });

chart.drawChart = function () {
    $.ajax(
        {
            url: urlPathHost + '/api/chart/view',
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                console.log(data);
                myPieChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: ["Đang thực hiện", "Đúng hạn", "Quá hạn"],
                        datasets: [{
                            data: data,
                            backgroundColor: ['#007bff', '#28a745', '#dc3545'],
                        }],
                    },
                });
            },
            error: function (e) {
                console.log(e.message);
            }
        });
}
$(document).ready(function () {
    chart.drawChart();
});
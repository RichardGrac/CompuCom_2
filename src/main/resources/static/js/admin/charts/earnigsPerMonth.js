$(document).ready(function () {

    dataOctuber = 0;
    dataNovember = 0;
    dataDecember = 0;
    dataJanuary = 0;
    dataFebruary = 0;
    dataMarch = 0;
    dataApril = 0;

    $.getJSON('/statistics/earnigs-months', function (json) {
        $.each(json, function (index, value) {
            if (value.month === 10){
                dataOctuber = value.sum;
            }else if(value.month === 11){
                dataNovember = value.sum;
            }else if(value.month === 12){
                dataDecember = value.sum;
            }else if(value.month === 1){
                dataJanuary = value.sum;
            }else if(value.month === 2){
                dataFebruary = value.sum;
            }else if(value.month === 3){
                dataMarch = value.sum;
            }else if(value.month === 4){
                dataApril = value.sum;
            }

            var areaChartData = {
                labels  : ['Octubre', 'Noviembre', 'Diciembre', 'Enero', 'Febrero', 'Marzo', 'Abril'],
                datasets: [
                    {
                        label               : 'Ganancias',
                        fillColor           : '#00a65a',
                        strokeColor         : '#00a65a',
                        pointColor          : '#00a65a',
                        pointStrokeColor    : '#00a65a',
                        pointHighlightFill  : '#00a65a',
                        pointHighlightStroke: 'rgba(220,220,220,1)',
                        data                : [dataOctuber, dataNovember, dataDecember, dataJanuary, dataFebruary, dataMarch, dataApril]
                    }
                ]
            };
            var barChartCanvas                   = $('#earnigsMonth').get(0).getContext('2d');
            var barChart                         = new Chart(barChartCanvas);
            var barChartData                     = areaChartData;
            var barChartOptions                  = {
                //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
                scaleBeginAtZero        : true,
                //Boolean - Whether grid lines are shown across the chart
                scaleShowGridLines      : true,
                //String - Colour of the grid lines
                scaleGridLineColor      : 'rgba(0,0,0,.05)',
                //Number - Width of the grid lines
                scaleGridLineWidth      : 1,
                //Boolean - Whether to show horizontal lines (except X axis)
                scaleShowHorizontalLines: true,
                //Boolean - Whether to show vertical lines (except Y axis)
                scaleShowVerticalLines  : true,
                //Boolean - If there is a stroke on each bar
                barShowStroke           : true,
                //Number - Pixel width of the bar stroke
                barStrokeWidth          : 2,
                //Number - Spacing between each of the X value sets
                barValueSpacing         : 5,
                //Number - Spacing between data sets within X values
                barDatasetSpacing       : 1,
                //String - A legend template
                legendTemplate          : '<ul class="<%=name.toLowerCase()%>-legend"><% for (var i=0; i<datasets.length; i++){%><li><span style="background-color:<%=datasets[i].fillColor%>"></span><%if(datasets[i].label){%><%=datasets[i].label%><%}%></li><%}%></ul>',
                //Boolean - whether to make the chart responsive
                responsive              : true,
                maintainAspectRatio     : true
            };

            barChartOptions.datasetFill = false;
            barChart.Bar(barChartData, barChartOptions)
        })
    });
});
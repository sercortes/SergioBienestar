function generateGraphicByCoor(data){
    
    let select = $('#coor').val()
     let charts = `<div id="chartCoordinacion" style="height: 400px;"></div>`
        let grafico = document.getElementById('graficos')
       grafico.innerHTML = charts
       
    
     var chart = new CanvasJS.Chart("chartCoordinacion", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Participacion X programa"
            },
            subtitles:[
                {
                    text:select
                }
            ],
            data: [{
                    type: "pie",
                    startAngle: 25,
                    toolTipContent: "<b>{label}</b>: {y}%",
                    showInLegend: "true",
                    legendText: "{label}",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}%",
                    dataPoints: 
			data
                    
                }]
        });
        chart.render();
}

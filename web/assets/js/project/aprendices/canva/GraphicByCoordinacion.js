
var dataG
var sel
function generateGraphicByCoor(data){
    dataG = data
    let select = $('#coor').val()
    sel = select
     let charts = `<div id="chartCoordinacion" style="height: 500px;"></div>`
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
                    indexLabelFontSize: 12,
                    indexLabel: "{label} - {y}%",
                    dataPoints: 
			data
                    
                }]
        });
        chart.render();
}

$(document).on('click', '#generateXlsCoorPrograma', function (){

    let cabezera = {
        columna1:'programa',
        columna2:'%'
    }

    for(var item of dataG){
        item.y=Math.round((item.y))
    }
    
    exportCSVFile(cabezera, dataG, 'PARTICIPACION-PROGRAMAS-'+sel)
    
})


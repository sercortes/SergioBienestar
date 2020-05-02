
var dataG
var sel
function generateGraphicByCoor(data) {

    let arregloEstadisticas = []
    for (var item of data) {
        let ob = {
            label: item.nombrePrograma,
            y: ((item.participaciones * 100) / total).toFixed(2)
        }
        arregloEstadisticas.push(ob)
    }
    let select = $('#coor').val()

    let charts = `<div id="chartCoordinacion" style="height: 500px;"></div>`
    let grafico = document.getElementById('graficos')
    grafico.innerHTML = charts


    var chart = new CanvasJS.Chart("chartCoordinacion", {
        theme: "light2", 
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Participacion X programa"
        },
        subtitles: [
            {
                text: select
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
                        arregloEstadisticas

            }]
    });
    chart.render();
}

function setArray(data, total) {
    
    let arregloEstadisticas = []
    
    for (var item of data) {
        let ob = {
            label: item.nombrePrograma,
            y: ((item.participaciones * 100) / total).toFixed(2)
        }
        arregloEstadisticas.push(ob)
    }
    dataG = arregloEstadisticas
       for (var item of dataG) {
        item.y = Math.round((item.y))
    }
    
    let cabezera = {
        columna1: 'programa',
        columna2: '%'
    }
    
   dataG.unshift(cabezera);

}

$(document).on('click', '#generateXlsCoorPrograma', function () {
    let sel = $('#coor').val()
    
    exportCSVFile('cabezera', dataG, 'PARTICIPACION-PROGRAMAS-' + sel)

})


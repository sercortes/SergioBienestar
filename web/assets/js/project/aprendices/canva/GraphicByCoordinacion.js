var dataGBarra
var dataG
var sel
function generateGraphicByCoor(data, total) {

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
            text: "Porcentaje Participaciones",
            fontSize: 25
        },
        axisY: {
            labelFontSize: 11
        },
        axislabel: {
            labelFontSize: 11
        },
        subtitles: [
            {
                text: select,
                fontSize: 11
            }
        ],
        legend: {
            fontSize: 15,
        },
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
    
    let arregloBarra = []
    
    for (var item of data) {
        let ob = {
            label: item.nombrePrograma,
            y: parseInt(item.participaciones)
        }
        arregloBarra.push(ob)
    }
    
    dataGBarra = arregloBarra

    let cabezeraBarra = {
        columna1: 'programa',
        columna2: 'Cantidad'
    }

    dataGBarra.unshift(cabezeraBarra);
}

$(document).on('click', '#generateXlsCoorPrograma', function () {
    let sel = $('#coor').val()
    exportCSVFile('cabezera', dataG, 'PARTICIPACION-PROGRAMAS-' + sel)

})

$(document).on('click', '#generateXlsBarra', function () {
    let sel = $('#coor').val()
    exportCSVFile('cabezera', dataGBarra, 'PARTICIPACION-PROGRAMAS-' + sel)
})


function generateGraphicByCoorBarra(data) {

    let arregloEstadisticas = []
    
    for (var item of data) {
        let ob = {
            label: item.nombrePrograma,
            y: parseInt(item.participaciones)
        }
        arregloEstadisticas.push(ob)
    }

    let select = $('#coor').val()

    let charts = `<div id="chartCoordinacionBarra" style="height: 500px;"></div>`
    let grafico = document.getElementById('graficaBarra')
    grafico.innerHTML = charts


    var chart = new CanvasJS.Chart("chartCoordinacionBarra", {
        animationEnabled: true,
        exportEnabled: true,
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        title: {
            text: "Cantidad Participaciones",
            fontSize: 25
        },
        subtitles: [
            {
                text: select,
                fontSize: 11
            }
        ],
        axisY: {
            labelFontSize: 11
        },
        axisX: {
            labelFontSize: 11,
            labelAngle: 360
        },
        data: [{
                type: "column", //change type to bar, line, area, pie, etc
                indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "#5A5757",
                indexLabelFontSize: 15,
                indexLabelPlacement: "outside",
                dataPoints:
                        arregloEstadisticas
            }]
    });
    chart.render();

}


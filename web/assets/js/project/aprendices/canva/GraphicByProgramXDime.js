var dataGlo
function graphicByProgramD(data) {

    $.ajax({
        type: 'GET',
        url: './ListStaticsBytypeProgram',
        dataType: 'JSON',
        data: data
    }).done(function (data) {
        dataGlo = data
        generateCanvas(data)

    })

}

function modifyData(data) {
    
    var data2 = []
    
    for (var item of data) {
        var ob = {
            label: item.tipo_actividad,
            y: Math.round(item.cantidad * 100) / 100
        }
        data2.push(ob)
    }
    
    return data2
    
}

function generateCanvas(data) {

    let data2 = modifyData(data)

    let titulo = document.getElementById('tittleAprendicesXPrograma').textContent
    var chart = new CanvasJS.Chart("chartTiposPrograma", {
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Eventos preferidos"
        },
        subtitles: [
            {
                text: titulo
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
                        data2

            }]
    });
    chart.render();

}

$('#generateXlsTiposProgramass').click(function () {

    let data2 = modifyDataTwo(dataGlo)

    let titulo = document.getElementById('tittleAprendicesXPrograma').textContent

    exportCSVFile('cabecera', data2, titulo + '-PARTICIPACIONES-DIMENSIONES')

})

function modifyDataTwo(data) {
    var data2 = []
    for (var item of data) {
        var ob = {
            label: item.tipo_actividad,
            y: Math.round(item.cantidad * 100) / 100
        }
        data2.push(ob)
    }
    let cabecera = {
        columna1: 'Dimensión',
        columna2: '%'
    }

    for (var item of data2) {
        item.y = Math.round(parseInt(item.y))
    }
    data2.unshift(cabecera)

    return data2
}
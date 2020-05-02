var dataP
function getByTypes(data) {
    $.ajax({
        type: "GET",
        url: './ListStaticsBytype',
        datatype: 'json',
        data: {
            id: data.objeto,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {
        var data2 = [
        ]
        for (var item of data) {
            delete item.idRealActividad
            var ob = {
                y: Math.round(item.cantidad * 100) / 100,
                label: item.tipo_actividad
            }
            data2.push(ob)
        }

        generateGraaa(data2)

        dataP = data

        organizeHeader()

    })
}

function organizeHeader() {
    let datos = dataP
    let cabecera = {}
    cabecera = {
        columna1: 'actividad',
        columna2: 'porcentaje'
    }
    for (var item of datos) {
        item.cantidad = Math.round(item.cantidad)
    }
    datos.unshift(cabecera)
    dataP = datos
}

$('#generateXlsAprendiz').click(function () {

    exportCSVFile('cabecera', dataP, 'informeAprendiz')
})

function generateGraaa(data2) {
    let nombre = $('#modalActividadesAprendiz').text()
    var chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Eventos preferidos"
        },
        subtitles: [{
                text: nombre,
                fontSize: 14
            }
        ],
        data: [{
                type: "pie",
                startAngle: 235,
                toolTipContent: "<b>{label}</b>: {y}%",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 12,
                indexLabel: "{label} - {y}%",
                dataPoints:
                        data2

            }]
    });
    chart.render()

}

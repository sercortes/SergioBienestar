var dataOne
var dataTwo
var titlee
var program
function graphicByTypesYearProgramGeneral(data) {

        titlee = 'Total'
        program = document.getElementById('program').value

    $.ajax({
        type: 'GET',
        url: './SelectByTypesbyYearProgramGeneral',
        dataType: 'JSON',
        data: data
    }).done(function (data) {

        if (controlQuery(data)) {
            return false
        }

        addTitle()

        addDivs()

        generateOther(castArray(data), 'graphicOne', program, 'light2', 'Total participaciones')

        generateGraaaTwo(castArrayTwo(data), 'graphicTwo', program, 'light2', 'Total participaciones')

        addButtons()

    })

}

function print() {

    let select = document.getElementById('TypeActivity').value
    generatePDF1('#informe', select, true)
}

function addButtons() {
    let buttons = `<button id="generatePdf" type="button" class="btn btn-primary hvr-pulse-grow" onclick="print()"><i class="fas fa-print"></i> Imprimir</button>`
    document.getElementById('buttons').innerHTML = buttons
}

function addTitle() {
    let datos = '<i class="fas fa-chart-line hvr-icon"></i> ' + titlee
    document.getElementById('tituloInforme').innerHTML = datos
}


function generateHeaderOne() {
    let cabecera = {
        columna1: 'año',
        columna: 'valor'
    }
    let dataM = []
    for (var item of dataOne) {
        var ob = {
            label: item.label,
            y: item.y
        }
        dataM.push(ob)
    }
    dataM.unshift(cabecera)
    dataOne = dataM
}

$(document).on('click', '#generateXlsOne', function () {

    exportCSVFile('cabecera', dataOne, titlee + 'participantes')
})

$(document).on('click', '#generateXlsTwo', function () {

    exportCSVFile('cabecera', dataTwo, titlee + '%-participantes')
})

function generateheaderTwo() {
    let cabecera = {
        columna1: 'año',
        columna: '%'
    }
    let dataM = []
    for (var item of dataTwo) {
        var ob = {
            label: item.label,
            y: Math.round(parseInt(item.y))
        }
        dataM.push(ob)
    }
    dataM.unshift(cabecera)
    dataTwo = dataM
}

function addDivs() {
    let divOne = document.getElementById('outputOne')
    divOne.innerHTML = `<div id="graphicOne" style="height: 400px;"></div><hr>
                        <button id="generateXlsOne" type="button" class="btn btn-success mb-3 float-right">
                        <i class="far fa-file-excel"></i> Generar</button>`
    let divTwo = document.getElementById('outPutTwo')
    divTwo.innerHTML = `<div id="graphicTwo" style="height: 400px;"></div><hr>
                        <button id="generateXlsTwo" type="button" class="btn btn-success mb-3 float-right">
                        <i class="far fa-file-excel"></i> Generar</button>`
}

function castArray(data) {
    let array1 = []
    for (var item of data) {
        let ob = {
            label: 'Año ' + item.label,
            y: parseInt(item.y)
        }
        array1.push(ob)
    }
    return array1
}

function castArrayTwo(data) {
    var cantidad = 0
    for (var item of data) {
        cantidad += parseInt(item.y)
    }

    let array = []
    for (var item of data) {
        let ob = {
            label: 'Año ' + item.label,
            y: parseFloat(((item.y * 100) / cantidad).toFixed(2))
        }
        array.push(ob)
    }
    return array

}

function generateOther(data, div, title, theme, sub) {
    dataOne = data
    generateHeaderOne()
    var chart = new CanvasJS.Chart(div, {
        animationEnabled: true,
        exportEnabled: true,
        theme: theme, // "light1", "light2", "dark1", "dark2"
        title: {
            text: title,
            fontSize: 25
        },
        subtitles: [
            {
                text: sub
            }
        ],
        axisY: {
            title: "valor",
            titleFontSize: 24
        },
        data: [{
                type: "column", //change type to bar, line, area, pie, etc
                indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "#5A5757",
                indexLabelFontSize: 16,
                indexLabelPlacement: "outside",
                dataPoints:
                        data
            }]
    });
    chart.render();


}


function generateGraaaTwo(data, div, title, theme, sub, ) {
    dataTwo = data
    generateheaderTwo()
    var chart = new CanvasJS.Chart(div, {
        theme: theme, // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: title,
            fontSize: 25
        },
        subtitles: [
            {
                text: sub
            }
        ],
        data: [{
                type: "pie",
                startAngle: 235,
                toolTipContent: "<b>{label}</b>: {y}%",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 16,
                indexLabel: "{label} - {y}%",
                dataPoints:
                        data

            }]
    });
    chart.render()

}

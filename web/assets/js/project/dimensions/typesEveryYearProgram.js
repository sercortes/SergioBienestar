var dataOne
var dataTwo
var titlee
var program
function dimensions(data) {

        titlee = document.getElementById('TypeActivity').value

    $.ajax({
        type: 'GET',
        url: './SelectDimensions',
        dataType: 'JSON',
        data: data
    }).done(function (data) {
        
        console.log(data)

        if (controlQuery(data)) {
            return false
        }

        addTitle()

        addDivs()

        generateOther(castArray(data), 'graphicTwo', 'Programas', 'light2', titlee)

        generateGraaaTwo(castArrayTwo(data), 'graphicOne', 'Programas', 'light2', titlee)

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
    let select = document.getElementById('TypeActivity').value
    let datos = '<i class="fas fa-chart-line hvr-icon"></i> ' + select

    document.getElementById('tituloInforme').innerHTML = datos
}


function generateHeaderOne() {
    let cabecera = {
        columna1: 'Programa',
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

$(document).on('click', '#generateXlsOneProgram', function () {

    exportCSVFile('cabecera', dataOne, titlee + 'participantes')
})

$(document).on('click', '#generateXlsTwoProgram', function () {

    exportCSVFile('cabecera', dataTwo, titlee + '%-participantes')
})

function generateheaderTwo() {
    let cabecera = {
        columna1: 'Programa',
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
    divOne.innerHTML = `<div id="graphicOne" style="height: 700px;"></div><hr>
                        <button id="generateXlsOneProgram" type="button" class="btn btn-success mb-3 float-right noexportar">
                        <i class="far fa-file-excel"></i> Generar</button>`
    let divTwo = document.getElementById('outPutTwo')
    divTwo.innerHTML = `<div id="graphicTwo" style="height: 700px;"></div><hr>
                        <button id="generateXlsTwoProgram" type="button" class="btn btn-success mb-3 float-right noexportar">
                        <i class="far fa-file-excel"></i> Generar</button>`
}

function castArray(data) {
    let array1 = []
    for (var item of data) {
        let ob = {
            label: item.label,
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
            label: item.label,
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
        subtitles: [
            {
                text: sub
            }
        ],
         axisY: {
            labelFontSize: 11
        },
        axisX: {
            labelFontSize: 10,
            labelAngle: 360
        },
        legend: {
            fontSize: 15,
        },
        data: [{
                type: "column", //change type to bar, line, area, pie, etc
                indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "#5A5757",
                indexLabelFontSize: 12,
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
        subtitles: [
            {
                text: sub
            }
        ],
         axisY: {
            labelFontSize: 11
        },
        axisX: {
            labelFontSize: 10
        },
        legend: {
            fontSize: 13,
        },
         axislabel: {
            labelFontSize: 7
        },
        data: [{
                type: "pie",
                startAngle: 235,
                toolTipContent: "<b>{label}</b>: {y}%",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 13,
                indexLabel: "{label} - {y}%",
                dataPoints:
                        data

            }]
    });
    chart.render()

}

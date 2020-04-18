var dataOne
var dataTwo
var titlee
function graphicByTypesYear(data) {

    $.ajax({
        type: 'GET',
        url: './SelectByTypesbyYear',
        dataType: 'JSON',
        data: data
    }).done(function (data) {
        
        addTitle()
        
        addDivs()
        
        let select = document.getElementById('TypeActivity').value
        titlee = select
        
        generateOther(castArray(data), 'graphicOne', '# aprendices', 'light2', select)

        generateGraaaTwo(castArrayTwo(data), 'graphicTwo', 'porcentaje', 'light2', select)
        
        addButtons()

    })

}

function print(){

     let select = document.getElementById('TypeActivity').value
    generatePDF('#informe', select, true)
}

function addButtons(){
    let buttons = `<button id="generatePdf" type="button" class="btn btn-primary hvr-pulse-grow" onclick="print()"><i class="fas fa-print"></i> Imprimir</button>`
    document.getElementById('buttons').innerHTML = buttons
}

function addTitle(){
    let select = document.getElementById('TypeActivity').value
    let datos = '<i class="fas fa-chart-line hvr-icon"></i> '+select
    
    document.getElementById('tituloInforme').innerHTML = datos
}

$(document).on('click','#generateXlsOne', function(){
    let cabecera = {
        columna1:'año',
        columna:'valor'
    }
    let dataM = []
    for(var item of dataOne){
        var ob ={
            label:item.label,
            y:item.y
        }
        dataM.push(ob)
    }
    exportCSVFile(cabecera, dataM, titlee+'participantes')
})

$(document).on('click', '#generateXlsTwo', function(){
    let cabecera = {
        columna1:'año',
        columna:'%'
    }
    let dataM = []
    for(var item of dataTwo){
        var ob ={
            label:item.label,
            y:Math.round(parseInt(item.y)) 
        }
        dataM.push(ob)
    }
    exportCSVFile(cabecera, dataM, titlee+'%-participantes')
})

function addDivs(){
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
    var chart = new CanvasJS.Chart(div, {
        animationEnabled: true,
        exportEnabled: true,
        theme: theme, // "light1", "light2", "dark1", "dark2"
        title: {
            text: title
        },
        subtitles:[
                {
                    text:sub
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


function generateGraaaTwo(data, div, title, theme, sub){
    dataTwo = data
      var chart = new CanvasJS.Chart(div, {
            theme: theme, // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: title
            },
            subtitles:[
                {
                    text:sub
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

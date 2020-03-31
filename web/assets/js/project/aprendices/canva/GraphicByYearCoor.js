
function generateQueryYearCoor(data){
    let datos
  $.ajax({
        type: "GET",
        url: './SelectYears',
        datatype: 'json',
        async: false,
        data:{
            id:data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data) {
        datos = data;
        
})
    return datos
}


function generateArrayStaticsYearCoor(data, total){
    let array = []
    for(var item of data){
        let ob = {
            label:item.label,
            y:((item.y * 100)/total).toFixed(2)
        }
        array.push(ob)
    }
    generateGraphicByYearCoor(array)
    generateGraphicByYearCoorRanges(array)
}

function generateGraphicByYearCoorRanges(arreglo){
    var menores = {
        label: 'menores de 18',
        y: 0
    }
    var jovenes = {
        label: '18 y 25',
        y: 0
    }
    var adultos = {
        label: '25 y 35',
        y: 0
    }
    var adultosDos = {
        label: 'más de 35',
        y: 0
    }
    for(var item of arreglo){
        var arreglo = []
        if (item.label < 18) {
            menores.y +=parseFloat(item.y)
        }else if(item.label >= 18 && item.label <= 25){
            jovenes.y +=parseFloat(item.y)
        }else if(item.label > 25 && item.label <= 35){
            adultos.y +=parseFloat(item.y)
        }else{
            adultosDos.y +=parseFloat(item.y)
        }
    }
    menores.y = menores.y.toFixed(2)
    jovenes.y = jovenes.y.toFixed(2)
    adultos.y = adultos.y.toFixed(2)
    adultosDos.y = adultosDos.y.toFixed(2)
    
    arreglo.push(menores)
    arreglo.push(jovenes)
    arreglo.push(adultos)
    arreglo.push(adultosDos)

    generateGraphicByYearCoorRange(arreglo)
}

function generateGraphicByYearCoorRange(data){
    let charts = `<div id="chartByYearCoorRange" style="height: 400px;"> </div>`
       document.getElementById('yearRange').innerHTML = charts
    
     var chart = new CanvasJS.Chart("chartByYearCoorRange", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Rango de edades Edad"
            },
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

function generateGraphicByYearCoor(data){
    
    let charts = `<div id="chartByYearCoor" style="height: 400px;"> </div>`
       document.getElementById('year').innerHTML = charts
    
     var chart = new CanvasJS.Chart("chartByYearCoor", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Edad participantes"
            },
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
 
        
    

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
}



function generateGraphicByYearCoor(data){
    
    let charts = `<div id="chartByYearCoor" style="height: 400px;"> </div>`
       document.getElementById('year').innerHTML = charts
    
     var chart = new CanvasJS.Chart("chartByYearCoor", {
            theme: "light1", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Participacion X Edad"
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
 
        
    

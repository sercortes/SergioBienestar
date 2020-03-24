 
 function graphicByProgram(data){
    
    $.ajax({
        type: 'GET',
        url: './ListStaticsByProgram',
        dataType: 'JSON',
        data:data
    }).done(function (data){
        
        var sum = 0 
        var data2 = []
        for (var item of data){
            var ob = {
                y:Math.round(item.actividades.cantidad * 100)/100,
                label:item.ficha
            }
                sum +=Math.round(item.actividades.cantidad * 100)/100
            data2.push(ob)
        }
        console.log('=0 '+sum)
        
          var chart = new CanvasJS.Chart("chartPrograma", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Participación X fichas"
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
			data2
                    
                }]
        });
        chart.render();
        
        
    })
    
 }

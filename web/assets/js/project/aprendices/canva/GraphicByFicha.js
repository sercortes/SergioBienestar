var dataE
var titu
var dataGlobal
$(document).on('click', '#generateXlsFicha', function(){
   
    
    exportCSVFile('cabecera', dataGlobal, titu)
    
})  

function generateHeaderFicha(){
     let cabecera = {
        columna1:'Dimensión',
        columna2:'%'
    }
     let data = []
    for(var item of dataE){
        let ob = {
            label:item.label,
            y:Math.round(parseInt(item.y))
        }
        data.push(ob)
    }
   data.unshift(cabecera)
   dataGlobal = data
}

 function graphicByFicha(data){
    
    $.ajax({
        type: 'GET',
        url: './ListStaticsBytypeFicha',
        dataType: 'JSON',
        data:{
            id:data.id,
            fechaInicial: data.fechaInicial,
            fechaFinal: data.fechaFinal
        }
    }).done(function (data){
       
        var data2 = []
        for (var item of data){
            var ob = {
                y:Math.round(item.cantidad * 100)/100,
                label:item.tipo_actividad
            }
            data2.push(ob)
        }
        dataE = data2
        generateHeaderFicha()
        let titulos = document.getElementById('tittleListAprendices').textContent
        titu = titulos
          var chart = new CanvasJS.Chart("chartFicha", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Eventos preferidos"
            },
            subtitles:[
                {
                    text:titulos
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
        
        
    })
    
 }

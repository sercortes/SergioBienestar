
 function graphicByProgram(data){
        let titulo = document.getElementById('tittleAprendicesXPrograma').textContent
          var chart = new CanvasJS.Chart("chartPrograma", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "Participación X fichas"
            },
            subtitles:[
                {
                text:titulo
            }
            ],
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

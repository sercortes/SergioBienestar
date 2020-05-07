function GrapColumn(data, tipo) {

    let dataBarra = []

    for (var item of data) {
        var ob = {
            label: item.label,
            y: parseInt(item.y)
        }
        dataBarra.push(ob)
    }


    var chart = new CanvasJS.Chart("graphicTwo", {
        animationEnabled: true,
        exportEnabled: true,
        theme: 'light2', // "light1", "light2", "dark1", "dark2"
        title: {
            text: 'Participación de programas',
            fontSize: 25
        },
        subtitles: [
            {
                text: tipo
            }
        ],
        axisY: {
            title: "valor",
            titleFontSize: 24
        },
        axisX: {
            labelFontSize: 11,
            labelAngle: 360
        },
        data: [{
                type: "column", //change type to bar, line, area, pie, etc
                indexLabel: "{y}", //Shows y value on all Data Points
                indexLabelFontColor: "#5A5757",
                indexLabelFontSize: 16,
                indexLabelPlacement: "outside",
                dataPoints:
                        dataBarra
            }]
    });
    chart.render();


}


function generateGrap(data, total, tipo) {

    let dataP = []

    for (var item of data) {
        var ob = {
            label: item.label,
            y: ((item.y * 100) / total).toFixed(2)
        }
        dataP.push(ob)
    }

    let chart = new CanvasJS.Chart("graphicOne", {
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        exportEnabled: true,
        animationEnabled: true,
        title: {
            text: "Participación de programas"
        }, subtitles: [
            {
                text: tipo
            }
        ],
        data: [{
                type: "pie",
                toolTipContent: "<b>{label}</b>: {y}%",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 12,
                indexLabel: "{label} - {y}%",
                dataPoints:
                        dataP

            }]
    });
    chart.render();
}
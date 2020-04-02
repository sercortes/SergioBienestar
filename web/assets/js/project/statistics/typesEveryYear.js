
function graphicByTypesYear(data) {

    $.ajax({
        type: 'GET',
        url: './SelectByTypesbyYear',
        dataType: 'JSON',
        data: data
    }).done(function (data) {
        
        addTitle()
        
        addDivs()
        
        generateOther(castArray(data), 'graphicOne', '# aprendices', 'light2')

        generateOther(castArrayTwo(data), 'graphicTwo', 'porcentaje', 'light2')

    })

}

function addTitle(){
    let select = document.getElementById('TypeActivity').value
    let datos = '<i class="fas fa-chart-line hvr-icon"></i> '+select
    
    document.getElementById('tituloInforme').innerHTML = datos
}

function addDivs(){
    let divOne = document.getElementById('outputOne')
    divOne.innerHTML = '<div id="graphicOne" style="height: 400px;"></div><hr>'
    let divTwo = document.getElementById('outPutTwo')
    divTwo.innerHTML = '<div id="graphicTwo" style="height: 400px;"></div>'
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

function generateOther(data, div, title, theme) {

    var chart = new CanvasJS.Chart(div, {
        animationEnabled: true,
        exportEnabled: true,
        theme: theme, // "light1", "light2", "dark1", "dark2"
        title: {
            text: title
        },
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

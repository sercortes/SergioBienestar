$(function () {

    menu('menuDimensions')

    typesActivitys()
    
    setDefaultDate()


})

document.getElementById('buttonGenerateG').addEventListener('click', function () {

    let data = generateData()
    
    if (!validation()) {
       return false;
    }
    
    console.log('Enviar')
    console.log(data)
    
    dimensions(data)

})

function validation() {
    
    let data = generateData()
    
    if (data.tipo == 'No') {
        messageInfo('Seleccione una dimensión')
        return false
    } 
    
    return true
    
}

function generateData() {
    let startYear = document.getElementById('fechaI').value
    let finishYear = document.getElementById('fechaF').value
    let typeA = document.getElementById('TypeActivity').value
    let data = {
        yearStart: startYear,
        finishYear: finishYear,
        tipo: typeA
    }

    return data

}


function controlQuery(data){
    if (data.length <= 0) {
        messageInfo('consulta sin resultados')
        document.getElementById('outputOne').innerHTML = ''
        document.getElementById('outPutTwo').innerHTML = ''
        document.getElementById('buttons').innerHTML = ''
        document.getElementById('tituloInforme').innerHTML = ''
        return true
    }else{
        return false
    }
}
$(function () {

    menu('menuStatistics')

    typesActivitys()

    let yearNow = new Date().getStartYear().slice(0, 4)
    let yearPas = yearNow - 1

    generateYears('yearStart', yearPas)
    generateYears('yearFinish', yearNow)

    getPrograms()

})

document.getElementById('buttonGenerateG').addEventListener('click', function () {

    let data = generateData()
    
    if (validation()) {
        

        if (data.tipo !== 'No' && data.program !== 'No') {
            graphicByTypesYearProgram(generateData())
        }else if(data.program !== 'No'){
            graphicByTypesYearProgramGeneral(generateData())
        }else{
            graphicByTypesYear(generateData())
        }
        
    }

})

function validation() {
    let data = generateData()
    if (data.program == 'No' && data.tipo == 'No') {
        messageInfo('Seleccione algún filtro')
        return false
    } else if (data.yearStart > data.finishYear) {
        messageInfo('Verifique el rango de años')
        return false
    }
    return true
}

function generateData() {
    let startYear = document.getElementById('yearStart').value
    let finishYear = document.getElementById('yearFinish').value
    let typeA = document.getElementById('TypeActivity').value
    let Program = document.getElementById('program').value
    let data = {
        yearStart: startYear,
        finishYear: finishYear,
        tipo: typeA,
        program:Program
    }

    return data

}

function generateYears(id, defect) {

    $.ajax({
        type: "GET",
        url: './getYears',
        datatype: 'json'
    }).done(function (data) {

        let select = document.getElementById(id)
        let str = `<option value="${defect}">${defect}</option>`

        for (var item of data) {
            if (defect != item.y) {
                str += `<option value="${item.y}">${item.y}</option>`
            }
        }

        select.innerHTML += str;
    })

}

function getPrograms() {

    let filtros2 = document.getElementById('program')

    let str = ``

    $.ajax({
        type: "GET",
        url: './getPrograms',
        datatype: 'json'
    }).done(function (data) {

        for (var item of data) {
            str += `<option value="${item.nombrePrograma}">${item.nombrePrograma}</option>`
        }

        filtros2.innerHTML += str;
    })

}
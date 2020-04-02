$(function(){
    
    menu('menuStatistics')
    
    typesActivitys()
   
    let yearNow = new Date().getStartYear().slice(0,4)
    let yearPas = yearNow-1
            
     generateYears('yearStart',yearPas)
     generateYears('yearFinish', yearNow)
     
     let button = document.getElementById('buttonGenerateG')
     button.addEventListener('click', function(){
         
         if (validation()) {
            graphicByTypesYear(generateData())
        }
         
     })
    
})

function validation(){
    let data = generateData()
    if (data.tipo == 'No') {
        messageInfo('seleccione un tipo de actividad')
        return false
    }else if(data.yearStart>data.finishYear){
       messageInfo('Verifique el rango de años')
        return false
    }
    return true
}

function generateData(){
    let startYear = document.getElementById('yearStart').value
    let finishYear = document.getElementById('yearFinish').value
    let typeA = document.getElementById('TypeActivity').value
    let data = {
        yearStart:startYear,
        finishYear:finishYear,
        tipo: typeA
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

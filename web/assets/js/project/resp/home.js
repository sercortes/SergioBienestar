$(function(){
    
    menu('menuResp')
    
    queryResponsables()
    
})

$('#botonAdd').click(function(){
    
    $('#modalAdd').modal('show')
    generateYears()
    
})

$('#botonAgregar').click(function (){
    
    let nombre = document.getElementById('name').value
    let codigo = document.getElementById('code').value
    let year = document.getElementById('year').value
    
    if (nombre == '' || codigo == '' || year == 'No') {
        messageInfo('complete el formulario')
    }else{
        
        let data = {
            nombre:nombre,
            codigo:codigo,
            year:year
        }
        
        addNewRecord(data);
        
    }
    
    
})

function addNewRecord(data){
    $.ajax({
        type: "POST",
        url: './addResponsables',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
      
        if (data) {
            messageOk('Generado con éxito')
            $('#formAdd').trigger('reset')
            queryResponsables()
        }
        
        
    })
    
    
}


function generateYears(){
    
    let year = document.getElementById('year')
    let srt = '<option value="No">No</option>'
    let actualyear = new Date().getStartYear().substring(0, 4)-2
   
   for(var i = actualyear;i<=actualyear+4;i++){
       srt +=`<option value="${i}">${i}</option>`
   }
   year.innerHTML = srt
   
}

function queryResponsables(){
    
    
        $.ajax({
        type: "POST",
        url: './SelectResponsables',
        datatype: 'json'
    }).done(function (data) {
   
        drawTable(data)
        
    })
    
}


function generateYearsEdit(years){
    
    let yearA = document.getElementById('yearE')
    let year = parseInt(years)
    let srt = `<option value="${year}">${year}</option>`
    let actualyear = new Date().getStartYear().substring(0, 4)-2
   
   for(var i = actualyear;i<=actualyear+4;i++){
        if (i != year) {
       srt +=`<option value="${i}">${i}</option>`
        }
   }
   yearA.innerHTML = srt
   
}
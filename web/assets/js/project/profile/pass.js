

function updatePassword(data){
    
    $('#emailPass').val(data)
    $('#modalUpdatePassword').modal('show')
    
}


$('#botonUpdatePass').click(function(){
     
     let passOne = $('#passE').val()
     let passTwo = $('#passTwo').val()
     
     if (passOne == '' || passTwo == '') {
        messageInfo('complete el formulario')
        return false;
    }
    if (passOne !== passTwo) {
        messageInfo('las contraseñas no coinciden')
        return false;
    }
     if (passOne.length <=6 || passTwo <=6) {
        messageInfo('Genere una contraseña más larga')
        return false;
    }
    
    let data = {
        pass:passOne
    }
 
    updatePasswordQuery(data)
    
})

function updatePasswordQuery(data){
    $.ajax({
        type: "POST",
        url: './updatePassIndividual',
        datatype: 'json',
        data:data
    }).done(function (data) {
   
      
        if (data) {
            messageOk('Editado con éxito')
        }else{
            messageError('=( ups ocurrio un error')
        }
        
        
    })
}
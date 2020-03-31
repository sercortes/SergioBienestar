

$(function(){
    
    let menuItem = document.getElementById('menuAprendices')
    menuItem.classList.add('active')
    
    let coor = document.getElementById('coor')
    
    let cordinacion = ``
    
    $.ajax({
            type: "GET",
            url: './getCoor',
            datatype: 'json'
        }).done(function (data) {
                      

            for (var item of data) {
                cordinacion += `<option value="${item.coordinacion}">${item.coordinacion}</option>`
            }
            
            
            coor.innerHTML += cordinacion;
               
            
        })
    
    setDefaultDate()
    
})

function cambiarFecha(){
    
    selectCoordinacion()
    
}


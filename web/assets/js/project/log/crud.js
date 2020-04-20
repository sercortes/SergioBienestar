
function drawTable(data){
    
    let select = document.getElementById('tabla');
    let str = ` `
   
    for (var item of data) {

        str += `<tr idUser="" idPerfil="" class="chiquito">
                                                    <td class="element">${item.email}</td>
                                                    <td class="element">${item.registros}</td>
                                                    <td class="element">${item.fechaModif}</td>
                                                </tr> `
    }
    
    select.innerHTML = str;
    
}






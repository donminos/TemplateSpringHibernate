$(document).ready(function () {
    getAllAlumnos();
});
function crearUsuario() {
    $('.id-usuarioc').val('');
    $('.usuarioc').val('');
    $('.correoc').val('');
    $('.nombrec').val('');
    $('.apepc').val('');
    $('.apemc').val('');
    $('.activoc').prop('checked', false);
    $('.contrasenac').val('');
    $.fancybox([
        {href: '#Form-create-usuario',
            helpers: {
                overlay: {closeClick: false

                }
                //Disable click outside event
            }
        }
    ]);
}
function getAllAlumnos() {
    $.ajax({
        type: "GET",
        url: '../materias/findAll.do',
        dataType: "json",
        context: this,
        //data: param,
        success: function (data) {
            var model = createStruct(data.response);
            $('#body-alumnos').html(model);
            table = $('#table-alumnos').DataTable({
                "info": false,
                "pagingType": "full_numbers",
                "columnDefs": [{
                        "targets": 'no-sort',
                        "orderable": false}],
                "order": [[1, "desc"]]
            });
        }
    });
}
createStruct = function (data) {
    var model = '';
    data.Materias.forEach(function (inf) {
        debugger;
        model += '<tr id="materia_' + inf.idMateria + '">';
        model += '<td valign="top">' + inf.nombre + '</td>';
        model += '<td valign="top">' + inf.grupo.descripcion + '</td>';
        model += '</tr>';
    });
    return model;
};
function alerta(data) {
    alert(JSON.stringify(data));
}
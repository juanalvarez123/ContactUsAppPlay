$(document).ready(function(){
	var estado = 1;
		return $.get("/consultarMedios/" + estado, function(medios) {
			return $.each(medios, function(index, medio) {
				$("#selectMedio").append($("<option />").val(medio.idMedio).text(medio.descripcion));
			});
		});
});
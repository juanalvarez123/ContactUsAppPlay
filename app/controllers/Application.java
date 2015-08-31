package controllers;

import play.*;
import play.mvc.*;
import play.db.jpa.*;
import views.html.*;
import play.data.Form;
import java.util.List;
import java.util.ArrayList;
import play.libs.F.Function;
import javax.inject.Inject;
import play.libs.ws.*;
import play.libs.F.Promise;
import static play.libs.Json.*;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.data.DynamicForm;
import play.data.Form;

public class Application extends Controller {

    public class Contacto { }
    public class Medio {
        public Integer idMedio;
        public Medio(Integer idMedio) {
            this.idMedio = idMedio;
        }
    }

	@Inject WSClient ws;

    public Result index() {
        return ok(index.render());
    }

	public Promise<Result> registrarContacto() {
        DynamicForm form = Form.form().bindFromRequest();
        Contacto contacto = new Contacto() {
            public String nombres = form.get("name");
            public String apellidos = form.get("lastName");
            public String email = form.get("email");
            public String telefono = form.get("telephone");
            public String pais = form.get("country");
            public String url = form.get("webSite");
            public Medio medio = new Medio(Integer.parseInt(form.get("selectMedio")));
            public String comentarios = form.get("comments");
        };
        JsonNode contactoJson = Json.toJson(contacto);
        WSRequest request = ws.url("http://localhost:8081/WS/rest/wsContactUs/registrarContacto");
        Promise<Result> promiseResultado = request.post(contactoJson).map(response -> {
            if(String.valueOf(response.asJson()) == "true") {
            	return ok("<h1>Solicitud registrada correctamente</h1>").as("text/html");
            } else {
                return ok("<h1>Error el registrar la solicitud</h1>").as("text/html");
            }
        });
        return promiseResultado;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Promise<Result> consultarMedios(String estado) {
    	WSRequest request = ws.url("http://localhost:8081/WS/rest/wsContactUs/consultarMedios/" + estado);
    	Promise<Result> promiseResultado = request.get().map(response -> {
    		return ok(response.asJson());
    	});

    	return promiseResultado;
    }

}
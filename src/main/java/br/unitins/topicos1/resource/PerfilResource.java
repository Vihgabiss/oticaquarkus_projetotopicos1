package br.unitins.topicos1.resource;

import java.util.Arrays;
import java.util.List;

import br.unitins.topicos1.model.Perfil;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/perfil")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PerfilResource {
    
    @GET
    public List<Perfil> getAll(){
        return Arrays.asList(Perfil.values());
    }
}

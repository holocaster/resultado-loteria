package br.com.prcompany;

import br.com.prcompany.dto.ResultadoConcursoDTO;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/megasena")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class LoteriaResource {

    @Inject
    LoteriaService loteriaService;

    @GET
    @Path("/verificar")
    @Blocking
    public List<ResultadoConcursoDTO> verificar(
            @QueryParam("inicio") int inicio,
            @QueryParam("fim") int fim) {
        return loteriaService.verificarConcursos(inicio, fim);
    }
}

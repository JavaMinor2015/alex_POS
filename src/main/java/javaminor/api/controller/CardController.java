package javaminor.api.controller;

import javaminor.api.domain.RestModel;
import javaminor.api.util.RestUtil;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.ScanItemRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 9/23/15.
 */
@Path(FidelityCard.URL)
@Produces({MediaType.APPLICATION_JSON})
public class CardController {

    @GET
    public Response getCards(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        return RestUtil.buildReponse(
                new RestModel<>(FidelityCard.URL,
                        start,
                        limit,
                        ScanItemRepository.getCards(start, limit)));
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") final int id){
        return RestUtil.buildReponse(ScanItemRepository.getItemById(id), id);
    }
}

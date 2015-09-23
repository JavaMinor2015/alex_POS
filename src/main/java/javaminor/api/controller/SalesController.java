package javaminor.api.controller;

import javaminor.api.domain.RestModel;
import javaminor.api.util.RestUtil;
import javaminor.domain.concrete.transactions.Sale;
import javaminor.logic.TransactionRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 9/23/15.
 */
@Path(Sale.ALL)
@Produces({MediaType.APPLICATION_JSON})
public class SalesController {

    @GET
    public Response getDiscounts(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        return RestUtil.buildReponse(
                new RestModel<>(Sale.ALL,
                        start,
                        limit,
                        TransactionRepository.getSales(start, limit)));
    }

    @GET
    @Path("/{id}")
    public Response getProductById(@PathParam("id") final int id){
        return RestUtil.buildReponse(TransactionRepository.getItemById(id), id);
    }
}

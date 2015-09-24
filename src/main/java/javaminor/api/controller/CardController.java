package javaminor.api.controller;

import javaminor.api.domain.RestModel;
import javaminor.api.util.CustomerDeserializer;
import javaminor.api.util.DiscountDeserializer;
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
    public Response getCardById(@PathParam("id") final int id){
        return RestUtil.buildReponse(ScanItemRepository.getCardById(id), id);
    }

    @POST
    @Path("/create")
    public Response create(@FormParam("json") String json) {

        FidelityCard card = RestUtil.getBuilder(new DiscountDeserializer(),new CustomerDeserializer())
                .create()
                .fromJson(json, FidelityCard.class);

        boolean success = ScanItemRepository.addCard(card);
        if (success) {
            return RestUtil.buildReponse(card.getId(), json);
        } else {
            return RestUtil.buildReponse("Failed to add card", json);
        }
    }

    @PUT
    @Path("/update")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response update(@FormParam("json") String json) {

        FidelityCard card = RestUtil.getBuilder(new DiscountDeserializer(),new CustomerDeserializer())
                .create()
                .fromJson(json, FidelityCard.class);

        if (!ScanItemRepository.scanItemExists(card)) {
            return RestUtil.buildReponse("Failed to update card, id not " +
                    "found.", json);
        }

        boolean success = ScanItemRepository.updateCard(card);
        if (success) {
            return RestUtil.buildReponse("Card updated: ", card);
        } else {
            return RestUtil.buildReponse("Failed to add card", json);
        }
    }
}

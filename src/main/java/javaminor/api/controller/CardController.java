package javaminor.api.controller;

import javaminor.api.domain.concrete.Cards;
import javaminor.api.util.RestUtil;
import javaminor.domain.abs.Discount;
import javaminor.domain.concrete.scanitems.FidelityCard;
import javaminor.logic.ScanItemRepository;
import javaminor.util.RefUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by alex on 9/23/15.
 */
@Path(FidelityCard.ALL)
@Produces({MediaType.APPLICATION_JSON})
public class CardController {

    @GET
    public Response getCards(@DefaultValue("0") @QueryParam("start") final int start, @DefaultValue("10") @QueryParam("limit") final int limit) {
        Cards list = new Cards();
        if(start > limit){
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start-limit) + "&limit=" + limit);
        }else{
            list.setPrev(RefUtil.BASE_URL + Discount.ALL + "?start=" + (0) + "&limit=" + limit);
        }
        list.setNext(RefUtil.BASE_URL + Discount.ALL + "?start=" + (start + limit) + "&limit=" + limit);
        list.setCards(ScanItemRepository.getCards(start, limit));
        return RestUtil.buildReponse(list, null);
    }
}

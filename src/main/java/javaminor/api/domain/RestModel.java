package javaminor.api.domain;

import javaminor.util.RefUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by alex on 9/23/15.
 */
@Getter
@Setter
public class RestModel<T> {

    public RestModel(final String spec, final int start, final int limit, final List<T> list){
        this.list = list;
        createUri(spec,start,limit);
    }

    private String prev;
    private String next;
    private List<T> list;

    private final void createUri(final String spec, final int start, final int limit){
        if (start > limit) {
            setPrev(RefUtil.BASE_URL + spec + "?start=" + (start
                    - limit) + "&limit=" + limit);
        } else {
            setPrev(RefUtil.BASE_URL + spec + "?start=" + (0) +
                    "&limit=" + limit);
        }
        setNext(RefUtil.BASE_URL + spec + "?start=" + (start +
                limit) + "&limit=" + limit);
    }
}

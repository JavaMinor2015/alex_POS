package javaminor.api.domain.abs;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by alex on 9/23/15.
 */
@Getter
@Setter
public abstract class RestModel {
    private String prev;
    private String next;
}

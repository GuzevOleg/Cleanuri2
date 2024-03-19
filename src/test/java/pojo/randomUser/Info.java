package pojo.randomUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Info {
    private String seed;
    private Integer results;
    private Integer page;
    private String version;
}

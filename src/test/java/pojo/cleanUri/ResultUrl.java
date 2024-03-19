package pojo.cleanUri;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ResultUrl {

    @SerializedName("result_url")
    private String resultUrl;
    private String error;

}

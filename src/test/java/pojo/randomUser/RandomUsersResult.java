package pojo.randomUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RandomUsersResult {
    private ArrayList<Result> results;
    private Info info;
}

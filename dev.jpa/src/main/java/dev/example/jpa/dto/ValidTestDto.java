package dev.example.jpa.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class ValidTestDto {
    @NotNull //변수가  null인지 아닌지
    private String notNUllString;
    @NotEmpty // 변수가 null이 아니면서 object.size()>0
    private String notEmptyString;
    @NotBlank // list에 사용 불가, 공백이 아닌 문자열 ex): "          " 이러면 실패함 , 즉 적어도 한글자 이상 작성하애함
    private String notBlankString;
    @NotEmpty // 변수가 null이 아니면서 object.size()>0
    private List<String> notEmptyStringList;
}

package hello.itemservice.domain.item;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity  //JPA에서 인식할수있도록 어노테이션 설정
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message ="총합이 10000원이 넘도록 입력 해주세요.")
public class Item {

    //@Id 어노테이션으로 PK 설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank(message = "공백X")
    @NotBlank(groups = UpdateCheck.class) //수정 요구사항 추가
    @Column(name = "item_name", length = 10) //객체의 필드를 컬럼과 매핑
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }


}

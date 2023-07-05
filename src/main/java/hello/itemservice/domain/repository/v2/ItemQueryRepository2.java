package hello.itemservice.domain.repository.v2;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.QItem;
import hello.itemservice.domain.repository.ItemSearchCond;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemQueryRepository2 {
    private final JPAQueryFactory query;

    public ItemQueryRepository2(EntityManager em) {
        this.query = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond){
        QItem item = QItem.item;

        return query.select(item)
                .from(item)
                .where(
                        likeItemName(cond.getItemName()),
                        maxPrice(cond.getMaxPrice())
                 )
                .fetch();
    }

    private Predicate maxPrice(Integer maxPrice) {
        if(maxPrice != null){
            return QItem.item.price.loe(maxPrice);
        }
        return null;
    }

    private BooleanExpression likeItemName(String itemName){
        if(StringUtils.hasText(itemName)){
            return QItem.item.itemName.like("%"+itemName+"%");
        }
        return null;
    }
}

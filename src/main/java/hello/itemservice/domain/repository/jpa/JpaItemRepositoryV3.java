package hello.itemservice.domain.repository.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.QItem;
import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.ItemSearchCond;
import hello.itemservice.domain.repository.ItemUpdateDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaItemRepositoryV3 implements ItemRepository {


    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }


    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }


    public List<Item> findAll_Old(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        QItem item = QItem.item;
        BooleanBuilder builder = new BooleanBuilder();
        if(StringUtils.hasText(itemName)){
            builder.and(item.itemName.like("%"+itemName+"%"));
        }
        if(maxPrice != null){
            builder.and(item.price.loe(maxPrice));
        }
        List<Item> result = query
                .select(item)
                .from(item)
                .where(builder)
                .fetch();
        //QItem item = new QItem("i");
//        List<Item> result = query
//                .select(QItem.item)
//                .from(QItem.item)
//                .where()
//                .fetch();
        return result;
    }
    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        QItem item = QItem.item;
//        BooleanBuilder builder = new BooleanBuilder();
//        if(StringUtils.hasText(itemName)){
//            builder.and(item.itemName.like("%"+itemName+"%"));
//        }
//        if(maxPrice != null){
//            builder.and(item.price.loe(maxPrice));
//        }
        List<Item> result = query
                .select(item)
                .from(item)
                .where(likeItemName(itemName),maxPrice(maxPrice))
                .fetch();

        return result;
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

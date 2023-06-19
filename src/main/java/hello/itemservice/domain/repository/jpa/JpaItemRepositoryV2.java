package hello.itemservice.domain.repository.jpa;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.ItemSearchCond;
import hello.itemservice.domain.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = repository.findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        if(StringUtils.hasText(itemName) && maxPrice != null){
            return repository.findItems("%"+itemName+"%", maxPrice);
        }else if(StringUtils.hasText(itemName)){
            return repository.findByItemNameLike("%"+itemName+"%");
        }else if (maxPrice != null){
            return repository.findByPriceLessThanEqual(maxPrice);
        }else{
            return repository.findAll();
        }
    }
}

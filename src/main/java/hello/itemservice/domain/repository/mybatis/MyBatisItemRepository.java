package hello.itemservice.domain.repository.mybatis;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.ItemSearchCond;
import hello.itemservice.domain.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

    private final ItemMapper itemMapper;

    @Override
    public Item save(Item item) {
        log.info("itemMapper class = {}" , itemMapper.getClass());
        log.info("여긴가11111"+item.toString());
        System.out.println("여긴가2222"+item.toString());

        itemMapper.save(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        itemMapper.update(itemId, updateParam);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        return itemMapper.findAll(cond);
    }



}

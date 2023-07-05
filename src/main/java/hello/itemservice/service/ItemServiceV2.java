package hello.itemservice.service;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.ItemSearchCond;
import hello.itemservice.domain.repository.ItemUpdateDto;
import hello.itemservice.domain.repository.v2.ItemQueryRepository2;
import hello.itemservice.domain.repository.v2.ItemRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceV2 implements ItemService{

    private final ItemRepositoryV2 itemRepositoryV2;
    private final ItemQueryRepository2 itemQueryRepository2;

    @Override
    public Item save(Item item) {
        return null;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = itemRepositoryV2.findById(itemId).orElseThrow();
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemRepositoryV2.findById(id);
    }

    @Override
    public List<Item> findItems(ItemSearchCond itemSearch) {
        return itemQueryRepository2.findAll(itemSearch);
    }
}

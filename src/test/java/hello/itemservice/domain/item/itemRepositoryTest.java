package hello.itemservice.domain.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.repository.ItemRepository;
import hello.itemservice.domain.repository.ItemSearchCond;
import hello.itemservice.domain.repository.ItemUpdateDto;
import hello.itemservice.domain.repository.memory.MemoryItemRepository;
import hello.itemservice.domain.repository.mybatis.MyBatisItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
@Slf4j
@Transactional
@SpringBootTest
class itemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
//
//    @Autowired
//    PlatformTransactionManager transactionManager;
//    TransactionStatus status;
//    @BeforeEach
//    void beforeEach(){
//        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//    }

    @AfterEach
    void afterEach() {
        //MemoryItemRepository 의 경우 제한적으로 사용
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }
        //트랜잭션 롤백
        //transactionManager.rollback(status);
    }

//    @Commit -> Transactional 이 붙어있어도 해당 테스트는 commit 되어서 DB에 적재
    @Test
    void save(){
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);

    }
//    @Test
//    void findAll(){
//        //given
//        Item item1 = new Item("item4",10000,10);
//        Item item2 = new Item("item3",20000,20);
//
//        itemRepository.save(item1);
//        itemRepository.save(item2);
//
//        //when
//        List<Item> result = itemRepository.findAll();
//
//        //then
//        assertThat(result.size()).isEqualTo(2);
//        assertThat(result).contains(item1, item2);
//    }
    @Test
    void updateItem(){
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        ItemUpdateDto updateParam = new ItemUpdateDto("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
    @Test
    void findItems() {
        //given
        Item item1 = new Item("itemA-1", 10000, 10);
        Item item2 = new Item("itemA-2", 20000, 20);
        Item item3 = new Item("itemB-1", 30000, 30);

        log.info("repository={}", itemRepository.getClass());
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //둘 다 없음 검증
        test(null, null, item1, item2, item3);
        test("", null, item1, item2, item3);

        //itemName 검증
        test("itemA", null, item1, item2);
        test("temA", null, item1, item2);
        test("itemB", null, item3);

        //maxPrice 검증
        test(null, 10000, item1);

        //둘 다 있음 검증
        test("itemA", 10000, item1);
    }

    void test(String itemName, Integer maxPrice, Item... items) {
        List<Item> result = itemRepository.findAll(new ItemSearchCond(itemName, maxPrice));
        assertThat(result).containsExactly(items);
    }

}

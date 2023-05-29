package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@Transactional
@SpringBootTest
class itemRepositoryTest {
    @Autowired
    ItemRepository itemRepository = new ItemRepository();
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
        itemRepository.clearStore();
       // transactionManager.rollback(status);
    }

//    @Commit -> Transactional 이 붙어있어도 해당 테스트는 commit 되어서 DB에 적재
    @Test
    void save(){
        //given
        Item item = new Item("itemA",10000,10);
        //when
        Item savedItem = itemRepository.save(item);
        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);

    }
    @Test
    void findAll(){
        //given
        Item item1 = new Item("item1",10000,10);
        Item item2 = new Item("item2",20000,20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);
    }
    @Test
    void updateItem(){
        //given
        Item item = new Item("item1",10000,10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item2",20000,30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }


}

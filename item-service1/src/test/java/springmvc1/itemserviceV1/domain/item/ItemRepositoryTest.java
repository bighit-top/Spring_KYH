package springmvc1.itemserviceV1.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 100);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 10000, 100);
        Item item2 = new Item("itemB", 20000, 100);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> items = itemRepository.findAll();

        //then
        Assertions.assertThat(items.size()).isEqualTo(2);
        Assertions.assertThat(items).contains(item1, item2);

    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("itemA", 10000, 100);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updateParam = new Item("item1", 100000, 500);
        itemRepository.update(itemId, updateParam);

        //then
        Item result = itemRepository.findById(itemId);

        Assertions.assertThat(result.getItemName()).isEqualTo(updateParam.getItemName());
        Assertions.assertThat(result.getPrice()).isEqualTo(updateParam.getPrice());
        Assertions.assertThat(result.getQuantity()).isEqualTo(updateParam.getQuantity());

    }

}

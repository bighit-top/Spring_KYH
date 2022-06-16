package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

    private final ItemMapper itemMapper; //mybatis에서 구현체 프록시를 생성해서 주입해줌

    @Override
    public Item save(Item item) {
        log.info("itemMapper class={}", itemMapper.getClass()) ;
        itemMapper.save(item);
        return item;
    }

    @Override
    public void update(Long id, ItemUpdateDto itemUpdateDto) {
        itemMapper.update(id, itemUpdateDto);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond itemSearch) {
        return itemMapper.findAll(itemSearch);
    }
}

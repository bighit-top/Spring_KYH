package springmvc1.itemserviceV1.web.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import springmvc1.itemserviceV1.domain.item.Item;
import springmvc1.itemserviceV1.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addV1(@RequestParam String itemName,
                         @RequestParam Integer price,
                         @RequestParam Integer quantity,
                         Model model) {

        Item item = itemRepository.save(new Item(itemName, price, quantity));
        model.addAttribute("item", item);
        return "basic/item";
    }

    //ModelAttribute
//    @PostMapping("/add")
    public String addV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);
        model.addAttribute(item);
        return "basic/item";
    }

    //ModelAttribute: 이름을 지정하면, model.addAttribute("이름", 객체) 기능을 포함한다.
//    @PostMapping("/add")
    public String addV3(@ModelAttribute("item") Item item) { //, Model model) {
        itemRepository.save(item);
//        model.addAttribute(item);
        return "basic/item";
    }

    //ModelAttribute: 이름을 생략하면 Item -> item으로 model.addAttribute 한다.
//    @PostMapping("/add")
    public String addV4(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //ModelAttribute: 생략 가능하다
//    @PostMapping("/add")
    public String addV5(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

    //redirect: 새로고침 시 POST 재요청 문제 수정 -> PRG(PostRedirectGet)
//    @PostMapping("/add")
    public String addV6(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    //RedirectAttributes: url encoding, query parameter
    @PostMapping("/add")
    public String addV7(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId()); //치환
        redirectAttributes.addAttribute("status", true); //query parameter
        return "redirect:/basic/items/{itemId}"; // /basic/items/{itemId}?status=true
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    //add test data
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("item1", 10000, 10000));
        itemRepository.save(new Item("item2", 20000, 1000));
        itemRepository.save(new Item("item3", 30000, 100));
    }
}

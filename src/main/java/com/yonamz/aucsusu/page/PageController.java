package page;

import com.yonamz.aucsusu.item.Item;
import com.yonamz.aucsusu.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController {
    @Autowired
    ItemRepository itemRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/page")
    public Page<PagingDTO> paging(@PageableDefault(size = 5, sort = "createdTime") Pageable pageRequest){
        Page<Item> itemList = itemRepository.findAll(pageRequest);

        Page<PagingDTO> pagingList = itemList.map(
                item -> new PagingDTO(
                        item.getItem_no(), item.getTitle(),
                        item.getWriter(),item.getReg_date()
                ));
        return pagingList;
    }
}

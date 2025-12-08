package com.example.demo.manufacturer.service;

import com.example.demo.manufacturer.db.models.Country;
import com.example.demo.manufacturer.db.models.Item;
import com.example.demo.manufacturer.db.repositories.CountryRepo;
import com.example.demo.manufacturer.db.repositories.ItemRepo;
import com.example.demo.manufacturer.dto.ItemDetailDto;
import com.example.demo.manufacturer.dto.ItemDto;
import com.example.demo.manufacturer.dto.ItemResDto;
import com.example.demo.manufacturer.mappers.ItemMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepo itemRepo;
    private final CountryRepo countryRepo;
    private final ItemMapper itemMapper;

    @Transactional
    public List<ItemResDto> getAllItems() {
        return itemMapper.toListDto(itemRepo.findAll());
    }

    @Transactional
    public ItemDetailDto getItemById(Integer id) {
        return itemMapper.toDetailDto(itemRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Item with id " + id + " not found!")));
    }

    @Transactional
    public ItemDetailDto create(ItemDto itemDto) {
        Item  item = itemMapper.toEntity(itemDto);

        itemRepo.save(item);
        return itemMapper.toDetailDto(item);
    }

    @Transactional
    public ItemDetailDto assignItemToCountry(Integer countryId, Integer itemId) {
        Country country = countryRepo.findById(countryId).orElseThrow(() -> new NoSuchElementException("Country with id " + countryId + " not found!"));
        Item item = itemRepo.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item with id " + itemId + " not found!"));

        item.setManufacturer(country);

        itemRepo.save(item);
        return itemMapper.toDetailDto(item);
    }

    @Transactional
    public ItemDetailDto updateItemById(Integer id, ItemDto itemDto) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Item with id " + id + " not found!"));

        itemMapper.updateItem(itemDto, item);
        itemRepo.save(item);

        return itemMapper.toDetailDto(item);
    }

    @Transactional
    public void deleteItemById(Integer id) {
        itemRepo.deleteById(id);
    }
}
